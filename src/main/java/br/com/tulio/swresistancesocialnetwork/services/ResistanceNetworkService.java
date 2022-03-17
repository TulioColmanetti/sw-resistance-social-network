package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.dto.TradeItemsDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.DenounceTraitorAlreadyRegisteredException;
import br.com.tulio.swresistancesocialnetwork.exceptions.DistinctRebelsRequiredException;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.exceptions.TradeNotValidException;
import br.com.tulio.swresistancesocialnetwork.mapper.DenounceTraitorMapper;
import br.com.tulio.swresistancesocialnetwork.model.DenounceTraitor;
import br.com.tulio.swresistancesocialnetwork.model.Item;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import br.com.tulio.swresistancesocialnetwork.repository.DenounceTraitorRepository;
import br.com.tulio.swresistancesocialnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ResistanceNetworkService {

    final DenounceTraitorRepository denounceTraitorRepository;
    final RebelRepository rebelRepository;
    private final DenounceTraitorMapper denounceTraitorMapper = DenounceTraitorMapper.INSTANCE;
    private final RebelService rebelService;

    public DenounceTraitorDTO denounceTraitor(DenounceTraitorDTO denounceTraitorDTO) throws RebelNotFoundException, DistinctRebelsRequiredException, DenounceTraitorAlreadyRegisteredException {
        Long informerRebelId = denounceTraitorDTO.getInformerId();
        Long traitorRebelId = denounceTraitorDTO.getTraitorId();

        Rebel informerRebel = verifyIfRebelExists(informerRebelId);
        Rebel traitorRebel = verifyIfRebelExists(traitorRebelId);
        if (Objects.equals(informerRebel, traitorRebel)) {
            throw new DistinctRebelsRequiredException();
        }

        verifyIfDenounceTraitorIsAlreadyRegistered(informerRebelId, traitorRebelId);
        DenounceTraitor denounceTraitor = denounceTraitorMapper.toModel(denounceTraitorDTO);
        DenounceTraitor savedDenounceTraitor = denounceTraitorRepository.save(denounceTraitor);
        return denounceTraitorMapper.toDTO(savedDenounceTraitor);
    }

//    public DenounceTraitorDTO findByInformerIdAndTraitorId(Long informerId, Long traitorId) throws DenounceTraitorNotFound {
//        DenounceTraitor foundDenounceTraitor = denounceTraitorRepository.findByInformerIdAndTraitorId(informerId, traitorId)
//                .orElseThrow(() -> new DenounceTraitorNotFound(informerId, traitorId));
//        return denounceTraitorMapper.toDTO(foundDenounceTraitor);
//    }

    private boolean checkIfTraitorRebelById(Long id) {
        return denounceTraitorRepository.findAllByTraitorId(id).size() >= 3;
    }

    private void verifyIfDenounceTraitorIsAlreadyRegistered(Long informerId, Long traitorId) throws DenounceTraitorAlreadyRegisteredException {
        Optional<DenounceTraitor> optDenounceTraitor = denounceTraitorRepository.findByInformerIdAndTraitorId(informerId, traitorId);
        if (optDenounceTraitor.isPresent()) {
            throw new DenounceTraitorAlreadyRegisteredException(informerId, traitorId);
        }
    }

    private Rebel verifyIfRebelExists(Long id) throws RebelNotFoundException {
        return rebelRepository.findById(id).orElseThrow(() -> new RebelNotFoundException(id));
    }

    public TradeItemsDTO tradeItems(TradeItemsDTO tradeItemsDTO) throws TradeNotValidException, DistinctRebelsRequiredException, RebelNotFoundException {
        checkIfValidTrade(tradeItemsDTO);

        RebelDTO firstRebelDTO = rebelService.updateItems(tradeItemsDTO.getFirstRebelId(), tradeItemsDTO.getSecondRebelItems());
        RebelDTO secondRebelDTO = rebelService.updateItems(tradeItemsDTO.getSecondRebelId(), tradeItemsDTO.getFirstRebelItems());

        tradeItemsDTO.setFirstRebelItems(secondRebelDTO.getItems());
        tradeItemsDTO.setSecondRebelItems(firstRebelDTO.getItems());

        return tradeItemsDTO;
    }

    private void checkIfValidTrade(TradeItemsDTO tradeItemsDTO) throws RebelNotFoundException, TradeNotValidException, DistinctRebelsRequiredException {
        Long firstRebelId = tradeItemsDTO.getFirstRebelId();
        Long secondRebelId = tradeItemsDTO.getSecondRebelId();

        // Check if both rebels exists
        Rebel firstRebel = verifyIfRebelExists(firstRebelId);
        Rebel secondRebel = verifyIfRebelExists(secondRebelId);

        if (Objects.equals(firstRebel, secondRebel)) {
            throw new DistinctRebelsRequiredException();
        }

        // Check if any of the rebels were denounced as traitors
        if (checkIfTraitorRebelById(firstRebelId) || checkIfTraitorRebelById(firstRebelId))
            throw new TradeNotValidException();

        //TODO: Antes de iniciar troca de itens, validar se eles tem no inventário

        int firstRebelPoints = calculateItemsListPoints(firstRebel.getItems());
        int secondRebelPoints = calculateItemsListPoints(secondRebel.getItems());

        //Check if item points of both rebels match
        if ((firstRebelPoints != secondRebelPoints))
            throw new TradeNotValidException();
    }

    private int calculateItemsListPoints(List<Item> itemsList) {
        int totalPoints = 0;

        for (Item item: itemsList) {
            totalPoints += item.getItemType().getPoints();
        }

        return totalPoints;
    }

//    private MessageResponseDTO createMessageResponse(Long id, String message) {
//        return MessageResponseDTO
//                .builder()
//                .message(message + id)
//                .build();
//    }
}
