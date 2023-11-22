package sn.fenix.edu.sensmartpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sn.fenix.edu.sensmartpay.model.Transaction;
import sn.fenix.edu.sensmartpay.service.TransactionService;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String transaction(Model model){
        Iterable<Transaction> listTransaction = transactionService.getTransactions();
        model.addAttribute("transactions",listTransaction);

        return "transaction";
    }

}
