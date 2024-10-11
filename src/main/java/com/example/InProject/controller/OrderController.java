package com.example.InProject.controller;

import com.example.InProject.model.OrderModel;
import com.example.InProject.service.*;
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
@RequestMapping("/orders")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private StatusOrderService statusOrderService;

    @Autowired
    private PaymentWayService paymentWayService;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getAllOrders(Model model) {
        List<OrderModel> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("order", new OrderModel());
        model.addAttribute("addresses", addressService.findAllAddresses());
        model.addAttribute("deliveries", deliveryService.findAllDeliveries());
        model.addAttribute("statuses", statusOrderService.findAllOrderStatuses());
        model.addAttribute("payments", paymentWayService.findAllPaymentWays());
        model.addAttribute("products", productService.findAllProducts());
        return "orderList";
    }

    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute("order") OrderModel order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orders", orderService.findAllOrders());
            model.addAttribute("addresses", addressService.findAllAddresses());
            model.addAttribute("deliveries", deliveryService.findAllDeliveries());
            model.addAttribute("statuses", statusOrderService.findAllOrderStatuses());
            model.addAttribute("payments", paymentWayService.findAllPaymentWays());
            model.addAttribute("products", productService.findAllProducts());
            return "orderList";
        }
        orderService.addOrder(order);
        return "redirect:/orders/all";
    }

    @PostMapping("/update")
    public String updateOrder(@Valid @ModelAttribute("order") OrderModel order, BindingResult result) {
        if (result.hasErrors()) {
            return "orderList";
        }
        orderService.updateOrder(order);
        return "redirect:/orders/all";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam UUID id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/all";
    }

    @GetMapping("/all/{id}")
    public String getIdOrder(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("order", orderService.findOrderById(id));
        model.addAttribute("addresses", addressService.findAllAddresses());
        model.addAttribute("deliveries", deliveryService.findAllDeliveries());
        model.addAttribute("statuses", statusOrderService.findAllOrderStatuses());
        model.addAttribute("payments", paymentWayService.findAllPaymentWays());
        model.addAttribute("products", productService.findAllProducts());
        return "orderList";
    }
}
