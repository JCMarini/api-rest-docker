# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.2/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.2/gradle-plugin/packaging-oci-image.html)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


`**INSTRUCCIONES**`

1.- Se debe ejecutar el commando **gradle build** para crear el compilado .jar 

2.- Se debe crear la imagen docker usando el comando 
**docker build . -t api-rest-docker:v1**

3.- Se debe iniciar docker con el comando
**docker run -p 8080:8080 api-rest-docker:v1**

4.- Validar que responda el servicio colocando la url en un navegador nos debera mostrar el mensaje **Hola desde docker** 
http://127.0.0.1:8080/hello