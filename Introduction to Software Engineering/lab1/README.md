# Report on Lab 1 Assignment

## Author
André Gabriel Butuc, Nmec: 103530; IES P1 2022


## 1.2 Build management with the Maven tool

### Maven in 5 minutes tutorial

To create a maven project the following command was written on the command line:

mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

Insights on the previous command:

* The '-D' switch is used to define/pass a property to Maven in CLI.

* The flag "groupId" and "artifactId" are the flags which should be specific for each project.

* "groupId" uniquely identifies the project across all projects. A group ID should follow Java's package name rules. This means it starts with a reversed domain name you control. For example:
"org.apache.maven" ou "org.apache.commons".

* You can create as many subgroups as you want. A good way to determine the granularity of the groupId is to use the project structure. That is, if the current project is a multiple module project, it should append a new identifier to the parent's groupId. For example:
"org.apache.maven", "org.apache.maven.plugins", "org.apache.maven.reporting".


* "artifactId is the name of the jar without version. If you created it, then you can choose whatever name you want with lowercase letters and no strange symbols. If it's a third party jar, you have to take the name of the jar as it's distributed. For example, "maven", "commons-math".


* "version" if you distribute it, then you can choose any typical version with numbers and dots (1.0, 1.1, 1.0.1, ...). Don't use dates as they are usually associated with SNAPSHOT (nightly) builds. If it's a third party artifact, you have to use their version number whatever it is, and as strange as it can look.

After creating a the maven project we get a standard project strucutre. In this structure we have:
* The src/main/java directory which contains the project source code;
* The src/test/java directory contains the test source;
* The pom.xml file is the project's Project Object Model, or POM.

__But what is the POM file?__
The pom.xml file is the core of a project's configuration in Maven. It is a single configuration file that contains the majority of information required to build a project in just the way you want. The POM is huge and can be daunting in its complexity, but it is not necessary to understand all of the intricacies just yet to use it effectively.


In order to build the maven project we must run the following command: 

mvn package

In order to test the newly compiled and packaged JAR, we can run the following command:

java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App


**In order to target a certain version of Java** we must change in the pom.xml file in the build field the maven-compiler-plugin to at least the version 3.6.0 and set the maven.compiler.release property to the Java release we are targetting.


**Maven Phases**
Although hardly a comprehensive list, these are the most common default lifecycle phases executed:
* validate: validate the project is correct and all necessary information is available;
* compile: compile the source code of the project;
* test: test the compiled source code using a suitable unit testing framework. These tesks should not require the code be packaged or deployed;
* integration-test: process and deploy the package if necessary into an environment where integration tests can be run;
* verify: run any chekcs to verify the package is valid and meets quality criteria;
* install: install the package into the local repository, for use as dependency in other projects locally;
* deploy: done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects.

There are two other Maven lifecycles of note beyond the default list above. They are:
* clean: cleans up artifcats created by prior builds
* site: generates site documentation for this project

The following command will clean the project, copy dependencies, and package the project (executing all phases up to package):

mvn clean dependency:copy-dependencies package

The following command generates a site based upon information on the project's pom. You can look at the documentation generated under target/site:

mvn site


### Using Maven - weather forecast project

Google's Gson is a Java library that can be used to convert Java Objects into their JSON representation.

Square's Retrofit is a type-safe HTTP client for Java, that allows mapping an external REST API into a local (Java) interface.

Notes from 2nd practical class:
Technologies: docker, kubernetes, postgres, nginx, vm.


## 1.3 Source code management using Git

To ignore certain files during synchronization between local and remote repositories we can create a file .gitignore and state the files and extensions of the files we want to ignore. Normally we don't want to send to the remote repository .class files or invisible files, since these files can account for a lot of processing which in the end of the day is not needed. This file should be added to the root of the project.

To colaborate with other developers, using a remote repository is quite useful for version control and contributing to the same project at the same time. With the git command of git add, git commit, git push and git pull we can easily communicate with our team our code developments.



## 1.4 Introduction to Docker

Brief report regarding my study/work on the exercise "1.4. Introduction to Docker".

1.  Docker Engine was installed sucessfully, confirmed with the command: sudo docker run hello-world. Afterwards, I added my user to the docker user group and tested the previous command without "sudo". Everything worked fine.

2.  "Orientation and Setupt" Docker tutorial Notes:

I started the tutorial with the following command:
docker run -d -p 80:80 docker/getting-started

Quick explanation over the flags used:
- d - run the container in detached mode (in the background)
- p 80:80 - map port 80 of the host to port 80 in the container
- docker/getting-started - the image to use 


**What is a container?** A container is simply another process on your machine that has been isolated from all other processes on the host machine. That isolation leverages kernel namespaces and cgroups, features that have been in Linux for a long time. Docker has worked to make these capabilities approachable and easy to use.

**What is a container image?**
When running a container, it uses an isolated filesystem. The custom filesystem is provided by a container image. Since the image contains the container's filesystem, it must contain everything needed to run an application - all dependencies, configuration, scripts, binaries, etc. The image also contains other configuration for the container, such as environment variables, a default command to run, and other metadata.


### Define your own image (Dockerfile)
First alternative failed because of the pool of keys being unacessible. Therefore I went with the alternative option.

