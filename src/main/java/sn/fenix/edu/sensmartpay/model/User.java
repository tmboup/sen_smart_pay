package sn.fenix.edu.sensmartpay.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

//insert into cardRfid(expiration_date,issue_date,solde,UID1,UID2,UID3,UID4) values('2023-12-12','2023-01-01',2000,1111,2222,3333,4444);

@Entity
@Table(name = "user")
@Data
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonne;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String telephone;

    @Column(nullable = false)
    private String dateNaissance;

    @Column(nullable = false)
    private String lieuNaissance;

    @Column(nullable = false)
    private String sexe;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CardRfid> cardRfids;


}
