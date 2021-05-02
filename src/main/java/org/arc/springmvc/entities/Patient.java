package org.arc.springmvc.entities;

import java.util.Date;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity // pour l utilisation de JPA
@Data @AllArgsConstructor @NoArgsConstructor @ToString // lombok Notations


public class Patient{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // incrémentation automatique de la cle id

    private Long id;
   // @Column(name = "name", length = 25) //Notation JPA pour précise la longeur du nom
    @NotNull
    @Size(min=5,max=15)// Notation Spring pour verifie la taille du champ
    private String name;
    @Temporal(TemporalType.DATE)// Notation JPA pour type de date
    @DateTimeFormat(pattern = "yyyy-MM-dd")// Notation Spring pour type de date
    private Date dateNaissance;

    private Integer score;
    private boolean malade;


}
