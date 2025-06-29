# API

## How to Run
### If you want to use Spring Boot App locally instead of the container
Change the **application.properties** folder to the following:
```
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto = create-drop

# influxDB
spring.influx.url=https://localhost:8086
spring.influx.user=admin
spring.influx.password=admin
spring.influx.database=mydb

# RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=growmate
spring.rabbitmq.password=growmate

# Connection to Queues
rabbitmq.exchange.name=amq.topic
rabbitmq.air.humidity.queue.name=humidity
rabbitmq.soil.humidity.queue.name=soil
rabbitmq.air.temperature.queue.name=temperature

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=0
```

### If you want to use the docker compose file


1. Inside the Spring Boot App folder
 ```
mvn clean install -Dmaven.test.skip=true
 ```

2. In the same folder as the docker-compose.yml file
```
docker compose up
```
