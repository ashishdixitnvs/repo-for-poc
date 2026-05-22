package com.example.samplecrudapp.service;

import com.example.samplecrudapp.exception.ResourceNotFoundException;
import com.example.samplecrudapp.model.Item;
import com.example.samplecrudapp.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id " + id));
    }

    public Item create(Item item) {
        item.setCreatedAt(LocalDateTime.now());
        return itemRepository.save(item);
    }

    public Item update(Long id, Item item) {
        Item existing = findById(id);
        existing.setName(item.getName());
        existing.setDescription(item.getDescription());
        existing.setPrice(item.getPrice());
        return itemRepository.save(existing);
    }

    public Item partialUpdate(Long id, Map<String, Object> updates) {
        Item existing = findById(id);

        if (updates.containsKey("name")) {
            existing.setName((String) updates.get("name"));
        }
        if (updates.containsKey("description")) {
            existing.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            Object price = updates.get("price");
            if (price != null) {
                existing.setPrice(new BigDecimal(price.toString()));
            }
        }

        return itemRepository.save(existing);
    }

    public void delete(Long id) {
        Item existing = findById(id);
        itemRepository.delete(existing);
    }
}
