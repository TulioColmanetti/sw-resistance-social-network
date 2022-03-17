package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.DenounceTraitorAlreadyRegisteredException;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelCanNotBeChosenException;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.mapper.DenounceTraitorMapper;
import br.com.tulio.swresistancesocialnetwork.model.DenounceTraitor;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import br.com.tulio.swresistancesocialnetwork.repository.DenounceTraitorRepository;
import br.com.tulio.swresistancesocialnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DenounceTraitorService {

    final DenounceTraitorRepository denounceTraitorRepository;
    final RebelRepository rebelRepository;
    private final DenounceTraitorMapper denounceTraitorMapper = DenounceTraitorMapper.INSTANCE;

    public DenounceTraitorDTO denounceTraitor(DenounceTraitorDTO denounceTraitorDTO) throws RebelNotFoundException, RebelCanNotBeChosenException, DenounceTraitorAlreadyRegisteredException {
        Long informerRebelId = denounceTraitorDTO.getInformerId();
        Long traitorRebelId = denounceTraitorDTO.getTraitorId();

        Rebel informerRebel = verifyIfRebelExists(informerRebelId);
        Rebel traitorRebel = verifyIfRebelExists(traitorRebelId);
        if (Objects.equals(informerRebel, traitorRebel)) {
            throw new RebelCanNotBeChosenException(traitorRebel.getId());
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

    public boolean checkIfTraitorRebelById(Long id) {
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

//    private MessageResponseDTO createMessageResponse(Long id, String message) {
//        return MessageResponseDTO
//                .builder()
//                .message(message + id)
//                .build();
//    }
}
