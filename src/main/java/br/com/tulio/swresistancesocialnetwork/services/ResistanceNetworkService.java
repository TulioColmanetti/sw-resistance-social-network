package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.dto.ItemDTO;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.dto.TradeItemsDTO;
import br.com.tulio.swresistancesocialnetwork.enums.ItemType;
import br.com.tulio.swresistancesocialnetwork.exceptions.DenounceTraitorAlreadyRegisteredException;
import br.com.tulio.swresistancesocialnetwork.exceptions.DistinctRebelsRequiredException;
import br.com.tulio.swresistancesocialnetwork.exceptions.RebelNotFoundException;
import br.com.tulio.swresistancesocialnetwork.exceptions.TradeNotValidException;
import br.com.tulio.swresistancesocialnetwork.mapper.DenounceTraitorMapper;
import br.com.tulio.swresistancesocialnetwork.mapper.ItemMapper;
import br.com.tulio.swresistancesocialnetwork.model.DenounceTraitor;
import br.com.tulio.swresistancesocialnetwork.model.Item;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import br.com.tulio.swresistancesocialnetwork.repository.DenounceTraitorRepository;
import br.com.tulio.swresistancesocialnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ResistanceNetworkService {

    final DenounceTraitorRepository denounceTraitorRepository;
    final RebelRepository rebelRepository;
    private final DenounceTraitorMapper denounceTraitorMapper = DenounceTraitorMapper.INSTANCE;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;
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

        Rebel firstRebel = verifyIfRebelExists(tradeItemsDTO.getFirstRebelId());
        Rebel secondRebel = verifyIfRebelExists(tradeItemsDTO.getSecondRebelId());

        List<Item> firstRebelNewInventory = removeItemsFromInventory(firstRebel.getItems(), tradeItemsDTO.getFirstRebelItems());
        List<Item> secondRebelNewInventory = removeItemsFromInventory(secondRebel.getItems(), tradeItemsDTO.getSecondRebelItems());

        firstRebelNewInventory.addAll(tradeItemsDTO.getSecondRebelItems().stream().map(itemMapper::toModel).collect(Collectors.toList()));
        secondRebelNewInventory.addAll(tradeItemsDTO.getFirstRebelItems().stream().map(itemMapper::toModel).collect(Collectors.toList()));

        System.out.println(firstRebelNewInventory);
        System.out.println(secondRebelNewInventory);

        RebelDTO firstRebelDTO = rebelService.updateItems(firstRebel.getId(), firstRebelNewInventory.stream().map(itemMapper::toDTO).collect(Collectors.toList()));
        RebelDTO secondRebelDTO = rebelService.updateItems(secondRebel.getId(), secondRebelNewInventory.stream().map(itemMapper::toDTO).collect(Collectors.toList()));

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
        if (checkIfTraitorRebelById(firstRebelId) || checkIfTraitorRebelById(secondRebelId))
            throw new TradeNotValidException();

        // Check if the rebels have the items in their inventories
        if (!checkRebelInventory(firstRebel.getItems(), tradeItemsDTO.getFirstRebelItems()) ||
                !checkRebelInventory(secondRebel.getItems(), tradeItemsDTO.getSecondRebelItems()))
            throw new TradeNotValidException();

        int firstRebelPoints = calculateItemsListPoints(tradeItemsDTO.getFirstRebelItems());
        int secondRebelPoints = calculateItemsListPoints(tradeItemsDTO.getSecondRebelItems());

        // Check if item points of both rebels match
        if ((firstRebelPoints != secondRebelPoints))
            throw new TradeNotValidException();
    }

    private boolean checkRebelInventory(List<Item> rebelItems, List<ItemDTO> itemsToCheck) {
//        List<ItemType> rebelItemsToItemType = rebelItems.stream().map(Item::getItemType).collect(Collectors.toList());
//        List<ItemType> itemsToCheckToItemType = itemsToCheck.stream().map(ItemDTO::getItemType).collect(Collectors.toList());

//        Map<String, List<Item>> rebelItemsGrouped =
//                rebelItems.stream().collect(Collectors.groupingBy(w -> w.getItemType().getName()));
//
//        Map<String, List<ItemDTO>> itemsToCheckGrouped =
//                itemsToCheck.stream().collect(Collectors.groupingBy(w -> w.getItemType().getName()));

        Map<String, Long> rebelItemsGrouped =
                rebelItems.stream().collect(Collectors.groupingBy(w -> w.getItemType().getName(), Collectors.counting()));

        Map<String, Long> itemsToCheckGrouped =
                itemsToCheck.stream().collect(Collectors.groupingBy(w -> w.getItemType().getName(), Collectors.counting()));

        for (Map.Entry<String, Long> entry : itemsToCheckGrouped.entrySet()) {
            if ((rebelItemsGrouped.get(entry.getKey()) == null) || (entry.getValue() > rebelItemsGrouped.get(entry.getKey()))){
                return false;
            }
        }
        return true;
    }

    private Item findItemByItemType(List<Item> itemsList, ItemType itemTypeToFind) {
        return itemsList.stream().filter(item-> itemTypeToFind.equals(item.getItemType()))
                .findFirst().orElse(null);
    }

    private List<Item> removeItemsFromInventory(List<Item> rebelItems, List<ItemDTO> itemsToRemove) {
        for (ItemDTO itemToRemove : itemsToRemove) {
            Item inventoryItem = findItemByItemType(rebelItems, itemToRemove.getItemType());
            rebelItems.remove(inventoryItem);
        }
        return rebelItems;
    }

    private int calculateItemsListPoints(List<ItemDTO> itemsList) {
        int totalPoints = 0;

        for (ItemDTO item: itemsList) {
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
