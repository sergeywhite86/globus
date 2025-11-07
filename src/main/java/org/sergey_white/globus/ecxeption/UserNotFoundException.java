package org.sergey_white.globus.ecxeption;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User with ID: " + id + " not found");
    }

}
