package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.dto.MessageResponseDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelCanNotBeChosenException;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.mapper.DenounceTraitorMapper;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import br.com.tulio.swresistancesocialnetwork.repository.DenounceTraitorRepository;
import br.com.tulio.swresistancesocialnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DenounceTraitorService {

    final DenounceTraitorRepository denounceTraitorRepository;
    final RebelRepository rebelRepository;
    private final DenounceTraitorMapper denounceTraitorMapper = DenounceTraitorMapper.INSTANCE;

    public MessageResponseDTO denounceTraitor(DenounceTraitorDTO denounceTraitorDTO) throws RebelNotFoundException, RebelCanNotBeChosenException {
        Rebel informerRebel = verifyIfExists(denounceTraitorDTO.getInformerId());
        Rebel traitorRebel = verifyIfExists(denounceTraitorDTO.getTraitorId());
        if (Objects.equals(informerRebel, traitorRebel)) {
            throw new RebelCanNotBeChosenException(traitorRebel.getId());
        }
        return createMessageResponse(traitorRebel.getId(), "Successfully denounced traitor rebel with ID ");
    }

    private Rebel verifyIfExists(Long id) throws RebelNotFoundException {
        return rebelRepository.findById(id).orElseThrow(() -> new RebelNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
