# E-Commerce API - Módulo de Favoritos e Historial

Backend desarrollado en **Spring Boot** para la gestión de productos favoritos, catálogo de productos e historial de auditoría de clientes en una plataforma de comercio electrónico.

---

## 🛠️ Tecnologías y Requisitos de Entorno

* **Java 21** (Versión Mínima Requerida)
  > ⚠️ **Nota de compatibilidad:** El proyecto está compilado y estructurado utilizando las características de Java 21. Para compilar y ejecutar la aplicación de forma correcta, es mandatorio disponer de un JDK 21 o superior configurado en el entorno local.
* **Spring Boot 3.x**
* **Spring Data JPA** (Persistencia de datos con Hibernate)
* **Lombok** (Optimización de código con `@Data` y `@RequiredArgsConstructor`)
* **MySQL** (Motor de Base de Datos exclusivo del proyecto)
* **Maven** (Gestor de dependencias)

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura limpia estructurada en capas, garantizando el desacoplamiento, la modularidad y la fácil mantenibilidad del código:

* **Controller:** Capa expuesta que define los endpoints REST de la aplicación e intercepta las peticiones HTTP (`FavoriteController`, `HistoryController`, `ProductController`).
* **Service:** Contiene la lógica de negocio, validaciones de stock, auditorías y procesamiento de datos (`FavoriteService`, `HistoryService`, `ProductService`).
* **Repository:** Capas de abstracción de datos que extienden de `JpaRepository` para interactuar con MySQL.
* **Entity:** Modelado de las tablas relacionales de la base de datos (`Favorites`, `Customers`, `Products`, `FHistory`).
* **DTO (Data Transfer Objects):** Objetos de transferencia de datos utilizados para desacoplar las entidades de las respuestas e ingresos HTTP (`FavoriteRequestDTO`, `FavoriteResponseDTO`, `ProductDTO`, `HistoryResponseDTO`, `HttpGlobalResposeDTO`).

---

## 🚀 Configuración e Instalación

### Prerrequisitos
1. **Java JDK 21** instalado correctamente y configurado en las variables de entorno (`JAVA_HOME`).
2. **Maven** instalado y mapeado en el sistema.
3. Servidor de **MySQL** activo y corriendo localmente.

### Pasos para Ejecutar
1. Clonar o descargar el repositorio del proyecto en tu máquina local.
2. Crear una base de datos en tu servidor MySQL con el nombre exacto de **`Ecomerce`**.
3. La configuración de conexión ya se encuentra preestablecida centralizadamente en el archivo de propiedades **`src/main/resources/application.yaml`** de la siguiente manera:
   ```yaml
   spring:
     application:
       name: ecomerce
     datasource:
       url: jdbc:mysql://localhost:3306/Ecomerce
       username: root
       password: tu_contraseña_aqui  # Asegúrate de colocar tu contraseña local de MySQL
       driver-class-name: com.mysql.cj.jdbc.Driver

     jpa:
       hibernate:
         ddl-auto: none  # Indica que las tablas ya deben estar creadas en la base de datos
       show-sql: true    # Imprime en la consola de logs las consultas SQL en ejecución
       properties:
         hibernate:
           "[format_sql]": true  # Formatea las sentencias SQL impresas en consola para legibilidad

   server:
     port: 1234  # Puerto de escucha de la aplicación
Abrir una terminal en la raíz del proyecto, limpiar el caché y compilar las dependencias utilizando Maven:

Bash
mvn clean install
Iniciar la aplicación:

Bash
mvn spring-boot:run
La API se desplegará y estará escuchando a través del puerto configurado por defecto (1234).

📑 Documentación de la API (Endpoints)
1. Módulo de Productos (ProductController)
Obtener Catálogo

Ruta: GET /product/get-catalog

Descripción: Retorna la lista completa de todos los productos disponibles en la tienda con su stock y marca correspondiente mapeados en un ProductDTO.

2. Módulo de Favoritos (FavoriteController)
Agregar a Favoritos

Ruta: POST /favorites/add-favorites

Cuerpo de la Petición (JSON):

JSON
{
    "idCustomer": 1,
    "idProduct": 2
}
Descripción: Asocia un producto a la lista de favoritos de un cliente persistiendo internamente la fecha y hora exacta del registro, y genera automáticamente un movimiento de tipo "Agregado" en el historial de auditoría.

Listar Favoritos de un Cliente

Ruta: GET /favorites/get-favorite-customer/{id}

Descripción: Recupera todos los productos marcados como favoritos usando el ID del cliente pasado por parámetro (@PathVariable). Realiza la validación de stock del producto para generar alertas dinámicas.

Eliminar de Favoritos

Ruta: DELETE /favorites/delete-favorite/{id}

Descripción: Remueve la relación de favoritos de la base de datos a partir de su ID único de favorito, registrando paralelamente un movimiento de tipo "Eliminado" en el historial para fines de auditoría.

3. Módulo de Historial (HistoryController)
Obtener Historial de un Cliente

Ruta: GET /history/Customer/{id}

Descripción: Devuelve de forma cronológica todas las acciones de auditoría ("Agregado", "Eliminado") que el cliente ha realizado sobre su lista de productos favoritos.

🧪 Pruebas del Sistema (Postman)
Para validar la funcionalidad de los componentes expuestos:

Asegúrate de que las tablas customers y products tengan registros de prueba idóneos creados directamente en MySQL.

Configura las peticiones en Postman respetando los métodos HTTP (GET, POST, DELETE) descritos anteriormente.

Para la petición POST, activa la opción body -> raw -> JSON e incluye obligatoriamente el encabezado Content-Type: application/json.