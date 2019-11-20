**********************************
* @author: An Pham
* version 1.0 date 19 Sep 2019
***********************************

Prerequisite:

Have IDE (ie: Eclipse) to import, compile, run the project
https://www.eclipse.org/downloads/packages/installer

Have Java JDK version 1.8 minumum and install on your machine.  
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Have Maven install on your machine.
https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows

Install Spring Tool Suite plugin for Eclipse
https://www.eclipse.org/downloads/packages/installer
--------------------------------------------------------------------------------------------------------

Import the project 

 
From: GitHub
Using eclipse smart git clone to clone git project from this URL 
https://github.com/DanielPham1984/Interview.git


From: Zipfile
Extract the file to any folder. 
Then import existing spring boot project to eclipse


If you want to compile and run from Eclipse the do the following otherwise go to Deploy section.
Now you can right click on the InterviewApplication and choose Run As > Spring Boot As


-------------------------------------------------------------------------------------------------------
Run test cases

Open command line client (ie: cmd, cygwin, git bash). If you are using linux use the terminal.
Go to the parent project folder (ie Interview folder)
# Run all the unit test classes.
$ mvn test



-------------------------------------------------------------------------------------------------------
Deploy
Open command line client (ie: cmd, cygwin, git bash). If you are using linux use the terminal.
Go to the parent project folder (ie Interview folder)
Run the commmand: "mvn clean package" (You need to have maven installed to run this)
The fat jar will be built with tomcat embedded in the folder target/Interview-0.0.1-SNAPSHOT.jar
In order to run the service. Run "jar -jar target/Interview-0.0.1-SNAPSHOT.jar"
Whenever you want to shutdown, then press ctlr-C to kill it.
When the service run you will be able to access the service using URL : http://localhost:8080/ in any browser.
This service is running on default port 8080 tomcat http.

-------------------------------------------------------------------------------------------------------
This project uses these technologies:
- Use Spring Boot
- Use Spring, Spring MVC
- Use Thymeleaf
- Use log4j
- Use Junit/Mockito
- Use CSS and Javascript
- Use Maven to build/test/run your project
- Use server-side validation
- display a meaningful message if a user types a wrong URL to access a resource
- Use AJAX and JSON
- Use JQuery
-------------------------------------------------------------------------------------------------------

TODO:
Clean up the HomeController. 
There are some duplicated codes I should merge it for reusing. 
I should create a singleton class to keep the record from CSV. So we do not need to load it everytime
Change the CSS file to make the layout look better.
Add more test cases to make 100% code coverage. 
For the log4j appender, I output to console now instead of file. Should redirect the appender to file.
Add security components.