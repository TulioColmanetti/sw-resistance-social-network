package br.com.tulio.swresistancesocialnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TradeNotValidException extends Exception {

    public TradeNotValidException() {
        super("Trade requested is not valid");
    }
}
