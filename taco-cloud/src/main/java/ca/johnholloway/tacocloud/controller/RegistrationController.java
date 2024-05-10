package ca.johnholloway.tacocloud.controller;

import ca.johnholloway.tacocloud.model.RegistrationForm;
import ca.johnholloway.tacocloud.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registrationForm(){
        return "register";
    }

    @PostMapping
    public ModelAndView processRegistration(RegistrationForm form){
        userRepository.save(form.toUser(passwordEncoder));

        ModelAndView mav = new ModelAndView("/login");
        mav.addObject("username", form.getUsername());
        mav.addObject("password", form.getPassword());
        //return "redirect:/login";
        return mav;
    }

}
