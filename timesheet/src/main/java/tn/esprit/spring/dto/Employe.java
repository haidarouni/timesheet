package tn.esprit.spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Employe {

    private String prenom;

    private String nom;

    private String email;

    private String password;

    private boolean actif;

    private Role role;

    private Contrat contrat;

    private List<Departement> departements;
    
    private List<Timesheet> timesheets;
}
