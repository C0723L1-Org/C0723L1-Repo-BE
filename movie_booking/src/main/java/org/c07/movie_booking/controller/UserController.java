package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.service.IRoleService;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

@Controller
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserService iUserSrevice;

    @GetMapping("register")
    public String showRegisterForm(Model model) {
        model.addAttribute("listRole", iRoleService.findAll());
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("register")
    public String addNewUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        new UserDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("listRole", iRoleService.findAll());
            return "register";
        }

        iUserSrevice.createNewUser(userDTO);
        redirectAttributes.addFlashAttribute("message", "User registered successfully!");
        return "redirect:/api/user/register";
    }

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = iUserSrevice.existsByEmail(email);
        return ResponseEntity.ok(new HashMap<String, Boolean>() {{
            put("exists", exists);
        }});
    }
    @GetMapping("list")
    public ResponseEntity<Page<UserDTO>> listUsers(Pageable pageable) {
        Page<UserDTO> userPage = iUserSrevice.getAllUser(pageable);
        return ResponseEntity.ok(userPage);
    }
    
}
