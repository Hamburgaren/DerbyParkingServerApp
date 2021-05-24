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
connect 'jdbc:derby://localhost:50000/parkingDB; create=true';


## Runtime requirements:

Currently requires an Apache Derby database server to be running on port 50000. 

That server also has to have an existing database called 'hibernate' (we should change this). 

No password is required to access this database at the moment.