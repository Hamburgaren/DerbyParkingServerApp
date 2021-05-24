# Assignment in Serverprogrammering class Java20


## Participants: 
Tommi Ahonen, Mehrdad Badeie, Thomas Haraldsson

## Requirements for compiling project:
Apache Maven has to be installed.

## How to compile project from command line:
```
ant
```

How to create database from Derby's IJ interactive scripting tool:

```
connect 'jdbc:derby://localhost:50000/parkingDB; create=true';
```

Also the following section of XML code needs to be added into the file C:\[installation directory of Wilfly]\standalone\configuration\standalone.xml and inside the <datasources> tag:

```
<datasource jndi-name="java:/DerbyParkingDatabase" pool-name="parkingDB" enabled="true" use-java-context="true">
	<connection-url>jdbc:derby://localhost:50000/hibernate</connection-url>
	<driver>derbyclient.jar</driver>
	<security>
		<user-name>APP</user-name>
		<password>APP</password>
	</security>
</datasource>
```

## Runtime requirements:

Currently requires an Apache Derby database server to be running on port 50000. 

That server also has to have an existing database called 'hibernate' (we should change this). 

No password is required to access this database at the moment.