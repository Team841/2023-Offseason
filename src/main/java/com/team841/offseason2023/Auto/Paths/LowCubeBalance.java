package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Auto.PIDControllers.AutoBalance;
import com.team841.offseason2023.Auto.PIDControllers.AutoDriveToDistance;
import com.team841.offseason2023.Drive.Drivetrain;
import com.team841.offseason2023.Superstructure.Intake;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class LowCubeBalance extends SequentialCommandGroup {

  public LowCubeBalance(Drivetrain aDrivetrain, Intake aIntake) {
    addCommands(aIntake.ThrowCube().withTimeout(2),
                new AutoDriveToDistance(aDrivetrain, -15),
                new AutoDriveToDistance(aDrivetrain, -15),
                new AutoDriveToDistance(aDrivetrain, -20),
                new AutoDriveToDistance(aDrivetrain, -55),
                new AutoBalance(aDrivetrain));
  }
}
