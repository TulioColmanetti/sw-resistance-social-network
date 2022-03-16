package br.com.tulio.swresistancesocialnetwork.controller;

import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.services.RebelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rebels")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RebelController {
    private final RebelService rebelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RebelDTO createRebel(@RequestBody @Valid RebelDTO rebelDTO) {
        return rebelService.createRebel(rebelDTO);
    }
}
