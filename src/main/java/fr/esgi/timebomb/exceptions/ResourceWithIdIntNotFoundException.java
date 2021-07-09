package fr.esgi.timebomb.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ResourceWithIdIntNotFoundException extends Throwable {

    private final Map<String, Object> errors;

    public ResourceWithIdIntNotFoundException(String resource, int id) {
        this.errors = new HashMap<>();
        this.errors.put("resource", resource);
        this.errors.put("id", id);
        log.error("Resource {} with id {} not found", resource, id);
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
