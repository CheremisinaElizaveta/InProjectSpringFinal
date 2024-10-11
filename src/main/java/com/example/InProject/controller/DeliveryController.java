package com.example.InProject.controller;

import com.example.InProject.model.DeliveryModel;
import com.example.InProject.service.CourierService;
import com.example.InProject.service.DeliveryService;
import com.example.InProject.service.OrderService;
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
@RequestMapping("/deliveries")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private CourierService courierService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public String getAllDeliveries(Model model) {
        List<DeliveryModel> deliveries = deliveryService.findAllDeliveries();
        model.addAttribute("deliveries", deliveries);
        model.addAttribute("delivery", new DeliveryModel());
        model.addAttribute("couriers", courierService.findAllCouriers());
        model.addAttribute("orders", orderService.findAllOrders());
        return "deliveryList";
    }

    @PostMapping("/add")
    public String addDelivery(@Valid @ModelAttribute("delivery") DeliveryModel delivery, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("deliveries", deliveryService.findAllDeliveries());
            model.addAttribute("couriers", courierService.findAllCouriers());
            model.addAttribute("orders", orderService.findAllOrders());
            return "deliveryList";
        }
        deliveryService.addDelivery(delivery);
        return "redirect:/deliveries/all";
    }

    @PostMapping("/update")
    public String updateDelivery(@Valid @ModelAttribute("delivery") DeliveryModel delivery, BindingResult result) {
        if (result.hasErrors()) {
            return "deliveryList";
        }
        deliveryService.updateDelivery(delivery);
        return "redirect:/deliveries/all";
    }

    @PostMapping("/delete")
    public String deleteDelivery(@RequestParam UUID id) {
        deliveryService.deleteDelivery(id);
        return "redirect:/deliveries/all";
    }

    @GetMapping("/all/{id}")
    public String getIdDelivery(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("delivery", deliveryService.findDeliveryById(id));
        model.addAttribute("couriers", courierService.findAllCouriers());
        model.addAttribute("orders", orderService.findAllOrders());
        return "deliveryList";
    }
}
