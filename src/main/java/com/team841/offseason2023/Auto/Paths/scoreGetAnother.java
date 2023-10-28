package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Auto.PIDControllers.AutoDriveToDistance;
import com.team841.offseason2023.Auto.PIDControllers.AutoTurn;
import com.team841.offseason2023.Constants.SubsystemManifest;
import com.team841.offseason2023.Drive.Drivetrain;
import com.team841.offseason2023.Superstructure.Intake;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class scoreGetAnother extends SequentialCommandGroup {

  private final SuperstructureFactoryBeta factory = SubsystemManifest.factory;

  public scoreGetAnother(Drivetrain aDrivetrain, Intake aIntake) {
    addCommands(
        // 1. move arm high position
        factory.moveHighScoreCube().withTimeout(2),
        // 2. spit out
        aIntake.ThrowCube().withTimeout(1.5),
        // 3. bring arm to home
        factory.moveHome().withTimeout(2),
        // 4. drive back 11ft to pick up cube

        new AutoDriveToDistance(aDrivetrain, -1),
        new AutoDriveToDistance(aDrivetrain, -5),
        new AutoDriveToDistance(aDrivetrain, -100),
        new AutoDriveToDistance(aDrivetrain, -50),
        new AutoDriveToDistance(aDrivetrain, -30),
        new AutoDriveToDistance(aDrivetrain, -10),

        // 5. turn 180
        new AutoTurn(aDrivetrain, 179).withTimeout(2),
        // 6. turn on intake
        new InstantCommand(aIntake::toggleIntakeIn, aIntake),
        // 7. set to ground
        factory.moveGround().withTimeout(2),
        // stop intake motors
        new InstantCommand(aIntake::stopMotor, aIntake),
        // bring arm home
        factory.moveHome().withTimeout(2),
        // turn 180
        new AutoTurn(aDrivetrain, 180).withTimeout(2),
        // drive back
        new AutoDriveToDistance(aDrivetrain, 70),
        new AutoDriveToDistance(aDrivetrain, 50),
        new AutoDriveToDistance(aDrivetrain, 30),
        // spit out cube
        aIntake.ThrowCube().withTimeout(1.5),
        factory.moveHome().withTimeout(2));
  }
}
