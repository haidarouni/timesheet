package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IMissionService;

import java.util.List;


@Controller
public class ControllerMissionImpl {

    @Autowired
    IMissionService missionService;

    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    public Mission getMissionById(int id) {
        return missionService.getMissionById(id);
    }

    public void deleteMission(Mission mission) {
        missionService.deleteMission(mission);
    }

    public void deleteMissionById(int id) {
        missionService.deleteMissionById(id);
    }

    public int addMission(Mission mission) {
        return missionService.addMission(mission);
    }

}
