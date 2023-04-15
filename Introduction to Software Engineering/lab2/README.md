# Lab2 Notebook

## Author

André Gabriel Butuc, Nmec: 103530; IES P1 2022

## 2.1 Server-side programming with servlets

### Servlets

A **servlet** is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP.

The **Servlet** interface defines methods to initialize a servlet, to service requests, and to remove a servlet from the server. These are known as life-cycle methods and are called in the following sequence:

1. The servlet is constructed, then initialized with the init method.
2. Any calls from clients to the service method are handled.
3. The servlet is taken out of service, then destroyed with the destroy method, then garbage collected and finalized.

In addition to the life-cycle methods, this interface provides the getServletConfig method, which the servlet can use to get any startup information, and the getServletInfo method, which allows the servlet to return basic information about itself, such as author, version, adn copyright.

**HttpServlet** provides an abstract class to be subclasses to create an HTTP servlet suitable for a Web site. 

A subclass of HttpServlet must override at least one method, usually one of these:
* doGet, if the servlet supports HTTP GET requests;
* doPost, for HTTP POST requests;
* doPut, for HTTP PUT requests;
* doDelete, for HTTP DELETE requests;
* init and destroy, to manage resources that are held for the life of the servlet;
* getServletInfo, which the servlet uses to provide information about itself 


### Web and Applications Servers for Java

The core difference between the two is that **applications servers** have full support for the Java EE spec. whereas **web servers** support a small subset of that functionality.

Some known web servers are:
* **Apache Tomcat**
* **Jetty**

Some known application servers are:
* **Apache TomEE**
* **Oracle WebLogic**
* **WebSphere**
* **Wildfly**
* **Apache Geronimo**
* **GlassFish** 


### First steps in Tomcat

Registed a tomcat-users role with the following credentials:
- username -> admin
- password -> secret


*Brief insight on the Parameters Example*:
 
The program itself just takes two inputs ("parameters") - First Name & Last Name" and displays them in front of text labels.

Behind the app, the code consists of two functions: **doGet** and **doPost**.

The doGet function lays out the HTML structure with the method "println" from the PrintWriter "out". It uses the HttpServletRequest "request" to call the method "getParameter" to retrive the information from the input boxes.

The doPost function calls the doGet function passing as arguments the "request" HttpServletRequest and "response" the HttpServletResponse.


### Deployed Tomcat First App

The .war file is the file that we send to the Tomcat server. Afterwards we can see the file in the Tomcat management interface. As suggested, I've installed a VS Code Extension called Community Server Connectors to upload and run directly the .war file without needing to upload it manually to the Tomcat management interface.


### Made my first Basic Servlet

After creating MyFirstServlet.java file, having used the HttpServlet Request and Response Operations and the Writer to output the HTML layout I wanted, I built the maven project and deployed the new .war file. Afterwards I called the servlet as such, passing as name parameter the name "Andre": http://localhost:8080/webapp-1.0-SNAPSHOT/MyFirstServlet?name=Andre

From this exercise I learnt how the basics of Servlets work and the procedure to pass information through the GET request.


*Quick side note*: I attempted to download payara but "Command start-domain failed" error occurred.

## 2.2 Server-side programming with embedded servers

From this exercise I have experienced how advantageous embedded servers may be, since they simplify the server start-up and deployment processes through just a simple "run" in a java app. However, I particularly struggled when configuring the POM file.
In the previous exercise with TomCat, having used the default tomcat archetype I wasn't aware of the necessary dependencies and plugins which then resulted on spending a few minutes trying to understand the errors I was having when using the Jetty Server.

Nonetheless, I have implemend the previous TomCat example, in the Jetty Server. The code is mainly copy&paste from the given example but adapted to get a "name" parameter from the URL and display a personalized text. 
An example of a search would be: http://localhost:8080/?name=Andre.



## 2.3 Introduction to web apps with a full-featured framework (Spring Boot)

Specs for the first **spring intializr** generation:
- Project -> Maven Project
- Language -> Java
- Spring Boot -> 2.7.4
- Project Metadata:
  - Group -> ies.lab2.e3
  - Artificat -> webapp
  - Name -> webapp
  - Description -> Demo project lab2 exercise 3 for Spring Boot
  - Package name -> ies.lab2.e3.webapp
  - Packaging -> Jar
  - Java -> 11
- Dependendices:
  - Spring Web

After downloading the zip generated, I compiled maven project and executed the WebappApplication.java. Afterwards I searched localhost:8080 and it appeard a "Whilelabel Error Page" header.



### Getting started with Spring Boot

*Some notes regarding the **Serving Web Content With Spring MVC** tutorial:*


**WebController** handles HTTP requests. It uses some annotations such as "@Controller" to be identified, "@GetMapping" to ensure that the HTTP GET requests are mapped to a certain method, "@RequestParam" bind the value of the query string parameter into the parameter of the method.

**Thymeleaf** performs server-side rendering of the HTML. Thymeleaf parses the greeting.html template and evaluates the th:text expression to render the value of the ${name} parameter that was set in the controller.The following listing (from src/main/resources/templates/greeting.html) shows the greeting.html template.

