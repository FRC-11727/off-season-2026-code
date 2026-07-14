// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBaseSubsystem extends SubsystemBase {
  private SparkMax motortopleft = null;
  private SparkMax motortopright = null;
  private SparkMax motorbottomleft = null;
  private SparkMax motorbottomright = null;
  private DifferentialDrive drive;
  /** Creates a new ExampleSubsystem. */
  public DriveBaseSubsystem(DifferentialDrive drive) {
    this.drive = null;
    motortopleft = new SparkMax(1, MotorType.kBrushless);
    motortopright = new SparkMax(2, MotorType.kBrushless);
    motorbottomleft = new SparkMax(3, MotorType.kBrushless);
    motorbottomright = new SparkMax(4, MotorType.kBrushless);


    
  }

  public DriveBaseSubsystem(){
    this.drive = null;
    SparkMaxConfig motortopleftconfig = new SparkMaxConfig();
    SparkMaxConfig motortoprightconfig = new SparkMaxConfig();
    SparkMaxConfig motorbottomleftconfig = new SparkMaxConfig();
    SparkMaxConfig motorbottomrightconfig = new SparkMaxConfig();

    motorbottomleftconfig.smartCurrentLimit(40);
    motortopleftconfig.smartCurrentLimit(40);
    motorbottomrightconfig.smartCurrentLimit(40);
    motorbottomleftconfig.smartCurrentLimit(40);

    motortopleftconfig.inverted(false);
    motortoprightconfig.inverted(true);

    motorbottomleftconfig.follow(motortopleft, true);
    motorbottomrightconfig.follow(motortopright, true);


   
    
    

    motorbottomleft.configure(motorbottomleftconfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    motortopleft.configure(motortopleftconfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    motorbottomright.configure(motorbottomrightconfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    motortopright.configure(motortoprightconfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);

    drive = new DifferentialDrive(motortopleft::set, motortopright::set);

    
  }


  public void arcade( double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  public void stop() {
    drive.stopMotor();
  }
  /**
   * Example command factory method.
   *
   * @return a command
   */

 // public Command DriveBaseMethodCommand(DoubleSupplier speed, DoubleSupplier rotation) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    //return Command
    //    () -> arcadeDrive(speed.getAsDouble()),
     //   () -> arcadeDrive(rotation.getAsDouble())
          /* one-time action goes here */
   // );
 // }

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
    SmartDashboard.putNumber("Left Speed", motortopleft.get());
    SmartDashboard.putNumber("Right Speed", motortopright.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
