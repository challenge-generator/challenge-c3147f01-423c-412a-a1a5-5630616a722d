package com.credito.cartera.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCredito {
    private String numeroOperacion;
    private String canal;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombreSolicitante;
    private String apellidoSolicitante;
    private BigDecimal montoSolicitado;
    private Integer plazoMeses;
    private EstadoSolicitud estado;
    private LocalDate fechaSolicitud;
    private String motivoRechazo;

    public enum EstadoSolicitud {
        PENDIENTE, APROBADA, RECHAZADA
    }

    public void aprobar() {
        this.estado = EstadoSolicitud.APROBADA;
        this.motivoRechazo = null;
    }

    public void rechazar(String motivo) {
        this.estado = EstadoSolicitud.RECHAZADA;
        this.motivoRechazo = motivo;
    }

    public String getIdempotencyKey() {
        return numeroOperacion + "|" + canal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudCredito that = (SolicitudCredito) o;
        return Objects.equals(numeroOperacion, that.numeroOperacion) && 
               Objects.equals(canal, that.canal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroOperacion, canal);
    }
}