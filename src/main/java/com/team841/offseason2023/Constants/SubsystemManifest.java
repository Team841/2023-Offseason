package com.team841.offseason2023.Constants;

import com.team841.offseason2023.Drive.Drivetrain;
import com.team841.offseason2023.Superstructure.Intake;
import com.team841.offseason2023.Superstructure.Superstructure;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;

public final class SubsystemManifest {

  public static final Drivetrain drivetrain = new Drivetrain();
  public static final Superstructure superstructure = new Superstructure();
  public static final SuperstructureFactoryBeta factory =
      new SuperstructureFactoryBeta(superstructure);
  public static final Intake intake = new Intake();
}
