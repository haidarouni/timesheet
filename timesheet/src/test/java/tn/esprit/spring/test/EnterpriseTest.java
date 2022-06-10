package tn.esprit.spring.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.config.LoggingAspect;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EnterpriseTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IEntrepriseService iEntrepriseservice;
    private int idEntreprise;
    private int idDep;

    
    @Before
    public void createEnterprise(){
    	log.info("/************BEFORE Create Enterprise**************/");
        Entreprise entreprise=new Entreprise("Vermeg","Lac2");
    	Departement dep= new Departement("Informatique");
        idEntreprise=iEntrepriseservice.ajouterEntreprise(entreprise);  
        idDep=iEntrepriseservice.ajouterDepartement(dep);  

    }
    @Test
    public void addEnterprise() {
    	log.info("/************Test add Enterprise & Department**************/");
        assertTrue(idEntreprise>0);
        assertTrue(idDep>0);
    }
    
    @Test
    public void getEnterpriseById() {
    	log.info("/************Get Enterprise By Id**************/");
        Entreprise entreprise= iEntrepriseservice.getEntrepriseById(idEntreprise);
        assertNotNull(entreprise);
    }
    @Test
    public void affectDepartmentToEnterprise() {
    	log.info("/************Add Enterprise to Department**************/");
    	Departement dep= new Departement("Informatique");
    	int idDepartment=iEntrepriseservice.ajouterDepartement(dep);
    	iEntrepriseservice.affecterDepartementAEntreprise(idDepartment, idEntreprise);
    	
    }
    @Test
    public void getDepartmentsNamesByEnterprise() {
    	log.info("/************Add Enterprise to Department**************/");
    	iEntrepriseservice.getAllDepartementsNamesByEntreprise(idEntreprise);
    }
    @org.junit.After
    public void deleteEnterpriseById() {
         log.info("/************AFTER running test delete enterprise & Department by Id**************/");
      iEntrepriseservice.deleteEntrepriseById(idEntreprise);
      iEntrepriseservice.deleteDepartementById(idDep);

    }





}
