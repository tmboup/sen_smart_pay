package sn.fenix.edu.sensmartpay.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.fenix.edu.sensmartpay.model.Transaction;
import sn.fenix.edu.sensmartpay.repository.TransactionRepository;


import java.util.Optional;

@Data
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> getTransaction(final Integer id){
        return transactionRepository.findById(id);
    }

    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(final Integer id){
        transactionRepository.deleteById(id);
    }

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }


}