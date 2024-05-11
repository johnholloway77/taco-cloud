package ca.johnholloway.tacocloud.controller;

import ca.johnholloway.tacocloud.config.holders.OrderProp;
import ca.johnholloway.tacocloud.model.TacoOrder;
import ca.johnholloway.tacocloud.model.TacoUser;
import ca.johnholloway.tacocloud.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepo;
    private final OrderProp orderProp;
    public OrderController(OrderRepository orderRepo, OrderProp orderProp) {
        this.orderRepo = orderRepo;
        this.orderProp = orderProp;
    }

    @GetMapping("/orderList")
    public String ordersForUsers(
            @AuthenticationPrincipal TacoUser tacoUser,
            @RequestParam(name="pageNo", defaultValue = "0") int pageNo,
            Model model){
        Pageable pageable = PageRequest.of(pageNo, orderProp.getPageSize());
        List<TacoOrder> tacoOrders = orderRepo.findByTacoUserOrderByPlacedAtDesc(tacoUser, pageable);
        for (TacoOrder tacoOrder : tacoOrders ) {
            tacoOrder.setPlacedAtFormatted(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy h:mm a").format(tacoOrder.getPlacedAt()));
        }
        model.addAttribute("orders", tacoOrders);
        return "orderList";
    }


    @GetMapping("/current")
    public String orderForm(
            @AuthenticationPrincipal TacoUser tacoUser,
            @ModelAttribute TacoOrder order) {

        if(order.getDeliveryName() == null){
            order.setDeliveryName(tacoUser.getFullname());
        }

        if(order.getDeliveryStreet() == null){
            order.setDeliveryStreet(tacoUser.getStreet());
        }

        if(order.getDeliveryCity() == null){
            order.setDeliveryCity(tacoUser.getCity());
        }

        if(order.getDeliveryState() == null){
            order.setDeliveryState(tacoUser.getState());
        }

        if(order.getDeliveryZip() == null){
            order.setDeliveryZip(tacoUser.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public ModelAndView processOrder(
            @Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal TacoUser tacoUser) {

        ModelAndView mav;

        log.info("Checking for errors");
        if (errors.hasErrors()) {
           // return "orderForm";
            mav = new ModelAndView();
            mav.setViewName("orderForm");
            return mav;
        }
        log.info("no erros");
        order.setTacoUser(tacoUser);
        order.setPlacedAt(LocalDateTime.now());
        log.info(tacoUser + "submitted order: {}", order);
        orderRepo.save(order);

        sessionStatus.setComplete();

        mav = new ModelAndView("/home");
        mav.addObject("message", "Order successfully placed:");
        mav.addObject("tacos", order.getTacos());
        return mav;
    }
}
