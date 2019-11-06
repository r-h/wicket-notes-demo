# wicket-notes-demo [![Build Status](https://travis-ci.org/r-h/wicket-notes-demo.svg?branch=master)](https://travis-ci.org/r-h/wicket-notes-demo)

A tech demo which uses [Wicket](https://wicket.apache.org/), [Spring 4](https://spring.io/), [JPA2](https://jcp.org/en/jsr/detail?id=317), [JSR-303 (Bean-Validation)](http://beanvalidation.org/1.0/spec/) to implement a multi-user notes app in Java

# About

This webapp provides a per user notes functionality, meaning that different users can login and create, read, update and
delete notes.

![wicket-notes-demo](https://github.com/r-h/wicket-notes-demo/raw/master/wicket-notes-demo.png)

Beside that the notes can be sorted by their attributes and are presented in a pageable result table.

The webapp was written using [Apache Wicket](http://wicket.apache.org/) to demonstrate a component oriented webapp using
POJOs which use [JSR-303 Annotations](http://beanvalidation.org/1.0/spec/) for validation constraints.

For presentation of the notes in the application [jggrid-Plugin for wiquery](https://code.google.com/p/wiquery-plugins/) is used.

The POJOs are loaded and persisted using [JPA2](https://jcp.org/en/jsr/detail?id=317) with [Hibernate ORM](http://hibernate.org/)
as the persistence provider.

The [Spring Framework](http://projects.spring.io/spring-framework/) was used for dependency injection and transaction management.

# Prerequisites
## to have a look at the code and try things out

You will need :

## If you dont want to install anything locally...

A [github.com account](https://github.com/login?return_to=%2Fr-h%2Fwicket-notes-demo) (if you dont have one... [![Github Sign Up!](https://img.shields.io/badge/Github-Sign%20Up%20!-brightgreen?logo=github&style=flat)](https://github.com/join?source_repo=r-h%2Fmaven-repository) .. it is free ! :)

Open in an Online IDE:

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/r-h/wicket-notes-demo.git)

## For building and trying this on your local system

Obviously [git](https://git-scm.com/).

Input in your (Windows-) cmd, (Un!x / Linux/ git-) bash or (MacOS-) Terminal :

`   git clone https://github.com/r-h/wicket-notes-demo.git`

Install a JDK >= 8, like e.g [OpenJDK](http://openjdk.java.net/install/index.html),

Have a working internet connection, so that depencies can be dowloaded.

# Building and running
After the **prerequisites** are met, enter in your local  (Un!x / Linux/ git-) bash or ([gitpodi.io](https://www.gitpod.io/) / MacOs-) Terminal:

`    ./mvnw clean package jetty:run`

or in your (Windows-) cmd:

`    ./mvnw.cmd clean package jetty:run`

to compile, package and launch the project.

**For being in [gitpod.io](https://www.gitpod.io/)**

press on the ["Open in Browser"-Link](https://www.gitpod.io/blog/gitpodify/#opening-previews)

**For trying things out on your local system**

Enter 
`   http://localhost:8080`

in the web-browser of your choice.

Alternatively you can run the project from your favorite IDE

[![Download VS Code](https://img.shields.io/badge/VS%20Code-Donwload%20!-blue.svg?logo=visual-studio-code)](https://code.visualstudio.com/download) or
[![Download Eclipse!](https://img.shields.io/badge/Eclipse-Download%20!-blueviolet.svg?logo=eclipse)](https://www.eclipse.org/downloads/) or
[![Download Netbeans](https://img.shields.io/badge/Netbeans-Donwload%20!-7396.svg?logo=java)](https://netbeans.apache.org/download/index.html)
[![Download IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-Donwload!-orange.svg?logo=intellij-idea)](https://www.jetbrains.com/idea/download)

by running the `io.github.r_h.wicket_notes_demo.Start`-class.

# Login

Currently there are only two predefined users:
`admin` with p/w `admin`
and `test` with p/w `test`

# How it works

The application consists of three main components, namely :
- `LoginPage`, which is responsible for the user login,
- `NotesPage` which contains a pageable and limited result listing for all notes,
- `NotePage` which is used to create and edit notes and also gives a detailed view on a single `Note` entity.

The login  is done in a ``Form``(provided by Wicket)  which ensures via the mentioned JSR-303 annotations at the entities that the entered user and
password information is not empty and between one and twenty characters long.

After checking that the given credentials represent a valid ``User`` entity, the `NotesPages` is set as the response page.

The first "page" of all the ``Note`` entities in the system are displayed in a ``Grid``(provided by jqgrid), which is enabled through
select/click/double-click-handler to update/delete the containing model aka the ``Note``entities.

The notes can be sorted via clicks on the header of the columns which is realised through the ``SortableNotesDataProvider``,
which itself uses the ``NotesDao`` for the data aggregation.

A double-click on a note in the grid leads to the ``NotePage`` which lets the user view and edit one ``Note`` entity.

Again annotation at the ``Note`` class assure that certain constraints are valid e.g. that a note text is not ``null`` and has a size between 1 and 100.

Back at the ``NotesPage`` the user has the ability to create a new ``Note`` by clicking on the "new Note" link, which works accordingly to editing.

# Known Issues

Rather old version of the frameworks/technologies that are used, due to the fact the app was written some years ago.

Persistence is only done in-memory (can be changed configuration-wise in `persistence.xml`)

No authorisation is implemented (every user can edit/delete every note)

Error-handling is completly omitted for the sake of simplicity

There is no log-out
