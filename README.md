http://localhost:8080/swagger-ui/index.html
  /v3/api-docs
  
http://localhost:8080/h2-console

http://localhost:8080/actuator/health

mvn spring-boot:build-image

docker run -it -p8080:8080 interest:0.0.1-SNAPSHOT
