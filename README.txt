Application with Microservice Architecture


1. Config server
2. Registry server (Eureka)
3. GateWay service (ZUUL)
4. Service with MySql DB (users-service)
5. Client service (users-dashboard service)


=============================================================

For run application use command Docker:
- docker-compose up

For using:
{gatewayhost}:4000/

 - via Service with DB
GET api/server/users
GET api/server/users/{name}

 - via Client service
GET api/client/dashboard/users
GET api/server/dashboard/users/{name}

Eureka service = localhost:9001