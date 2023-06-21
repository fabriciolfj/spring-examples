package com.github.fabriciolfj.javaexamples.config;


import org.springframework.context.annotation.Configuration;
import java.util.Collections;

@Configuration
public class SecurityConfig {

   /* private static final String URL =  "/api/v2/**";

    @Autowired
    private RequestHeaderAuthenticationProvider requestHeaderAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.addFilterAt(requestHeaderAuthenticationFilter(), HeaderWriterFilter.class) //adicionar um filtro antes do HeaderWriterFilter
                .authorizeHttpRequests(e -> e.requestMatchers(URL, "/**").permitAll());

        return http.build();
    }

    @Bean
    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() {
        final RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
        filter.setPrincipalRequestHeader("x-auth-secret-key");
        filter.setExceptionIfHeaderMissing(false);
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(URL));
        filter.setAuthenticationManager(authenticationManager());

        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(requestHeaderAuthenticationProvider));
    }*/
}
