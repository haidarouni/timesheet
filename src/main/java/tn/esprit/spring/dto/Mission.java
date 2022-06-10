package tn.esprit.spring.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Timesheet;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Mission {
    private String name;
    private String description;
    private Departement departement;
    private List<Timesheet> timesheets;
}
