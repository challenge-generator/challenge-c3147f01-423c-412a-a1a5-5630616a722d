package com.credito.cartera.bdd.tasks;

import com.credito.cartera.domain.model.SolicitudCredito;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;

import java.math.BigDecimal;

public class RegistrarSolicitud implements Task {

    private final String numeroOperacion;
    private final String canal;
    private final String tipoDocumento;
    private final String numeroDocumento;
    private final String nombreSolicitante;
    private final String apellidoSolicitante;
    private final BigDecimal montoSolicitado;
    private final Integer plazoMeses;

    private RegistrarSolicitud(
            String numeroOperacion,
            String canal,
            String tipoDocumento,
            String numeroDocumento,
            String nombreSolicitante,
            String apellidoSolicitante,
            BigDecimal montoSolicitado,
            Integer plazoMeses) {
        this.numeroOperacion = numeroOperacion;
        this.canal = canal;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreSolicitante = nombreSolicitante;
        this.apellidoSolicitante = apellidoSolicitante;
        this.montoSolicitado = montoSolicitado;
        this.plazoMeses = plazoMeses;
    }

    public static RegistrarSolicitud conDatos(
            String numeroOperacion,
            String canal,
            String tipoDocumento,
            String numeroDocumento,
            String nombreSolicitante,
            String apellidoSolicitante,
            BigDecimal montoSolicitado,
            Integer plazoMeses) {
        return Tasks.instrumented(
            RegistrarSolicitud.class,
            numeroOperacion,
            canal,
            tipoDocumento,
            numeroDocumento,
            nombreSolicitante,
            apellidoSolicitante,
            montoSolicitado,
            plazoMeses
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SolicitudCredito solicitud = new SolicitudCredito();
        solicitud.setNumeroOperacion(numeroOperacion);
        solicitud.setCanal(canal);
        solicitud.setTipoDocumento(tipoDocumento);
        solicitud.setNumeroDocumento(numeroDocumento);
        solicitud.setNombreSolicitante(nombreSolicitante);
        solicitud.setApellidoSolicitante(apellidoSolicitante);
        solicitud.setMontoSolicitado(montoSolicitado);
        solicitud.setPlazoMeses(plazoMeses);

        actor.attemptsTo(
            net.serenitybdd.screenplay.Tasks.instrumented(
                RegistrarSolicitud.class,
                solicitud
            )
        );
    }
}