# 🪐 swapi-proxy

Star Wars Api consumer application built with [Java SE 11.0.15](https://www.oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html) , [Apache Maven 3.8.5](https://maven.apache.org/download.cgi) and   
Spring Boot with the following configuration:
<img width="1338" alt="Captura de Pantalla 2022-05-17 a las 18 16 54" src="https://user-images.githubusercontent.com/99420574/169096010-09b9a067-77f9-4245-b979-fc4f03507cdd.png">

## 🏗️ How to build and run
1. Clone this repository
2. Enter cloned repository folder
    * If you want to build a executable .jar --> Open a terminal here and type: **mvn package** 
        --> this will generate the .jar inside target folder where you can also open a terminal and type: **java -jar proxy-api-0.0.1-SNAPSHOT.jar** to run the application
    * If you want to simply run the app --> Open Open a terminal here and type: **mvn spring-boot:run** 


## 📬 How to make requests
1. Once the application is running (see previous steps) --> Open your favourite API Client
2. Select **GET** petition and introduce the following URL: **http://localhost:8080/swapi-proxy/person-info?name={CharacterName}**
3. Press send and check the results


