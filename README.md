## Modern mainstream web application example

Build tools: 

* [Gradle](https://www.gradle.org/) 7.0 with for the backend,
  managing the frontend build, and mushing the frontend and backend together.

* [Yarn](https://yarnpkg.com/lang/en/) 2
  for managing the frontend dependencies, and the frontend static web site build.

Virtual machines / programming language runtimes:

* [JVM](https://adoptopenjdk.net/) 16
  with ZGC
  for running the backend, as well as executing the build.

* [Node.js](https://nodejs.org/en/) v14 LTS
  for running the frontend build.

Programming languages:

* [Kotlin](https://kotlinlang.org/) 1.4
  for developing the backend. Compiles into JVM class files into a JDK11-runnable JAR.

* [TypeScript](https://www.typescriptlang.org/) 3.1
  for developing the frontend. Compiles into browser-runnable ES5 JavaScript files.

Frameworks and libraries:

* [Spring Boot](https://projects.spring.io/spring-boot/) 2.4

* [Vue.js](https://vuejs.org/) 2.6
  with `@vue/cli` for developing the frontend.
  
* [Bootstrap](https://getbootstrap.com/) v4
  for presenting the combination of web content and visual user interface,
  and structuring the collaboration between frontend developers and UI/visual designers.

Other resources:

* [Wrap Bootstrap](https://wrapbootstrap.com/) and [Pixelarity](https://pixelarity.com/)
  for high-level user interface design products, which allow you to spend your
  UI budget on features, rather than duplicating standard work.

* [Unsplash](https://unsplash.com/) for great photographic visual elements.

Operational tools:

* [Docker](https://www.docker.com/) and
  [Docker Compose](https://docs.docker.com/compose/)
  for building production/staging-deployable application containers.
  
* [Traefik](https://traefik.io/) as a reverse HTTP/S proxy and
  [Let's Encrypt](https://letsencrypt.org/) free SSL certificate automation manager.

* [Sentry](https://sentry.io/welcome/) for collecting and managing the application execution
  errors reports produced by both the backend JVM and the frontend end-user web browsers.

* [Prometheus](https://prometheus.io/) for collecting quantitative data related both to the
  business logic and the application infrastructure.

* [OpenTracing](https://opentracing.io/) and
  Uber's [Jaeger](https://www.jaegertracing.io/)
  for distributed enterprise application tracing, where you need transparency into
  business processes which have been distributed across many separate applications.

### How to develop

Frontend and backend developers should first collaborate in building a functional paper
prototype of the minimum viable product, or something equivalent, which allows them to
specify the most elementary aspects of the interface between the backend and the frontend.

After that, the backend developers should create a mock interface with static data pulled
from JSON / YAML files provided by the frontend developers, while the frontend developers
create simple browser-based integration tests for accessing the mock backend.

After that, the development will begin in earnest, and iteration can start.

The developers should use [IntelliJ IDEA](https://www.jetbrains.com/idea/), which has good
support for all of the elements used in this example, and can be licensed on a monthly basis.

Developers use Docker on their laptops, which should be relatively beefy, to run the various
infrastructure components locally.

If you genuinely can't afford the commercial version of IDEA, get the free community
version of IDEA for backend development, and use [VS Code](https://code.visualstudio.com/)
for frontend TypeScript development. It's not quite as good as IDEA, but it will do in a pinch.

### How to deploy locally, with `docker-compose`

```bash
docker-compose -f docker-compose-dev.yml -p app up -d
```

### How to deploy into staging

The ideal way to handle deployment into the shared development environment, or staging,
is with an automatic deployment from the CI/CD system, which packages the application into
a Docker container, and executes a Docker Compose command which creates / recreates the
whole set of containers required for the operation of the service.

### How to deploy into production

Assuming that the application is run in the cloud, the CI/CD system should push any release
containers (usually as a response of being tagged as releases in Git) into the cloud service's
container registry. From there, the operators should use the cloud service's own deployment
tools, if they don't want to use continuous deployment into production.

### How to operate in production

The most important aspect of production operation is automating the collection and presentation
of the kind of data you'll need to resolve production issues. To that end, there are several
relevant data collection services attached to the project.
 
Learn how to use those, and how to interpret data. As the business processes managed in the 
application take shape, add missing debug data into the logged context, using the tools provided
by the data collection platforms.
