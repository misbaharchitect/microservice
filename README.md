# microservice: https://github.com/misbaharchitect/microservice
spring-boot-version=2.3.12 (released on 10-Jun-2021)
https://spring.io/blog/2021/06/10/spring-boot-2-3-12-release-available-now

For making a new Microservice, download the skeleton project and add/remove dependencies based on your requirements.

web: RESTful
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

jpa: Data Access(Relational DB)
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

data-rest:
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>

h2: In-Memory DB
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>

dev-tools:
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<scope>runtime</scope>
	<optional>true</optional>
</dependency>

++++ spring-cloud starts ++++
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>Hoxton.SR11</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>

eureka-client:
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

eureka-server:
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

feign:
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

hystrix:
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>

zuul:
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>

oauth2:
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>

sleuth:
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>

zipkin:
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>

---- spring-cloud ends ----

++++ non-spring starts ++++
swagger: API Documentation
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-boot-starter</artifactId>
	<version>3.0.0</version>
</dependency>

cucumber: BDD
<dependency>
	<groupId>io.cucumber</groupId>
	<artifactId>cucumber-java</artifactId>
	<version>6.7.0</version>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>io.cucumber</groupId>
	<artifactId>cucumber-junit</artifactId>
	<version>6.7.0</version>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>io.cucumber</groupId>
	<artifactId>cucumber-spring</artifactId>
	<version>6.7.0</version>
</dependency>

---- non-spring ends ----