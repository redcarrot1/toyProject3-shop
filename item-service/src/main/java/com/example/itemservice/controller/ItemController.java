package com.example.itemservice.controller;

import com.example.itemservice.entity.Item;
import com.example.itemservice.form.*;
import com.example.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService service;

    @PostMapping("/addItem")
    public ResponseAddItem addItem(@RequestBody RequestAddItem form) {
        Long itemId = service.addItem(form);
        return new ResponseAddItem(itemId);
    }

    @GetMapping("/items")
    public List<ResponseItemList> itemList() {
        ModelMapper model = new ModelMapper();
        List<Item> items = service.findItems();
        List<ResponseItemList> result = new ArrayList<>();
        items.forEach(e -> result.add(model.map(e, ResponseItemList.class)));
        return result;
    }

    @GetMapping("/{itemId}")
    public ResponseItem itemByItemId(@PathVariable Long itemId) {
        ModelMapper model = new ModelMapper();
        Item items = service.findItemByItemId(itemId);
        return model.map(items, ResponseItem.class);
    }

    @PostMapping("/{itemId}")
    public ResponseItem editItem(@PathVariable Long itemId,
                                 @RequestBody RequestEditItem form) {
        Item item = service.editItem(itemId, form);
        ModelMapper model = new ModelMapper();
        return model.map(item, ResponseItem.class);
    }

    @PostMapping("/{itemId}/{count}")
    public void reduceItemStock(@PathVariable Long itemId, @PathVariable Integer count) {
        service.reduceItemStock(itemId, count);
    }


}
