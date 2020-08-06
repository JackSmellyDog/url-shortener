# URL shortener
An implementation of an URL shortener with Spring Boot and Thymeleaf as a template engine.
The project uses embedded file based H2 database. However it can be easily changed to another vendor due to using Spring Data.

### Build
~~~sh
mvn clean install
~~~

or (with no tests)
~~~sh
mvn clean install -DskipTests
~~~

### Run
~~~sh
cd target
~~~

To run on localhost:8080

~~~sh
java -jar shortener.jar
~~~

To run with your domain address
~~~sh
java -jar -Ddomain-url=https://your.site.com shortener.jar
~~~

To run as a daemon process
~~~sh
nohup java -jar -Ddomain-url=https://your.site.com shortener.jar &
~~~
