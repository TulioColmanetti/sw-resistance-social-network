package br.com.tulio.swresistancesocialnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DenounceTraitorAlreadyRegisteredException extends Exception {

    public DenounceTraitorAlreadyRegisteredException(Long informerId, Long traitorId) {
        super("Denounce from rebel ID " + informerId + " to rebel ID " + traitorId + " already registered");
    }
}
