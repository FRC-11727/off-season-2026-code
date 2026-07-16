// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static final double TARGET_SHOOT_RPM = 4500.0;

  private final SparkClosedLoopController closedLoopController;
  private static final double IDLE_SPEED = 0.0;
  private final SparkMax shooterMotor;

  public ShooterSubsystem() {
    shooterMotor = new SparkMax(20, MotorType.kBrushless);

    closedLoopController = shooterMotor.getClosedLoopController();

    SparkMaxConfig config = new SparkMaxConfig();
    config.closedLoop.pid(0.01, 0, 0.001);

    shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setShooterSpeed(double targetRPM) {
    closedLoopController.setSetpoint(
        targetRPM, com.revrobotics.spark.SparkBase.ControlType.kVelocity);
  }

  public void stopShooterSpeed() {
    setShooterSpeed(IDLE_SPEED);
  }

  public boolean isAtSpeed() {
    double currentRPM = shooterMotor.getEncoder().getVelocity();
    return Math.abs(currentRPM - TARGET_SHOOT_RPM) <= 150.0;
  }
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command shooterMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return Commands.startEnd(() -> setShooterSpeed(TARGET_SHOOT_RPM), () -> stopShooterSpeed());
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Shooter at Speed", isAtSpeed());
    SmartDashboard.putNumber("Shooter Speed", shooterMotor.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
