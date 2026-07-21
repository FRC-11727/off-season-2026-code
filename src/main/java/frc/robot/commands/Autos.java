// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DriveBaseSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
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

  public static Command moveBackIntakeAndShoot(
      IntakeSubsystem intakeSubsystem,
      DriveBaseSubsystem driveBaseSubsystem, 
      ShooterSubsystem shooterSubsystem) {

    return Commands.sequence(
        // 1. Reset gyro heading at start
        Commands.runOnce(driveBaseSubsystem::zeroHeading, driveBaseSubsystem),

        // 2. Move backward initially
        new DriveCommands(driveBaseSubsystem, () -> 0.5, () -> 0),
        Commands.waitSeconds(1.5),

        // 3. Turn ~45 degrees towards the intake spot
        new DriveCommands(driveBaseSubsystem, () -> 0, () -> 0.4),
        Commands.waitSeconds(0.8),

        // 4. Pause briefly
        new DriveCommands(driveBaseSubsystem, () -> 0, () -> 0),
        Commands.waitSeconds(0.5),

        // 5. Drive forward toward intake spot & intake item
        Commands.race(
            new DriveCommands(driveBaseSubsystem, () -> -0.5, () -> 0),
            Commands.run(() -> intakeSubsystem.intakeUntilBall(), intakeSubsystem).withTimeout(2.0)
        ),

        // 6. DRIVE BACKWARD out of the intake area to return to shooting distance
        new DriveCommands(driveBaseSubsystem, () -> 0.5, () -> 0).withTimeout(2.0),

        // 7. Turn back -45 degrees to realign with the goal
        new DriveCommands(driveBaseSubsystem, () -> 0, () -> -0.4).withTimeout(0.8),

        // 8. Stop driving to stabilize
        new DriveCommands(driveBaseSubsystem, () -> 0, () -> 0).withTimeout(0.1),

        // 9. SHOOT!
        new ShooterCommands(shooterSubsystem).withTimeout(3.0),
        new ShooterStopCommands(shooterSubsystem)
    );
  }

  // move back, turn 45 degrees, keep moving back, go to intake spot, get intake, shoooooooooooooooooooot

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
