# Conversor de Monedas

Este es un conversor de monedas que utiliza la API de ExchangeRate-API para realizar conversiones entre diferentes monedas de forma sencilla y rápida. El programa permite consultar las monedas disponibles, convertir cantidades entre dos monedas y obtener la tasa de cambio actualizada.

## Funcionalidades

1. **Ver Monedas Disponibles**: Muestra una lista de todas las monedas soportadas por la API.
2. **Convertir Moneda**: Permite seleccionar una moneda de origen, una de destino y la cantidad que deseas convertir.
3. **Interfaz de Consola**: El programa se ejecuta en la terminal o consola, proporcionando un flujo interactivo.

## Fases del Proyecto

Este proyecto se ha dividido en varias fases para lograr un mejor entendimiento del uso de las clases HttpClient, HttpRequest e HttpResponse de Java:

### Fase 4: Uso de HttpClient
Se emplea la clase `HttpClient` para realizar solicitudes a la API de tasas de cambio y obtener datos esenciales.

### Fase 5: Uso de HttpRequest
Se utiliza la clase `HttpRequest` para configurar y personalizar las solicitudes a la API de tasas de cambio.

### Fase 6: Uso de HttpResponse
Se enfoca en el uso de la interfaz `HttpResponse` para gestionar las respuestas recibidas de la API.

## Requisitos

- **Java 17** o superior.
- **Maven** para gestionar dependencias.
- Una clave de API válida de [ExchangeRate-API](https://www.exchangerate-api.com/).
