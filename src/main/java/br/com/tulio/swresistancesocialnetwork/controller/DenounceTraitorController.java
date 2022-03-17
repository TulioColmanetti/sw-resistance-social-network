package br.com.tulio.swresistancesocialnetwork.controller;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.dto.MessageResponseDTO;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.DenounceTraitorAlreadyRegisteredException;
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
    public DenounceTraitorDTO denounceTraitor(@Valid @RequestBody DenounceTraitorDTO denounceTraitorDTO) throws RebelNotFoundException, RebelCanNotBeChosenException, DenounceTraitorAlreadyRegisteredException {
        return denounceTraitorService.denounceTraitor(denounceTraitorDTO);
    }

    @GetMapping("/{id}")
    public boolean checkIfTraitorRebelById(@PathVariable Long id) throws RebelNotFoundException {
        return denounceTraitorService.checkIfTraitorRebelById(id);
    }
}
