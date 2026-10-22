# Casos de Prueba - Sistema de Inventario de Ambientes SENA

## Sprint 1: Gestión e Inventarios de Ambientes

---

### CP-01: Registro correcto de un ambiente
* **Identificador:** CP-01
* **Descripción:** Verificar que un ambiente con datos válidos se registre correctamente en el sistema.
* **Datos de Entrada:**
  * Nombre: `Ambiente 302`
  * Piso: `3`
* **Resultado Esperado:** El ambiente se guarda exitosamente y se muestra mensaje de confirmación en consola.
* **Resultado Obtenido:** El ambiente fue creado correctamente sin errores.
* **Estado:** PASÓ (Aprobado)

---

### CP-02: Intento de registro de ambiente repetido
* **Identificador:** CP-02
* **Descripción:** Validar que el sistema impida el registro de dos ambientes con el mismo nombre.
* **Datos de Entrada:**
  * Nombre: `Ambiente 302` (ya existente)
  * Piso: `3`
* **Resultado Esperado:** El sistema no permite el registro y lanza un mensaje indicando que el ambiente ya existe.
* **Resultado Obtenido:** Se detecta el nombre duplicado y se bloquea el registro con un mensaje claro en consola.
* **Estado:** PASÓ (Aprobado)

---

### CP-03: Entrada inválida al registrar ambiente
* **Identificador:** CP-03
* **Descripción:** Comprobar que la clase Validaciones rechace nombres nulos o vacíos en el nombre del ambiente.
* **Datos de Entrada:**
  * Nombre: `""`, `"   "` (solo espacios) y `null`, probados con pruebas/PruebasValidaciones.java
* **Resultado Esperado:** El sistema no procesa la solicitud y muestra advertencia sobre nombre de ambiente inválido.
* **Resultado Obtenido:** PruebasValidaciones confirmó que los tres nombres son rechazados; para el nombre vacío se mostró "El nombre del ambiente no puede estar vacio." sin que el programa se cerrara.
* **Estado:** PASÓ (Aprobado)
---

### CP-04: Registro y consulta de observaciones desde el menú
* **Identificador:** CP-04
* **Funcionalidad:** Opciones de observaciones del menú (5 y 7).
* **Datos de Entrada:** Con `AULA 1` (piso 1) y el elemento `COMPUTADOR` (actual 8, esperada 10, `BUENO`): opción `5` con ambiente `AULA 1`, elemento `COMPUTADOR`, tipo `FALTANTE`, cantidad afectada `2`, descripción `FALTAN DOS COMPUTADORES`; luego opción `7` con ambiente `AULA 1` y `S` en "Solo pendientes?".
* **Resultado Esperado:** La observación se registra en estado Pendiente y aparece en el listado de pendientes del ambiente.
* **Resultado Obtenido:** Mostró "Observacion registrada en estado Pendiente." y el listado mostró "[Pendiente] 2026-10-22 | COMPUTADOR | Faltante | Cant: 2 | FALTAN DOS COMPUTADORES". Después, la consulta del inventario siguió mostrando Cant. Actual: 8.
* **Estado:** Satisfactorio

---

### CP-05: Filtro de ambientes por piso
* **Identificador:** CP-05
* **Funcionalidad:** Opción de filtro por piso del menú (6).
* **Datos de Entrada:** Con `AULA 1` registrada en el piso 1: opción `6` con piso `1`; en otra consulta, opción `6` con piso `2`.
* **Resultado Esperado:** Para el piso 1 se lista `AULA 1`; para el piso 2 se informa que no hay ambientes.
* **Resultado Obtenido:** Con piso 1 mostró "--- AMBIENTES DEL PISO 1 ---" y "- AULA 1". Con piso 2 mostró "No hay ambientes registrados en el piso 2."
* **Estado:** Satisfactorio

---

### CP-06: Opción no reconocida en el menú
* **Identificador:** CP-06
* **Funcionalidad:** Gestión de opciones inexistentes en el menú principal.
* **Datos de Entrada:** Opción `15`.
* **Resultado Esperado:** Mensaje de opción no válida y el menú se vuelve a mostrar sin cerrar el programa.
* **Resultado Obtenido:** Mostró "Opcion no valida. Intenta de nuevo.", seguido de una línea suelta "9. Reporte de faltantes por piso", y volvió a mostrar el menú.
* **Estado:** Satisfactorio

