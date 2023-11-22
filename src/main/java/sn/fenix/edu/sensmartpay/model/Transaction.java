package sn.fenix.edu.sensmartpay.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
@Data
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;


    private String lieu;

    @Column(nullable = false)
    private int montant;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String transactionType;

    @ManyToOne(cascade = CascadeType.ALL) // Add cascade here
    @JoinColumn(name = "idCardRfid", referencedColumnName = "idCardRfid")
    private CardRfid cardRfid;


}
