# Assignment in Serverprogrammering class Java20 at Yrgo/Göteborg.


## Participants: 
Tommi Ahonen, Mehrdad Badeie, Thomas Haraldsson

## Requirements compiling and running project:
 - Apache Ant
 - Apache WildFly
 - Apache Derby must be running on port 50000

## How to compile project from command line:
```
ant
```



## How to create database

How to create database from Derby's IJ interactive scripting tool: `connect 'jdbc:derby://localhost:50000/parkingDB; create=true';`


Also the following section of XML code needs to be added into the file C:\[installation directory of Wildfly]\standalone\configuration\standalone.xml and inside the <datasources> tag:

```
<datasource jndi-name="java:/DerbyParkingDatabase" pool-name="ParkingDatabase" enabled="true" use-java-context="true">
	<connection-url>jdbc:derby://localhost:50000/parkingDB</connection-url>
	<driver>derbyclient.jar</driver>
	<security>
		<user-name>APP</user-name>
		<password>APP</password>
	</security>
</datasource>
```

You should now be able to login to the Wildfly administration page from your web-browser and open the Configuration --> Subsystems --> Datasource&Driver --> Datasources --> 'ParkingDatabase'. And if you click on 'ParkingDatabase' you should be able to choose "Test connection" and that should display the text "Successfully tested connection for datasource ParkingDatabase.".
