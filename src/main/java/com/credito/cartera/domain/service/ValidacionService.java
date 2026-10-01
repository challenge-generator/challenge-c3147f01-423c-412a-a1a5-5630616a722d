package com.credito.cartera.domain.service;

import com.credito.cartera.domain.model.SolicitudCredito;
import com.credito.cartera.domain.exception.FraudeDetectadoException;
import com.credito.cartera.domain.exception.SolicitudInvalidaException;
import com.credito.cartera.domain.port.BuroRiesgosPort;
import com.credito.cartera.domain.port.MotorAntifraudePort;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class ValidacionService {
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("1000000");
    private static final int PLAZO_MAXIMO = 84;
    private static final int PLAZO_MINIMO = 6;

    private final MotorAntifraudePort motorAntifraude;
    private final BuroRiesgosPort buroRiesgos;

    public void validarSolicitud(SolicitudCredito solicitud) {
        validarDatosBasicos(solicitud);
        validarAntifraude(solicitud);
        validarBuroRiesgos(solicitud);
    }

    private void validarDatosBasicos(SolicitudCredito solicitud) {
        if (solicitud.getMontoSolicitado() == null || solicitud.getMontoSolicitado().compareTo(BigDecimal.ZERO) <= 0) {
            throw new SolicitudInvalidaException("El monto solicitado debe ser mayor a cero");
        }
        if (solicitud.getMontoSolicitado().compareTo(MONTO_MAXIMO) > 0) {
            throw new SolicitudInvalidaException("El monto solicitado excede el límite máximo permitido");
        }
        if (solicitud.getPlazoMeses() == null || solicitud.getPlazoMeses() < PLAZO_MINIMO || solicitud.getPlazoMeses() > PLAZO_MAXIMO) {
            throw new SolicitudInvalidaException("El plazo debe estar entre " + PLAZO_MINIMO + " y " + PLAZO_MAXIMO + " meses");
        }
        if (solicitud.getTipoDocumento() == null || solicitud.getNumeroDocumento() == null || 
            solicitud.getNombreSolicitante() == null || solicitud.getApellidoSolicitante() == null) {
            throw new SolicitudInvalidaException("Datos del solicitante incompletos");
        }
    }

    private void validarAntifraude(SolicitudCredito solicitud) {
        boolean esFraude = motorAntifraude.verificarRiesgoFraude(
            solicitud.getTipoDocumento(), 
            solicitud.getNumeroDocumento(), 
            solicitud.getMontoSolicitado()
        );
        if (esFraude) {
            throw new FraudeDetectadoException("Solicitud marcada como posible fraude");
        }
    }

    private void validarBuroRiesgos(SolicitudCredito solicitud) {
        String riesgo = buroRiesgos.consultarRiesgo(
            solicitud.getTipoDocumento(), 
            solicitud.getNumeroDocumento()
        );
        if ("ALTO".equalsIgnoreCase(riesgo)) {
            throw new SolicitudInvalidaException("Solicitud rechazada por alto riesgo en buró");
        }
    }
}