package br.com.tulio.swresistancesocialnetwork.controller;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.dto.ReportDTO;
import br.com.tulio.swresistancesocialnetwork.dto.TradeItemsDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.DenounceTraitorAlreadyRegisteredException;
import br.com.tulio.swresistancesocialnetwork.exceptions.DistinctRebelsRequiredException;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.exceptions.TradeNotValidException;
import br.com.tulio.swresistancesocialnetwork.services.ResistanceNetworkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/resistance-network")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ResistanceNetworkController {

    private final ResistanceNetworkService resistanceNetworkService;

    @PostMapping("/traitors")
    @ResponseStatus(HttpStatus.CREATED)
    public DenounceTraitorDTO denounceTraitor(@Valid @RequestBody DenounceTraitorDTO denounceTraitorDTO) throws RebelNotFoundException, DistinctRebelsRequiredException, DenounceTraitorAlreadyRegisteredException {
        return resistanceNetworkService.denounceTraitor(denounceTraitorDTO);
    }

    @PostMapping("/trade")
    public TradeItemsDTO tradeItems(@Valid @RequestBody TradeItemsDTO tradeItemsDTO) throws TradeNotValidException, DistinctRebelsRequiredException, RebelNotFoundException {
        return resistanceNetworkService.tradeItems(tradeItemsDTO);
    }

    @GetMapping("/reports")
    public ReportDTO getReports() {
        return resistanceNetworkService.getReports();
    }
}
