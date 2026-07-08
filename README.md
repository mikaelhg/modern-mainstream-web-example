## Modern mainstream web application example

**Dev tools:**

* [mise-en-place](https://mise.jdx.dev/)
  for installing node, pnpm, the JDK, various command line tools.

**Build tools:**

* [Gradle](https://www.gradle.org/) with a NPM plugin for the backend build and for
  managing the frontend build, and mushing the frontend and backend together.

* Gradle's dependency verification mechanism for SHA pinning dependency JARs.

To update the verification data after upgrading dependencies:

```shell
./gradlew --write-verification-metadata sha256 build
```

**Virtual machines / programming language runtimes:**

* [JDK](https://adoptium.net/) 25 LTS with ZGC
  for running the backend, as well as executing the build.

* [Node.js](https://nodejs.org/en/) LTS and [pnpm](https://pnpm.io/)
  for running the frontend build.

**Programming languages:**

* [Kotlin](https://kotlinlang.org/)
  for developing the backend. Compiles into JVM class files and a runnable JAR.

* TypeScript for the frontend.

**Frameworks and libraries:**

* [Spring Boot](https://projects.spring.io/spring-boot/)
  and Spring Security

* [React.js](https://react.dev/),
  [Redux](https://react-redux.js.org/), 
  [Redux Toolkit](https://redux-toolkit.js.org/)
  with [Vite](https://vite.dev/) for developing the frontend.
  
* [TailwindCSS](https://tailwindcss.com/)
  for presenting the combination of web content and visual user interface,
  and structuring the collaboration between frontend developers and UI/visual designers.

**Operational tools:**

* [Docker](https://www.docker.com/) and
  [Docker Compose](https://docs.docker.com/compose/)
  for building production/staging-deployable application containers.
  
* [Dex](https://dexidp.io/)
  as the OpenID / OAuth2 authentication server, suitable for both 
  local containers and production deployments.

* [Traefik](https://traefik.io/) or 
  [Caddy](https://caddyserver.com/) as a reverse HTTP/S proxy and
  [Let's Encrypt](https://letsencrypt.org/) free SSL certificate automation manager.

* [VictoriaLogs](https://github.com/VictoriaMetrics/VictoriaLogs) and
  [Vector](https://vector.dev/)
  for collecting log events and errors into a searchable and trackable database.
  
* [VictoriaMetrics](https://github.com/victoriametrics/VictoriaMetrics) 
  for collecting quantitative data related both to the business logic and the infrastructure.

* [OpenTelemetry](https://opentelemetry.io/) and
  [VictoriaTraces](https://github.com/VictoriaMetrics/VictoriaTraces)
  for distributed enterprise application tracing, where you need transparency into
  business processes which have been distributed across many separate applications.

### How to develop

Frontend and backend developers should first collaborate in building a functional paper
prototype of the minimum viable product, or something equivalent, which allows them to
specify the most elementary aspects of the interface between the backend and the frontend.

After that, the backend developers should create a mock API with static data pulled
from JSON / YAML files, while the frontend developers create simple browser-based
integration tests for accessing the mock backend.

After that, the development will begin in earnest, and iteration can start.

The developers should use [IntelliJ IDEA](https://www.jetbrains.com/idea/), which has good
support for all of the elements used in this example, and can be licensed on a monthly basis.

Developers use Docker Compose to run a complete environment on their laptops.

If you genuinely can't afford the commercial version of IDEA, get the free community
version of IDEA for backend development, and use [VS Code](https://code.visualstudio.com/)
for frontend development. It's not quite as good as IDEA, but it will do in a pinch.

### How to deploy locally, with `docker compose`

    docker compose up

After you've started the application, you can browse these links:

[Application](http://localhost:20080/),
[Jaeger](http://localhost:26668/search),
[Grafana](http://localhost:23000/).

### How to deploy into dev, test and prod

Have your CI automatically build and deploy from master branch pushes
to the dev environment.

Manual deploys of packaged images to the test and production environments.

It's a good idea to set up CI automation so that you can kick off temporary
PR specific environments for testing.

### How to operate in production

Set up your production environment so the team can spot, debug, and fix issues before 
they turn into user-facing outages.

**Instrument properly:**

*   Structure your logs, metrics, and traces. Include critical context like user IDs, 
session IDs, and request timelines.
*   Collect everything into a single, searchable location and keep the data long 
enough to spot recurring issues.

**Automate alerts, but be sensible:**

*   Set up alerts for key metrics like latency spikes, high error rates, and resource limits.
*   Only page people for issues that actually impact users, or the team will 
just start ignoring the noise.

**Observability is never "done":**

*   Regularly check for gaps in your monitoring as the system grows. You need to be able to 
trace a user's journey across multiple services from login to error.
*   After a post-mortem, prioritize fixing any logs or metrics that failed to help you diagnose the issue.

Don't worry too much about specific tools. What matters is the mindset: 
assume things will fail in production, and make sure your team has the visibility to figure out why quickly.
