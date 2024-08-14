package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.dto.response.UserResDTO;
import org.c07.movie_booking.service.IRoleService;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/user/")
public class UserController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserService iUserService;

    @GetMapping("/public/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("listRole", iRoleService.findAll());
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/public/register")
    public String addNewUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        new UserDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("listRole", iRoleService.findAll());
            return "register";
        }

        iUserService.createNewUser(userDTO);
        redirectAttributes.addFlashAttribute("message", "User registered successfully!");
        return "redirect:/api/v1/user/public/register";
    }

    //Show List and Search Employee
    @GetMapping("private/list-employee")
    public ResponseEntity<Page<UserResDTO>> searchEmployees(
            @RequestParam(value = "valueSearch", defaultValue = "") String valueSearch,
            @RequestParam("page") Optional<Integer> page) {

        System.out.println("Search criteria:");
        System.out.println("Name: " + valueSearch);

        int currentPage = page.map(p -> Math.max(p, 0)).orElse(0);
        Pageable pageable = PageRequest.of(currentPage, 5);
        Page<UserResDTO> employees = iUserService.searchEmployees(
                valueSearch, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Remove Employee
    @PutMapping("private/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        UserResDTO userResDTO = iUserService.findEmployeeById(id);
        if (userResDTO == null) {
            return new ResponseEntity<>("Employee not found to delete !", HttpStatus.BAD_REQUEST);
        }
        iUserService.deleteEmployeeById(id);
        return new ResponseEntity<>("Delete employee successfully.", HttpStatus.OK);
    }
}
