package br.com.tulio.swresistancesocialnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RebelNotFoundException extends Exception {

    public RebelNotFoundException(Long id) {
        super("Rebel not found with ID " + id);
    }
}
