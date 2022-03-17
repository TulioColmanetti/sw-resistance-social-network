package br.com.tulio.swresistancesocialnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DistinctRebelsRequiredException extends Exception {

    public DistinctRebelsRequiredException() {
        super("Distinct rebels must be chosen");
    }
}
