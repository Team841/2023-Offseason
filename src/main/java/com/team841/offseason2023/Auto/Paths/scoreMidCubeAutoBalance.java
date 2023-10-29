package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Auto.PIDControllers.AutoBalance;
import com.team841.offseason2023.Auto.PIDControllers.AutoDriveToDistance;
import com.team841.offseason2023.Constants.SubsystemManifest;
import com.team841.offseason2023.Drive.Drivetrain;
import com.team841.offseason2023.Superstructure.Intake;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class scoreMidCubeAutoBalance extends SequentialCommandGroup {

  private final SuperstructureFactoryBeta factory = SubsystemManifest.factory;

  public scoreMidCubeAutoBalance(Drivetrain aDrivetrain, Intake aIntake) {

    addCommands(
        aIntake.IntakeCube().withTimeout(0.1),
        new InstantCommand(aIntake::stopMotor, aIntake).withTimeout(0.2),
        factory.moveMidScoreCube(),
        aIntake.ThrowCube().withTimeout(2),
        factory.moveHome(),
        new InstantCommand(aIntake::stopMotor, aIntake).withTimeout(0.2),
        new AutoDriveToDistance(aDrivetrain, -15),
        new AutoDriveToDistance(aDrivetrain, -15),
        new AutoDriveToDistance(aDrivetrain, -20),
        new AutoDriveToDistance(aDrivetrain, -55),
        new AutoBalance(aDrivetrain));
  }
}
