package sn.fenix.edu.sensmartpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.fenix.edu.sensmartpay.model.CardRfid;
import sn.fenix.edu.sensmartpay.model.Transaction;
import sn.fenix.edu.sensmartpay.model.User;
import sn.fenix.edu.sensmartpay.service.CardRfidService;
import sn.fenix.edu.sensmartpay.service.TransactionService;
import sn.fenix.edu.sensmartpay.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class CardRfidController {

    @Autowired
    private CardRfidService cardRfidService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/cardRfids")
    public String cardRfids(Model model){
        Iterable<CardRfid> listCardRfid = cardRfidService.getCardRfids();
        model.addAttribute("cardRfids", listCardRfid);

        return "cardRfid";
    }

    @GetMapping("/updateCardRfid/{id}")
    public String updateCardRfid(Model model,@PathVariable Long id){
        Optional<CardRfid> getCardRfid = cardRfidService.getCardRfid(id);
        if(getCardRfid.isPresent()) {
            CardRfid cardRfidToUpdate = getCardRfid.get();
            model.addAttribute("cardRfidToUpdate", cardRfidToUpdate);
            return "updateCardRfid";
        }
        return null;
    }

    @PostMapping("/updateCardRfid/{id}")
    public String validateUpdateUser(@PathVariable Long id, @ModelAttribute CardRfid updateCardRfid, RedirectAttributes redirectAttributes) {
        Optional<CardRfid> verifieCardRfid = cardRfidService.getCardRfid(id);
        if(verifieCardRfid.isPresent()) {

            CardRfid existingCardRfid = verifieCardRfid.get();
            existingCardRfid.setUid(updateCardRfid.getUid());
            existingCardRfid.setIssueDate(updateCardRfid.getIssueDate());
            existingCardRfid.setExpirationDate(updateCardRfid.getExpirationDate());
            existingCardRfid.setSolde(updateCardRfid.getSolde());

            cardRfidService.saveCardRfid(existingCardRfid);
            redirectAttributes.addFlashAttribute("Message", "Votre card Rfidà jour avec succès.");
            //  return "redirect:/users"; // Page de redirection après modification
        } else {
            redirectAttributes.addFlashAttribute("Erreur", "Card Rfid non trouve");
            // return "error"; // Page de gestion des erreurs
        }

        return "redirect:/cardRfids";
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addCardRfid")
    public String addCardRfid(Model model){
        Iterable<User> listUser = userService.getUsers();
        model.addAttribute("users", listUser);
        return "addCardRfid";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/valideAddCarteRfid")
    public String valideAddcardRfid(CardRfid cardRfid) {
        cardRfidService.saveCardRfid(cardRfid);
        return "redirect:/cardRfids";
    }

    @GetMapping("/depot/{idCardDepot}")
    public String depot(Model model,@PathVariable Long idCardDepot){
        Optional<CardRfid> getCardRfid = cardRfidService.getCardRfid(idCardDepot);
        if(getCardRfid.isPresent()) {
            CardRfid cardRfidToDepot = getCardRfid.get();
            model.addAttribute("cardRfidToDepot", cardRfidToDepot);
            return "depot";
        }
        return null;
    }

    @PostMapping("/depot/{idCardDepot}")
    public String validateDepot(@PathVariable Long idCardDepot, @ModelAttribute CardRfid depotCardRfid,@RequestParam("depot") int depot, @RequestParam("lieu") String lieu,RedirectAttributes redirectAttributes) {
        Optional<CardRfid> verifieCardRfid = cardRfidService.getCardRfid(idCardDepot);
        if(verifieCardRfid.isPresent()) {
            CardRfid existingCardRfid = verifieCardRfid.get();
            existingCardRfid.setSolde(existingCardRfid.getSolde()+depot);
            cardRfidService.saveCardRfid(existingCardRfid);

            Transaction transaction = new Transaction();
            transaction.setCardRfid(existingCardRfid);
            transaction.setLieu(lieu);
            transaction.setTransactionType("depot");
            transaction.setDate(String.valueOf(LocalDate.now()));
            transaction.setMontant(depot);
            transactionService.saveTransaction(transaction);


            redirectAttributes.addFlashAttribute("Message", "Votre depot a ete effectue avec succès.");
            //  return "redirect:/users"; // Page de redirection après modification
        } else {
            redirectAttributes.addFlashAttribute("Erreur", "Card Rfid non trouve");
            // return "error"; // Page de gestion des erreurs
        }

        return "redirect:/transactions";
    }


    @GetMapping("/deleteCardRfid/{id}")
    public String supprimerCardRfid(@PathVariable Long id) {
        cardRfidService.deleteCardRfid(id);
        return "redirect:/cardRfids";
    }

}
