package com.example.itemservice.service;

import com.example.itemservice.dto.ItemStatus;
import com.example.itemservice.entity.Item;
import com.example.itemservice.error.ApiException;
import com.example.itemservice.error.ExceptionEnum;
import com.example.itemservice.form.RequestAddItem;
import com.example.itemservice.form.RequestEditItem;
import com.example.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Long addItem(RequestAddItem form) {
        ModelMapper model = new ModelMapper();
        Item item = model.map(form, Item.class);
        item.setItemSellStatus(ItemStatus.SELLING_ITEM.getValue());

        return itemRepository.save(item).getItemId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findItemByItemId(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ApiException(ExceptionEnum.NO_ITEM));
    }

    public Item editItem(Long itemId, RequestEditItem form) {
        Item findItem = findItemByItemId(itemId);
        ModelMapper model = new ModelMapper();
        model.map(form, findItem);

        return itemRepository.save(findItem);
    }

    public Integer reduceItemStock(Long itemId, Integer count) {
        Item findItem = findItemByItemId(itemId);
        if(findItem.getStock()<count) throw new ApiException(ExceptionEnum.NO_STOCK);

        findItem.setStock(findItem.getStock() - count);
        return itemRepository.save(findItem).getStock();
    }
}
