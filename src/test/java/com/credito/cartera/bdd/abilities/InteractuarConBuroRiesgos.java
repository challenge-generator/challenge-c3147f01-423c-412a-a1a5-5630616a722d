package com.credito.cartera.bdd.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.Step;

public class InteractuarConBuroRiesgos implements Ability {

    private final String endpointBuro;
    private final String apiKey;

    public InteractuarConBuroRiesgos(String endpointBuro, String apiKey) {
        this.endpointBuro = endpointBuro;
        this.apiKey = apiKey;
    }

    public static InteractuarConBuroRiesgos conCredenciales(String endpoint, String apiKey) {
        return new InteractuarConBuroRiesgos(endpoint, apiKey);
    }

    public static InteractuarConBuroRiesgos comoActor(Actor actor) {
        return actor.abilityTo(InteractuarConBuroRiesgos.class);
    }

    @Step("Consultar bureau de riesgos para documento {0}")
    public RespuestaBuro consultarPorDocumento(String tipoDocumento, String numeroDocumento) {
        throw new UnsupportedOperationException("Implementar consulta al bureau de riesgos");
    }

    @Step("Obtener score crediticio para documento {0}")
    public Integer obtenerScoreCrediticio(String tipoDocumento, String numeroDocumento) {
        throw new UnsupportedOperationException("Implementar obtencion de score");
    }

    @Step("Verificar historial de morosidad para documento {0}")
    public boolean tieneHistorialMorosidad(String tipoDocumento, String numeroDocumento) {
        throw new UnsupportedOperationException("Implementar verificacion de morosidad");
    }

    public String getEndpoint() {
        return endpointBuro;
    }

    public String getApiKey() {
        return apiKey;
    }

    public static class RespuestaBuro {
        private String codigoRespuesta;
        private String mensajeRespuesta;
        private Integer score;
        private boolean tieneDeudasVencidas;
        private Integer cantidadConsultasUltimos30Dias;

        public String getCodigoRespuesta() {
            return codigoRespuesta;
        }

        public void setCodigoRespuesta(String codigoRespuesta) {
            this.codigoRespuesta = codigoRespuesta;
        }

        public String getMensajeRespuesta() {
            return mensajeRespuesta;
        }

        public void setMensajeRespuesta(String mensajeRespuesta) {
            this.mensajeRespuesta = mensajeRespuesta;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public boolean isTieneDeudasVencidas() {
            return tieneDeudasVencidas;
        }

        public void setTieneDeudasVencidas(boolean tieneDeudasVencidas) {
            this.tieneDeudasVencidas = tieneDeudasVencidas;
        }

        public Integer getCantidadConsultasUltimos30Dias() {
            return cantidadConsultasUltimos30Dias;
        }

        public void setCantidadConsultasUltimos30Dias(Integer cantidadConsultasUltimos30Dias) {
            this.cantidadConsultasUltimos30Dias = cantidadConsultasUltimos30Dias;
        }
    }
}