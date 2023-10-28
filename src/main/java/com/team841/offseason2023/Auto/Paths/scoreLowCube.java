package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Superstructure.Intake;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class scoreLowCube extends SequentialCommandGroup {

  public scoreLowCube(Intake aIntake) {
    addCommands(
        new InstantCommand(aIntake::ThrowCube, aIntake).withTimeout(3)
        // new AutoDriveToDistance(p_Drivetrain, -10)
        );
  }
}
