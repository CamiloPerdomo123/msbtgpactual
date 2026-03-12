Prueba Técnica – Ingeniero de Desarrollo Back End
Juan Camilo Perdomo Carreño 


Tecnologías Utilizadas
•	Java 21
•	Spring Boot
•	Spring Data MongoDB
•	Gradle
•	MongoDB
•	JUnit 5
•	Mockito
•	Docker
•	AWS CloudFormation

Reglas de Negocio
•	El cliente inicia con un saldo inicial de COP $500.000
•	Cada fondo tiene un monto mínimo de vinculación
•	El cliente puede invertir un monto mayor al mínimo
•	Si el cliente no tiene saldo suficiente se retorna un error
•	Al cancelar una suscripción se devuelve el monto invertido
•	Cada operación genera una transacción única
•	Se envía notificación según la preferencia del cliente (Email o SMS)


Despliegue con AWS CloudFormation
El backend puede desplegarse en AWS utilizando CloudFormation, permitiendo crear la infraestructura automáticamente.
La infraestructura incluye:
•	Instancia EC2
•	Docker
•	Ejecución del backend en contenedor
Para desplegar:
aws cloudformation create-stack \
--stack-name mspactual-backend \
--template-body file://cloudformation.yml

POSTMAN

01. GUARDA UN CLIENTE 

POST: http://localhost:8080/api/mspactual/clientes
REQUEST
{
    "cedula": "1007674846",
    "nombre": "Juan Camilo",
    "preferenciaNotificacion": "Email",
    "emailOrSms": "Jperdomo@gmail.com"
}

02. SUSCRIPCION

POST: http://localhost:8080/api/mspactual/fondos/suscribir
REQUEST

{
    "clienteId": "66818fd2-9364-4f8b-9768-b6fa55c00ab3",
    "fondoId": "1",
    "monto": 76000
}

03. CANCELAR_SUSCRIPCION

POST: http://localhost:8080/api/mspactual/fondos/cancelar
REQUEST

{
    "clienteId": "66818fd2-9364-4f8b-9768-b6fa55c00ab3",
    "fondoId": "1"
}

04. OBTENER_TRANSACCIONES

GET: http://localhost:8080/api/mspactual/fondos/transacciones/663ced23-4a0a-438b-90a7-c17294eb815d














