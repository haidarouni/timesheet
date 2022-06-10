package tn.esprit.spring.dto;


import java.util.Date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Employe;


@Getter
@Setter
@NoArgsConstructor
public class Contrat {
	private Date dateDebut;

	private String typeContrat;

	private Employe employe;

	private float salaire;


}
