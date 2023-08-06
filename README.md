# Módulo Raíz

## Introducción

Este módulo es la raiz de un proyecto multiproyecto. Se inspira en los conceptos de arquitectura límpia de Robert C. Martin y en la arquitectura hexagonal de Alistair Cockburn. Genera una API Rest en Kotlin que permite realizar CRUD's sobre tablas y/o consultas sobre vistas.

##📜 Diagrama de contexto
## Structure of the multi module project

<img style="display: block; margin: 0 auto" alt="Clean Architecture" src="https://imgur.com/H0rIi8S.png"/>

Mapping of projects in Amiga projects to the layers of the clean Architecture (we take the &#956;Service D (Mako) of the blueprints like an example):

|**Module**|**Clean Architecture Layer**|**Comments**|
|--- |--- |--- |
|![#52dd4e](https://via.placeholder.com/15/52dd4e/52dd4e.png) *mako-rrhh-boot*|Infra Layer|Main project. Configuration and transversal services.|
|![#52dd4e](https://via.placeholder.com/15/52dd4e/52dd4e.png) *mako-rrhh-api-rest*|Infra Layer|Primary Adapters. One project for protocol technology REST. Controlers, routes, caches, etc.|
|![#52dd4e](https://via.placeholder.com/15/52dd4e/52dd4e.png) *mako-rrhh-api-rest-clients*|Infra Layer|Primary Adapters. One project for protocol technology REST. Controlers, routes, caches, etc.|
|![#7DE77B](https://via.placeholder.com/15/7DE77B/7DE77B.png)  *mako-rrhh-api-model*| | |
|![#fffd54](https://via.placeholder.com/15/fffd54/fffd54.png) *mako-rrhh-model*|Domain Layer|Enterprise Bussiness Logic. Core domain and logic. Logic that can be shared between use cases. |
## 🚀Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The prerequisites to be able to run and develop this microservice locally are:
- Have a version of Java JDK 11 or higher installed. This version must have the Inditex certificates installed.
- Have the last version of Kotlin compiler.
- Have an installed version of Maven 3.6.3.
- Have a git client. You must configure the signature for your commits. 
- Have WSL2 (Windows Subsystem Linux) to be able to lift the infrastructure containers.
- And an IDE like: Eclipse, IntelliJ, Visual Studio Code, etc. Or any IDE or editor of the developer's preference.

[Link to alternative guide](https://github.com/inditex/doc-sgadev/wiki/Configuraci%C3%B3n-de-Workspace#docker-desktop) that explains different custom configurations in Inditex for legacy apps in Java 8. Please, use this link only for complete information that you can't find or not understand in the previos links.

## Lenguajes, Frameworks y librerías utilizados

La aplicación genera código en Kotlin para la parte de código que se utiliza en explotación. Y se genera código en Spock (es un DSL en Groovy) para la generación de los test.
Se utiliza Spring Core, Spring Web, Spring Security y Spring JDBC en la capa
de infraestructura, así como Hibernate Validations, Swagger etc.

## Arquitectura

La arquitectura desacopla el negocio de librerías y frameworks, de manera que estos se utilicen desde negocio a través de interfícies que implementan un contrato. Pero el negocio en ningún momento se acopla a frameworks ni librerías, esto permite realizar tests unitarios de manera muy sencilla.

## Módulos de la aplicación

La aplicación (mako, este es un nombre de ejemplo) consta de los siguientes módulos/proyectos:

```
- CleanArchitecture    <- Carpeta donde tenemos el workspace
    |-> clean-architecture-generator  <- Proyecto que tiene las definiciones de entidades
    |-> clean-architecture-workingset  <- Proyecto donde están las plantillas del conjunto de generación de código.
    |->Mako
        |-> commons-domain <- Módulo común donde están las clases de negocio reaprovechables por más API's o aplicaciones
        |-> commons-infrastructure <- Módulo común donde están las clases comunes de infraestructura reutilizables por más API's o aplicaciones.
        |-> mako-rrhh-adapter <- Este sería el módulo/jar donde están las clases de infrastructure del paquete rrhh. Aquí irían los repositorrios y servicios de infraestructura (correo, sms, colas, buses, etc).
        |-> mako-rrhh-app <- Este sería el proyecto principal. El nombre 'mako' es de ejemplo es configurable en las definiciones del proyecto generador (clean-architecture-generator)
        |-> mako-rrhh-config <- Este sería el proyecto donde se configura todo lo referente al framework, librerias etc, de infraestructura que se vayan a utilizar.
        |-> mako-rrhh-domain <- Este sería el módulo/jar donde está el dominio propio de mako del paquete de rrhh. Es lógica de negocio que aplica a toda la aplicación o incluso a toda la organización (diferentes aplicaciones).
        |-> mako-rrhh-usecases <- Este sería el módulo/jar donde están las clases con el negocio de los casos
        de uso del módulo rrhh ().
```

## Los proyectos propios de la aplicación:

```
commons-domain
commons-infrastructure
mako-rrhh-app
mako-rrhh-adapter
mako-rrhh-config
mako-rrhh-domain
mako-rrhh-usecases
```


## Instalación

Basta con descargar los proyectos:

```
commmons-domain
commmons-infrastructure
mako-rrhh-app
mako-rrhh-adapter
mako-rrhh-config
mako-rrhh-domain
mako-rrhh-usecases
```

Cuando se descarga por primera vez hay que hacer dentro de cada uno de los proyectos:

```
gradle clean
gradle publishToMavenLocal
```

Luego si sólo se quiere compilar:
```
gradle build
```

Si se quiere compilar sin ejecutar tests:
```
gradle build -x test
```

Aunque como esta aplicación es multiproyecto es aconsejable utilizar el publishToMavenLocal para
que los cambios esten disponibles al resto de módulos.

Si no se hace gradle no encontrará en nuestro repositorio de maven esta librería.


## Crear la BD en local

La base de datos en local se puede crear con un docker-compose que se encuentra en la carpeta docker. Parecido al siguiente bloque:

```
version: '3'
services:
  PostgreSQL-DB-LOCAL:
    image: 'postgres:latest'
    restart: always
    volumes:
      - './postgres_data:/var/lib/postgresql/data'
    environment:
      - POSTGRES_DB=mako
      - POSTGRES_USER=mako
      - POSTGRES_PASSWORD=mako
    ports:
      - '5432:5432'
```

Luego conectándonos con un cliente SQL como el DBeaver crearíamos el schema rrh.
Y después de realizar eso podremos lanzar la tarea de gradle de liquibase que nos creará las tablas y nos pondrá datos con el comando:

```
gradle task liquiDDEV update
```

También podemos hacerlo con la BD que tenemos de test sustituyendo liquiDEV por liquiTEST.

## Arrancar la aplicación

Como este proyecto utiliza Spring Boot, para arrancar la aplicación basta con ejecutar con gradle
el siguiente comando dentro de la carpeta mako-rrhh-app:

```
gradle bootRun
```

Arrancar la aplicación indicando que qeuremos utilizar el entorno dev:

```
gradle bootRun -PjvmArgs="-Dspring.profiles.active=dev"
```


Arrancar la aplicación indicando que qeuremos utilizar el entorno pre:

```
gradle bootRun -PjvmArgs="-Dspring.profiles.active=pre"
```

Etc.