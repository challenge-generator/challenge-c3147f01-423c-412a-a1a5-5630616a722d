# Language: es
Feature: Validación Antifraude del Sistema de Gestión de Cartera de Crédito

  Como originador de créditos
  Necesito que el sistema valide las solicitudes contra el motor antifraude y el buró de riesgos
  Para aprobar o rechazar solicitudes de crédito de manera segura y prevenir fraudes

  Background:
    Given que el sistema está disponible
    And el motor antifraude está operativo
    And el buró de riesgos está operativo

  @smoke @antifraude
  Scenario: Solicitud de crédito pasa validación antifraude exitosamente
    Given una solicitud de crédito con datos válidos
    When el sistema procesa la solicitud
    Then el motor antifraude debe ser consultado
    And el resultado de la consulta debe ser "SIN_ALERTA"
    And la solicitud debe ser aprobada

  @smoke @antifraude
  Scenario: Solicitud de crédito es rechazada por alerta de fraude
    Given una solicitud de crédito con datos válidos
    When el sistema procesa la solicitud
    And el motor antifraude devuelve "ALERTA_FRAUDE"
    Then la solicitud debe ser rechazada
    And el motivo de rechazo debe ser "FRAUDE_DETECTADO"

  @regresion @antifraude
  Scenario: Solicitud de crédito es rechazada por riesgo alto en buró
    Given una solicitud de crédito con datos válidos
    When el sistema procesa la solicitud
    And el motor antifraude devuelve "SIN_ALERTA"
    And el buró de riesgos devuelve "RIESGO_ALTO"
    Then la solicitud debe ser rechazada
    And el motivo de rechazo debe ser "RIESGO_BURO"

  @regresion @antifraude
  Scenario: Solicitud de crédito es aprobada con riesgo medio en buró
    Given una solicitud de crédito con datos válidos
    When el sistema procesa la solicitud
    And el motor antifraude devuelve "SIN_ALERTA"
    And el buró de riesgos devuelve "RIESGO_MEDIO"
    Then la solicitud debe ser aprobada

  @regresion @antifraude
  Scenario: Fallo en comunicación con motor antifraude
    Given una solicitud de crédito con datos válidos
    And el motor antifraude está fuera de línea
    When el sistema procesa la solicitud
    Then debe抛出 una excepción de tipo "MotorAntifraudeNoDisponibleException"
    And la solicitud no debe ser procesada

  @regresion @antifraude
  Scenario: Fallo en comunicación con buró de riesgos
    Given una solicitud de crédito con datos válidos
    And el buró de riesgos está fuera de línea
    When el sistema procesa la solicitud
    Then debe lanzar una excepción de tipo "BuroRiesgosNoDisponibleException"
    And la solicitud no debe ser procesada

  @smoke @antifraude
  Scenario Outline: Validación antifraude con diferentes montos
    Given una solicitud de crédito con monto "<monto>" y plazo "<plazo>" meses
    When el sistema procesa la solicitud
    Then el resultado debe ser "<resultado>"

    Examples:
      | monto     | plazo | resultado     |
      | 5000      | 12    | APROBADO      |
      | 50000     | 12    | REVISAR       |
      | 100000    | 24    | REVISAR       |
      | 5000      | 60    | REVISAR       |

  @integration @antifraude
  Scenario: Flujo completo de validación con ambos servicios
    Given una solicitud de crédito con los siguientes datos:
      | campo              | valor                    |
      | numeroOperacion   | OP-2024-00001            |
      | canal             | WEB                      |
      | tipoDocumento     | CEDULA                  |
      | numeroDocumento   | 1234567890              |
      | nombreSolicitante | Juan                    |
      | apellidoSolicitante | Pérez                  |
      | montoSolicitado   | 25000                   |
      | plazoMeses        | 24                      |
    When el sistema procesa la solicitud
    And el motor antifraude responde con código "SIN_ALERTA"
    And el buró de riesgos responde con código "RIESGO_BAJO"
    Then la solicitud debe ser aprobada
    And el estado final debe ser "APROBADA"
    And debe generarse una clave de idempotencia