// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// // drive command ; DriveBaseSubsystem, speed, rotation
// // shoot command ; ShooterSubsystem <- spin up shooter to constants rpm
// // stop shoot command ; ShooterSubsystem <- stops shooter

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.ShooterSubsystem;

// /** An example command that uses an example subsystem. */
//   public void DriveBaseCommand(DriveBaseSubsystem driveBaseSubsystem
//     DoubleSupplier speedSupplier,
//     DoubleSupplier rotationSupplier) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(D);
//   }
// //
//   /**
//    * Creates a new ExampleCommand.
//    *
//    * @param subsystem The subsystem used by this command.
//    */
//   public ExampleCommand(ShooterSubsystem subsystem) {
//     m_subsystem = subsystem;
//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(subsystem);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     m_subsystem.setShooterSpeed(0);
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {}

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {}

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
