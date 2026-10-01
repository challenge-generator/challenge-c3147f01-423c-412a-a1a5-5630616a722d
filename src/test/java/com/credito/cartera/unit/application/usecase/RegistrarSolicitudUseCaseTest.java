package com.credito.cartera.unit.application.usecase;

import com.credito.cartera.application.usecase.RegistrarSolicitudUseCase;
import com.credito.cartera.domain.service.ValidacionService;
import com.credito.cartera.infrastructure.repository.SolicitudCreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias - RegistrarSolicitudUseCase")
class RegistrarSolicitudUseCaseTest {

    @Mock
    private SolicitudCreditoRepository solicitudRepository;

    @Mock
    private ValidacionService validacionService;

    private RegistrarSolicitudUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new RegistrarSolicitudUseCase(solicitudRepository, validacionService);
    }

    @Test
    @DisplayName("Debe registrar solicitud exitosamente cuando es valida y no existe duplicado")
    void debeRegistrarSolicitudExitosamente() {
        throw new UnsupportedOperationException("Implementar test: registrar solicitud exitosa");
    }

    @Test
    @DisplayName("Debe retornar solicitud existente cuando la clave de idempotencia ya existe")
    void debeRetornarSolicitudExistentePorIdempotencia() {
        throw new UnsupportedOperationException("Implementar test: idempotencia");
    }

    @Test
    @DisplayName("Debe validar solicitud antes de registrar")
    void debeValidarSolicitudAntesDeRegistrar() {
        throw new UnsupportedOperationException("Implementar test: validacion");
    }

    @Test
    @DisplayName("Debe rechazar solicitud cuando la validacion falla")
    void debeRechazarSolicitudValidacionFallida() {
        throw new UnsupportedOperationException("Implementar test: rechazo por validacion");
    }

    @Test
    @DisplayName("Debe generar clave de idempotencia basada en operacion y canal")
    void debeGenerarClaveIdempotencia() {
        throw new UnsupportedOperationException("Implementar test: clave idempotencia");
    }

    @Test
    @DisplayName("Debe aprobar solicitud cuando pasa todas las validaciones")
    void debeAprobarSolicitudCuandoPasaValidaciones() {
        throw new UnsupportedOperationException("Implementar test: aprobacion");
    }
}