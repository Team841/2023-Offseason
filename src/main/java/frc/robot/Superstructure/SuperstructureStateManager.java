package frc.robot.Superstructure;

import frc.robot.Constants.SC;
import frc.robot.states.States;
import frc.robot.states.SuperstructureStatePreset;
import frc.robot.util.BetterArrayList;

public class SuperstructureStateManager{
    
    private BetterArrayList<SuperstructureStatePreset> presets;

    public SuperstructureStateManager(){
        this.presets = new BetterArrayList<SuperstructureStatePreset>();
    }

    public boolean addPreset(SuperstructureStatePreset preset){
        try{
            this.presets.add(preset);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void updateState(Double ShoulderAngle, Double ElbowAngle){
        
        BetterArrayList<SuperstructureStatePreset> possibleStates = new BetterArrayList<SuperstructureStatePreset>();
        
        for (SuperstructureStatePreset preset : this.presets){
            if (preset.inPreset(ShoulderAngle, ElbowAngle)){
                possibleStates.add(preset);
            }
        }
        
        if (possibleStates.size() == 1){
            SC.superstructureState = possibleStates.get(0).getState();
        } else if (possibleStates.size() == 0){
            SC.superstructureState = States.Nothing;
        } else {
            System.out.println("Multiple states found for current position. Returning false.");
            SC.superstructureState = States.Nothing;
        }

    }
}