**Spring Boot Devtools**:
- enables hot swapping
- switches templates engines to disable caching
- enables LiveReload to automatically refresh the browser
- Other reasonable defaults based on development instead of production.

**App Execution**
The Spring Initializr creates an application class for us. The @SpringBootApplication is a convenience annotation that add all of the following:
- @Configuration: Tags the class as a source of bean definitions for the application context.
- @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.
- @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

The main() method uses Spring Boot's SpringApplication.run() method to launch an application.


To build the program using maven we should use the following command:
./mvnw spring-boot:run


**Static Resources** can be served from the Spring Boot app by dropping them into the right place in the source code. By default, Spring BOot serves static content from resources in the classpath at /static. The index.html resource is special because, if it exists, it is used as a "welcome page".


### Building a RESTful Web Service
After having a look at the suggested guide I implemented an additional feature to the web project of the previous exercise, namely a RESTful Web Service.
I followed the steps that were in the guide, but I had to make some research because there was a conflit between the Controller notation and the RestController notation. Since I wanted to have both working simultaneously, I stuck with the @Controller, but added the notation @ResponseBody to the Restful method.
This way I achieve the ThymeLeaf HTML renderization and the Restful JSON response in the same project.



## Wrapping-up & integrating concepts / Basic API

To sum up the second IES lab, I created a simple API of films and quotes. In the lab's pdf, it mentioned show/film, so to simplify and to not have two classes, Film and Show, I adapted the API to only Films and their quotes. Therefore the Java classes created were "Film" and "Quote".

I created, just like the previous exercise, a Controller, this time a RestController to process the HTTP requests and return JSON objects (and not a Controller with ResponseBody). 

This controller processes three paths, as required by the lab2 guide, namely:
- /api/quotes
- /api/films
- /api/quotes?show={show_id}

All the information regarding films and quotes were stored statically in ArrayList variables.

The three methods created were:
* getQuoteFromID, which loads the films and retrieves the film with the specified ID.
* getRandomQuote(), uses Java's Random to randomnly pick a film ID.
* loadFils, which simple creates an arraylist with Film objects.

To further improve:
- getQuoteFromID error page.


## Review Questions

### A) What are the responsibilities/services of a “servlet container”?
Servlet containers execute and manage servlets by calling the servlets methods and providing services that the servlet needs when executing. 
The servlet containers provides the servlet easy access to properties of the HTTP request, such as its headers and parameters. When a servlet is called, the Web server passes the HTTP request to the servlet container. The container, in turn, passes the request to the servlet. 
In the course of managing a servlet, a servlet containers performs the following tasks:
* It creates an instance of the servlet and calls its *init()* method to initialize it.
* It constructs a request object to pass to the servlet
* It constructs a response object for the servlet.
* It invokes the servlet *service()* method. 
* It calls the *destroy()* method of the servlet to discard it when appropriate, so that it can be garbage collected.

*(adapted from Docs Oracle)*


### B) Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)

The dynamics of Model-View-Controller approach envolves a Model, Controller, View and Front Controller:
* A model contains the data of the application.
* A controller contains the business logic of an application.
* A view represents the provided information in a particular format.
* A front controller is responsible to manage the flow of the Spring Boot app.

**The flow of Spring Boot** consists initially on incoming requests which are intercepted by the front controller. This front controller gets an entry of handler mapping from the XML file and forwards the request to the controller. The controller returns an object of ModelAndView. The front controller then checks the entry of view resolver in the XML file and invokes the specified view component.


### C) Inspect the POM.xml for the previous SpringBoot projects. What is the role of the “starters” dependencies?

Starter POMs are a set of convenient dependency descriptors that we can include in our application so that we can get a one-stop-shop for all the Spring and related technology that we need. These dependencies allow us to have everything we need without having to hunt through sample code and copy-paste loads of dependency descriptors.


### D) Which annotations are transitively included in the @SpringBootApplication?

The @SpringBootApplication is a convenience annotation that adds all of the following:
- @Configuration: Tags the class as a source of bean definitions for the application context.
- @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.
- @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.


### E) Search online for the topic “Best practices for REST APIdesign”. From what you could learn, select your “top 5”practices,and briefly explain them in you own words.

**Pratice 1** - Use JSON as the Format for Sending and Receiving Data

JSON is by far the most compatible and simpler data format for decoding and encoding. Modern programming languages have all compatible methods to work with it, so we should leave XML and HTML alone and go for JSON.

**Practice 2** - Use Nouns Instead of Verbs in Endpoints

HTTP methods use verbs, so we shouldn't user getSomething in a an endpoint and just use "Something" without the verb.

**Practice 3** - Name Collections with Plural Nouns

When having a page with several branches, for instance a collection of books, it is important that in the request it is clear that there's a collection somewhere behind and not just a single object. This will avoid unwanted deletions of collections when wanting to delete a single instance.

**Practice 4** - Use Status Codes in Error Handling

This is a practice which was mainly talked about in the course of Human-Computer Interaction. We should always give the user feedback regarding the status of the system, so we should use the status codes available as such.

**Practice 5** Provide Accurate API Documentation

This falls again on the course I mentioned previously. We need to remember that we are working with clients which can have lower technical knowledge about APIs so it's important to have good documentation to guide them.