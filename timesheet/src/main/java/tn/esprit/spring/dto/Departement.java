package tn.esprit.spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Departement {

    private String name;

    private List<Employe> employes;

    private Entreprise entreprise;

    private List<Mission> missions;

}
