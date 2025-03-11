package org.example.accesshop_jpa.exception;

public class EntidadNoEncontradaException extends RuntimeException{
    public EntidadNoEncontradaException(Long id, Class entidad) {
        super("No se ha podido encontrar la entidad " + entidad.getSimpleName() + " con id: " + id);
    }
}
