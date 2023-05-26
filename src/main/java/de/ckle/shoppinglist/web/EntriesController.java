package de.ckle.shoppinglist.web;

import de.ckle.shoppinglist.domain.ShoppinglistEntry;
import de.ckle.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntriesController {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @GetMapping("/shoppinglist/entries")
    public ResponseEntity<List<ShoppinglistEntryDto>> getEntries(){
        List<ShoppinglistEntry> entries = shoppingListRepository.findAll();

        List<ShoppinglistEntryDto> result = entries.stream()
                .map(entity -> new ShoppinglistEntryDto(entity.getTitle(), entity.getAmount(), entity.getCategory()))
                .toList();

        return ResponseEntity.ok(result);
    }
}
