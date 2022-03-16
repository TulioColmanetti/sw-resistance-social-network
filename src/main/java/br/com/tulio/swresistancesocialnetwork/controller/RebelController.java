package br.com.tulio.swresistancesocialnetwork.controller;

import br.com.tulio.swresistancesocialnetwork.dto.LocationDTO;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.services.RebelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<RebelDTO> listAll(){
        return rebelService.listAll();
    }

    @GetMapping("/{id}")
    public RebelDTO findById(@PathVariable Long id) throws RebelNotFoundException {
        return rebelService.findById(id);
    }

    @PatchMapping("/{id}/updateLocation")
    public RebelDTO updateLocation(@PathVariable Long id, @RequestBody @Valid LocationDTO locationDTO) throws RebelNotFoundException {
        return rebelService.updateLocation(id, locationDTO);
    }
}
