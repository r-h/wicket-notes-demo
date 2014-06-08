# wicket-notes-demo

A tech demo which uses Wicket, Spring, JPA2, JSR-303 (Bean-Validation) to implement a multi-user notes app in Java

# About

This webapp provides a per user notes functionality, meaning that different users can login and create, read, update and
delete notes.

Beside that the notes can be sorted by their attributes and are presented in a pageable result table.

The webapp was written using [Apache Wicket](http://wicket.apache.org/) to demonstrate a component oriented webapp using
POJOs which use [JSR-303 Annotations](http://beanvalidation.org/1.0/spec/) for validation constraints.

The POJOs are loaded and persisted using [JPA2](https://jcp.org/en/jsr/detail?id=317) with [Hibernate ORM](http://hibernate.org/)
as the persistence provider.

The [Spring Framework](http://projects.spring.io/spring-framework/) was used for dependency injection and transaction management.

# Prerequisites

You will need :

a JDK >= 6, like [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK](http://openjdk.java.net/install/index.html),

[Maven >= 3](http://maven.apache.org/download.cgi), 

a working internet connection, so that maven can download missing deps.
 
An IDE is recommended, but not required
e.g. [Eclipse](http://www.eclipse.org/) with [Maven-Plugin](https://www.eclipse.org/m2e/), [Qwickie Plugin](https://code.google.com/p/qwickie/)

# Building and running
After cloning this repo, in the project folder do a

`    mvn clean package`

to compile and package the project.

The resulting WAR under the target folder should run in virtually every
servlet container that supports servlet spec 2.4

To run the app in place, do a 

`    mvn jetty:run`

you then should be able to connect with your web-browser
to 'http://localhost:8080/'

Alternatively you can run the project from your favorite IDE
by running the `io.github.r_h.wicket_notes_demo.Start`-class.

Currently there are only two predefined users:
`admin` with p/w `admin`
and `test` with p/w `test`

# How it works

The application consists of three main components, namely :
- `LoginPage`, which is responsible for the user login,
- `NotesPage` which contains a pageable and limited result listing for all notes,
- `NotePage` which is used to create and edit notes and also gives a detailed view in a single note.

The login  is done in a form which ensures via the mentioned JSR-303 annotations that the entered user and
password information is not empty and between one and twenty characters long.

After checking that 

# Known Issues

Persistence is only done in-memory (can be changed configuration-wise in `persistence.xml`)

No authorisation is implemented (every user can edit/delete every note)

Error-handling is completly omitted for the sake of simplicity

There is no log-out
