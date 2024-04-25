package com.chatop.chatopapi.security;

import com.chatop.chatopapi.controllers.AuthRestController;
import com.chatop.chatopapi.exceptions.AccessDeniedException;
import com.chatop.chatopapi.services.impl.UserDetailsServiceImpl;
import com.chatop.chatopapi.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final Logger logger = LogManager.getLogger(AuthRestController.class);


    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    public JWTAuthFilter(UserDetailsServiceImpl userDetailsService, ObjectMapper objectMapper) {
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    @Autowired
    JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("Request info {}", request);

        try {
            String authHeader = request.getHeader("Authorization");

            String token = null;
            String username = null;

            // remove filter check for permitted routes
            if (authHeader == null && !request.getRequestURI().contains("register") && !request.getRequestURI().contains("login") && !request.getRequestURI().contains("swagger-ui") && !request.getRequestURI().contains("chatop-api-docs")) {
                AccessDeniedException accessDeniedException = new AccessDeniedException("No Authorization header in the request");
                resolver.resolveException(request, response, null, accessDeniedException);
                return;
            }


            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                token = authHeader.substring(7);
                username = jwtUtils.extractUsername(token);
            }

            // If the accessToken is null. It will pass the request to next filter in the chain.
            // Any login and signup requests will not have jwt token in their header, therefore they will be passed to next filter chain and return an error
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

//       If any accessToken is present, then it will validate the token and then authenticate the request in security context
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtUtils.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            // this is 'working' pass through the next filter since the JWT was validated, therefore following filters will allow the request
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException e) {
            resolver.resolveException(request, response, null, e);

        }
    }

}