# RockWeb

RockWeb can be imported as a maven project and uses a maven-wrapper which can be installed using:

    mvn -N io.takari:maven:wrapper

upon installing the following command can be used to start the webserver (Unix)

    ./mvnw spring-boot:run

Or (Windows)

    ./mvnw.cmd spring-boot:run

By default RockWeb expects a postgres database named 'rockweb'.

This among other settings can be modified at src/main/resources > application.properties

necessary tables and sequences will be created upon startup.


A postman collection with all usable API calls is availble at src/main/resources > rockweb.postman_collection.json

please run createArtists and createSongs first to make full use of API
