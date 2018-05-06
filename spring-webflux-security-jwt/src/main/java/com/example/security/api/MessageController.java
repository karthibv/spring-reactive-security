
package com.example.security.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.domain.FormattedMessage;
import com.example.security.service.MessageService;

import reactor.core.publisher.Flux;

/**
 * A controller serving rest endpoints to show authorization features in this project
 * Endpoints for authentication are open, and others require the authenticated user to
 * have certain roles
 *
 * @author rafa
 */
@RestController
public class MessageController {
    private static final Log LOGGER = LogFactory.getLog(MessageController.class);

    @Autowired
    MessageService messageService;

    /**
     * Root endpoint serves as a resource for Basic Authentication
     *
     * @return A publisher that serves a welcoming message
     */
    @GetMapping("/")
    public Flux<FormattedMessage> hello() {
        return messageService.getDefaultMessage();
    }

    /**
     * Common login endpoint is also available for basic authentication
     *
     * @return A publisher serving a message stating successful log in
     */
    @GetMapping("/login")
    public Flux<FormattedMessage> login() {
    	LOGGER.debug("MessageController> login");
        return messageService.getDefaultMessage();
    }

    /**
     * A restricted endpoint requiring consumers to be authenticated and also
     * have the right roles for this resource
     *
     * @return A publisher serving a message when access is granted
     */
    @GetMapping("/api/private")
    @PreAuthorize("hasRole('USER')")
    public Flux<FormattedMessage> privateMessage() {
    	LOGGER.debug("MessageController> privateMessage");

        return messageService.getCustomMessage("User");
    }

    /**
     * A restricted endpoint requiring consumers to be authenticated and also
     * have the right roles for this resource
     *
     * @return A publisher serving a message when access is granted
     */
    @GetMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<FormattedMessage> privateMessageAdmin() {
    	LOGGER.debug("MessageController> privateMessageAdmin");
        return messageService.getCustomMessage("Admin");
    }

    /**
     * A restricted endpoint requiring consumers to be authenticated and also
     * have the right roles for this resource
     *
     * @return A publisher serving a message when access is granted
     */
    @GetMapping("/api/guest")
    @PreAuthorize("hasRole('GUEST')")
    public Flux<FormattedMessage> privateMessageGuest() {
    	LOGGER.debug("MessageController> privateMessageGuest");
        return messageService.getCustomMessage("Guest");
    }
}
