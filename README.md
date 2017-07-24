## Modern mainstream web application example

Build tools: 

* Maven 3.5.0 with [https://github.com/takari/polyglot-maven](`polyglot-maven`) for the backend,
  managing the frontend build, and mushing the frontend and backend together.

* Yarn 0.27.5 for managing the frontend dependencies, and the frontend static web site build.

Virtual machines:

* JVM 8 for running the backend, as well as executing the build.

* Node v6 for running the frontend build.

Languages:

* Kotlin 1.1 for developing the backend. Compiles into JVM-runnable class files.

* TypeScript 2.5 for developing the frontend. Compiles into browser-runnable old style JavaScript files.

Frameworks:

* Spring Boot 2.0 with webflux for developing the backend.

* Angular 4 with `@angular/cli` for developing the backend.