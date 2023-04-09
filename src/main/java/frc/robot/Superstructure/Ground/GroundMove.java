// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Superstructure.Ground;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.SC;
import frc.robot.Superstructure.States;
import frc.robot.Superstructure.Superstructure;
import frc.robot.Superstructure.setJointAngles;


public class GroundMove extends SequentialCommandGroup {

  private Superstructure superstructure;

  public GroundMove(Superstructure _Superstructure) {
    this.superstructure = _Superstructure;
    
    if (superstructure.getState() != States.Home){
      end(true);
    }

    superstructure.setInput(States.Ground);

    addCommands(
      new setJointAngles(_Superstructure, SC.PresetPositions.ExtendOut[0], SC.PresetPositions.ExtendOut[1]),
      new setJointAngles(_Superstructure, SC.PresetPositions.Ground[0], SC.PresetPositions.Ground[1])
    );
  }
}
