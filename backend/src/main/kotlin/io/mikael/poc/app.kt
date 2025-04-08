package io.mikael.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.header.writers.CrossOriginEmbedderPolicyHeaderWriter.CrossOriginEmbedderPolicy.REQUIRE_CORP
import org.springframework.security.web.header.writers.CrossOriginOpenerPolicyHeaderWriter.CrossOriginOpenerPolicy.SAME_ORIGIN
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN
import org.springframework.security.web.header.writers.StaticHeadersWriter
import org.springframework.security.web.header.writers.CrossOriginResourcePolicyHeaderWriter.CrossOriginResourcePolicy.SAME_ORIGIN as CORP_SAME_ORIGIN

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<Application>(*args)
    }
}

@SpringBootApplication
class Application


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            // --- Disable Default Security Features ---

            // Disable HTTP Basic authentication
            httpBasic { disable() }

            // Disable Form-based authentication (prevents login page redirect)
            formLogin { disable() }

            // Disable CSRF protection - Common for stateless APIs.
            // If you have web forms served by Spring Boot, you might want to keep CSRF enabled: csrf { }
            csrf { disable() }

            // Optional: Configure session management to be stateless if this is purely an API
            // This prevents Spring Security from creating an HttpSession
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }

            // --- Permit All Requests ---
            // This ensures that adding Spring Security doesn't block any endpoints by default
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }

            // --- Configure Security Headers ---
            headers {
                // X-Frame-Options: Prevent clickjacking
                frameOptions { sameOrigin = true }

                // Referrer-Policy: Control referrer information
                referrerPolicy { policy = STRICT_ORIGIN_WHEN_CROSS_ORIGIN }

                // Permissions-Policy: Control browser features access (Customize heavily!)
                permissionsPolicy {
                    policy = "camera=(), microphone=(), geolocation=(), interest-cohort=()"
                }

                // Content-Security-Policy (CSP): Mitigates XSS (Customize heavily!)
                contentSecurityPolicy {
                    policyDirectives =
                        "default-src 'self';" +
                                "script-src 'self';" +
                                "style-src 'self' 'unsafe-inline';" + // Review 'unsafe-inline'
                                "img-src 'self' data:;" +
                                "font-src 'self';" +
                                "object-src 'none';" +
                                "frame-ancestors 'self';" +
                                "base-uri 'self';" +
                                "form-action 'self';" +
                                "upgrade-insecure-requests"
                }

                // X-Content-Type-Options: Prevent MIME-sniffing (enabled by default, explicit is fine)
                contentTypeOptions { }

                // Strict-Transport-Security (HSTS): Enforce HTTPS (Uncomment ONLY for HTTPS sites)
                /*
                httpStrictTransportSecurity {
                    includeSubDomains = true
                    maxAgeInSeconds = 31536000 // 1 year
                    // preload = true // Consider after careful testing
                }
                 */

                // Cross-Origin-Opener-Policy (COOP)
                crossOriginOpenerPolicy { policy = SAME_ORIGIN }

                // Cross-Origin-Embedder-Policy (COEP)
                crossOriginEmbedderPolicy { policy = REQUIRE_CORP } // Or unsafeNone() if requireCorp breaks things

                // Cross-Origin-Resource-Policy (CORP)
                crossOriginResourcePolicy { policy = CORP_SAME_ORIGIN } // Or crossOrigin()

                // X-Permitted-Cross-Domain-Policies: Legacy Flash control
                addHeaderWriter(StaticHeadersWriter("X-Permitted-Cross-Domain-Policies", "none"))

                // Clear-Site-Data: Usually not set globally (omitted here)
            }
        }
        return http.build()
    }
}