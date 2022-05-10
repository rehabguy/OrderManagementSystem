package com.rk.orderservice.service;

import com.rk.orderservice.dto.InventoryResponse;
import com.rk.orderservice.dto.OrderLineItemsDto;
import com.rk.orderservice.dto.OrderRequest;
import com.rk.orderservice.model.Order;
import com.rk.orderservice.model.OrderLineItems;
import com.rk.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired          //inject the bean created in config file
    private WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems=
                    orderRequest.getOrderLineItemsDtoList()
                    .stream()
                    .map(OrderLineItemsDto->mapToDto(OrderLineItemsDto))
                            .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);


        List<String> skucodes=order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems->OrderLineItems.getSkuCode())
                .collect(Collectors.toList());

        //call inventory service to place order if product is in stock

        InventoryResponse[] inventoryResponseArray=             //check if result is to be saved or not
                webClientBuilder.build().get()
                 .uri("http://inventory-service/api/inventory",
                         uriBuilder -> uriBuilder.queryParam("skuCode",skucodes).build())
                 .retrieve()
                .bodyToMono(InventoryResponse[].class)  //async request by default
                 .block();       //add block() to convert async request to sync request

        boolean allProductsInMatch=Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if(allProductsInMatch)      //if result is true, save it to database
        orderRepository.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock, please try again later");
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
