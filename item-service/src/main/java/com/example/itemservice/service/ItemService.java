package com.example.itemservice.service;

import com.example.itemservice.entity.Item;
import com.example.itemservice.form.RequestAddItem;
import com.example.itemservice.form.RequestEditItem;
import com.example.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Long addItem(RequestAddItem form) {
        ModelMapper model = new ModelMapper();
        Item item = model.map(form, Item.class);
        item.setItemSellStatus(1);

        return itemRepository.save(item).getItemId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findItemByItemId(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        if (findItem.isEmpty()) {
            //TODO 오류
        }
        return findItem.get();
    }

    public Item editItem(Long itemId, RequestEditItem form) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        if (findItem.isEmpty()) {
            //TODO 오류
        }
        Item item = findItem.get();

        ModelMapper model = new ModelMapper();
        model.map(form, item);

        itemRepository.save(item);
        return item;
    }

    public Integer reduceItemStock(Long itemId, Integer count) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        if (findItem.isEmpty()) {
            //TODO 오류처리
        }
        Item item = findItem.get();
        item.setStock(item.getStock() - count);
        return itemRepository.save(item).getStock();
    }
}
