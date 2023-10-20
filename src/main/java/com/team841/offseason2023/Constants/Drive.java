// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team841.offseason2023.Constants;

public final class Drive {


    public static final class Drivetrain{
        public static int currentLimit = 60;

        //Physical setup of the drive
        public static double gearRatio = 1/7.23;
        public static double wheelDiameter = 6.06;
        public static double countsPerRev = 42; // this is the neo brushless

        //PID constants for turning, specifically for AutoTurn command//
        public static final double turn_kp = 0.018275; 
        public static final double turn_ki = 0;
        public static final double turn_kd = 0.00175;
        public static final double turn_tolerance = 0.4;
        public static final double turn_velocity_tolerance = 0.025; 
        public static final double antiWindUp = 0.1;

        //PID constants for AutoDriveToDistance command
        public static final double distance_kp = 0.040;   
        public static final double distance_ki = 0.0001; 
        public static final double distance_kd = 1;     
        public static final double distance_kff = 0;
        public static final double distance_tolerance = 2; // in revolutions
        public static final double distance_kIz = 2; // in revolutions
        
        //PID constants for AutoBalance command
        public static final double balance_kp = 0.006;
        public static final double balance_ki = 0;
        public static final double balance_kd = 0; 

        public static final double balance_point = 0; //0 degrees

        public static final int Brake = 6;
        public static final int Brake_b = 7;
    }

    public static final class Drivestyle{
        //Drive Style definition, loop up. Don't change this! This is needed for the subsystem.
        public static final int tankdrive = 1;
        public static final int chezydrive = 2;

        //Drive. We can change this.
        public static int drivestyle = chezydrive;
        public static int invert = -1;

        public static double slowModeScaleFactor = 0.25;

        //Tuning the Chezy Drive - deadband, sensitivity & tolerancing values on raw joystick inputs
        public static final double throttleDeadband = 0.02; 
        public static final double wheelDeadband = 0.02;	
        public static final double sensitivityHigh = 0.5;	
        public static final double sensitivityLow = 0.5; // (**deafult uses low gear**)


        public static final double wheelNonLinearityHighGear = 0.5; //Chezy Drive non-linearity
        public static final double wheelNonLinearityLowGear = 0.6; //Chezy Drive non-linearity  (**deafult uses low gear**)

        public static final double QuickTurnSensitivityHigh = 0.005; //Chezy Drive quick turn sensitivity
        public static final double QuickTurnSensitivityLow = 0.005; //Chezy Drive quick turn sensitivity  (**deafult uses low gear**)

    }
}
