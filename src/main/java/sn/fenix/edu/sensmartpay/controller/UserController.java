package sn.fenix.edu.sensmartpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.fenix.edu.sensmartpay.model.User;
import sn.fenix.edu.sensmartpay.service.UserService;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String users(Model model){
        Iterable<User> listUser = userService.getUsers();
        model.addAttribute("users", listUser);
        return "user";
    }

    @GetMapping("/updateUser/{idPersonne}")
    public String updateUser(Model model,@PathVariable Integer idPersonne){
        Optional<User> getUser = userService.getUser(idPersonne);
        if(getUser.isPresent()) {
            model.addAttribute("userToUpdate", getUser.get());
            return "updateUser";
        }
        return "users";
    }




    @PostMapping("/updateUser/{idPersonne}")
    public String validateUpdateUser(@PathVariable Integer idPersonne, @ModelAttribute User updatedUser,RedirectAttributes redirectAttributes) {
        Optional<User> userFind = userService.getUser(idPersonne);
        if(userFind.isPresent()) {
            User user = userFind.get();

            user.setPrenom(updatedUser.getPrenom());
            user.setNom(updatedUser.getNom());
            user.setEmail(updatedUser.getEmail());
            user.setSexe(updatedUser.getSexe());
            user.setTelephone(updatedUser.getTelephone());
            user.setDateNaissance(updatedUser.getDateNaissance());
            user.setLieuNaissance(updatedUser.getLieuNaissance());

            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message", "Utilisateur mis à jour avec succès.");
          //  return "redirect:/users"; // Page de redirection après modification
            } else {
                redirectAttributes.addFlashAttribute("message", "Utilisateur mis à jour avec succès.");
               // return "error"; // Page de gestion des erreurs
            }

        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addUser")
    public String addUser(){
        return "addUser";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/valideAddUser")
    public String valideAddUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }


    // cette fonction permet de supprimer un utilisateur
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }




}
