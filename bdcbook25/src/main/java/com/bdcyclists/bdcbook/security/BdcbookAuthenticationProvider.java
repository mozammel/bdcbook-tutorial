package com.bdcyclists.bdcbook.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bdcyclists.bdcbook.domain.User;
import com.bdcyclists.bdcbook.service.UserService;

@Service("bdcbookAuthenticationProvider")
public class BdcbookAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(BdcbookAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        LOGGER.info("additionalAuthenticationChecks, userDetails={}", userDetails == null ? "null" : userDetails.toString());
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        LOGGER.info("retrieveUser, for username={}", username);

        if (StringUtils.isEmpty(username)) {
            //By default the AbstractUserDetailsAuthenticationProvider throws a BadCredentialsException if a username is
            // not found or the password is incorrect. Setting this property to false will cause
            // UsernameNotFoundExceptions to be thrown instead for the former.
            setHideUserNotFoundExceptions(false);
            LOGGER.debug("username is not found, throwing UsernameNotFoundException");
            throw new UsernameNotFoundException("Enter your email address.");
        }

        String password = (String) authentication.getCredentials();
        if (password == null) {
            LOGGER.debug("password is not found, throwing BadCredentialsException");

            throw new BadCredentialsException("Password no provided, please try again");
        }

        LOGGER.debug("Retrieving user by email: {}", username);
        User user = userService.findByEmail(username);

        if (user == null) {
            LOGGER.debug("user doesn't exist in database with email: {}, throwing UsernameNotFoundException", username);

            throw new UsernameNotFoundException("Username doesn't exists");
        }
        if (!encoder.matches(password, user.getPassword())) {
            LOGGER.debug("provided password and database password doesn't match, throwing BadCredentialsException", username);

            throw new BadCredentialsException("Invalid password, please try again");
        }

        if (!user.isEnabled()) {
            LOGGER.debug("User is not enabled, can't help login");

            throw new BadCredentialsException("User disabled");
        }

        LOGGER.debug("Everything is alright, user can proceed to login, returning user");
        return user;
    }
}