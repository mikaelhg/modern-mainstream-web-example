package io.mikael.poc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.header.writers.CrossOriginEmbedderPolicyHeaderWriter.CrossOriginEmbedderPolicy.REQUIRE_CORP
import org.springframework.security.web.header.writers.CrossOriginOpenerPolicyHeaderWriter.CrossOriginOpenerPolicy.SAME_ORIGIN
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN
import org.springframework.security.web.header.writers.CrossOriginResourcePolicyHeaderWriter.CrossOriginResourcePolicy.SAME_ORIGIN as CORP_SAME_ORIGIN

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            oauth2Login { }

            httpBasic { disable() }
            formLogin { disable() }
            csrf { disable() }

            authorizeHttpRequests {
                authorize(pattern = "/api/*", access = authenticated)
                authorize(anyRequest, authenticated)
            }

            headers {
                // X-Frame-Options: Prevent clickjacking
                frameOptions { sameOrigin = true }

                // Referrer-Policy: Control referrer information
                referrerPolicy { policy = STRICT_ORIGIN_WHEN_CROSS_ORIGIN }

                // Permissions-Policy: Control browser features access
                permissionsPolicy {
                    policy = "camera=(), microphone=(), geolocation=(), interest-cohort=()"
                }

                // Content-Security-Policy (CSP): Mitigates XSS
                contentSecurityPolicy {
                    policyDirectives =
                        "default-src 'self'; " +
                                "script-src 'self'; " +
                                "style-src 'self' 'unsafe-inline'; " + // Review 'unsafe-inline'
                                "img-src 'self' data:; " +
                                "font-src 'self'; " +
                                "object-src 'none'; " +
                                "frame-ancestors 'self'; " +
                                "base-uri 'self'; " +
                                "form-action 'self'; "
                                // "upgrade-insecure-requests"
                }

                // X-Content-Type-Options: Prevent MIME-sniffing
                contentTypeOptions { }

                // Strict-Transport-Security (HSTS): Enforce HTTPS
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
                crossOriginEmbedderPolicy { policy = REQUIRE_CORP }

                // Cross-Origin-Resource-Policy (CORP)
                crossOriginResourcePolicy { policy = CORP_SAME_ORIGIN }

                // Clear-Site-Data: Usually not set globally (omitted here)
            }
        }
        return http.build()
    }
}