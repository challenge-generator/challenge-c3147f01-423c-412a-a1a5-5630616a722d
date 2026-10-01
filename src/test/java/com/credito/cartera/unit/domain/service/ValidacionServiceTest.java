package com.credito.cartera.unit.domain.service;

import com.credito.cartera.domain.model.SolicitudCredito;
import com.credito.cartera.domain.model.SolicitudCredito.EstadoSolicitud;
import com.credito.cartera.domain.service.ValidacionService;
import com.credito.cartera.infrastructure.port.MotorAntifraudePort;
import com.credito.cartera.infrastructure.port.BuroRiesgosPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidacionServiceTest {

    @Mock
    private MotorAntifraudePort motorAntifraude;

    @Mock
    private BuroRiesgosPort buroRiesgos;

    private ValidacionService validacionService;
    private SolicitudCredito solicitudValida;

    @BeforeEach
    void setUp() {
        validacionService = new ValidacionService(motorAntifraude, buroRiesgos);
        
        solicitudValida = new SolicitudCredito();
        solicitudValida.setNumeroOperacion("OP-2024-001");
        solicitudValida.setCanal("DIGITAL");
        solicitudValida.setTipoDocumento("DNI");
        solicitudValida.setNumeroDocumento("12345678");
        solicitudValida.setNombreSolicitante("Juan");
        solicitudValida.setApellidoSolicitante("Pérez");
        solicitudValida.setMontoSolicitado(new BigDecimal("50000.00"));
        solicitudValida.setPlazoMeses(24);
        solicitudValida.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudValida.setFechaSolicitud(LocalDate.now());
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeValidarSolicitudExitosamente() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeLlamarMotorAntifraude() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeLlamarBuroRiesgos() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoAntifraudeDetectaFraude() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoBuroRiesgosIndicaAltoRiesgo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoMontoExcedeMaximo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoPlazoExcedeMaximo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoPlazoEsMenorAMinimo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoDocumentoEsNulo() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoMontoEsCero() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeRechazarCuandoNombreEsVacio() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeValidarDatosBasicosPrimero() {
        fail("Pendiente de implementar");
    }

    @Test
    @Disabled("Implementar en fase 2")
    void debeContinuarValidacionSiDatosBasicosSonValidos() {
        fail("Pendiente de implementar");
    }
}