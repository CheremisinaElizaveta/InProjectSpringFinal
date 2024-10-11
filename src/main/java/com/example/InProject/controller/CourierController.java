package com.example.InProject.controller;

import com.example.InProject.model.CourierModel;
import com.example.InProject.service.CourierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/couriers")
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @GetMapping("/all")
    public String getAllCouriers(Model model) {
        List<CourierModel> couriers = courierService.findAllCouriers();
        model.addAttribute("couriers", couriers);
        model.addAttribute("courier", new CourierModel());
        return "courierList";
    }

    @PostMapping("/add")
    public String addCourier(@Valid @ModelAttribute("courier") CourierModel courier, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("couriers", courierService.findAllCouriers());
            return "courierList";
        }
        courierService.addCourier(courier);
        return "redirect:/couriers/all";
    }

    @PostMapping("/update")
    public String updateCourier(@Valid @ModelAttribute("courier") CourierModel courier, BindingResult result) {
        if (result.hasErrors()) {
            return "courierList";
        }
        courierService.updateCourier(courier);
        return "redirect:/couriers/all";
    }

    @PostMapping("/delete")
    public String deleteCourier(@RequestParam UUID id) {
        courierService.deleteCourier(id);
        return "redirect:/couriers/all";
    }

    @GetMapping("/all/{id}")
    public String getIdCourier(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("courier", courierService.findCourierById(id));
        return "courierList";
    }
}
