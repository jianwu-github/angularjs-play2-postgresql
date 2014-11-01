A Scala Play2 based RESTful API Server with AngularJS Front-end
===============================================================


This is a Demo Web Application with AngularJS as Front-end, A Scala Play2 based RESTful API Server with PostgreSQL DB as Back-end. 


####1. PostgreSQL DB with "company and "computer" tables and data

This Demo App assumes a local PostgreSQL DB running on Port 5432, the db schema (tables with data) need be pre-created with "sql/app_db.sql" script. The following is the table definition used in app:

```
CREATE TABLE company (
  id    bigint NOT NULL,
  name  varchar(255) NOT NULL,
  constraint company_pkey primary key (id)
);

CREATE TABLE computer (
    id              bigint NOT NULL,
    name            varchar(255) NOT NULL,
    manufacture_id  bigint NOT NULL,
    CONSTRAINT computer_pkey PRIMARY KEY (id)
);

ALTER TABLE computer ADD CONSTRAINT manufacture_fk 
  FOREIGN KEY (manufacture_id) REFERENCES company(id);

... ...

```


####2. Play2 RESTful API Server 

Play2 RESTful API Server is using [Slick](http://slick.typesafe.com/) to access PostgreSQL DB Table "company" and "computer", it exposes CRUD operations on db tables as RESTful API as:  

```
  GET     /companies                 
  GET     /companies/:id              
  POST    /companies                  
  PUT     /companies/:id              
  
  GET     /computers                  
  GET     /computers/:id              
  POST    /computers                  
  PUT     /computers/:id              

  GET     /companies/:id/computers
``` 


####3. AngularJS Front-end

The Home Page of Demo App is rendered with Play2 Scala HTML Templates with AngularJS (UI-Router), it has a "CompanyPreviews" Page, which will use AngularJS $http to make REST API Call (/companies) to fetch and display a list of companies using AngularJS ngTable:

![](about_app/previewcompanies.png?raw=true)

When clicking on the "List of Computers" link, Demo App will use AngularJS $http to make REST API Call (/companies/:id/computers) to fetch and display a list of computers produced by associated company:

![](about_app/company_computers.png?raw=true)


####4. Setup and Run Demo App

1. Clone the GitHub Repository to your local machine
2. Install local PostgreSQL DB and run "sql/app_db.sql" with psql
3. Under local cloned project home directory, start activator to compile and run the Demo App:

```
$ activator 
```

```
[angularjs-play2-postgresql] $ compile
```

```
[angularjs-play2-postgresql] $ run

```
