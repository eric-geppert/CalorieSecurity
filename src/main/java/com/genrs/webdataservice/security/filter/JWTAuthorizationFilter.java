package com.genrs.webdataservice.security.filter;
//helpful link https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.genrs.webdataservice.security.constants.WebSecurityConstants.*;

//We have extended the BasicAuthenticationFilter to make Spring replace it in the filter chain with our custom implementation.
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    //creates JWTauthFilter^, with a call to the parent classes (basicAthFilter) constructor method

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //how does it know the response already??
        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            //When we add a new filter to Spring Security, we can explicitly define where in the filter chain we want that filter,
            // or we can let the framework figure it out by itself. By extending the filter provided within the security framework,
            // Spring can automatically identify the best place to put it in the security chain.
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        //private getAuthentication method. This method reads the JWT from the Authorization header, and then uses JWT to validate the token.
        // If everything is in place, we set the user in the SecurityContext and allow the request to move on.

        SecurityContextHolder.getContext().setAuthentication(authentication); //where get this authentication?????
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        System.out.println("(authorization) executing getAuthentication request: "+ request);
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            System.out.println("decodedJWT: " +decodedJWT);
            String user = decodedJWT.getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
