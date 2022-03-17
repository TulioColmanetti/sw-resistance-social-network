package br.com.tulio.swresistancesocialnetwork.controller;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.dto.MessageResponseDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelCanNotBeChosenException;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.services.DenounceTraitorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/traitors")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DenounceTraitorController {

    private final DenounceTraitorService denounceTraitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO denounceTraitor(@Valid @RequestBody DenounceTraitorDTO denounceTraitorDTO) throws RebelNotFoundException, RebelCanNotBeChosenException {
        return denounceTraitorService.denounceTraitor(denounceTraitorDTO);
    }
}
