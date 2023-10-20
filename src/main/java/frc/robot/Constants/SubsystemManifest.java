package frc.robot.Constants;

import frc.robot.Drive.Drivetrain;
import frc.robot.Superstructure.Intake;
import frc.robot.Superstructure.Superstructure;
import frc.robot.Superstructure.factory.SuperstructureFactoryBeta;

public final class SubsystemManifest {
    
    public static final Drivetrain drivetrain = new Drivetrain();
    public static final Superstructure superstructure = new Superstructure();
    public static final SuperstructureFactoryBeta factory = new SuperstructureFactoryBeta(superstructure);
    public static final Intake intake = new Intake();
}
