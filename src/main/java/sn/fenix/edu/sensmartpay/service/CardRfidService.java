package sn.fenix.edu.sensmartpay.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.fenix.edu.sensmartpay.model.CardRfid;
import sn.fenix.edu.sensmartpay.repository.CardRfidRepository;


import java.util.Optional;

@Data
@Service
public class CardRfidService {

    @Autowired
    private CardRfidRepository cardRfidRepository;

    public Optional<CardRfid> getCardRfid(final Long id){
        return cardRfidRepository.findById(id);
    }

    public Iterable<CardRfid> getCardRfids(){
        return cardRfidRepository.findAll();
    }

    public void deleteCardRfid(final Long id){
        cardRfidRepository.deleteById(id);
    }

    public void saveCardRfid(CardRfid cardRfid){
        cardRfidRepository.save(cardRfid);
    }


}
