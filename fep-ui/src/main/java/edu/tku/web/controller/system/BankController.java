package edu.tku.web.controller.system;

import edu.tku.db.model.Bank;
import edu.tku.db.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BankController {
    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/bank")
    public String page(Model model, @RequestParam(name = "bankId", required = false) String bankId) {
        List<Bank> banks = new ArrayList<>();
        if(bankId != null && !bankId.equals("")) {
            bankRepository.findById(bankId).ifPresent(bank -> banks.add(bank));
        }else{
            banks.addAll(bankRepository.findAll());
        }
        model.addAttribute("banks", banks);
        return "system/bank";
    }
    @GetMapping("/bank/detail")
    public String pageDetail(Model model, @RequestParam(name = "bankId", required = false) String bankId) {
        Bank bank = bankRepository.findById(StringUtils.defaultString(bankId, "")).orElse(new Bank());
        model.addAttribute("bank", bank);
        return "system/bankDetail";
    }
    @PostMapping("/bank")
    public String pageDetail(Model model, @ModelAttribute Bank bank) {
        if(bank.getAction().equals("D")) {
            bankRepository.deleteById(bank.getBankId());
        }else {
            bankRepository.save(bank);
        }
        model.addAttribute("banks", bankRepository.findAll());
        return "system/bank";
    }

}
