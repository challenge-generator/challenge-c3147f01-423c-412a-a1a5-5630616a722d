package com.credito.cartera.application.usecase;

import com.credito.cartera.domain.model.SolicitudCredito;
import com.credito.cartera.domain.service.ValidacionService;
import com.credito.cartera.infrastructure.repository.SolicitudCreditoRepository;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Caso de uso para el registro de solicitudes de crédito.
 * Orkuesta la validación de la solicitud y su persistencia con soporte de idempotencia.
 */
public class RegistrarSolicitudUseCase {

    private final ValidacionService validacionService;
    private final SolicitudCreditoRepository repository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param validacionService Servicio de validación del dominio
     * @param repository Repositorio para persistencia de solicitudes
     */
    public RegistrarSolicitudUseCase(ValidacionService validacionService, 
                                      SolicitudCreditoRepository repository) {
        this.validacionService = validacionService;
        this.repository = repository;
    }

    /**
     * Ejecuta el caso de uso de registro de solicitud de crédito.
     * Aplica validación de idempotencia antes de procesar la solicitud.
     *
     * @param solicitud La solicitud a registrar
     * @return La solicitud procesada (nueva o existente por idempotencia)
     * @throws SolicitudInvalidaException Si la solicitud no pasa las validaciones
     * @throws SolicitudDuplicadaException Si existe una solicitud idempotente
     */
    public SolicitudCredito ejecutar(SolicitudCredito solicitud) {
        validarEntradas(solicitud);
        
        String idempotencyKey = solicitud.getIdempotencyKey();
        String numeroOperacion = solicitud.getNumeroOperacion();
        String canal = solicitud.getCanal();
        
        if (repository.existeSolicitudIdempotente(numeroOperacion, canal)) {
            SolicitudCredito existente = repository.findByNumeroOperacionAndCanal(numeroOperacion, canal)
                    .orElseThrow(() -> new SolicitudDuplicadaException(
                            "Ya existe una solicitud para la operación: " + numeroOperacion + " en canal: " + canal));
            return existente;
        }
        
        try {
            validacionService.validarSolicitud(solicitud);
        } catch (ValidacionException e) {
            solicitud.rechazar(e.getMessage());
            return repository.save(solicitud);
        }
        
        solicitud.aprobar();
        solicitud.setFechaSolicitud(LocalDate.now());
        
        if (solicitud.getId() == null || solicitud.getId().isEmpty()) {
            solicitud.setId(UUID.randomUUID().toString());
        }
        
        return repository.save(solicitud);
    }

    private void validarEntradas(SolicitudCredito solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        if (solicitud.getNumeroOperacion() == null || solicitud.getNumeroOperacion().isBlank()) {
            throw new IllegalArgumentException("El número de operación es requerido");
        }
        if (solicitud.getCanal() == null || solicitud.getCanal().isBlank()) {
            throw new IllegalArgumentException("El canal es requerido");
        }
    }

    /**
     * Excepción para solicitudes que no pasan las validaciones de negocio.
     */
    public static class ValidacionException extends RuntimeException {
        public ValidacionException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepción para solicitudes duplicadas detectadas por idempotencia.
     */
    public static class SolicitudDuplicadaException extends RuntimeException {
        public SolicitudDuplicadaException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepción para solicitudes con datos inválidos.
     */
    public static class SolicitudInvalidaException extends RuntimeException {
        public SolicitudInvalidaException(String mensaje) {
            super(mensaje);
        }
    }
}