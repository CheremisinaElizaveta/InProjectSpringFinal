package com.example.InProject.controller;

import com.example.InProject.model.StatusOrderModel;
import com.example.InProject.service.StatusOrderService;
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
@RequestMapping("/orderstatuses")
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class StatusOrderController {
    @Autowired
    private StatusOrderService statusOrderService;

    @GetMapping("/all")
    public String getAllOrderStatuses(Model model) {
        List<StatusOrderModel> orderStatuses = statusOrderService.findAllOrderStatuses();
        model.addAttribute("orderStatuses", orderStatuses);
        model.addAttribute("statusOrder", new StatusOrderModel());
        return "orderStatusList";
    }

    @PostMapping("/add")
    public String addOrderStatus(@Valid @ModelAttribute("statusOrder") StatusOrderModel statusOrder, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderStatuses", statusOrderService.findAllOrderStatuses());
            return "orderStatusList";
        }
        statusOrderService.addOrderStatus(statusOrder);
        return "redirect:/orderstatuses/all";
    }

    @PostMapping("/update")
    public String updateOrderStatus(@Valid @ModelAttribute("statusOrder") StatusOrderModel statusOrder, BindingResult result) {
        if (result.hasErrors()) {
            return "orderStatusList";
        }
        statusOrderService.updateOrderStatus(statusOrder);
        return "redirect:/orderstatuses/all";
    }

    @PostMapping("/delete")
    public String deleteOrderStatus(@RequestParam UUID id) {
        statusOrderService.deleteOrderStatus(id);
        return "redirect:/orderstatuses/all";
    }

    @GetMapping("/all/{id}")
    public String getIdOrderStatus(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("statusOrder", statusOrderService.findOrderStatusById(id));
        return "orderStatusList";
    }
}
