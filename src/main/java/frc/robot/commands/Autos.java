// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DriveBaseSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command moveForwardAndShoot(DriveBaseSubsystem driveBaseSubsystem, ShooterSubsystem shooterSubsystem) {
    return Commands.sequence(
      new DriveCommands(driveBaseSubsystem, () -> -1, () -> 0),
      Commands.waitSeconds(2),
      new DriveCommands(driveBaseSubsystem, () -> 0, () -> 0),
      new ShooterCommands(shooterSubsystem),
      Commands.waitSeconds(5),
      new ShooterStopCommands(shooterSubsystem)
  );
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
