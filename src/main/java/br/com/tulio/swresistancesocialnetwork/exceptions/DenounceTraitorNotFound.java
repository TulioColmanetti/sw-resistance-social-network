package br.com.tulio.swresistancesocialnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DenounceTraitorNotFound extends Exception {

    public DenounceTraitorNotFound(Long informerId, Long traitorId) {
        super("Denounce from rebel ID " + informerId + " to rebel ID " + traitorId + " not found");
    }
}
