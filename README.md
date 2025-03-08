# 🚀 Proyecto Final - Microservicios para Agencia de Viajes

### 📌 Descripción
Este proyecto consiste en una **aplicación de gestión de reservas de viajes** desarrollada con **Spring Boot** y basada en **arquitectura de microservicios**. Permite a los usuarios **reservar vuelos y hoteles** de forma integrada, asegurando escalabilidad y mantenimiento eficiente.

---

## 🏰️ **Arquitectura del Proyecto**

🔹 **Microservicios:**
- **Hoteles Service** 🏨 - Gestión de hoteles disponibles.
- **Vuelos Service** ✈️ - Administración de vuelos y disponibilidad de plazas.
- **Reservas Service** 👌 - Registro y control de reservas.
- **Auth Service** 🔒 - Registro y autenticación de usuarios.
- **API Gateway** 🚪 - Punto de entrada único para todas las peticiones.
- **Eureka Server** 📡 - Descubrimiento de servicios.

🔹 **Bases de Datos:**
- **Cada microservicio tiene su propia base de datos en MySQL**.
- **JPA + Hibernate** para la persistencia de datos.

🔹 **Comunicación:**
- **Spring Cloud Netflix Eureka** para el descubrimiento de servicios.
- **Spring Cloud Gateway** para centralizar las peticiones.
- **`RestTemplate`** para comunicación entre microservicios.

🔹 **Seguridad:**
- **Spring Security sin JWT**.
- **Autenticación con credenciales y sesiones**.
- **Control de acceso en API Gateway**.

🔹 **Frontend:**
- **Interfaz gráfica en JavaFX** para login, registro y reservas.
- **Comunicación con microservicios mediante HTTP Requests**.

🔹 **Despliegue:**
- **Docker** para contenedorización (próximo paso).

---

## 🚀 **Tecnologías Utilizadas**
- **Java 17+**
- **Spring Boot 3+**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **JavaFX** (para el frontend)
- **Docker (próximo paso)**
- **Postman (para pruebas)**

---

## 💁 **Estructura del Proyecto**
```
📆 proyecto-agencia-viajes
 ├📂 hoteles-service
 ├📂 vuelos-service
 ├📂 reservas-service
 ├📂 auth-service
 ├📂 apigateway
 ├📂 eurekaserver
 ├📂 frontend
 │   ├📂 src/main/java/org/example/frontend
 │   │   📚 LoginView.java
 │   │   📚 RegisterView.java
 │   │   📚 ReservaView.java
 │   ├📂 src/main/resources
 │   │   📚 login.fxml
 │   │   📚 register.fxml
 │   │   📚 reserva.fxml
 ├📄 README.md
 ├📄 .gitignore
 └📄 pom.xml
```

---

## ⚡ **Cómo Ejecutar el Proyecto**
### **1️⃣ Prerrequisitos**
✅ Tener **Java 17+** instalado.
✅ Tener **MySQL** en ejecución y crear las bases de datos:
```sql
CREATE DATABASE hoteles_db;
CREATE DATABASE vuelos_db;
CREATE DATABASE reservas_db;
CREATE DATABASE auth_db;
```
✅ Tener **Maven** instalado y configurado.

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
   cd auth-service && mvn spring-boot:run
   ```
4. **Ejecutar API Gateway:**
   ```bash
   cd apigateway
   mvn spring-boot:run
   ```
5. **Ejecutar el Frontend JavaFX:**
   ```bash
   cd frontend
   mvn javafx:run
   ```

---

## 📀 **Pruebas con Postman**
Una vez en ejecución, puedes probar los endpoints con **Postman o cURL**:

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `POST` | `http://localhost:8080/auth/register` | Registrar usuario |
| `POST` | `http://localhost:8080/auth/login` | Iniciar sesión |
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
✅ Implementar **Spring Security con JWT** para mejorar autenticación. 🔒
✅ Contenerizar con **Docker** para despliegue más eficiente. 🐳
✅ Optimizar el frontend con **React o Angular** en el futuro. 🎨

---

## 🤝 **Contribuciones**
¡Las contribuciones son bienvenidas! Si encuentras un error o quieres mejorar algo, abre un **issue** o envía un **pull request**. 🚀

---

## 🐝 **Licencia**
Este proyecto está bajo la licencia **MIT**.

👨‍💻 **Desarrollado por [Álvaro del Rosal](https://github.com/tuusuario)**

