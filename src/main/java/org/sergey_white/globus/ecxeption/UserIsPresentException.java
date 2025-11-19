package org.sergey_white.globus.ecxeption;

public class UserIsPresentException extends RuntimeException{
    public UserIsPresentException(String email) {
       super(String.format("user with email %s is present",email));
    }
}
