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
* **Descripción:** Comprobar que el sistema valide entradas nulas o vacías en el nombre del ambiente.
* **Datos de Entrada:**
  * Nombre: `""` (cadena vacía) o `null`
* **Resultado Esperado:** El sistema no procesa la solicitud y muestra advertencia sobre nombre de ambiente inválido.
* **Resultado Obtenido:** Se valida el dato de entrada y se muestra mensaje de error sin que colapse el programa.
* **Estado:** PASÓ (Aprobado)