---

### CP-07: Entrada no numérica en el menú
* **Identificador:** CP-07
* **Funcionalidad:** Gestión de entradas incorrectas en la opción del menú.
* **Datos de Entrada:** Texto `ABC` en la opción del menú.
* **Resultado Esperado:** Mensaje de entrada inválida y el programa sigue funcionando.
* **Resultado Obtenido:** Mostró "Entrada no valida: escribe un numero entero." y volvió a pedir la opción; el programa siguió funcionando.
* **Estado:** Satisfactorio

---

### CP-08: Registro de ambiente con nombre vacío
* **Identificador:** CP-08
* **Funcionalidad:** Validación del nombre al registrar un ambiente.
* **Datos de Entrada:** Con `AULA 1` registrada en el piso 1: opción `1` con nombre vacío (solo Enter) y piso `2`; luego opción `6` con piso `2`.
* **Resultado Esperado:** Se rechaza el nombre vacío y no se registra ningún ambiente en el piso 2.
* **Resultado Obtenido:** Mostró "No se registro: El nombre no puede estar vacio."; el filtro del piso 2 mostró "No hay ambientes registrados en el piso 2."
* **Estado:** Satisfactorio

---

### CP-09: Registro de ambiente con piso cero
* **Identificador:** CP-09
* **Funcionalidad:** Validación del piso al registrar un ambiente.
* **Datos de Entrada:** Opción `1` con nombre `AULA 2` y piso `0`; luego opción `6` con piso `0`.
* **Resultado Esperado:** Se rechaza el piso 0 y no se registra el ambiente.
* **Resultado Obtenido:** Mostró "No se registro: El piso debe ser un numero positivo."; el filtro del piso 0 mostró "No hay ambientes registrados en el piso 0."
* **Estado:** Satisfactorio

---

### CP-10: Registro de ambiente con piso no numérico
* **Identificador:** CP-10
* **Funcionalidad:** Gestión de entradas incorrectas al pedir el piso.
* **Datos de Entrada:** Opción `1` con nombre `AULA 3` y piso `ABC`; cuando el programa volvió a pedir el piso, `0`.
* **Resultado Esperado:** Mensaje de entrada inválida, el programa vuelve a pedir el piso y no se cierra.
* **Resultado Obtenido:** Con `ABC` mostró "Entrada no valida: escribe un numero entero." y volvió a pedir el piso; con `0` mostró "No se registro: El piso debe ser un numero positivo." y volvió al menú.
* **Estado:** Satisfactorio

---

### CP-11: Registro de ambiente con nombre repetido
* **Identificador:** CP-11
* **Funcionalidad:** Regla de negocio: no se repiten nombres de ambiente.
* **Datos de Entrada:** Opción `1` con nombre `AULA 1` y piso `1`; luego opción `1` con nombre `AULA 1` y piso `2`.
* **Resultado Esperado:** El primero se registra; el segundo se rechaza con un mensaje de ambiente repetido.
* **Resultado Obtenido:** El primero mostró "Ambiente registrado."; el segundo mostró "No se registro: Ya existe un ambiente con ese nombre."
* **Estado:** Satisfactorio

---

### CP-12: Agregar un elemento a un ambiente y consultarlo
* **Identificador:** CP-12
* **Funcionalidad:** Registro de elementos y consulta del inventario (opciones 2 y 3).
* **Datos de Entrada:** Opción `1` con `AULA 1` y piso `1`; opción `2` con ambiente `AULA 1`, elemento `COMPUTADOR`, cantidad actual `8`, cantidad esperada `10`, estado `BUENO`; opción `3` con `AULA 1`.
* **Resultado Esperado:** El elemento se agrega y la consulta muestra nombre, cantidad actual, cantidad esperada y estado.
* **Resultado Obtenido:** Mostró "Elemento agregado." y el inventario de AULA 1 (Piso: 1) con Nombre: COMPUTADOR, Cant. Actual: 8, Cant. Esperada: 10, Estado: BUENO.
* **Estado:** Satisfactorio

