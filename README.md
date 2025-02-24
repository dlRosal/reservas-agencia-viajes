# 🚀 Proyecto Final - Microservicios para Agencia de Viajes

### 📌 Descripción
Este proyecto consiste en una **aplicación de gestión de reservas de viajes** desarrollada con **Spring Boot** y basada en **arquitectura de microservicios**. Permite a los usuarios **reservar vuelos y hoteles** de forma integrada, asegurando escalabilidad y mantenimiento eficiente.

---

## 🏗️ **Arquitectura del Proyecto**

🔹 **Microservicios:**
- **Hoteles Service** 🏨 - Gestión de hoteles disponibles.
- **Vuelos Service** ✈️ - Administración de vuelos y disponibilidad de plazas.
- **Reservas Service** 📋 - Registro y control de reservas.
- **API Gateway** 🚪 - Punto de entrada único para todas las peticiones.
- **Eureka Server** 📡 - Descubrimiento de servicios.

🔹 **Bases de Datos:**
- **Cada microservicio tiene su propia base de datos en MySQL**.
- **JPA + Hibernate** para la persistencia de datos.

🔹 **Comunicación:**
- **Spring Cloud Netflix Eureka** para el descubrimiento de servicios.
- **Spring Cloud Gateway** para centralizar las peticiones.
- **`RestTemplate` y `WebClient`** para comunicación entre microservicios.

🔹 **Seguridad:**
- **Spring Security + JWT** (próximo paso).

🔹 **Despliegue:**
- **Docker** para contenedorización (próximo paso).

---

## 🚀 **Tecnologías Utilizadas**
- **Java 17+**
- **Spring Boot 3+**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **Docker (próximo paso)**
- **Postman (para pruebas)**

---

## 📁 **Estructura del Proyecto**
```
📦 proyecto-agencia-viajes
 ┣ 📂 hoteles-service
 ┃ ┣ 📂 src/main/java/org/example/hotelesservice
 ┃ ┣ 📜 HotelesServiceApplication.java
 ┣ 📂 vuelos-service
 ┃ ┣ 📂 src/main/java/org/example/vuelosservice
 ┃ ┣ 📜 VuelosServiceApplication.java
 ┣ 📂 reservas-service
 ┃ ┣ 📂 src/main/java/org/example/reservasservice
 ┃ ┣ 📜 ReservasServiceApplication.java
 ┣ 📂 apigateway
 ┃ ┣ 📂 src/main/java/org/example/apigateway
 ┃ ┣ 📜 ApiGatewayApplication.java
 ┣ 📂 eurekaserver
 ┃ ┣ 📂 src/main/java/org/example/eurekaserver
 ┃ ┣ 📜 EurekaServerApplication.java
 ┣ 📜 README.md
 ┣ 📜 .gitignore
 ┗ 📜 pom.xml
```

---

## ⚡ **Cómo Ejecutar el Proyecto**
### **1️⃣ Prerrequisitos**
✔ Tener **Java 17+** instalado.
✔ Tener **MySQL** en ejecución y crear las bases de datos:
```sql
CREATE DATABASE hoteles_db;
CREATE DATABASE vuelos_db;
CREATE DATABASE reservas_db;
```
✔ Tener **Maven** instalado y configurado.

### **2️⃣ Pasos para Ejecutar**
1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tuusuario/proyecto-agencia-viajes.git
   cd proyecto-agencia-viajes
   ```
2. **Ejecutar Eureka Server:**
   ```bash
   cd eurekaserver
   mvn spring-boot:run
   ```
3. **Ejecutar los Microservicios:**
   ```bash
   cd hoteles-service && mvn spring-boot:run
   cd vuelos-service && mvn spring-boot:run
   cd reservas-service && mvn spring-boot:run
   ```
4. **Ejecutar API Gateway:**
   ```bash
   cd apigateway
   mvn spring-boot:run
   ```

---

## 📌 **Pruebas con Postman**
Una vez en ejecución, puedes probar los endpoints con **Postman o cURL**:

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `GET` | `http://localhost:8080/hoteles` | Listar hoteles |
| `GET` | `http://localhost:8080/vuelos` | Listar vuelos |
| `GET` | `http://localhost:8080/reservas` | Listar reservas |
| `POST` | `http://localhost:8080/reservas` | Crear una reserva |

Ejemplo de `POST /reservas`:
```json
{
  "cliente": "Juan Pérez",
  "idHotel": 1,
  "idVuelo": 2,
  "total": 300.00
}
```

---

## 🔥 **Mejoras Futuras**
✅ Implementar **Spring Security + JWT** para autenticación. 🔐
✅ Contenerizar con **Docker** para despliegue más eficiente. 🐳
✅ Desarrollar una interfaz gráfica (React o Angular). 🎨

---

## 🤝 **Contribuciones**
¡Las contribuciones son bienvenidas! Si encuentras un error o quieres mejorar algo, abre un **issue** o envía un **pull request**. 🚀

---

## 📜 **Licencia**
Este proyecto está bajo la licencia **MIT**.

👨‍💻 **Desarrollado por [Álvaro del Rosal](https://github.com/tuusuario)**

