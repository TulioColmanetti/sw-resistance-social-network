package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.dto.ItemDTO;
import br.com.tulio.swresistancesocialnetwork.dto.LocationDTO;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.mapper.ItemMapper;
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
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

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

    public RebelDTO updateLocation(Long id, LocationDTO updatedLocationDTO) throws RebelNotFoundException {
        Rebel rebelToUpdateLocation = verifyIfExists(id);
        rebelToUpdateLocation.setLatitude(updatedLocationDTO.getLatitude());
        rebelToUpdateLocation.setLongitude(updatedLocationDTO.getLongitude());
        rebelToUpdateLocation.setBaseName(updatedLocationDTO.getBaseName());
        Rebel rebelWithLocationUpdated = rebelRepository.save(rebelToUpdateLocation);
        return rebelMapper.toDTO(rebelWithLocationUpdated);
    }

    public RebelDTO updateItems(Long id, List<ItemDTO> updatedItemsDTO) throws RebelNotFoundException {
        Rebel rebelToUpdateItems = verifyIfExists(id);
        rebelToUpdateItems.setItems(updatedItemsDTO.stream().map(itemMapper::toModel).collect(Collectors.toList()));
        Rebel rebelWithItemsUpdated = rebelRepository.save(rebelToUpdateItems);
        return rebelMapper.toDTO(rebelWithItemsUpdated);
    }

    private Rebel verifyIfExists(Long id) throws RebelNotFoundException {
        return rebelRepository.findById(id).orElseThrow(() -> new RebelNotFoundException(id));
    }

}
