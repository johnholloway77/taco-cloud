package ca.johnholloway.tacocloud.controller;

import ca.johnholloway.tacocloud.services.AdminServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminServices adminServices;
    public AdminController(AdminServices adminServices){
        this.adminServices = adminServices;
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders(){
        adminServices.deleteAllOrders();
        return "redirect:/admin";
    }

}

