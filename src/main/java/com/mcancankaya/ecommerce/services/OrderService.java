package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.exceptions.BusinessException;
import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.entities.Order;
import com.mcancankaya.ecommerce.entities.OrderItem;
import com.mcancankaya.ecommerce.entities.Product;
import com.mcancankaya.ecommerce.entities.User;
import com.mcancankaya.ecommerce.entities.enums.OrderStatus;
import com.mcancankaya.ecommerce.repositories.OrderItemRepository;
import com.mcancankaya.ecommerce.repositories.OrderRepository;
import com.mcancankaya.ecommerce.services.dtos.request.order.OrderItemRequest;
import com.mcancankaya.ecommerce.services.dtos.request.order.CreateOrderRequest;
import com.mcancankaya.ecommerce.services.dtos.request.order.UpdateOrderRequest;
import com.mcancankaya.ecommerce.services.dtos.request.product.UpdateProductRequest;
import com.mcancankaya.ecommerce.services.dtos.response.OrderResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapperService modelMapperService;
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    public CustomResponse<OrderResponse> getById(Integer id) {
        OrderResponse orderResponse = modelMapperService.forResponse().map(orderRepository.findById(id), OrderResponse.class);
        return new CustomResponse<>(orderResponse, "Siparişler Id'e göre listelendi");
    }

    public CustomResponse<List<OrderResponse>> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = orders.stream().map(order -> modelMapperService.forResponse().map(order, OrderResponse.class)).toList();
        return new CustomResponse<>(orderResponses, "Siparişler listelendi.");
    }

    @Transactional
    public CustomResponse<OrderResponse> create(CreateOrderRequest request) {
        User user = userService.getById(request.getUserId());
        Order order = Order.builder().user(user).orderStatus(OrderStatus.SUCCESS).orderDate(LocalDateTime.now()).orderItems(new ArrayList<>()).build();
        BigDecimal totalAmount = BigDecimal.ZERO;

        totalAmount = orderAndOrderItemsPrepare(request.getOrderItems(), order, totalAmount);

        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);
        return new CustomResponse<>(modelMapperService.forResponse().map(savedOrder, OrderResponse.class), "Sipariş oluşturuldu.");
    }

    @Transactional
    public CustomResponse<OrderResponse> update(UpdateOrderRequest request) {
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new BusinessException("Sipariş bulunamadı."));
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> currentOrderItems = order.getOrderItems();
        totalAmount = orderAndOrderItemsPrepare(request.getOrderItems(), order, totalAmount);

        orderItemRepository.deleteAll(currentOrderItems);

        order.setTotalAmount(totalAmount);
        Order updatedOrder = orderRepository.save(order);
        return new CustomResponse<>(modelMapperService.forResponse().map(updatedOrder, OrderResponse.class), "Sipariş güncellendi.");
    }

    public CustomResponse<?> deleteById(Integer id) {
        orderRepository.deleteById(id);
        return new CustomResponse<>("Sipariş silindi.");
    }

    private BigDecimal orderAndOrderItemsPrepare(List<OrderItemRequest> orderItemList, Order order, BigDecimal totalAmount) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest orderItem : orderItemList) {
            Product product = modelMapperService.forResponse().map(productService.getById(orderItem.getProductId()).getData(), Product.class);
            OrderItem newOrderItem = OrderItem.builder().productId(product).order(order).quantity(orderItem.getQuantity()).build();

            product.setStockAmount(product.getStockAmount() - orderItem.getQuantity());
            productService.update(modelMapperService.forRequest().map(product, UpdateProductRequest.class));
            orderItems.add(newOrderItem);
            BigDecimal productPrice = product.getPrice();
            BigDecimal itemAmount = productPrice.multiply(BigDecimal.valueOf(orderItem.getQuantity().longValue()));
            totalAmount = totalAmount.add(itemAmount);
        }
        order.setOrderItems(orderItems);
        return totalAmount;
    }

    public CustomResponse<List<OrderResponse>> getByUserId(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderResponse> orderResponses = orders.stream().map(order -> modelMapperService.forResponse().map(order, OrderResponse.class)).toList();
        return new CustomResponse<>(orderResponses, "Kullanıcının siparişleri listelendi.");
    }
}
