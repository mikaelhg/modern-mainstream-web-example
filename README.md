## Modern mainstream web application example

Build tools: 

* [Maven](http://maven.apache.org/) 3.5.0 with 
  [polyglot-maven](https://github.com/takari/polyglot-maven)
  ([YAML](http://www.yaml.org/)) for the backend,
  managing the frontend build, and mushing the frontend and backend together.

* [Yarn](https://yarnpkg.com/lang/en/) 0.27.5
  for managing the frontend dependencies, and the frontend static web site build.

Virtual machines:

* [JVM](http://openjdk.java.net/projects/jdk8/) 8
  for running the backend, as well as executing the build.

* [Node.js](https://nodejs.org/en/) v6
  for running the frontend build.

Languages:

* [Kotlin](https://kotlinlang.org/) 1.1
  for developing the backend. Compiles into JVM class files in a JRE8-runnable JAR.

* [TypeScript](https://www.typescriptlang.org/) 2.4
  for developing the frontend. Compiles into browser-runnable ES5 JavaScript files.

Frameworks and libraries:

* [Spring Boot](https://projects.spring.io/spring-boot/) 2.0
  with webflux for developing the backend.

* [Angular](https://angular.io/) 4
  with `@angular/cli` for developing the backend.
  
* [Project Reactor](https://projectreactor.io/)
  for structuring the backend data manipulation and data flow management in a 
  particular manner, which has some potential for reducing long-term maintenance costs.
  
* [Bootstrap](https://v4-alpha.getbootstrap.com/) v4
  for structuring and presenting web content in a standardized high-level way.

Other resources:

* [Wrap Bootstrap](https://wrapbootstrap.com/) and [Pixelarity](https://pixelarity.com/)
  for high-level user interface design products which allow you to spend your
  UI budget on features, rather than duplicating standard work.

* [Unsplash](https://unsplash.com/) for great photographic visual elements.