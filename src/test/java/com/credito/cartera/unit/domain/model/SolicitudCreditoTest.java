package com.credito.cartera.unit.domain.model;

import com.credito.cartera.domain.model.SolicitudCredito;
import com.credito.cartera.domain.model.SolicitudCredito.EstadoSolicitud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SolicitudCreditoTest {

    private SolicitudCredito solicitud;

    @BeforeEach
    void setUp() {
        solicitud = new SolicitudCredito();
        solicitud.setNumeroOperacion("OP-2024-001");
        solicitud.setCanal("DIGITAL");
        solicitud.setTipoDocumento("DNI");
        solicitud.setNumeroDocumento("12345678");
        solicitud.setNombreSolicitante("Juan");
        solicitud.setApellidoSolicitante("Pérez");
        solicitud.setMontoSolicitado(new BigDecimal("50000.00"));
        solicitud.setPlazoMeses(24);
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        solicitud.setFechaSolicitud(LocalDate.now());
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeAprobarSolicitudCuandoDatosSonValidos() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarSolicitudCuandoMontoExcedeMaximo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarSolicitudCuandoPlazoExcedeMaximo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeGenerarClaveIdempotenciaUnica() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeMantenerMismaClaveIdempotenciaParaMismaOperacion() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeCambiarEstadoAProbado() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeCambiarEstadoARechazado() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRegistrarMotivoDeRechazo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeValidarNumeroDocumentoNoNulo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeValidarMontoMayorACero() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeValidarPlazoMayorACero() {
        fail("Pendiente de implementar");
    }
}