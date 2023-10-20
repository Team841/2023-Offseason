package com.team841.offseason2023.Superstructure;

import com.team841.offseason2023.Constants.SC;
import com.team841.offseason2023.states.States;
import com.team841.offseason2023.states.SuperstructureStatePreset;
import com.team841.offseason2023.util.BetterArrayList;

public class SuperstructureStateManager{
    
    private BetterArrayList<SuperstructureStatePreset> presets;

    public SuperstructureStateManager(){
        this.presets = new BetterArrayList<SuperstructureStatePreset>();
    }

    public void addPreset(SuperstructureStatePreset preset){
        try{
            this.presets.add(preset);
        } catch (Exception e){
            System.out.println("Error adding preset to SuperstructureStateManager. Returning false.");
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