---

### CP-13: Elemento con nombre repetido en el mismo ambiente
* **Identificador:** CP-13
* **Funcionalidad:** Regla de negocio: no se repiten nombres de elemento dentro de un ambiente.
* **Datos de Entrada:** Con `COMPUTADOR` ya agregado a `AULA 1`: opción `2` con ambiente `AULA 1`, elemento `Computador`, cantidad actual `3`, cantidad esperada `5`, estado `bueno`.
* **Resultado Esperado:** Se rechaza con un mensaje de elemento repetido.
* **Resultado Obtenido:** Mostró "No se agrego: Ya existe un elemento llamado 'Computador' en el ambiente 'AULA 1'." (lo consideró repetido aunque cambian las mayúsculas).
* **Estado:** Satisfactorio

---

### CP-14: Elemento con cantidad negativa
* **Identificador:** CP-14
* **Funcionalidad:** Regla de negocio: las cantidades no pueden ser negativas.
* **Datos de Entrada:** Opción `2` con ambiente `aula 1`, elemento `mesa`, cantidad actual `-5`, cantidad esperada `20`, estado `bueno`.
* **Resultado Esperado:** Se rechaza con un mensaje de error y el elemento no se agrega.
* **Resultado Obtenido:** Mostró "No se agrego: La cantidad actual no puede ser negativa. Valor recibido: -5."
* **Estado:** Satisfactorio

---

### CP-15: Agregar un elemento a un ambiente inexistente
* **Identificador:** CP-15
* **Funcionalidad:** Regla de negocio: el ambiente debe existir.
* **Datos de Entrada:** Opción `2` con ambiente `AULA 99`, elemento `SILLA`, cantidad actual `1`, cantidad esperada `1`, estado `BUENO`.
* **Resultado Esperado:** Se rechaza con un mensaje indicando que el ambiente no existe.
* **Resultado Obtenido:** Mostró "No se agrego: El ambiente 'AULA 99' no existe."
* **Estado:** Satisfactorio

---

### CP-16: Conservación del inventario tras operaciones rechazadas
* **Identificador:** CP-16
* **Funcionalidad:** Una operación rechazada no modifica el inventario.
* **Datos de Entrada:** Después de CP-13, CP-14 y CP-15, opción `3` con ambiente `AULA 1`.
* **Resultado Esperado:** El inventario sigue igual: solo el elemento agregado al inicio.
* **Resultado Obtenido:** Mostró únicamente el elemento COMPUTADOR con Cant. Actual: 8, Cant. Esperada: 10, Estado: BUENO.
* **Estado:** Satisfactorio

---

### CP-17: Reporte de faltantes por piso
* **Identificador:** CP-17
* **Funcionalidad:** Cálculo del faltante (esperada menos actual) agrupado por piso (opción 9).
* **Datos de Entrada:** Con `AULA 1` (piso 1) y `COMPUTADOR` (actual 8, esperada 10): opción `9`.
* **Resultado Esperado:** Se muestra el faltante del elemento y el total del piso.
* **Resultado Obtenido:** Mostró "Piso 1", "AULA 1 | COMPUTADOR | esperada: 10 | actual: 8 | faltan: 2" y "TOTAL piso 1: 2".
* **Estado:** Satisfactorio

---

### CP-18: Observación con cantidad afectada mayor que la registrada
* **Identificador:** CP-18
* **Funcionalidad:** Regla de negocio: la cantidad afectada no puede superar la cantidad registrada.
* **Datos de Entrada:** Con `COMPUTADOR` (actual 8) y una observación ya registrada: opción `5` con ambiente `AULA 1`, elemento `COMPUTADOR`, tipo `FALTANTE`, cantidad afectada `50`; luego opción `7` con `AULA 1` y `S`.
* **Resultado Esperado:** Se rechaza con un mensaje de error y no se registra la observación.
* **Resultado Obtenido:** Mostró "No se registro: La cantidad afectada (50) supera la cantidad registrada (8)."; el listado de pendientes mostró solo la observación anterior (Cant: 2).
* **Estado:** Satisfactorio