package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Auto.PIDControllers.AutoBalance;
import com.team841.offseason2023.Auto.PIDControllers.AutoDriveToDistance;
import com.team841.offseason2023.Drive.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class balance extends SequentialCommandGroup {

  public balance(Drivetrain aDrivetrain) {
    addCommands(new AutoDriveToDistance(aDrivetrain, -15),
                new AutoDriveToDistance(aDrivetrain, -15),
                new AutoDriveToDistance(aDrivetrain, -20),
                new AutoDriveToDistance(aDrivetrain, -55),
                new AutoBalance(aDrivetrain));
  }
}
