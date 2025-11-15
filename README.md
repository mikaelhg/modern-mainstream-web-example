## Modern mainstream web application example

**Dev tools:**

* [mise-en-place](https://mise.jdx.dev/)
  for installing node, pnpm, the JDK, various command line tools.

**Build tools:**

* [Gradle](https://www.gradle.org/) with a NPM plugin for the backend build and for
  managing the frontend build, and mushing the frontend and backend together.

**Virtual machines / programming language runtimes:**

* [JDK](https://adoptium.net/) 25 with ZGC
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
  
* [Bootstrap](https://getbootstrap.com/) or
  [TailwindCSS](https://tailwindcss.com/)
  for presenting the combination of web content and visual user interface,
  and structuring the collaboration between frontend developers and UI/visual designers.

**Other resources:**

* [CoreUI](https://coreui.io/)
  for a NPM-packaged Bootstrap web interface for administrative user interfaces.

* [Wrap Bootstrap](https://wrapbootstrap.com/) and [Themeforest](https://themeforest.net/)
  for high-level user interface design products, which allow you to spend your
  UI budget on features, rather than duplicating standard components and views.

* [Unsplash](https://unsplash.com/) for great photographic visual elements.

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

* [Grafana Loki](https://github.com/grafana/loki), 
  [Grafana Alloy](https://github.com/grafana/alloy) and
  [Vector](https://vector.dev/)
  for collecting log events and errors into a searchable and trackable database.
  
* [Prometheus](https://prometheus.io/) for collecting quantitative data related both
  to the business logic and the application infrastructure.

* [OpenTelemetry](https://opentelemetry.io/) and
  [Jaeger](https://www.jaegertracing.io/)
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

The ideal way to handle deployment into the shared development and test environments,
is to utilize the release management mechanism of your project hosting platform as a trigger
to the CI/CD pipeline, which will create a versioned release image, and trigger its deployment
to dev.

From there, manual deployment actions should trigger the deployment of the release image
into the tst and prod environments, as appropriate.

### How to operate in production

The production environment should be set up so that the team can 
**observe**, **diagnose**, and **adapt** to problems *before* they 
turn into outages or failures for the user.

**Instrument properly:**

- Log everything important in a structured format: logs, metrics, and traces. 
  The data needs context, like user IDs, session IDs, and request timelines.
- All this data should be collected into one central place where you can search it. 
  Keep the data for long enough that you can find repeating problems.

**Automate alerts, but be sensible:**

- Set up some rules and thresholds for the important things, for example, for spikes 
  in API latency, high error rates, or running out of memory.
- The system should only send alerts when the problem actually affects users. 
  Otherwise, people will just start ignoring them.

**Observability is never "done":**

- As the application gets more complicated, you need to check what you are missing 
  in your monitoring. For example, if a user's action goes through multiple services, 
  can you actually follow it from start to finish? Can you see the whole journey from 
  login to the point where they got an error?
- If you have a post-mortem and find that the logs or metrics were not useful, your first 
  priority should be to fix them so they are useful the next time.

In the end, the specific tools you use are not the most important thing. 
What is important is the team's mindset: you have to assume that things **will** fail 
in production, and make sure the team has what it needs to figure out why, quickly.
