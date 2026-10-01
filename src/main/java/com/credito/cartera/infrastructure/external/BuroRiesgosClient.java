package com.credito.cartera.infrastructure.external;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Adaptador para la comunicación con el servicio externo de Buró de Riesgos.
 * Implementa el patrón de cliente HTTP con manejo de errores y timeouts configurables.
 */
public class BuroRiesgosClient {

    private static final String BASE_URL = System.getProperty("buro.riesgos.url", "https://api.buroriesgos.example.com");
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    private static final int MAX_REINTENTOS = 3;
    
    private final String apiKey;
    private final RequestSpecification defaultRequest;

    /**
     * Constructor que inicializa el cliente con la configuración del entorno.
     *
     * @param apiKey Clave de API para autenticación con el buró de riesgos
     */
    public BuroRiesgosClient(String apiKey) {
        this.apiKey = apiKey;
        this.defaultRequest = buildDefaultRequest();
    }

    private RequestSpecification buildDefaultRequest() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + apiKey)
                .header("X-Correlation-ID", generateCorrelationId())
                .header("X-Client-Version", "1.0.0")
                .timeout(TIMEOUT.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    /**
     * Consulta el historial crediticio de un solicitante en el buró de riesgos.
     *
     * @param tipoDocumento Tipo de documento de identificación
     * @param numeroDocumento Número de documento
     * @return Respuesta del buró con el historial crediticio
     */
    public BuroRiesgosResponse consultarHistorial(String tipoDocumento, String numeroDocumento) {
        Map<String, Object> body = new HashMap<>();
        body.put("tipoDocumento", tipoDocumento);
        body.put("numeroDocumento", numeroDocumento);
        body.put("tipoConsulta", "HISTORIAL_COMPLETO");
        body.put("fechaConsulta", java.time.Instant.now().toString());
        
        Response response = executeWithRetry(() -> 
            defaultRequest
                .body(body)
                .when()
                .post("/v1/consultas/historial")
        );
        
        return mapResponseToBuroRiesgosResponse(response);
    }

    /**
     * Consulta el score crediticio del solicitante.
     *
     * @param tipoDocumento Tipo de documento de identificación
     * @param numeroDocumento Número de documento
     * @return Respuesta con el score y clasificación de riesgo
     */
    public BuroRiesgosResponse consultarScore(String tipoDocumento, String numeroDocumento) {
        Map<String, Object> body = new HashMap<>();
        body.put("tipoDocumento", tipoDocumento);
        body.put("numeroDocumento", numeroDocumento);
        body.put("tipoConsulta", "SCORE");
        body.put("modeloScoring", "SCORE_V3");
        
        Response response = executeWithRetry(() -> 
            defaultRequest
                .body(body)
                .when()
                .post("/v1/consultas/score")
        );
        
        return mapResponseToBuroRiesgosResponse(response);
    }

    /**
     * Consulta múltiples productos de buró en una sola llamada.
     *
     * @param tipoDocumento Tipo de documento
     * @param numeroDocumento Número de documento
     * @return Respuesta consolidada con historial y score
     */
    public BuroRiesgosResponse consultarCompleto(String tipoDocumento, String numeroDocumento) {
        Map<String, Object> body = new HashMap<>();
        body.put("tipoDocumento", tipoDocumento);
        body.put("numeroDocumento", numeroDocumento);
        body.put("tipoConsulta", "COMPLETA");
        body.put("incluirReporte", true);
        body.put("incluirScore", true);
        body.put("incluirAlertas", true);
        
        Response response = executeWithRetry(() -> 
            defaultRequest
                .body(body)
                .when()
                .post("/v1/consultas/completa")
        );
        
        return mapResponseToBuroRiesgosResponse(response);
    }

    private Response executeWithRetry(java.util.function.Supplier<Response> requestSupplier) {
        int intentos = 0;
        Exception ultimaExcepcion = null;
        
        while (intentos < MAX_REINTENTOS) {
            try {
                return requestSupplier.get();
            } catch (Exception e) {
                ultimaExcepcion = e;
                intentos++;
                if (intentos < MAX_REINTENTOS) {
                    try {
                        Thread.sleep((long) Math.pow(2, intentos) * 100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        throw new BuroRiesgosException("Error después de " + MAX_REINTENTOS + " intentos: " + 
                (ultimaExcepcion != null ? ultimaExcepcion.getMessage() : "Unknown"));
    }

    private BuroRiesgosResponse mapResponseToBuroRiesgosResponse(Response response) {
        int statusCode = response.getStatusCode();
        
        if (statusCode == 200) {
            return new BuroRiesgosResponse(
                response.jsonPath().getBoolean("exitoso"),
                response.jsonPath().getString("codigoRespuesta"),
                response.jsonPath().getString("mensaje"),
                response.jsonPath().getInt("score"),
                response.jsonPath().getString("clasificacionRiesgo"),
                response.jsonPath().getObject("historial", BuroHistorial.class),
                response.jsonPath().getList("alertas", String.class)
            );
        } else if (statusCode == 404) {
            return new BuroRiesgosResponse(false, "NO_ENCONTRADO", 
                "No se encontró información para el documento", null, null, null, null);
        } else if (statusCode == 429) {
            throw new BuroRiesgosException("Límite de consultas alcanzado");
        } else if (statusCode >= 500) {
            throw new BuroRiesgosException("Error del servicio de buró: " + statusCode);
        } else {
            throw new BuroRiesgosException("Error en consulta de buró: " + statusCode);
        }
    }

    private String generateCorrelationId() {
        return "CRÉDITO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Excepción específica para errores de comunicación con el buró de riesgos.
     */
    public static class BuroRiesgosException extends RuntimeException {
        public BuroRiesgosException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * DTO para representar la respuesta del buró de riesgos.
     */
    public static class BuroRiesgosResponse {
        private final boolean exitoso;
        private final String codigoRespuesta;
        private final String mensaje;
        private final Integer score;
        private final String clasificacionRiesgo;
        private final BuroHistorial historial;
        private final java.util.List<String> alertas;

        public BuroRiesgosResponse(boolean exitoso, String codigoRespuesta, String mensaje,
                                   Integer score, String clasificacionRiesgo, 
                                   BuroHistorial historial, java.util.List<String> alertas) {
            this.exitoso = exitoso;
            this.codigoRespuesta = codigoRespuesta;
            this.mensaje = mensaje;
            this.score = score;
            this.clasificacionRiesgo = clasificacionRiesgo;
            this.historial = historial;
            this.alertas = alertas;
        }

        public boolean isExitoso() { return exitoso; }
        public String getCodigoRespuesta() { return codigoRespuesta; }
        public String getMensaje() { return mensaje; }
        public Integer getScore() { return score; }
        public String getClasificacionRiesgo() { return clasificacionRiesgo; }
        public BuroHistorial getHistorial() { return historial; }
        public java.util.List<String> getAlertas() { return alertas; }

        public boolean tieneRiesgoAlto() {
            return "ALTO".equalsIgnoreCase(clasificacionRiesgo) || 
                   (score != null && score < 500);
        }

        public boolean tieneAlertas() {
            return alertas != null && !alertas.isEmpty();
        }
    }

    /**
     * DTO para el historial crediticio del buró.
     */
    public static class BuroHistorial {
        private Integer cantidadCreditosActivos;
        private Integer cantidadCreditosVencidos;
        private BigDecimal montoTotalDeuda;
        private Integer cantidadConsultasRecientes;

        public Integer getCantidadCreditosActivos() { return cantidadCreditosActivos; }
        public Integer getCantidadCreditosVencidos() { return cantidadCreditosVencidos; }
        public BigDecimal getMontoTotalDeuda() { return montoTotalDeuda; }
        public Integer getCantidadConsultasRecientes() { return cantidadConsultasRecientes; }

        public void setCantidadCreditosActivos(Integer cantidadCreditosActivos) { 
            this.cantidadCreditosActivos = cantidadCreditosActivos; 
        }
        public void setCantidadCreditosVencidos(Integer cantidadCreditosVencidos) { 
            this.cantidadCreditosVencidos = cantidadCreditosVencidos; 
        }
        public void setMontoTotalDeuda(BigDecimal montoTotalDeuda) { 
            this.montoTotalDeuda = montoTotalDeuda; 
        }
        public void setCantidadConsultasRecientes(Integer cantidadConsultasRecientes) { 
            this.cantidadConsultasRecientes = cantidadConsultasRecientes; 
        }
    }
}