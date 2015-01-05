spring-hibernate-angular
=======================

SpringMvcWithNG is a sample project to include spring hibernate with angularjs. 
As backend we are using h2 (in memory database).

#### Required Tools:
* [Java 8+](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](http://maven.apache.org/)
* [Git](http://git-scm.com/)
* [Nodejs](http://nodejs.org/)
* Gulp `npm install -g gulp`
* Bower `npm install -g bower`
* Protractor `npm install protractor`
* Webdriver `./node_modules/protractor/bin/webdriver-manager update`

#### Project Compile:
* `npm install`
* `bower install`
* `node_modules/protractor/bin/install_selenium_standalone`
* `gulp build`
* `mvn tomcat7:run`

#### Unit Testing
* Java: `mvn test`
* Karma: `gulp spec`

#### E2E Testing
* Protractor: `gulp e2e`
