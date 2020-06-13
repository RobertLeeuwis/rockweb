# RockWeb

RockWeb can be imported as a maven project.
to start the webserver use (Unix)
    ./mvnw spring-boot:run

Or (Windows)
    ./mvnw.cmd spring-boot:run

By default RockWeb expects a postgres database named 'rockweb'.
This among other setting can be modified at src/main/resources > application.properties
necessary tables and sequences will be created upon startup.

A postman collection with all usable API calls is availble at
src/main/resources > rockweb.postman_collection.json

please run createArtists and createSongs first to make full use of API