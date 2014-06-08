# wicket-notes-demo

A tech demo which uses Wicket, Spring, JPA2, JSR-303 (Bean-Validation) to implement a multi-user notes app in Java

# About

This webapp provides a per user notes functionality, meaning that different users can login and create, read, update and
delete notes.

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

# How it works

# Known Issues
