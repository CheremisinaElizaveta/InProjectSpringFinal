package com.example.InProject.controller;

import com.example.InProject.model.PaymentWayModel;
import com.example.InProject.service.PaymentWayService;
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
@RequestMapping("/paymentways")
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class PaymentWayController {
    @Autowired
    private PaymentWayService paymentWayService;

    @GetMapping("/all")
    public String getAllPaymentWays(Model model) {
        List<PaymentWayModel> paymentWays = paymentWayService.findAllPaymentWays();
        model.addAttribute("payments", paymentWays);
        model.addAttribute("payment", new PaymentWayModel());
        return "paymentWayList";
    }

    @PostMapping("/add")
    public String addPaymentWay(@Valid @ModelAttribute("payment") PaymentWayModel paymentWay, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("payments", paymentWayService.findAllPaymentWays());
            return "paymentWayList";
        }
        paymentWayService.addPaymentWay(paymentWay);
        return "redirect:/paymentways/all";
    }

    @PostMapping("/update")
    public String updatePaymentWay(@Valid @ModelAttribute("payment") PaymentWayModel paymentWay, BindingResult result) {
        if (result.hasErrors()) {
            return "paymentWayList";
        }
        paymentWayService.updatePaymentWay(paymentWay);
        return "redirect:/paymentways/all";
    }

    @PostMapping("/delete")
    public String deletePaymentWay(@RequestParam UUID id) {
        paymentWayService.deletePaymentWay(id);
        return "redirect:/paymentways/all";
    }

    @GetMapping("/all/{id}")
    public String getIdPaymentWay(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("payment", paymentWayService.findPaymentWayById(id));
        return "paymentWayList";
    }
}
