package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // http://localhost:8082/api/inventory/iphone-13,iphone13-red    //@PathVariable

    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red //@RequestParam
        @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
//
//    public boolean IsinStock(@PathVariable("sku-code") String skucode){
//        return inventoryService.IsinStock(skucode);
//    }
}
