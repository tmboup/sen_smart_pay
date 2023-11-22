package sn.fenix.edu.sensmartpay.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cardRfid")
@Data
public class CardRfid implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCardRfid;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = true)
    private LocalDate issueDate;

    @Column(nullable = true)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private int solde;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cardRfid", cascade = CascadeType.ALL, orphanRemoval = true) // Add cascade here
    private List<Transaction> transactions;



}
