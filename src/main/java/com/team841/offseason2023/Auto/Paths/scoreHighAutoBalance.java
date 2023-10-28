package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Auto.PIDControllers.AutoBalance;
import com.team841.offseason2023.Auto.PIDControllers.AutoDriveToDistance;
import com.team841.offseason2023.Constants.SubsystemManifest;
import com.team841.offseason2023.Drive.Drivetrain;
import com.team841.offseason2023.Superstructure.Intake;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class scoreHighAutoBalance extends SequentialCommandGroup {

  private final SuperstructureFactoryBeta factory = SubsystemManifest.factory;

  public scoreHighAutoBalance(Drivetrain aDrivetrain, Intake aIntake) {

    addCommands(
        new InstantCommand(aIntake::IntakeCone, aIntake).withTimeout(1),

        // new InstantCommand(() -> cSuperstructure.stopMotor(), cSuperstructure),
        factory.moveHighScoreCone().withTimeout(2),
        new InstantCommand(aIntake::ThrowCone, aIntake).withTimeout(1.5),
        factory.moveHome(),
        new AutoDriveToDistance(aDrivetrain, -15),
        new AutoDriveToDistance(aDrivetrain, -15),
        new AutoDriveToDistance(aDrivetrain, -20),
        new AutoDriveToDistance(aDrivetrain, -55),
        new AutoBalance(aDrivetrain));
  }
}
