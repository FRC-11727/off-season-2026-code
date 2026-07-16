// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ShooterConstants {
    public static final int kShooterMotorID = 5;
    public static final double kTARGET_SHOOT_RPM = 4500.0;
    public static final double kIDLE_SPEED = 0.0;
    public static final double kp = 0.01;
    public static final double ki = 0;
    public static final double kd = 0.001;
  }

  public static class IntakeConstants {
    public static final int kIntakeMotorID = 13;
    public static final int kBallChannel = 0;
    public static final int kspeed = 0;
    public static final double kIntakespeed = 0.8;
  }

  public static class DriveConstants{
    public static final int kMotorTL1 = 1;
    public static final int kMotorTR2 = 2;
    public static final int kMotorBL3 = 3;
    public static final int kMotorBR4 = 4;
    public static final int kstall = 40;
  }
}



