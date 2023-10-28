package com.team841.offseason2023.Auto.Paths;

import com.team841.offseason2023.Constants.SubsystemManifest;
import com.team841.offseason2023.Superstructure.Intake;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class scoreHighCone extends SequentialCommandGroup {

  private final SuperstructureFactoryBeta factory = SubsystemManifest.factory;

  // todo: test this code
  public scoreHighCone(Intake aIntake) {
    addCommands(
        factory.moveHighScoreCone().withTimeout(2),
        new InstantCommand(aIntake::ThrowCone, aIntake).withTimeout(1.5),
        factory.moveHome());
  }
}
