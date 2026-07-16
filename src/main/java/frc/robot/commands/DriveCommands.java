// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// drive command ; DriveBaseSubsystem, speed, rotation
// shoot command ; ShooterSubsystem <- spin up shooter to constants rpm
// stop shoot command ; ShooterSubsystem <- stops shooter

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveBaseSubsystem;
import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class DriveCommands extends Command {
  @SuppressWarnings("PMD.UnusedPrivateField")
  private final DriveBaseSubsystem m_DriveBaseSubsystem;

  private final DoubleSupplier m_speedSupplier;
  private final DoubleSupplier m_rotationSupplier;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommands(
      DriveBaseSubsystem subsystem, DoubleSupplier speedSupplier, DoubleSupplier rotationSupplier) {
    m_DriveBaseSubsystem = subsystem;
    m_speedSupplier = speedSupplier;
    m_rotationSupplier = rotationSupplier;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_DriveBaseSubsystem);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveBaseSubsystem.arcade(m_speedSupplier, m_rotationSupplier);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_DriveBaseSubsystem.arcade(m_speedSupplier.getAsDouble(), m_rotationSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
