# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de TDD y BDD en un sistema de gestión de cartera de crédito**.

| | |
|---|---|
| Tema | TDD y BDD |
| Nivel | senior-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Serenity BDD con Cucumber y JUnit 5 |
| Patron arquitectonico | Patrón Screenplay para BDD con capas de dominio, tareas, preguntas y actores, separado de las pruebas unitarias TDD en estructura hexagonal |
| Tiempo estimado | 15 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- net.serenity-bdd:serenity-core 4.1.10
- net.serenity-bdd:serenity-cucumber 4.1.10
- io.cucumber:cucumber-java 7.15.0
- io.cucumber:cucumber-junit-platform-engine 7.15.0
- org.junit.jupiter:junit-jupiter 5.10.0
- org.mockito:mockito-core 5.5.0
- org.seleniumhq.selenium:selenium-java 4.16.0
- io.rest-assured:rest-assured 5.4.0
- org.apache.maven.plugins:maven-surefire-plugin 3.2.5
- net.serenity-bdd:serenity-maven-plugin 4.1.10
- org.projectlombok:lombok 1.18.30

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de comportamientos y casos de prueba**: Documento de casos de prueba en Gherkin que describe los comportamientos del sistema.
- **Fase 2 — Implementación de pruebas unitarias con TDD**: Código implementado con pruebas unitarias que pasan todos los casos de prueba definidos en la fase 1.
- **Fase 3 — Refactorización y optimización del código**: Código refactorizado y optimizado que cumple con los umbrales de rendimiento definidos.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/resources/features/solicitud_credito.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/features/validacion_antifraude.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/features/performance.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/bdd/runners/RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/bdd/steps/SolicitudCreditoSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/bdd/questions/EstadoSolicitud.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/bdd/abilities/InteractuarConBuroRiesgos.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/bdd/models/SolicitudCredito.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/credito/cartera/unit/application/usecase/RegistrarSolicitudUseCaseTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (49)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.exception.FraudeDetectadoException`
      El import com.credito.cartera.domain.exception.FraudeDetectadoException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.exception.SolicitudInvalidaException`
      El import com.credito.cartera.domain.exception.SolicitudInvalidaException usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.port.BuroRiesgosPort`
      El import com.credito.cartera.domain.port.BuroRiesgosPort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `com.credito.cartera.domain.port.MotorAntifraudePort`
      El import com.credito.cartera.domain.port.MotorAntifraudePort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `com.credito.cartera.infrastructure.port.MotorAntifraudePort`
      El import com.credito.cartera.infrastructure.port.MotorAntifraudePort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `com.credito.cartera.infrastructure.port.BuroRiesgosPort`
      El import com.credito.cartera.infrastructure.port.BuroRiesgosPort usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/credito/cartera/bdd/steps/SolicitudCreditoSteps.java` — `org.hamcrest.Matchers`
      El import org.hamcrest.Matchers.equalTo pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getMontoSolicitado`
      Se invoca `getMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getTipoDocumento`
      Se invoca `getTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getNumeroDocumento`
      Se invoca `getNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getNombreSolicitante`
      Se invoca `getNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/domain/service/ValidacionService.java` — `SolicitudCredito.getApellidoSolicitante`
      Se invoca `getApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.getNumeroOperacion`
      Se invoca `getNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.getCanal`
      Se invoca `getCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `ValidacionException.getMessage`
      Se invoca `getMessage` sobre `ValidacionException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.setFechaSolicitud`
      Se invoca `setFechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.getId`
      Se invoca `getId` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java` — `SolicitudCredito.setId`
      Se invoca `setId` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setNumeroOperacion`
      Se invoca `setNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setCanal`
      Se invoca `setCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setTipoDocumento`
      Se invoca `setTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setNumeroDocumento`
      Se invoca `setNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setNombreSolicitante`
      Se invoca `setNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setApellidoSolicitante`
      Se invoca `setApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setMontoSolicitado`
      Se invoca `setMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setPlazoMeses`
      Se invoca `setPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setEstado`
      Se invoca `setEstado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java` — `SolicitudCredito.setFechaSolicitud`
      Se invoca `setFechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setNumeroOperacion`
      Se invoca `setNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setCanal`
      Se invoca `setCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setTipoDocumento`
      Se invoca `setTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setNumeroDocumento`
      Se invoca `setNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setNombreSolicitante`
      Se invoca `setNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setApellidoSolicitante`
      Se invoca `setApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setMontoSolicitado`
      Se invoca `setMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setPlazoMeses`
      Se invoca `setPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setEstado`
      Se invoca `setEstado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java` — `SolicitudCredito.setFechaSolicitud`
      Se invoca `setFechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setNumeroOperacion`
      Se invoca `setNumeroOperacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setCanal`
      Se invoca `setCanal` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setTipoDocumento`
      Se invoca `setTipoDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setNumeroDocumento`
      Se invoca `setNumeroDocumento` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setNombreSolicitante`
      Se invoca `setNombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setApellidoSolicitante`
      Se invoca `setApellidoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setMontoSolicitado`
      Se invoca `setMontoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java` — `SolicitudCredito.setPlazoMeses`
      Se invoca `setPlazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/credito/cartera/bdd/questions/EstadoSolicitud.java` — `SolicitudCredito.getEstado`
      Se invoca `getEstado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.10`
      net.serenity-bdd:serenity-bom declara la version 4.1.10, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (20)

