package sn.fenix.edu.sensmartpay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.fenix.edu.sensmartpay.model.Transaction;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {

}
