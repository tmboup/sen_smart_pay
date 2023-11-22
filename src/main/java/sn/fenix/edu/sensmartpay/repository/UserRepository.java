package sn.fenix.edu.sensmartpay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.fenix.edu.sensmartpay.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

}