- `pom.xml`
- `src/test/java/com/credito/cartera/bdd/models/SolicitudCredito.java`
- `src/main/java/com/credito/cartera/domain/model/SolicitudCredito.java`
- `src/main/java/com/credito/cartera/domain/service/ValidacionService.java`
- `src/main/java/com/credito/cartera/infrastructure/repository/SolicitudCreditoRepository.java`
- `src/main/java/com/credito/cartera/application/usecase/RegistrarSolicitudUseCase.java`
- `src/main/java/com/credito/cartera/infrastructure/external/BuroRiesgosClient.java`
- `src/main/resources/serenity.conf`
- `src/test/java/com/credito/cartera/unit/domain/model/SolicitudCreditoTest.java`
- `src/test/java/com/credito/cartera/unit/domain/service/ValidacionServiceTest.java`
- `src/test/resources/features/solicitud_credito.feature`
- `src/test/resources/features/validacion_antifraude.feature`
- `src/test/resources/features/performance.feature`
- `src/test/java/com/credito/cartera/bdd/runners/RunCucumberTest.java`
- `src/test/java/com/credito/cartera/bdd/steps/SolicitudCreditoSteps.java`
- `src/test/java/com/credito/cartera/bdd/tasks/RegistrarSolicitud.java`
- `src/test/java/com/credito/cartera/bdd/questions/EstadoSolicitud.java`
- `src/test/java/com/credito/cartera/bdd/abilities/InteractuarConBuroRiesgos.java`
- `src/test/java/com/credito/cartera/unit/application/usecase/RegistrarSolicitudUseCaseTest.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/credito/cartera/domain`
- `src/main/java/com/credito/cartera/application`
- `src/main/java/com/credito/cartera/infrastructure`
- `src/test/java/com/credito/cartera/bdd/features`
- `src/test/java/com/credito/cartera/bdd/steps`
- `src/test/java/com/credito/cartera/bdd/tasks`
- `src/test/java/com/credito/cartera/bdd/questions`
- `src/test/java/com/credito/cartera/bdd/abilities`
- `src/test/java/com/credito/cartera/bdd/models`
- `src/test/java/com/credito/cartera/unit`
- `src/test/resources/features`

## Verificacion

```bash
mvn clean test-compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **Patrón Screenplay para BDD con capas de dominio, tareas, preguntas y actores, separado de las pruebas unitarias TDD en estructura hexagonal**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Seniority Senior
- Brecha que el reto ataca: Demuestra su compromiso con la práctica de marcos como TDD y BDD, lo cual ademas de fortalecer su liderazgo, se traduce en la excelencia en calidad del producto, eficiencia del desarrollo y satisfacción del cliente, siendo clave para el éxito general del equipo y del proyecto
- Mision: Especialista en QA con experiencia como automatizador senior

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
