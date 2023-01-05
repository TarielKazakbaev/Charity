package com.example.Charity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        for (GrantedAuthority gra : authentication.getAuthorities()) {
            if (gra.equals(new SimpleGrantedAuthority("USER"))) {
                response.sendRedirect("/userPage");
            }
            else if (gra.equals(new SimpleGrantedAuthority("ADMIN"))) {
                response.sendRedirect("/adminPage");
            }
            else if (gra.equals(new SimpleGrantedAuthority("MODER"))) {
                response.sendRedirect("/moderPage");
            }
            else if(gra.equals(new SimpleGrantedAuthority("RECIPIENT"))){
                response.sendRedirect("/recipientPage");
            }
            else {
                response.sendRedirect("/main");
            }
        }
    }
}