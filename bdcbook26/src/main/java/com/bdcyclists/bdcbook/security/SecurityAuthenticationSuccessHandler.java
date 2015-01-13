package com.bdcyclists.bdcbook.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.request.RequestContextHolder;

import com.bdcyclists.bdcbook.domain.Role;

public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationSuccessHandler.class);

	@Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, 
		HttpServletResponse httpServletResponse, 
		Authentication authentication) throws IOException, ServletException {
	
        LOGGER.debug("onAuthenticationSuccess() login successful with login={}, sid={}", authentication.getName(), RequestContextHolder.currentRequestAttributes().getSessionId());

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains(Role.ROLE_ADMIN.name())) {
            LOGGER.debug("ROLE_ADMIN found, redirecting to admin page");
            // here we will redirect user to respective page
            //response.sendRedirect("admin");
        } else if (roles.contains(Role.ROLE_USER.name())) {
            LOGGER.debug("ROLE_USER found, redirecting to user page page");
            // here we will redirect user to respective page
            httpServletResponse.sendRedirect("/");
        }
    }		

}
