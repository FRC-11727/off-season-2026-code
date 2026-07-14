// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  public final SparkMax intakeMotor;
  public final DigitalInput ballSensor;

  /** Creates a new ExampleSubsystem. */
  public IntakeSubsystem() {
    intakeMotor = new SparkMax(13, MotorType.kBrushless);
    ballSensor = new DigitalInput(0);
   
  }

  public void intakeSpeed(double speed) {
        intakeMotor.set(speed);
    }

    public void stopIntake() {
        intakeMotor.set(0);
    }

    public boolean hasBall() {
        return ballSensor.get();
    }
  /**
   * Example command factory method.
   
   * @return a command
   */
  public Command IntakeMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return Commands.startEnd(
        () -> intakeSpeed(0.8),
        () -> stopIntake()
        );
    
 }

 public Command intakeUntilBall() {
    return Commands.run(
        () -> intakeSpeed(0.8)

    )
    .until(this::hasBall)
    .finallyDo(interrupter -> stopIntake());
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
    SmartDashboard.putBoolean("Has Ball", hasBall());
    SmartDashboard.putNumber("Intake Speed", intakeMotor.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
