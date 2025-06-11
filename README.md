## Modern mainstream web application example

**Build tools:**

* [Gradle](https://www.gradle.org/) with a NPM plugin for the backend build and for
  managing the frontend build, and mushing the frontend and backend together.

**Virtual machines / programming language runtimes:**

* [JDK](https://adoptium.net/) 24 with ZGC
  for running the backend, as well as executing the build.

* [Node.js](https://nodejs.org/en/) LTS
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
  
* [Dex](https://dexidp.io/) or 
  [Authelia](https://www.authelia.com/)
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
for frontend development. It's not quite as good as IDEA, but it will do in a pinch.

### How to deploy locally, with `docker compose`

    docker compose up

After you've started the application, you can browse these links:

[Application](http://localhost:20080/),
[Jaeger](http://localhost:26668/search),
[Grafana](http://localhost:23000/).

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

Your operational infrastructure should enable teams to systematically 
**observe**, **diagnose**, and **adapt** to issues *before* they escalate into outages 
or user-facing failures.  

**Instrument comprehensively:**  
- Capture structured logs, key metrics, and transaction traces in a format 
  that contextualizes events (e.g., user IDs, session identifiers, request timelines).  
- Aggregate this data into searchable, centralized systems that retain history long 
  enough to investigate recurring patterns.  

**Automate anomaly detection:**  
- Define thresholds and heuristics for critical workflows (e.g., API latency spikes, 
  error rate surges, resource exhaustion).  
- Trigger alerts only when deviations correlate with user impact, to avoid alert fatigue.  

**Treat observability as iterative:**  
- As your application’s logic grows, audit your instrumentation gaps. 
  For example: Do complex multi-service workflows leave breadcrumbs? 
  Can you reconstruct a user’s journey from login to error?  
- Prioritize adding context to logs/metrics that repeatedly lack actionable details 
  during post-mortems.  

Successful operations depend less on specific tools and more on cultivating a 
**production-first mindset** – assume failures *will* occur, and ensure your team can
efficiently gather evidence to explain why.
