# apache-camel-with-spring-boot

Repository for use with the Udemy course Learn Apache Camel Framework with Spring Boot

spring init --build=maven --java-version=11 --dependencies=devtools,web,actuator,camel --groupId=com.microservices --artifactId=camel-microservice-b --name=camel-microservice-a --bootVersion=2.5.7
spring init --build=maven --java-version=11 --dependencies=devtools,web,actuator,camel --groupId=com.microservices --artifactId=camel-microservice-b --name=camel-microservice-b --bootVersion=2.5.7

unzip camel-microservice-a.zip -d camel-microservice-a
unzip camel-microservice-b.zip -d camel-microservice-b

rm \*.zip

cd camel-microservice-a
mvn spring-boot:run

docker run -p 61616:61616 -p 8161:8161 rmohr/activemq:5.15.9

cd files/json
echo '{"id": 1, "from": "EUR", "to": "USD", "conversion": 10}' > test.json

cd files/xml
echo '<xml><id>1</id><from>EUR</from><to>USD</to><conversion>2</conversion></xml>' > test.xml

kafka
add to /etc/hosts
127.0.0.1 hostname of kafka (docker ps check container id)
can't we solve this some other way?
