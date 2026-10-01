package com.credito.cartera.bdd.models;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SolicitudCredito {
    private String numeroOperacion;
    private String canal;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombreSolicitante;
    private String apellidoSolicitante;
    private BigDecimal montoSolicitado;
    private Integer plazoMeses;
    private String estado;
    private LocalDate fechaSolicitud;
    private String motivoRechazo;
}