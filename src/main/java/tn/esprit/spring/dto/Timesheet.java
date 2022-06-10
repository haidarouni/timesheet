package tn.esprit.spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;

@Getter
@Setter
@NoArgsConstructor
public class Timesheet {

    private Mission mission;
    private Employe employe;
    private boolean isValide;


}
