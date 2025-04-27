package com.epf.exception;

//Gestion ressource non trouvée, message et message + cause
public class ResourceNotFoundException extends RuntimeException {

    // Constructeur
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