I was able to download and build the docker image with postgresql and executed the command:

docker exec -it pg-docker psql -U postgres -c "CREATE DATABASE testdb;"

I also attempted to visualize through a graphical client the container with the use of pgAdmin.

It is possible to execute SQL commands individually through the CLI, but normally we need to create a docker file to build an image.  


### Multiple services (Docker compose)

To build a docker compose we first need to define a Dockerfile.

Afterwards we define a compose yml file which in the specific case of study defined two services: web and redis.

We can build and run our app with Compose with the use of the following command:

**docker compose up**

Some docker compose commands are:
* docker compose run - allows us to run one-off commands for our services.
* docker compose --help - to see other available commands
* docker compose stop - to stop our services once we've finished with them.
* docker compose down --volumes - we can bring everything down, removing the containers entirely. By passing the flag --volumes we also remove the data volume used by the Redis container.


## 1.5 Wrapping-up & integrating concepts
In order to force the separation of the previous Weather App I created two separate Maven Projects:
* IAC (IpmaApiClient) which has 4 Java Files, namely, CityForecast.java, IpmaCityForecast.java, IpmaService.java (nothing new) and finally IpmaApiClient.java which contains a functions that deals with all the API connections and data retrievel that was previously on the file WeatherStarter.java. The function "retrieveForecast" takes an Integer argument of the ID of the city.

* WFBC (WeatherForecastByCity) consists on only one WeatherStarter.java file which only retrieves the argument passed in console, it invokes the previous mentioned function from IAC and display the information of the forecast. This project only has one true dependency which is IAC. 

The previous POM file from exercise 1.2 was splitted between the two new POM files, where all the dependencies regarding retrofit and gson went to the IAC project.

By forcing the separation into two small, independent Java Maven projects it was clear that the structure of the project as a whole got more organized. I can already see the advantages that achieving such modularization will give in high complexity projects.


## Review Questions

**A)** The main phases in the default lifecycle of Maven are:
* **validate**: validate the project is correct and all necessary information is available;
* **compile**: compile the source code of the project;
* **test**: test the compiled source code using a suitable unit testing framework. These tesks should not require the code be packaged or deployed;
* **integration-test**: process and deploy the package if necessary into an environment where integration tests can be run;
* **verify**: run any chekcs to verify the package is valid and meets quality criteria;
* **install**: install the package into the local repository, for use as dependency in other projects locally;
* **deploy**: done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects.


**B)** Most definitely. We should migrate our project to Maven because MAven offers:
"
* A huge, continuously growing repository of user libraries
* The ability to set up projects easily, using best practices
* Dependency management, featuring automatic updating
* Backwards compatible with previous versions
* Strong error and integrity reporting
* Automatic parent versioning
* Ensures consistent usage across all projects
* It’s extensible, and you can easily write plug-ins using scripting languages or Java.
"
simplilearn

**C)** A very likely sequency of GIt commands required to contribute with a new feature to a given project would be:
* Initially make sure that you are working with the latest version of the repository by inputting the command - **git pull**;
* After developing some increment you should either add the changed file(s) to be commit through individual commands such as - **git add path/to/file** or add all the changed files in one single command, as follows - **git add .**;
* With the changes commited we need to think of a commit message and commit the changes through the command - **git commit -m "Commit Message"**;
* Finally we just need to synchronize our local repository with the remote one, and we can do that with the following command - **git push**.


**D)** Ideally a commit message should be what you would like to read on a colaborative project repository after not paying much attention for the last month. We want to get a clear idea of what changes were made just from a short sentence.
In order to achieve such goal we can follow a few good-practices:

1. **Keep it short** (less than 150 characters total);

2. **Use the imperative mood**;

    This convention aligns with commit messages generated by commands like git merge and git revert
    Consistency enhances speed of reading comprehension
    Tends to be more concise than the other moods

3. **Add a title**:
    Less than 50 characters
    Use Title case (i.e. "Add Logging" instead of "add logging")

4. **Add a body**:
    Less than 100 characters
    Explain WHAT the change is, but especially WHY the change was needed
    Leave a blank line between the title and body
    Separate paragraphs in the body with blank lines
    Use a hyphen (-) for bullet points if needed
    Use hanging indents if needed


**E)** Explanation taken from docker docs: "Volumes are the preferred mechanism for persisting data generated by and used by Docker containers. While bind mounts are dependent on the directory structure and OS of the host machine, volumes are completely managed by Docker. Volumes have several advantages over bind mounts:

* Volumes are easier to back up or migrate than bind mounts.
* You can manage volumes using Docker CLI commands or the Docker API.
* Volumes work on both Linux and Windows containers.
* Volumes can be more safely shared among multiple containers.
* Volume drivers let you store volumes on remote hosts or cloud providers, to encrypt the contents of volumes, or to add other functionality.
* New volumes can have their content pre-populated by a container.
* Volumes on Docker Desktop have much higher performance than bind mounts from Mac and Windows hosts. 

In addition, volumes are often a better choice than persisting data in a container’s writable layer, because a volume does not increase the size of the containers using it, and the volume’s contents exist outside the lifecycle of a given container. "

