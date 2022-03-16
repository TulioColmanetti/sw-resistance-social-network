package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.mapper.RebelMapper;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import br.com.tulio.swresistancesocialnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RebelService {

    final RebelRepository rebelRepository;
    private final RebelMapper rebelMapper = RebelMapper.INSTANCE;

    public RebelDTO createRebel(RebelDTO rebelDTO) {
        Rebel rebel = rebelMapper.toModel(rebelDTO);
        Rebel savedRebel = rebelRepository.save(rebel);
        return rebelMapper.toDTO(savedRebel);
    }
}
