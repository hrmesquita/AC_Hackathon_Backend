# AC_Hackathon_Backend

> **Note**
> We are no longer working on this project. 
> This was a backend project to participate on a contest.

<br>
<h1 align="center">
  Greyt_Coders
</h1>

<h4 align="center"><em>It is the name of our team on this awesome project we made for a programming Hackathon.</em></h4>

## Our idea

The idea was to create a web application to sell services. As a minimum viable product we should have a backend prepared to communicate through endpoints and this would need to contain a database to store accounts, products and reservations.

## Our approach

From the beginning we decided that our application was supposed to communicate through endpoints, using a REST API. On the backend we used Java because we wanted to deepen our knowledge on this language.
<br><br>Prior to start typing we normalized our database and, also due the time that we had, came to a conclusion that it needed to contain at least 3 tables. Since this was a small projects and we also wanted fast communication and so we used an H2 relational in memory database.
We used Spring's built-in repository to assemble our database classes.
<br><br>For all of this we used a MVC architecture.

## What was left undone 

We wanted to include the necessary testing to our product, but this wasn't possible due to time constraints.
<br><br>Our project, despite being totally functional, still had major issues that we knew needed another approach. Some of them being security flaws accessing the API like, for example, proper encryption to the password.
<br><br>We also had in mind that we needed to create DTOs to communicate through the endpoints. In addition we wanted to create DAOs as well to make the connection with the database.

## All technologies and languages used

- Spring Boot
- Maven
- Apache Tomcat
- H2
- Java
- Postman

## My backend partner and also a coding machine

- [DansPT](https://www.github.com/DansPT) 👨‍💻
