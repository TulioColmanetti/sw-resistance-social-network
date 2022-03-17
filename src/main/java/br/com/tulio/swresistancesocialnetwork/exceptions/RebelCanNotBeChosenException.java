package br.com.tulio.swresistancesocialnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RebelCanNotBeChosenException extends Exception {

    public RebelCanNotBeChosenException(Long id) {
        super("Rebel with ID " + id + " can not be chosen");
    }
}
