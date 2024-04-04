package phong.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phong.orderservice.dto.OrderLineItemDto;
import phong.orderservice.dto.OrderRequest;
import phong.orderservice.entity.OrderLineItem;
import phong.orderservice.entity.OrderEntity;
import phong.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItem = orderRequest.getOrderLineItemDtoList().stream().
                map(this::dtoToEntity).toList();
        order.setOrderLineItems(orderLineItem);
        orderRepository.save(order);
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
