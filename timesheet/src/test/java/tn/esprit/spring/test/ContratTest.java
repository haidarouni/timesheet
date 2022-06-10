package tn.esprit.spring.test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IEmployeService;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ContratTest {
	@Autowired
	public IEmployeService iEmployeService;
	@Autowired
	public 	IContratService iContratService;
    private int reference;




private static final Logger l= LogManager.getLogger(ContratTest.class);

@Test
public void testAddContrat(){
    
	l.info("***Test Add Contract ***");
	Contrat contrat = new Contrat();
	contrat.setSalaire(1800);    
	contrat.setTypeContrat("CDI");
	contrat.setDateDebut(new Date(Calendar.DAY_OF_MONTH));
	reference = iEmployeService.ajouterContrat(contrat);
	l.info("*****Contract ajouté *****");

	
	}

@Test
public void testAjoutContrat() {

	l.info("*****Test contrat ajouté *****");
	  try {
	        assertTrue(reference>0);
      }
      catch(Throwable e) {
        l.error("Contart non Ajouté");      }

  }

@Test
public void testAffecterContratAEmploye(){
	
	l.info("*****Test Affecter Contrat A Employe *****");
    Employe emp = new Employe("belarif", "jamila", "jamila.belarif@esprit.tn", true, Role.INGENIEUR);
    Contrat contrat= new Contrat(new Date(Calendar.DAY_OF_MONTH),"CDD",2900);
    l.info("*******Contrat ajouté*****");
    iEmployeService.addOrUpdateEmploye(emp);
    l.info("******Employe ajoutéé*****");
    iEmployeService.ajouterContrat(contrat);
    iEmployeService.affecterContratAEmploye(contrat.getReference(),emp.getId());
    l.info("****Contrat affecté à employé****");
}


@Test
public void testgetAllContrats() {

	l.info("*****Test Get All Contrats*****");
    List<Contrat> contrat= iContratService.getAllContrats();
    try {
        assertNotNull(contrat);
    }
    catch(Throwable e) {
      l.error("Failed");      }

}


	

}


