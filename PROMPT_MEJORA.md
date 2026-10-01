# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/resources/features/solicitud_credito.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/features/validacion_antifraude.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/features/performance.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/bdd/runners/RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/bdd/steps/SolicitudCreditoSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/bdd/questions/EstadoSolicitud.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/bdd/abilities/InteractuarConBuroRiesgos.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/bdd/models/SolicitudCredito.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/credito/cartera/unit/application/usecase/RegistrarSolicitudUseCaseTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.exception.FraudeDetectadoException`: El import com.credito.cartera.domain.exception.FraudeDetectadoException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.exception.SolicitudInvalidaException`: El import com.credito.cartera.domain.exception.SolicitudInvalidaException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.port.BuroRiesgosPort`: El import com.credito.cartera.domain.port.BuroRiesgosPort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.port.MotorAntifraudePort`: El import com.credito.cartera.domain.port.MotorAntifraudePort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `com.credito.cartera.infrastructure.port.MotorAntifraudePort`: El import com.credito.cartera.infrastructure.port.MotorAntifraudePort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `com.credito.cartera.infrastructure.port.BuroRiesgosPort`: El import com.credito.cartera.infrastructure.port.BuroRiesgosPort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/credito/cartera/bdd/steps/SolicitudCreditoSteps.java` — `org.hamcrest.Matchers`: El import org.hamcrest.Matchers.equalTo pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getMontoSolicitado`: Se invoca `getMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getTipoDocumento`: Se invoca `getTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getNumeroDocumento`: Se invoca `getNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getNombreSolicitante`: Se invoca `getNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getApellidoSolicitante`: Se invoca `getApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.getNumeroOperacion`: Se invoca `getNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.getCanal`: Se invoca `getCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `ValidacionException.getMessage`: Se invoca `getMessage` sobre `ValidacionException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.setFechaSolicitud`: Se invoca `setFechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.getId`: Se invoca `getId` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.setId`: Se invoca `setId` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setNumeroOperacion`: Se invoca `setNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setCanal`: Se invoca `setCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setTipoDocumento`: Se invoca `setTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setNumeroDocumento`: Se invoca `setNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setNombreSolicitante`: Se invoca `setNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setApellidoSolicitante`: Se invoca `setApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setMontoSolicitado`: Se invoca `setMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setPlazoMeses`: Se invoca `setPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setEstado`: Se invoca `setEstado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setFechaSolicitud`: Se invoca `setFechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setNumeroOperacion`: Se invoca `setNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setCanal`: Se invoca `setCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setTipoDocumento`: Se invoca `setTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setNumeroDocumento`: Se invoca `setNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setNombreSolicitante`: Se invoca `setNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setApellidoSolicitante`: Se invoca `setApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setMontoSolicitado`: Se invoca `setMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setPlazoMeses`: Se invoca `setPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setEstado`: Se invoca `setEstado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setFechaSolicitud`: Se invoca `setFechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setNumeroOperacion`: Se invoca `setNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setCanal`: Se invoca `setCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setTipoDocumento`: Se invoca `setTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setNumeroDocumento`: Se invoca `setNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setNombreSolicitante`: Se invoca `setNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setApellidoSolicitante`: Se invoca `setApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setMontoSolicitado`: Se invoca `setMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setPlazoMeses`: Se invoca `setPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/credito/cartera/bdd/questions/EstadoSolicitud.java` — `SolicitudCredito.getEstado`: Se invoca `getEstado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.10`: net.serenity-bdd:serenity-bom declara la version 4.1.10, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Seniority Senior

### Brecha de conocimiento
Demuestra su compromiso con la práctica de marcos como TDD y BDD, lo cual ademas de fortalecer su liderazgo, se traduce en la excelencia en calidad del producto, eficiencia del desarrollo y satisfacción del cliente, siendo clave para el éxito general del equipo y del proyecto

### Misión / candidato
Especialista en QA con experiencia como automatizador senior

