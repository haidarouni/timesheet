package tn.esprit.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.services.IDepartementService;

@SpringBootTest
public class DepartementTest {

	@Autowired
	IDepartementService departementService;
	
	@Test
	void create() {
		int nbrDepartsBefore = departementService.getAllDepartements().size();
		Departement departement = new Departement();
		departement.setName("researchs");
		Departement savedDepartement = departementService.save(departement);
		assertNotNull(savedDepartement);
		int nbrDepartsafter = departementService.getAllDepartements().size();
		assertEquals(nbrDepartsBefore+1, nbrDepartsafter);
	}
	
	@Test
	void delete() {
		List<Departement> departs = departementService.getAllDepartements();
		if(departs.size()>0) {
			int nbrDepartsBefore =departs.size();
			departementService.delete(departs.get(0).getId());
			int nbrDepartsafter = departementService.getAllDepartements().size();
			assertEquals(nbrDepartsBefore-1, nbrDepartsafter);
		}
	}
	
	
}
