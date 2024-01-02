# capitole-challenge

Este proyecto consiste en una aplicación/servicio desarrollado en Spring Boot que proporciona un endpoint REST para consultar información de precios de productos en la base de datos de comercio electrónico de la compañía.

La API acepta parámetros de entrada, incluyendo la fecha de aplicación, identificador de producto y la cadena a la que pertenece el producto. Devuelve datos relevantes, como el identificador de producto, la cadena, la tarifa aplicada, las fechas de aplicación y el precio final.

## Requisitos Previos

Asegúrate de tener instalado lo siguiente antes de ejecutar la aplicación:

Java (11) compatible con 17 
Maven (3.8.4)
Base de datos H2 (integrada en memoria)
Spring boot 2.7

## Configuración del Proyecto

1. Clona el repositorio:
   ```bash
  https://github.com/Mitadiel/capitole-challenge.git

Base de Datos
La base de datos H2 se inicializa con los datos de ejemplo proporcionados.

Construcción y Ejecución
Para construir y ejecutar la aplicación, utiliza el siguiente comando:

mvn spring-boot:run

Uso de la api

curl --location 'http://localhost:8080/v1/api/prices/query?applicationDate=2020-06-14T11%3A00%3A00&productId=35455&brandId=1'

Ejemplo de respuesta
{
    "id": 1,
    "brand": {
        "id": 1,
        "name": "ZARA",
        "description": "Consumer electronics and technology company"
    },
    "start_date": "2020-06-14T00:00:00",
    "end_date": "2020-12-31T23:59:59",
    "price_list": 1,
    "product": {
        "id": 35455,
        "code": "35455",
        "name": "Smartphone Samsung",
        "sku": "SNG-789"
    },
    "priority": 0,
    "price": 35.50,
    "currency": "EUR"
}

Testing

Test de integración y validaciones solicitadas
Se encuentran en la capa de bootLoader

<img width="1091" alt="image" src="https://github.com/Mitadiel/capitole-challenge/assets/32845447/eb3774ea-2865-41c4-81aa-040625472b53">

Test unitarios
Se encuentran en la capa de dominio
<img width="1111" alt="image" src="https://github.com/Mitadiel/capitole-challenge/assets/32845447/1c4b2b6f-4394-4b5b-b375-1d23ec55c22c">


