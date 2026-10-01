# language: es

Característica: Gestión de Solicitudes de Crédito
  Como originador de créditos
  Quiero procesar solicitudes de crédito de manera eficiente
  Para aprobar o rechazar solicitudes en función de la validación de datos,
  historial crediticio y detección de fraude

  # Casos felices -happy path-
  Escenario: Aprobación de solicitud de crédito con datos válidos
    Dado que el solicitante envía una solicitud con datos completos
    Y el monto solicitado es menor al máximo permitido
    Y el plazo solicitado está dentro del rango válido
    Cuando el sistema procesa la solicitud
    Y el motor antifraude no detecta fraude
    Y el buró de riesgos indica bajo riesgo
    Entonces la solicitud debe ser aprobada
    Y el estado debe ser "APROBADA"

  Escenario: Solicitud con monto exactamente en el límite
    Dado que el solicitante envía una solicitud con datos completos
    Y el monto solicitado es exactamente 500000
    Y el plazo solicitado es 60 meses
    Cuando el sistema procesa la solicitud
    Y el motor antifraude no detecta fraude
    Y el buró de riesgos indica bajo riesgo
    Entonces la solicitud debe ser aprobada
    Y el estado debe ser "APROBADA"

  # Validaciones de datos básicos
  Escenario: Rechazo por monto excede máximo
    Dado que el solicitante envía una solicitud con datos completos
    Pero el monto solicitado excede 500000
    Cuando el sistema procesa la solicitud
    Entonces la solicitud debe ser rechazada
    Y el motivo debe indicar "Monto excede el máximo permitido"

  Escenario: Rechazo por plazo excede máximo
    Dado que el solicitante envía una solicitud con datos completos
    Pero el plazo solicitado excede 60 meses
    Cuando el sistema procesa la solicitud
    Entonces la solicitud debe ser rechazada
    Y el motivo debe indicar "Plazo excede el máximo permitido"

  Escenario: Rechazo por plazo menor al mínimo
    Dado que el solicitante envía una solicitud con datos completos
    Pero el plazo solicitado es menor a 6 meses
    Cuando el sistema procesa la solicitud
    Entonces la solicitud debe ser rechazada
    Y el motivo debe indicar "Plazo menor al mínimo permitido"

  Escenario: Rechazo por documento nulo
    Dado que el solicitante envía una solicitud
    Pero el número de documento está vacío
    Cuando el sistema procesa la solicitud
    Entonces la solicitud debe ser rechazada
    Y el motivo debe indicar "Documento requerido"

  Escenario: Rechazo por monto cero
    Dado que el solicitante envía una solicitud
    Pero el monto solicitado es cero
    Cuando el sistema procesa la solicitud
    Entonces la solicitud debe ser rechazada
    Y el motivo debe indicar "Monto debe ser mayor a cero"

  # Validación de antifraude
  Escenario: Rechazo por fraude detectado
    Dado que el solicitante envía una solicitud con datos válidos
    Cuando el sistema procesa la solicitud
    Y el motor antifraude detecta fraude
    Entonces la solicitud debe ser rechazada
    Y el motivo debe indicar "Fraude detectado"

  # Validación de buró de riesgos
  Escenario: Rechazo por alto riesgo crediticio
    Dado que el solicitante envía una solicitud con datos válidos
    Y el motor antifraude no detecta fraude
    Cuando el sistema procesa la solicitud
    Y el buró de riesgos indica alto riesgo
    Entonces la solicitud debe ser rechazada
    Y el motivo debe indicar "Alto riesgo crediticio"

  # Idempotencia
  Escenario: Solicitud duplicada retorna misma respuesta
    Dado que el solicitante envía una solicitud
    Cuando el sistema procesa la misma solicitud dos veces
    Entonces debe retornar la misma respuesta
    Y solo debe crear un registro en el sistema

  Escenario: Solicitud duplicada después de 24 horas crea nuevo registro
    Dado que el solicitante envía una solicitud
    Y la solicitud fue procesada hace más de 24 horas
    Cuando el sistema procesa la misma solicitud
    Entonces debe crear un nuevo registro
    Y debe generar una nueva clave de idempotencia

  # Edge cases
  Esquema del escenario: Múltiples solicitudes con diferentes montos y plazos
    Dado que el solicitante envía una solicitud con datos válidos
    Y el monto solicitado es <monto>
    Y el plazo solicitado es <plazo> meses
    Cuando el sistema procesa la solicitud
    Y el motor antifraude no detecta fraude
    Y el buró de riesgos indica bajo riesgo
    Entonces el resultado debe ser <resultado>

    Ejemplos:
      | monto    | plazo | resultado   |
      | 10000    | 6     | APROBADA    |
      | 100000   | 12    | APROBADA    |
      | 250000   | 36    | APROBADA    |
      | 500000   | 60    | APROBADA    |
      | 500001   | 60    | RECHAZADA    |
      | 500000   | 61    | RECHAZADA    |
      | 0        | 12    | RECHAZADA    |
      | 10000    | 5     | RECHAZADA    |

  Escenario: Error de conexión con motor antifraude
    Dado que el solicitante envía una solicitud con datos válidos
    Cuando el sistema procesa la solicitud
    Y el motor antifraude no responde
    Entonces debe lanzar error de conexión
    Y no debe aprobar la solicitud

  Escenario: Error de conexión con buró de riesgos
    Dado que el solicitante envía una solicitud con datos válidos
    Y el motor antifraude no detecta fraude
    Cuando el sistema procesa la solicitud
    Y el buró de riesgos no responde
    Entonces debe lanzar error de conexión
    Y no debe aprobar la solicitud