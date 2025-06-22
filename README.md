# Networking Project

Proyecto que implementa diferentes aspectos de networking usando Java, incluyendo sockets TCP/UDP, RMI y manejo de URLs.

## Descripción

Este proyecto demuestra diferentes conceptos de redes y comunicación en Java:

- Lectura y descarga de contenido desde URLs
- Servidor TCP para cálculo de cuadrados
- Servidor TCP para funciones trigonométricas 
- Servidor/Cliente UDP para servicio de hora
- Chat P2P usando RMI (Remote Method Invocation)

## Requisitos

- Java 17
- Maven
- Spring Boot 3.5.0

## Instalación

```bash
git clone https://github.com/DavidBarbosag/Networking.git
cd Networking
mvn clean install
```

## Uso

Ejecutar la aplicación:
```bash
mvn clen package
java -Djava.rmi.server.hostname=localhost -jar target/networking-0.0.1-SNAPSHOT.jar
```

**Para la ejecucion correcta se deben tener dos consolas ejecutandose al mismo tiempo, debido al funcionamineto del servicio de chat**

## Funcionalidades


1. URL Reader

* Lee contenido de URLs
* Descarga archivos HTML
* Servidor de Cuadrados (Puerto 5000)


2. Recibe un número

* Devuelve su cuadrado
* Servidor Trigonométrico (Puerto 6000)


> Soporta operaciones:
Seno (fun:sin)
Coseno (fun:cos)
Tangente (fun:tan)
Servidor/Cliente UDP (Puerto 4445)


3. Servicio de hora

* Actualizaciones periódicas

4. Chat RMI
* Comunicación P2P
* Intercambio de mensajes en tiempo real
