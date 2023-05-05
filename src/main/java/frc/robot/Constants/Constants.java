// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Constants;


public final class Constants {

    public static boolean isDisabled = true;

    public static final class OI{
        public static final int driverPortLeft = 0; //controller USB port 0
        public static final int driverPortRight = 1; //controller USB port 1
        public static final int codriverPort = 2; //controller USB port 2
    }

    public static final class CANid{
        public static final int driveRight1 = 1;
        public static final int driveRight2 = 2; 
        public static final int driveLeft1 = 3;
        public static final int driveLeft2 = 4;

        public static final int shoulderMotor_Starboard = 9;
        public static final int shoulderMotor_Port = 10;
        public static final int elbowMotor = 11;

        public static final int IntakeNeo = 6;
        public static final int IntakeTalon = 7;
    }

    public static final class Tune{
        public static final boolean tuning = false;
    }
}
