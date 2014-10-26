A Scala Play2 based RESTful API Server with AngularJS Front-end
===============================================================


####1. PostgreSQL DB with "company and "computer" tables 


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
```


####2. Play2 RESTful API Server providing CRUD operations on "company" and "computer" table


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
