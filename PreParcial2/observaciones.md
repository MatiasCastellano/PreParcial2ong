# Observaciones - CASTELLANO

## Problemas encontrados:

### 1. Modelo Donacion.java - Línea 15
- **Problema**: El campo `nombre` tiene longitud limitada a 20 caracteres, pero el enunciado especifica VARCHAR(100)
- **Línea**: `@Column(name="donor_name", nullable = false, length = 20)`

### 2. Modelo AsignacionDonacion.java - Línea 8
- **Problema**: Espacio extra en el nombre de la tabla que puede causar errores
- **Línea**: `@Table(name="donation_assignment ")`

### 3. Modelo Donacion.java - Línea 47
- **Problema**: Asignación duplicada de fecha en el constructor
- **Línea**: `this.fecha=fechaDon;` (aparece dos veces)

### 4. Service Logica.java - Línea 67
- **Problema**: Persistencia duplicada de la asignación
- **Línea**: `session.persist(nuevaAsignacion);` (aparece dos veces)

### 5. Service Logica.java - Línea 32-38
- **Problema**: Creación innecesaria de objeto Donacion vacío cuando existe constructor con parámetros
- **Líneas**: Se crea un objeto vacío y luego se setean todos los campos individualmente

### 6. Tests LogicaTest.java - Línea 1
- **Problema**: Import innecesario que no se utiliza
- **Línea**: `import net.bytebuddy.asm.Advice;`

### 7. Tests LogicaTest.java - Línea 35
- **Problema**: Error de tipeo en la categoría
- **Línea**: `donacion1.setCategoria("eduacion");` (debería ser "educacion")

### 8. Main.java - Línea 30
- **Problema**: Asignación por defecto sin validación de entrada
- **Línea**: `tipoEnum = Donacion.Tipo.INDIVIDUAL; //por defecto se asigna individual`

### 9. Service Logica.java - Línea 95
- **Problema**: Error en la consulta CriteriaBuilder - comparación incorrecta con enum
- **Línea**: `cb.equal(donacion.get("estado"), Donacion.Estado.RECEIVED)` debería usar el enum directamente 