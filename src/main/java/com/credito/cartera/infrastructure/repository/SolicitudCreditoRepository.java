package com.credito.cartera.infrastructure.repository;


import com.credito.cartera.domain.model.EstadoSolicitud;
import com.credito.cartera.domain.model.SolicitudCredito;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de solicitudes de crédito.
 * Implementa el patrón de idempotencia basado en número de operación y canal.
 */
public interface SolicitudCreditoRepository {

    /**
     * Guarda una solicitud de crédito aplicando lógica de idempotencia.
     * Si ya existe una solicitud con el mismo número de operación y canal,
     * retorna la existente en lugar de crear una nueva.
     *
     * @param solicitud La solicitud a persistir
     * @return La solicitud persistida (existente o nueva)
     */
    SolicitudCredito save(SolicitudCredito solicitud);

    /**
     * Busca una solicitud por su identificador único.
     *
     * @param id El identificador de la solicitud
     * @return Optional conteniendo la solicitud si existe
     */
    Optional<SolicitudCredito> findById(String id);

    /**
     * Busca una solicitud por su clave de idempotencia (número de operación + canal).
     * Esta consulta es la base para garantizar la idempotencia del registro.
     *
     * @param numeroOperacion El número de operación único
     * @param canal El canal por el cual se recibió la solicitud
     * @return Optional conteniendo la solicitud si existe
     */
    Optional<SolicitudCredito> findByNumeroOperacionAndCanal(String numeroOperacion, String canal);

    /**
     * Lista todas las solicitudes de un solicitante específico.
     *
     * @param tipoDocumento Tipo de documento del solicitante
     * @param numeroDocumento Número de documento del solicitante
     * @return Lista de solicitudes encontradas
     */
    List<SolicitudCredito> findBySolicitante(String tipoDocumento, String numeroDocumento);

    /**
     * Lista todas las solicitudes en un estado específico.
     * Útil para procesos de auditoría y batch.
     *
     * @param estado El estado de las solicitudes a buscar
     * @return Lista de solicitudes en el estado especificado
     */
    List<SolicitudCredito> findByEstado(SolicitudCredito.EstadoSolicitud estado);

    /**
     * Actualiza el estado de una solicitud existente.
     *
     * @param id Identificador de la solicitud
     * @param nuevoEstado El nuevo estado a asignar
     * @param motivo Motivo del cambio de estado (opcional, requerido para rechazos)
     * @return La solicitud actualizada
     */
    SolicitudCredito actualizarEstado(String id, SolicitudCredito.EstadoSolicitud nuevoEstado, String motivo);

    /**
     * Verifica si existe una solicitud con la misma clave de idempotencia
     * dentro de la ventana de tiempo especificada (por defecto 24 horas).
     *
     * @param numeroOperacion Número de operación
     * @param canal Canal de la solicitud
     * @return true si existe una solicitud idempotente válida
     */
    boolean existeSolicitudIdempotente(String numeroOperacion, String canal);
}