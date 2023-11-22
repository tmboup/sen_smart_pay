package sn.fenix.edu.sensmartpay.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.fenix.edu.sensmartpay.model.User;
import sn.fenix.edu.sensmartpay.repository.UserRepository;

import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final Integer id){
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(final Integer id){
        userRepository.deleteById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }


}