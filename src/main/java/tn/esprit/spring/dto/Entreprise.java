package tn.esprit.spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Departement;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Entreprise {
    private String name;
    private String raisonSocial;
    private List<Departement> departements = new ArrayList<>();
}
