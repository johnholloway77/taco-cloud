package ca.johnholloway.tacocloud.services;

import ca.johnholloway.tacocloud.repository.OrderRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    private final OrderRepository orderRepository;
    public AdminServices(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }

}
