# Language: es
Feature: Pruebas de Rendimiento del Sistema de Gestión de Cartera de Crédito

  Como ingeniero de rendimiento
  Necesito que el sistema cumpla con los umbrales de rendimiento establecidos
  Para garantizar una experiencia óptima bajo carga de producción

  @performance @carga
  Scenario: El sistema procesa 1500 solicitudes por segundo
    Given que el sistema está desplegado en el entorno de pruebas
    When se envían 1500 solicitudes de crédito por segundo durante 60 segundos
    Then el sistema debe procesar todas las solicitudes
    And el tiempo de respuesta promedio debe ser menor a 500ms
    And la tasa de errores debe ser menor al 1%

  @performance @carga
  Scenario: El sistema mantiene latencia bajo umbral en hora pico
    Given una carga base de 500 solicitudes por segundo
    When se incrementa la carga a 1500 solicitudes por segundo
    Then la latencia p99 debe ser menor a 500ms
    And la latencia p95 debe ser menor a 300ms
    And la latencia p50 debe ser menor a 100ms

  @performance @latencia
  Scenario: Latencia individual por solicitud
    Given una solicitud de crédito válida
    When el sistema procesa la solicitud
    Then el tiempo total de procesamiento debe ser menor a 500ms
    And el tiempo de consulta al motor antifraude debe ser menor a 100ms
    And el tiempo de consulta al buró de riesgos debe ser menor a 100ms
    And el tiempo de validación de datos debe ser menor a 50ms

  @performance @estres
  Scenario: El sistema soporta carga sostenida por 30 minutos
    Given que el sistema está en estado estable con 800 solicitudes por segundo
    When se mantiene esta carga durante 30 minutos
    Then no debe haber degradación de rendimiento
    And la memoria utilizada debe ser menor al 80%
    And el CPU utilizado debe ser menor al 85%
    And no debe haber errores de conexión a la base de datos

  @performance @estres
  Scenario: El sistema se recupera después de pico de carga
    Given una carga normal de 500 solicitudes por segundo
    When se produce un pico de 3000 solicitudes por segundo
    And el pico dura 30 segundos
    And la carga vuelve a la normalidad
    Then el sistema debe recuperarse en menos de 60 segundos
    And el tiempo de respuesta debe volver a valores normales

  @performance @concurrencia
  Scenario: Múltiples solicitudes concurrentes con mismos datos
    Given una solicitud de crédito con número de operación "OP-2024-CONC-001"
    When 10 solicitudes concurrentes con el mismo número de operación se envían
    Then solo una debe ser procesada exitosamente
    And las otras 9 deben recibir la respuesta de la solicitud original
    And el tiempo total debe ser menor a 1000ms

  @performance @concurrencia
  Scenario: Múltiples solicitudes concurrentes con datos diferentes
    Given 100 solicitudes de crédito con datos diferentes
    When se envían todas simultáneamente
    Then todas deben ser procesadas
    And el tiempo total debe ser menor a 5 segundos
    And cada solicitud debe tener su propio identificador único

  @performance @escalabilidad
  Scenario: El sistema escala horizontalmente bajo carga
    Given que el sistema tiene 2 instancias activas
    When la carga aumenta a 2000 solicitudes por segundo
    Then las solicitudes deben distribuirse entre las instancias
    And ninguna instancia debe superar el 70% de utilización

  @smoke @performance
  Scenario: Verificación de salud del sistema bajo carga
    Given que el sistema está bajo carga de 1000 solicitudes por segundo
    When se solicita el estado de salud del sistema
    Then la respuesta debe ser "HEALTHY"
    And el tiempo de respuesta debe ser menor a 50ms
    And todos los servicios dependientes deben estar disponibles