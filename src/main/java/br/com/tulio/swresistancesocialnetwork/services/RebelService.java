package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.dto.LocationDTO;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.mapper.RebelMapper;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import br.com.tulio.swresistancesocialnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<RebelDTO> listAll() {
        List<Rebel> allPeople = rebelRepository.findAll();
        return allPeople.stream()
                .map(rebelMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RebelDTO findById(Long id) throws RebelNotFoundException {
        Rebel rebel = verifyIfExists(id);

        return rebelMapper.toDTO(rebel);
    }

    public RebelDTO updateLocation(Long id, LocationDTO updateLocationDTO) throws RebelNotFoundException {
        Rebel rebelToUpdateLocation = verifyIfExists(id);
        rebelToUpdateLocation.setLatitude(updateLocationDTO.getLatitude());
        rebelToUpdateLocation.setLongitude(updateLocationDTO.getLongitude());
        rebelToUpdateLocation.setBaseName(updateLocationDTO.getBaseName());
        Rebel rebelWithLocationUpdated = rebelRepository.save(rebelToUpdateLocation);
        return rebelMapper.toDTO(rebelWithLocationUpdated);
    }

    private Rebel verifyIfExists(Long id) throws RebelNotFoundException {
        return rebelRepository.findById(id).orElseThrow(() -> new RebelNotFoundException(id));
    }
}
