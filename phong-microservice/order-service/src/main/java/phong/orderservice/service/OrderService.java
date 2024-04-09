package phong.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import phong.orderservice.dto.InventoryResponse;
import phong.orderservice.dto.OrderLineItemDto;
import phong.orderservice.dto.OrderRequest;
import phong.orderservice.entity.OrderLineItem;
import phong.orderservice.entity.OrderEntity;
import phong.orderservice.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;


    public void placeOrder(OrderRequest orderRequest){
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItem = orderRequest.getOrderLineItemDtoList().stream().
                map(this::dtoToEntity).toList();
        order.setOrderLineItems(orderLineItem);

        List<String> listProductCode = order.getOrderLineItems().stream()
                .map(OrderLineItem::getProductCode).toList();

        // check productCode
        InventoryResponse[] resultInventory = webClientBuilder.build().get()
                .uri("http://inventory-service/inventory",
                        uriBuilder -> uriBuilder.queryParam("listProductCode",listProductCode).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean checkAllInStock  = Arrays.stream(Objects.requireNonNull(resultInventory)).allMatch(InventoryResponse::isInStock);
        if(checkAllInStock) {
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Product is not in stock");
        }
    }

    private OrderLineItem dtoToEntity(OrderLineItemDto orderLineItemDto){
        OrderLineItem oderLineItem = new OrderLineItem();
        orderLineItemDto.setId(orderLineItemDto.getId());
        orderLineItemDto.setPrice(orderLineItemDto.getPrice());
        orderLineItemDto.setQuantity(orderLineItemDto.getQuantity());
        orderLineItemDto.setProductCode(orderLineItemDto.getProductCode());
        return oderLineItem;
    }
}
