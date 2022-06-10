package tn.esprit.spring.test;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.config.LoggingAspect;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

import tn.esprit.spring.services.IEmployeService;

import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.logging.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IEmployeService iemployeservice;
    private int idEmploye;


    @Before
    public void createEmploye(){
        System.out.println("before insert");
        Employe employe=new Employe("Boundka","Eya","eya.boundka@esprit.tn",true, Role.INGENIEUR);
        l.info("testing adding employe");
        idEmploye=iemployeservice.addOrUpdateEmploye(employe);
        //test
    }


    @Test
    public void testAjouterEmploye() {

    	l.info("ajout employe");
        assertTrue(idEmploye>0);
    }

    @Test
    public void testModifierEmploye() {

    	l.info("modifier employe");
        iemployeservice.mettreAjourEmailByEmployeId("y@y",idEmploye);
        //assertTrue(idEmploye>0);
    }

    @After()
    public  void deleteEmploye(){
    	l.info("Delete after");
        iemployeservice.deleteEmployeById(idEmploye);
    }




}
