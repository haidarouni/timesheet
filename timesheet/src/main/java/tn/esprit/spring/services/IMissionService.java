package tn.esprit.spring.services;

import tn.esprit.spring.entities.Mission;

import java.util.List;


public interface IMissionService {

    public int addMission(Mission mission);

    public void deleteMissionById(int idMission);

    public void deleteMission(Mission mission);

    public List<Mission> getAllMissions();

    public Mission getMissionById(int idMission);


}
