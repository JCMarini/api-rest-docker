# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo jar construido desde tu proyecto local al contenedor
COPY build/libs/api-rest-docker-0.0.1-SNAPSHOT.jar api-rest-docker.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar el archivo jar
ENTRYPOINT ["java", "-jar", "api-rest-docker.jar"]