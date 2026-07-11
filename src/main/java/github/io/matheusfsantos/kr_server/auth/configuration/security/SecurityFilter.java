package github.io.matheusfsantos.kr_server.auth.configuration.security;

import github.io.matheusfsantos.kr_server.auth.application.core.model.TokenInformation;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.AuthException;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.GetTokenInformationInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.ValidateTokenInputPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Configuration
public class SecurityFilter extends OncePerRequestFilter {
    private final ValidateTokenInputPort validateTokenInputPort;
    private final GetTokenInformationInputPort getTokenInformationInputPort;

    public SecurityFilter(ValidateTokenInputPort validateTokenInputPort, GetTokenInformationInputPort getTokenInformationInputPort) {
        this.validateTokenInputPort = validateTokenInputPort;
        this.getTokenInformationInputPort = getTokenInformationInputPort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> authToken = this.getHeaderToken(request);
        if(authToken.isPresent()) {
            Boolean isValid = this.validateTokenInputPort.validate(authToken.get());

            if(Boolean.TRUE.equals(isValid)) {
                try {
                    TokenInformation tokenInformation = this.getTokenInformationInputPort.get(authToken.get());
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(String.format("ROLE_%s", tokenInformation.role()));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        tokenInformation.id(),
                        null,
                        Collections.singletonList(authority)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (AuthException e) {
                    log.error("{} - doFilterInternal - message: {}", getClass().getSimpleName(), e.getMessage());
                    SecurityContextHolder.clearContext();
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getHeaderToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")) return Optional.empty();

        return Optional.of(token.substring(7).trim());
    }
}
