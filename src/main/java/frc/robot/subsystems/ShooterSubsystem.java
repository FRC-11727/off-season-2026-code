// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static final double SHOOT_SPEED = 1.0;
  private static final double IDLE_SPEED = 0.0;
  private final SparkMax shooterMotor;
  public ShooterSubsystem() {
    shooterMotor = new SparkMax(20, MotorType.kBrushless);
    


  }
  public void setShooterSpeed(double speed) {
    shooterMotor.set(speed);
  }
  public void stopShooterSpeed(){
    setShooterSpeed(IDLE_SPEED);
  }

  public boolean isAtSpeed() {
    return shooterMotor.get() >= SHOOT_SPEED -0.05;
  }
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command shooterMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return Commands.startEnd(
      () -> setShooterSpeed(SHOOT_SPEED),
      () -> stopShooterSpeed()

    );

        
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
