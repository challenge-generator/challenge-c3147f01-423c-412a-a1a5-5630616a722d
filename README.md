# Implementación de TDD y BDD en un sistema de gestión de cartera de crédito

En un sistema de gestión de cartera de crédito, se requiere implementar prácticas de TDD y BDD para asegurar la calidad del producto, la eficiencia del desarrollo y la satisfacción del cliente. El sistema debe manejar solicitudes de crédito, validar la información del solicitante, y decidir sobre la aprobación o rechazo de la solicitud. Los actores involucrados son el 'originador de créditos', el'motor antifraude', y el 'buró de riesgos'. El sistema debe procesar un mínimo de 1 500 solicitudes por segundo en hora pico y mantener una latencia de respuesta inferior a 500ms. La idempotencia del registro de solicitudes se asegura mediante el número de operación y el canal de solicitud, asegurando que dos invocaciones con la misma clave produzcan un solo registro y devuelvan la misma respuesta dentro de una ventana de 24 horas.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | TDD y BDD |
| **Nivel** | senior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 15 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de comportamientos y casos de prueba

**Objetivo:** Establecer los comportamientos esperados del sistema y los casos de prueba iniciales utilizando BDD.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Identificar los comportamientos clave del sistema de gestión de cartera de crédito.
- Escribir casos de prueba utilizando Gherkin para describir estos comportamientos.
- Asegurar que los casos de prueba cubran los escenarios felices, las validaciones y los edge cases del dominio.

**Entregable:** Documento de casos de prueba en Gherkin que describe los comportamientos del sistema.

<details>
<summary>Pistas de conocimiento</summary>

- Considerar los diferentes actores y sus interacciones en el dominio.
- Incluir validaciones de datos y edge cases naturales del dominio.

</details>

### Fase 2: Implementación de pruebas unitarias con TDD

**Objetivo:** Implementar pruebas unitarias para los comportamientos definidos en la fase anterior utilizando TDD.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Escribir pruebas unitarias para cada caso de prueba definido en la fase 1.
- Implementar el código necesario para pasar las pruebas unitarias.
- Asegurar que el código cumpla con los criterios de aceptación definidos en los casos de prueba.

**Entregable:** Código implementado con pruebas unitarias que pasan todos los casos de prueba definidos en la fase 1.

<details>
<summary>Pistas de conocimiento</summary>

- Utilizar el ciclo rojo-verde-refactor para implementar las pruebas y el código.
- Considerar las dependencias del sistema y cómo mockearlas en las pruebas.

</details>

### Fase 3: Refactorización y optimización del código

**Objetivo:** Refactorizar y optimizar el código implementado para mejorar la calidad y eficiencia del desarrollo.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Analizar el código implementado en la fase 2 y identificar áreas de mejora.
- Refactorizar el código para mejorar la legibilidad, mantenibilidad y eficiencia.
- Optimizar el código para asegurar que el sistema cumpla con los umbrales de rendimiento definidos en la descripción del problema.

**Entregable:** Código refactorizado y optimizado que cumple con los umbrales de rendimiento definidos.

<details>
<summary>Pistas de conocimiento</summary>

- Utilizar patrones de diseño y principios de programación limpia para refactorizar el código.
- Considerar las métricas de rendimiento y cómo mejorarlas.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es TDD y BDD y cómo se aplican en este reto?
- **paraQueSirve**: ¿Para qué sirven las prácticas de TDD y BDD en el desarrollo de software?
- **comoSeUsa**: ¿Cómo se utilizan TDD y BDD en la implementación de un sistema de gestión de cartera de crédito?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar TDD y BDD y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la refactorización y optimización del código en este reto?

## Criterios de Evaluacion

- Definición clara de comportamientos y casos de prueba utilizando BDD.
- Implementación de pruebas unitarias utilizando TDD que pasan todos los casos de prueba.
- Código refactorizado y optimizado que cumple con los umbrales de rendimiento definidos.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
