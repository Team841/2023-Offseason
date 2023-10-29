package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Auto.PIDControllers.AutoBalance;
import com.team841.offseason2023.Auto.PIDControllers.AutoDriveToDistance;
import com.team841.offseason2023.Auto.PIDControllers.AutoTurn;
import com.team841.offseason2023.Drive.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class driveOffAutoBalance extends SequentialCommandGroup {
  /** Creates a new driveOffAutoBalance. */
  public driveOffAutoBalance(Drivetrain a_Drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new AutoDriveToDistance(a_Drivetrain, 60),
        new AutoTurn(a_Drivetrain, 270),
        new AutoDriveToDistance(a_Drivetrain, 48),
        new AutoTurn(a_Drivetrain, 270),
        new AutoDriveToDistance(a_Drivetrain, 72),
        new AutoBalance(a_Drivetrain));
  }
}
