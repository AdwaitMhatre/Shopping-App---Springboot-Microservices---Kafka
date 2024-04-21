package Springboot.orderService.Controller;

import Springboot.basedomains.dto.Order;
import Springboot.basedomains.dto.OrderEvent;
import Springboot.orderService.Kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v0")
public class OrderController {
    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }
    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order is being placed");
        orderEvent.setStatus("Pending");

        orderProducer.sendMessage(orderEvent);

        return "Order Placed Successfully";
    }
}
