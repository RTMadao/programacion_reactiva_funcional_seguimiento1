# Proyecto seguimiento 1 para el curso de programación Reactiva y funcional

Proyecto de seguimiento 1 del curso de programación reactiva y funcional haciendo uso de java 17, spring boot y gradle

El dominio del proyecto es un ejemplo simple de un software para un restaurante, que recibe pedidos en el local y para domicilios.

El servicio cuenta con 5 entidades y 2 interfaces

- __Plato__: Entidad que representa los platos del __menú__ del restaurante.
- __Pedido__: Interfaz que abstrae los diferentes tipos de pedidos que se producen en el restaurante.
- __Domicilio__: Entidad que representa uno de los tipos de __pedido__ del restaurante, aquellos que se realizan vía teléfono o internet para enviar a el domicilio de un cliente registrado.
- __OrdenLocal__: Entidad que representa uno de los tipos de __pedido__ del restaurante, aquellos que se realizan en el local directamente por lo que no es necesaria la información del cliente.
- __PlatoPedido__: Entidad que relaciona los __platos__ del menú registrados en un __pedido__, con sus respectivas cantidades.
- __Persona__: Interfaz que abstrae los diferentes tipos de personas o usuarios que se registren en el sistema.
- __Cliente__: Entidad que representa la información necesaria de un cliente para la entrega de los pedidos a domicilio.


## Peticiones del servicio

### Obtener menu 
`curl --location --request GET 'http://localhost:8080/menu/`

### Obtener plato del menu por id
`curl --location --request GET 'http://localhost:8080/menu/5`

### Obtener listado de clientes
`curl --location --request GET 'http://localhost:8080/cliente/`

### Obtener un cliente consultando por id
`curl --location --request GET 'http://localhost:8080/cliente/5`

### Obtener listado de pedidos en el local
`curl --location --request GET 'http://localhost:8080/pedido/local/`

### Obtener información de un pedido en el local consultando por id
`curl --location --request GET 'http://localhost:8080/pedido/local/5`

### Crear pedidos en el local
```
curl --location --request POST 'http://localhost:8080/pedido/local/' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "fecha": "2023-07-27T12:50:00",
    "platos": [
        {
            "plato": {
                "id": 1,
                "nombre": "Sopa",
                "descripcion": "Deliciosa sopa de hueso",
                "precio": 7000
            },
            "cantidad": 2
        },
        {
            "plato": {
                "id": 9,
                "nombre": "Gaseosa",
                "descripcion": "Refrescante Gaseosa Cocacola, cola roman",
                "precio": 3000.0
            },
            "cantidad": 2
        }
    ]
}'
```

### Modificar pedidos en el local
```
curl --location --request PUT 'http://localhost:8080/pedido/local/' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "id": 1,
    "fecha": "2023-07-27T12:50:00",
    "total": 12000.0
}'
```

### Eliminar un pedido en el local por id
`curl --location --request DELETE 'http://localhost:8080/pedido/local/5`

### Obtener información de un pedido en el local de topico de kafka
`curl --location --request GET 'http://localhost:8080/pedido/local/topico-kakfa/Pedidos-2023-08`

### Crear cola sqs
```
curl --location --request POST 'http://localhost:8080/pedido/local/aws/createQueue' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "queueName":"local-orders"
}'
```

### Publicar mensaje en cola sqs
```
curl --location --request POST 'http://localhost:8080/pedido/local/aws/postMessageQueue/local-orders' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "id": 1,
    "fecha": "2023-07-27T12:30",
    "total": 20000
}'
```

### Procesar mensaje de cola sqs por fecha y hora
```
curl --location --request POST 'http://localhost:8080/pedido/local/aws/processPedidoByFecha' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "queueName":"local-orders",
    "maxNumberMessages":10,
    "waitTimeSeconds":5,
    "fechaPedido":"2023-07-27T12:30"
}'
```

### Obtener listado de pedidos a domicilio
`curl --location --request GET 'http://localhost:8080/pedido/domicilio/`

### Obtener información de un pedido a domicilio consultando por id
`curl --location --request GET 'http://localhost:8080/pedido/domicilio/5`

### Crear pedidos a domicilio
```
curl --location --request POST 'http://localhost:8080/pedido/domicilio/' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "fecha": "2023-07-27T12:50:00",
    "identificacionCliente": "TEL_3125486791",
    "valorDomicilio": 5000.0,
    "platos": [
        {
            "plato": {
                "id": 1,
                "nombre": "Sopa",
                "descripcion": "Deliciosa sopa de hueso",
                "precio": 7000
            },
            "cantidad": 2
        },
        {
            "plato": {
                "id": 9,
                "nombre": "Gaseosa",
                "descripcion": "Refrescante Gaseosa Cocacola, cola roman",
                "precio": 3000.0
            },
            "cantidad": 2
        }
    ]
}'
```

### Modificar pedidos a domicilio
```
curl --location --request PUT 'http://localhost:8080/pedido/domicilio/' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "id": 1,
    "fecha": "2023-07-27T12:50:00",
    "identificacionCliente": "TEL_3125486791",
    "valorDomicilio": 5000.0,
    "total": 12000.0
}'
```

### Eliminar un pedido a domicilio por id
`curl --location --request DELETE 'http://localhost:8080/pedido/domicilio/5`