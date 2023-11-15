# Speakr - Backend
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
[![License](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](https://mit-license.org/)

Este es el backend de la aplicación móvil Speakr.

El backend se encarga de gestionar los vídeos musicales de la comunidad de clientes de MyTube y proporcionar los servicios necesarios para la aplicación móvil.
Speakr es una aplicación que permite a los usuarios autenticarse y acceder a la colección de vídeos musicales, así como gestionar su lista de reproducción de favoritos.

## Descripción del proyecto
El backend de Speakr es una API REST que ofrece una serie de funcionalidades para los usuarios de la aplicación móvil.
Proporciona servicios para autenticación, búsqueda y visualización de vídeos musicales, administración de listas de reproducción de favoritos y gestión de usuarios.

### Funcionalidades del backend
El backend de Speakr proporciona las siguientes funcionalidades:

- :lock: Autenticación de usuarios.
- :musical_note: Búsqueda de vídeos musicales.
- :file_folder: Gestión de listas de reproducción de favoritos.
- :card_index: Access Token mediante JWT. 

## Desarrollado
Está construido utilizando las siguientes tecnologías:
- :coffee: Java 17
- :rocket: Spring Boot 3.15
- :file_folder: MySQL

## Inicio rápido
Para configurar e instalar el backend, siga estos pasos:

### Prerrequisitos
Asegúrese de tener instalado lo siguiente:
- :coffee: Java
- :file_folder: MySQL
- :file_cabinet: Generar la [base de datos](https://drive.google.com/file/d/1uGDIiAAWEu7zwJAZB4eEHtbPfzcFdSVJ/view?usp=sharing) utilizada

### Instalación
1. Clonar el repositorio del backend:
   
```git
git clone https://github.com/Galder0/Reto1_grupo2_SpringBoot.git
```
2. Importar el archivo como un proyecto Maven.
3. Modificar el archivo `application.properties` con la configuración necesaria, como las credenciales de la base de datos y otros ajustes específicos del entorno.
4. Ejecutar el proyecto.

## Documentación de la API
La documentación de la API estará disponible a través de Swagger en la URL generada.
```url
http://localhost:8080/swagger-ui/index.html#/
```
Asegúrese de que el servidor esté en funcionamiento para acceder a la documentación. 
Tenga en cuenta que la mayoría de los métodos no serán accesibles a menos que esté autenticado.

## Contacto
Para cualquier consulta o soporte relacionado con el backend de Speakr, puede ponerse en contacto con los desarrolladores:
- :computer:  [Ager](ager.algortape@elorrieta-errekamari.com)
- :computer:  [Galder](galder.gonzalez-balsiz@elorrieta-errekamari.com)
- :computer:  [Ander](ander.lopezdevallejohi@elorrieta-errekamari.com)

## Licencia
Distribuido bajo la Licencia MIT