### Reto
- Tema: TDD y BDD
- Seniority: senior-l2
- Tipo: mixed
- Título: Implementación de TDD y BDD en un sistema de gestión de cartera de crédito
- Tiempo estimado: 15 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de comportamientos y casos de prueba — objetivo: Establecer los comportamientos esperados del sistema y los casos de prueba iniciales utilizando BDD. — entregable (NO resolver): Documento de casos de prueba en Gherkin que describe los comportamientos del sistema.
- Fase 2: Implementación de pruebas unitarias con TDD — objetivo: Implementar pruebas unitarias para los comportamientos definidos en la fase anterior utilizando TDD. — entregable (NO resolver): Código implementado con pruebas unitarias que pasan todos los casos de prueba definidos en la fase 1.
- Fase 3: Refactorización y optimización del código — objetivo: Refactorizar y optimizar el código implementado para mejorar la calidad y eficiencia del desarrollo. — entregable (NO resolver): Código refactorizado y optimizado que cumple con los umbrales de rendimiento definidos.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.credito.cartera</groupId>
    <artifactId>credito-cartera</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <serenity.version>4.1.10</serenity.version>
        <cucumber.version>7.15.0</cucumber.version>
        <junit.version>5.10.0</junit.version>
        <restassured.version>5.4.0</restassured.version>
        <selenium.version>4.16.0</selenium.version>
        <lombok.version>1.18.30</lombok.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-bom</artifactId>
                <version>${serenity.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Serenity BDD -->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-webdriver</artifactId>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>${cucumber.version}</version>
        </dependency>

        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Mockito -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.5.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.5.0</version>
            <scope>test</scope>
        </dependency>

        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <!-- REST Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${restassured.version}</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-path</artifactId>
            <version>${restassured.version}</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>xml-path</artifactId>
            <version>${restassured.version}</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/*Steps.java</include>
                        <include>**/RunCucumberTest.java</include>
                    </includes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <profiles>
        <profile>
            <id>integration</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-failsafe-plugin</artifactId>
                        <version>3.2.5</version>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>integration-test</goal>
                                    <goal>verify</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
</project>

// === ARCHIVO: src/test/java/com/credito/cartera/bdd/models/SolicitudCredito.java ===
package com.credito.cartera.bdd.models;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SolicitudCredito {
    private String numeroOperacion;
    private String canal;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombreSolicitante;
    private String apellidoSolicitante;
    private BigDecimal montoSolicitado;
    private Integer plazoMeses;
    private String estado;
    private LocalDate fechaSolicitud;
    private String motivoRechazo;
}

// === ARCHIVO: src/main/java/com/credito/cartera/domain/model/SolicitudCredito.java ===
package com.credito.cartera.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCredito {
    private String numeroOperacion;
    private String canal;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombreSolicitante;
    private String apellidoSolicitante;
    private BigDecimal montoSolicitado;
    private Integer plazoMeses;
    private EstadoSolicitud estado;
    private LocalDate fechaSolicitud;
    private String motivoRechazo;

    public enum EstadoSolicitud {
        PENDIENTE, APROBADA, RECHAZADA
    }

    public void aprobar() {
        this.estado = EstadoSolicitud.APROBADA;
        this.motivoRechazo = null;
    }

    public void rechazar(String motivo) {
        this.estado = EstadoSolicitud.RECHAZADA;
        this.motivoRechazo = motivo;
    }

    public String getIdempotencyKey() {
        return numeroOperacion + "|" + canal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudCredito that = (SolicitudCredito) o;
        return Objects.equals(numeroOperacion, that.numeroOperacion) && 
               Objects.equals(canal, that.canal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroOperacion, canal);
    }
}

// === ARCHIVO: src/main/java/com/credito/cartera/domain/service/ValidacionService.java ===
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

// === ARCHIVO: src/main/java/com/credito/cartera/infrastructure/repository/SolicitudCreditoRepository.java ===
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

// === ARCHIVO: src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java ===
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

// === ARCHIVO: src/main/java/com/credito/cartera/infrastructure/external/BuroRiesgosClient.java ===
package com.credito.cartera.infrastructure.external;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Adaptador para la comunicación con el servicio externo de Buró de Riesgos.
 * Implementa el patrón de cliente HTTP con manejo de errores y timeouts configurables.
 */
public class BuroRiesgosClient {

    private static final String BASE_URL = System.getProperty("buro.riesgos.url", "https://api.buroriesgos.example.com");
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    private static final int MAX_REINTENTOS = 3;
    
    private final String apiKey;
    private final RequestSpecification defaultRequest;

    /**
     * Constructor que inicializa el cliente con la configuración del entorno.
     *
     * @param apiKey Clave de API para autenticación con el buró de riesgos
     */
    public BuroRiesgosClient(String apiKey) {
        this.apiKey = apiKey;
        this.defaultRequest = buildDefaultRequest();
    }

    private RequestSpecification buildDefaultRequest() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + apiKey)
                .header("X-Correlation-ID", generateCorrelationId())
                .header("X-Client-Version", "1.0.0")
                .timeout(TIMEOUT.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    /**
     * Consulta el historial crediticio de un solicitante en el buró de riesgos.
     *
     * @param tipoDocumento Tipo de documento de identificación
     * @param numeroDocumento Número de documento
     * @return Respuesta del buró con el historial crediticio
     */
    public BuroRiesgosResponse consultarHistorial(String tipoDocumento, String numeroDocumento) {
        Map<String, Object> body = new HashMap<>();
        body.put("tipoDocumento", tipoDocumento);
        body.put("numeroDocumento", numeroDocumento);
        body.put("tipoConsulta", "HISTORIAL_COMPLETO");
        body.put("fechaConsulta", java.time.Instant.now().toString());
        
        Response response = executeWithRetry(() -> 
            defaultRequest
                .body(body)
                .when()
                .post("/v1/consultas/historial")
        );
        
        return mapResponseToBuroRiesgosResponse(response);
    }

    /**
     * Consulta el score crediticio del solicitante.
     *
     * @param tipoDocumento Tipo de documento de identificación
     * @param numeroDocumento Número de documento
     * @return Respuesta con el score y clasificación de riesgo
     */
    public BuroRiesgosResponse consultarScore(String tipoDocumento, String numeroDocumento) {
        Map<String, Object> body = new HashMap<>();
        body.put("tipoDocumento", tipoDocumento);
        body.put("numeroDocumento", numeroDocumento);
        body.put("tipoConsulta", "SCORE");
        body.put("modeloScoring", "SCORE_V3");
        
        Response response = executeWithRetry(() -> 
            defaultRequest
                .body(body)
                .when()
                .post("/v1/consultas/score")
        );
        
        return mapResponseToBuroRiesgosResponse(response);
    }

    /**
     * Consulta múltiples productos de buró en una sola llamada.
     *
     * @param tipoDocumento Tipo de documento
     * @param numeroDocumento Número de documento
     * @return Respuesta consolidada con historial y score
     */
    public BuroRiesgosResponse consultarCompleto(String tipoDocumento, String numeroDocumento) {
        Map<String, Object> body = new HashMap<>();
        body.put("tipoDocumento", tipoDocumento);
        body.put("numeroDocumento", numeroDocumento);
        body.put("tipoConsulta", "COMPLETA");
        body.put("incluirReporte", true);
        body.put("incluirScore", true);
        body.put("incluirAlertas", true);
        
        Response response = executeWithRetry(() -> 
            defaultRequest
                .body(body)
                .when()
                .post("/v1/consultas/completa")
        );
        
        return mapResponseToBuroRiesgosResponse(response);
    }

    private Response executeWithRetry(java.util.function.Supplier<Response> requestSupplier) {
        int intentos = 0;
        Exception ultimaExcepcion = null;
        
        while (intentos < MAX_REINTENTOS) {
            try {
                return requestSupplier.get();
            } catch (Exception e) {
                ultimaExcepcion = e;
                intentos++;
                if (intentos < MAX_REINTENTOS) {
                    try {
                        Thread.sleep((long) Math.pow(2, intentos) * 100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        throw new BuroRiesgosException("Error después de " + MAX_REINTENTOS + " intentos: " + 
                (ultimaExcepcion != null ? ultimaExcepcion.getMessage() : "Unknown"));
    }

    private BuroRiesgosResponse mapResponseToBuroRiesgosResponse(Response response) {
        int statusCode = response.getStatusCode();
        
        if (statusCode == 200) {
            return new BuroRiesgosResponse(
                response.jsonPath().getBoolean("exitoso"),
                response.jsonPath().getString("codigoRespuesta"),
                response.jsonPath().getString("mensaje"),
                response.jsonPath().getInt("score"),
                response.jsonPath().getString("clasificacionRiesgo"),
                response.jsonPath().getObject("historial", BuroHistorial.class),
                response.jsonPath().getList("alertas", String.class)
            );
        } else if (statusCode == 404) {
            return new BuroRiesgosResponse(false, "NO_ENCONTRADO", 
                "No se encontró información para el documento", null, null, null, null);
        } else if (statusCode == 429) {
            throw new BuroRiesgosException("Límite de consultas alcanzado");
        } else if (statusCode >= 500) {
            throw new BuroRiesgosException("Error del servicio de buró: " + statusCode);
        } else {
            throw new BuroRiesgosException("Error en consulta de buró: " + statusCode);
        }
    }

    private String generateCorrelationId() {
        return "CRÉDITO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Excepción específica para errores de comunicación con el buró de riesgos.
     */
    public static class BuroRiesgosException extends RuntimeException {
        public BuroRiesgosException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * DTO para representar la respuesta del buró de riesgos.
     */
    public static class BuroRiesgosResponse {
        private final boolean exitoso;
        private final String codigoRespuesta;
        private final String mensaje;
        private final Integer score;
        private final String clasificacionRiesgo;
        private final BuroHistorial historial;
        private final java.util.List<String> alertas;

        public BuroRiesgosResponse(boolean exitoso, String codigoRespuesta, String mensaje,
                                   Integer score, String clasificacionRiesgo, 
                                   BuroHistorial historial, java.util.List<String> alertas) {
            this.exitoso = exitoso;
            this.codigoRespuesta = codigoRespuesta;
            this.mensaje = mensaje;
            this.score = score;
            this.clasificacionRiesgo = clasificacionRiesgo;
            this.historial = historial;
            this.alertas = alertas;
        }

        public boolean isExitoso() { return exitoso; }
        public String getCodigoRespuesta() { return codigoRespuesta; }
        public String getMensaje() { return mensaje; }
        public Integer getScore() { return score; }
        public String getClasificacionRiesgo() { return clasificacionRiesgo; }
        public BuroHistorial getHistorial() { return historial; }
        public java.util.List<String> getAlertas() { return alertas; }

        public boolean tieneRiesgoAlto() {
            return "ALTO".equalsIgnoreCase(clasificacionRiesgo) || 
                   (score != null && score < 500);
        }

        public boolean tieneAlertas() {
            return alertas != null && !alertas.isEmpty();
        }
    }

    /**
     * DTO para el historial crediticio del buró.
     */
    public static class BuroHistorial {
        private Integer cantidadCreditosActivos;
        private Integer cantidadCreditosVencidos;
        private BigDecimal montoTotalDeuda;
        private Integer cantidadConsultasRecientes;

        public Integer getCantidadCreditosActivos() { return cantidadCreditosActivos; }
        public Integer getCantidadCreditosVencidos() { return cantidadCreditosVencidos; }
        public BigDecimal getMontoTotalDeuda() { return montoTotalDeuda; }
        public Integer getCantidadConsultasRecientes() { return cantidadConsultasRecientes; }

        public void setCantidadCreditosActivos(Integer cantidadCreditosActivos) { 
            this.cantidadCreditosActivos = cantidadCreditosActivos; 
        }
        public void setCantidadCreditosVencidos(Integer cantidadCreditosVencidos) { 
            this.cantidadCreditosVencidos = cantidadCreditosVencidos; 
        }
        public void setMontoTotalDeuda(BigDecimal montoTotalDeuda) { 
            this.montoTotalDeuda = montoTotalDeuda; 
        }
        public void setCantidadConsultasRecientes(Integer cantidadConsultasRecientes) { 
            this.cantidadConsultasRecientes = cantidadConsultasRecientes; 
        }
    }
}

// === ARCHIVO: src/main/resources/serenity.conf ===
webdriver {
    driver = chrome
    auto.download = true
    {
        capabilities {
            "goog:chromeOptions" {
                args = ["headless", "disable-gpu", "no-sandbox", "disable-dev-shm-usage", "disable-extensions"]
                prefs = {
                    "download.prompt_for_download" = false
                    "download.default_directory" = "target/downloads"
                    "safebrowsing.enabled" = true
                }
            }
            "common.capabilities.timeout" = 30
        }
        wait.for.timeout = 5000
        wait.polling.interval = 500
    }
}

serenity {
    project.name = "Sistema de Gestión de Cartera de Crédito"
    test.root = "src/test/resources/features"
    {
        reporter {
            generate.before.and.after.each.scenario = true
            generate.scenario.violations = true
            output.inline.files = true
        }
        capture {
            engine.events = true
            step.events = true
            step.matches = true
            test.events = true
        }
        logging {
            verbosity = NORMAL
            show.pending.steps = true
            show.steptypes = true
        }
    }
}

rest {
    baseurl = "http://localhost:8080/api"
    {
        mock {
            enabled = false
        }
    }
}

environments {
    default = local
    local {
        webdriver.base.url = "http://localhost:8080"
        api.base.url = "http://localhost:8080/api"
        api.timeout = 30000
    }
    dev {
        webdriver.base.url = "https://dev.cartera.credito.com"
        api.base.url = "https://dev.cartera.credito.com/api"
        api.timeout = 45000
    }
    qa {
        webdriver.base.url = "https://qa.cartera.credito.com"
        api.base.url = "https://qa.cartera.credito.com/api"
        api.timeout = 60000
    }
    prod {
        webdriver.base.url = "https://cartera.credito.com"
        api.base.url = "https://cartera.credito.com/api"
        api.timeout = 30000
    }
}

performance {
    thresholds {
        response.time = 500
        throughput = 1500
    }
    monitoring {
        enabled = true
        interval = 1000
    }
}

buro {
    endpoint = "https://buro.riesgos.internal/api/v2"
    timeout = 10000
    retries = 3
    {
        mock {
            enabled = true
            response.delay = 500
        }
    }
}

antifraude {
    endpoint = "https://antifraude.motor.internal/api/v1"
    timeout = 8000
    retries = 2
    {
        mock {
            enabled = true
            response.delay = 300
        }
    }
}

reporting {
    outputDirectory = "target/site/serenity"
    historyDirectory = "target/site/serenity-history"
    {
        formats {
            html = true
            json = true
            xml = true
        }
    }
}

// === ARCHIVO: src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java ===
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

// === ARCHIVO: src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java ===
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

// === ARCHIVO: src/test/resources/features/solicitud_credito.feature ===
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

// === ARCHIVO: src/test/resources/features/validacion_antifraude.feature ===
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

// === ARCHIVO: src/test/resources/features/performance.feature ===
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

// === ARCHIVO: src/test/java/com/credito/cartera/bdd/runners/RunCucumberTest.java ===
package com.credito.cartera.bdd.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.credito.cartera.bdd.steps",
        "com.credito.cartera.bdd.hooks"
    },
    tags = "@smoke or @regresion or @integration or @performance",
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber.html",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    monochrome = true,
    strict = true,
    dryRun = false
)
public class RunCucumberTest {
}

// === ARCHIVO: src/test/java/com/credito/cartera/bdd/steps/SolicitudCreditoSteps.java ===
package com.credito.cartera.bdd.steps;

import com.credito.cartera.bdd.tasks.RegistrarSolicitud;
import com.credito.cartera.bdd.questions.EstadoSolicitud;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;

import java.math.BigDecimal;
import java.time.LocalDate;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class SolicitudCreditoSteps {

    @Dado("que el originador de créditos ingresa los datos de una solicitud de crédito")
    public void queElOriginadorIngresaLosDatos() {
        Actor actor = OnStage.theActorCalled("Originador");
        actor.wasAbleTo(
            RegistrarSolicitud.conDatos(
                "OPE-2024-001",
                "WEB",
                "DNI",
                "12345678",
                "Juan",
                "Pérez",
                new BigDecimal("15000.00"),
                12
            )
        );
    }

    @Cuando("el sistema procesa la solicitud de crédito")
    public void elSistemaProcesaLaSolicitud() {
        Actor actor = OnStage.theActorInTheSpotlight();
    }

    @Entonces("la solicitud debe ser registrada con estado {string}")
    public void laSolicitudDebeSerRegistrada(String estadoEsperado) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(
            seeThat(
                "El estado de la solicitud",
                EstadoSolicitud.es(),
                equalTo(estadoEsperado)
            )
        );
    }

    @Dado("que existe una solicitud de crédito con número de operación {string}")
    public void queExisteUnaSolicitud(String numeroOperacion) {
        Actor actor = OnStage.theActorCalled("Originador");
    }

    @Cuando("se intenta registrar una nueva solicitud con el mismo número de operación")
    public void seIntentaRegistrarConMismoNumero() {
        Actor actor = OnStage.theActorInTheSpotlight();
    }

    @Entonces("el sistema debe retornar la misma respuesta anterior")
    public void elSistemaDebeRetornarLaMismaRespuesta() {
        Actor actor = OnStage.theActorInTheSpotlight();
    }
}

// === ARCHIVO: src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java ===
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

// === ARCHIVO: src/test/java/com/credito/cartera/bdd/questions/EstadoSolicitud.java ===
package com.credito.cartera.bdd.questions;

import com.credito.cartera.domain.model.SolicitudCredito;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

public class EstadoSolicitud implements Question<String> {

    private static final String CONSULTA_ESTADO = 
        "SELECT estado FROM solicitud_credito WHERE numero_operacion = ?";

    private EstadoSolicitud() {
    }

    public static EstadoSolicitud es() {
        return new EstadoSolicitud();
    }

    @Override
    public String answeredBy(Actor actor) {
        List<SolicitudCredito> solicitudes = actor.recall("solicitudes_registradas");
        
        if (solicitudes != null && !solicitudes.isEmpty()) {
            SolicitudCredito ultima = solicitudes.get(solicitudes.size() - 1);
            return ultima.getEstado().name();
        }
        
        return "SIN_REGISTRO";
    }

    public static Question<String> conNumeroOperacion(String numeroOperacion) {
        return new Question<String>() {
            @Override
            public String answeredBy(Actor actor) {
                List<SolicitudCredito> solicitudes = actor.recall("solicitudes_registradas");
                
                if (solicitudes != null) {
                    return solicitudes.stream()
                        .filter(s -> s.getNumeroOperacion().equals(numeroOperacion))
                        .findFirst()
                        .map(s -> s.getEstado().name())
                        .orElse("NO_ENCONTRADO");
                }
                
                return "NO_ENCONTRADO";
            }

            @Override
            public String getSubject() {
                return "El estado de la solicitud con número de operación " + numeroOperacion;
            }
        };
    }
}

// === ARCHIVO: src/test/java/com/credito/cartera/bdd/abilities/InteractuarConBuroRiesgos.java ===
package com.credito.cartera.bdd.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.Step;

public class InteractuarConBuroRiesgos implements Ability {

    private final String endpointBuro;
    private final String apiKey;

    public InteractuarConBuroRiesgos(String endpointBuro, String apiKey) {
        this.endpointBuro = endpointBuro;
        this.apiKey = apiKey;
    }

    public static InteractuarConBuroRiesgos conCredenciales(String endpoint, String apiKey) {
        return new InteractuarConBuroRiesgos(endpoint, apiKey);
    }

    public static InteractuarConBuroRiesgos comoActor(Actor actor) {
        return actor.abilityTo(InteractuarConBuroRiesgos.class);
    }

    @Step("Consultar bureau de riesgos para documento {0}")
    public RespuestaBuro consultarPorDocumento(String tipoDocumento, String numeroDocumento) {
        throw new UnsupportedOperationException("Implementar consulta al bureau de riesgos");
    }

    @Step("Obtener score crediticio para documento {0}")
    public Integer obtenerScoreCrediticio(String tipoDocumento, String numeroDocumento) {
        throw new UnsupportedOperationException("Implementar obtencion de score");
    }

    @Step("Verificar historial de morosidad para documento {0}")
    public boolean tieneHistorialMorosidad(String tipoDocumento, String numeroDocumento) {
        throw new UnsupportedOperationException("Implementar verificacion de morosidad");
    }

    public String getEndpoint() {
        return endpointBuro;
    }

    public String getApiKey() {
        return apiKey;
    }

    public static class RespuestaBuro {
        private String codigoRespuesta;
        private String mensajeRespuesta;
        private Integer score;
        private boolean tieneDeudasVencidas;
        private Integer cantidadConsultasUltimos30Dias;

        public String getCodigoRespuesta() {
            return codigoRespuesta;
        }

        public void setCodigoRespuesta(String codigoRespuesta) {
            this.codigoRespuesta = codigoRespuesta;
        }

        public String getMensajeRespuesta() {
            return mensajeRespuesta;
        }

        public void setMensajeRespuesta(String mensajeRespuesta) {
            this.mensajeRespuesta = mensajeRespuesta;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public boolean isTieneDeudasVencidas() {
            return tieneDeudasVencidas;
        }

        public void setTieneDeudasVencidas(boolean tieneDeudasVencidas) {
            this.tieneDeudasVencidas = tieneDeudasVencidas;
        }

        public Integer getCantidadConsultasUltimos30Dias() {
            return cantidadConsultasUltimos30Dias;
        }

        public void setCantidadConsultasUltimos30Dias(Integer cantidadConsultasUltimos30Dias) {
            this.cantidadConsultasUltimos30Dias = cantidadConsultasUltimos30Dias;
        }
    }
}

// === ARCHIVO: src/test/java/com/credito/cartera/unit/application/usecase/RegistrarSolicitudUseCaseTest.java ===
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

// === ARCHIVO: README.md ===
# Sistema de Gestión de Cartera de Crédito

Proyecto de gestión de solicitudes de crédito con implementación de prácticas TDD y BDD utilizando Serenity BDD, Cucumber y JUnit 5.

## Requisitos del Entorno

- Java Development Kit (JDK) 21 o superior
- Apache Maven 3.8 o superior
- Navegador web para pruebas de interfaz de usuario (Chrome, Firefox)
- Acceso a servicios externos: Motor Antifraude, Bureau de Riesgos

## Estructura del Proyecto

```
credito-cartera/
├── src/
│   ├── main/
│   │   └── java/com/credito/cartera/
│   │       ├── domain/              # Modelo de dominio y lógica de negocio
│   │       │   ├── model/           # Entidades del dominio
│   │       │   └── service/         # Servicios de dominio
│   │       ├── application/         # Casos de uso
│   │       │   └── usecase/
│   │       └── infrastructure/      # Implementaciones técnicas
│   │           ├── repository/      # Persistencia
│   │           └── external/        # Clientes externos
│   └── test/
│       ├── java/com/credito/cartera/
│       │   ├── bdd/                # Pruebas BDD Screenplay
│       │   │   ├── abilities/       # Capacidades de los actores
│       │   │   ├── tasks/           # Tareas encapsuladas
│       │   │   ├── questions/       # Preguntas para asserts
│       │   │   ├── steps/           # Step definitions Cucumber
│       │   │   └── runners/         # Ejecutores de pruebas
│       │   └── unit/                # Pruebas unitarias TDD
│       │       ├── domain/
│       │       └── application/
│       └── resources/
│           ├── features/            # Escenarios Gherkin
│           └── serenity.conf        # Configuración de Serenity
├── pom.xml
└── README.md
```

## Configuración

El archivo `serenity.conf` contiene las propiedades del entorno de ejecución. Verificar que los endpoints de servicios externos estén correctly configurados antes de ejecutar las pruebas.

### Propiedades Principales

- `webdriver.base.url`: URL base para pruebas de interfaz
- `buro.riesgos.endpoint`: Endpoint del servicio de bureau de riesgos
- `antifraude.endpoint`: Endpoint del motor antifraude
- `api.key`: Clave de autenticación para servicios externos

## Ejecución de Pruebas

### Compilar el Proyecto

```bash
mvn clean compile
```

### Ejecutar Pruebas Unitarias

```bash
mvn test
```

### Ejecutar Pruebas BDD

```bash
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.name="Registro de solicitud"
```

### Ejecutar Todas las Pruebas con Reportes

```bash
mvn verify
```

### Generar Reportes Serenity

```bash
mvn serenity:aggregate
```

Los reportes se generan en: `target/site/serenity/index.html`

### Ejecutar con Perfil de Integración

```bash
mvn verify -Pintegration
```

## Patrones de Diseño

### Screenplay (BDD)

- **Abilities**: Capacidades que un actor puede utilizar (interactuar con servicios, navegador, API)
- **Tasks**: Acciones de alto nivel que encapsulan la lógica de interacción
- **Questions**: Consultas que permiten verificar el estado del sistema
- **Actors**: Representan los roles del dominio (originador, sistema, buró)

### TDD (Pruebas Unitarias)

- Ciclo Red-Green-Refactor
- Nombres descriptivos para casos de prueba
- Un assertions por test cuando sea posible
- Mocks para dependencias externas

## Métricas de Calidad

El sistema debe cumplir con los siguientes umbrales:

- **Rendimiento**: Mínimo 1,500 solicitudes por segundo en hora pico
- **Latencia**: Respuesta inferior a 500ms
- **Idempotencia**: Clave basada en número de operación y canal (ventana de 24 horas)

## Servicios Externos

- **Motor Antifraude**: Validación de patrones de fraude
- **Bureau de Riesgos**: Consulta de historial crediticio y score

## Licencia

Proprietario - Todos los derechos reservados
```
