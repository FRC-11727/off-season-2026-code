// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.ctre.phoenix6.hardware.Pigeon2;

// implement a gyro using a pigeon2
// 1. install CTRE phoenix 6 venderdep VVVVVVV
// 2. create a pigeon 
// 3. integrate that with your current odometry


public class DriveBaseSubsystem extends SubsystemBase {
  // Motor Controllers
  private final SparkMax motortopleft;
  private final SparkMax motortopright;
  private final SparkMax motorbottomleft;
  private final SparkMax motorbottomright;
  private final DifferentialDrive drive;

  // Encoders
  private final RelativeEncoder m_leftEncoder;
  private final RelativeEncoder m_rightEncoder;

  // Odometry & Field Visualization
  private final DifferentialDriveOdometry m_odometry;
  private final Field2d m_field = new Field2d();
  private final Pigeon2 m_pigeon;

  // Robot Physical Constants (Adjust wheel diameter & gear ratio to your physical robot!)
  private static final double kWheelDiameterMeters = 0.1524; // 6 inches in meters
  private static final double kGearRatio = 8.45;              // Example drivetrain gear ratio (8.45:1)
  private static final double kTrackWidthMeters = 0.65;       // Distance between left and right wheels in meters

  
  public DriveBaseSubsystem() {
    motortopleft = new SparkMax(DriveConstants.kMotorTL1, MotorType.kBrushless);
    motortopright = new SparkMax(DriveConstants.kMotorTR2, MotorType.kBrushless);
    motorbottomleft = new SparkMax(DriveConstants.kMotorBL3, MotorType.kBrushless);
    motorbottomright = new SparkMax(DriveConstants.kMotorBR4, MotorType.kBrushless);

    m_leftEncoder = motortopleft.getEncoder();
    m_rightEncoder = motortopright.getEncoder();
    m_pigeon = new Pigeon2(DriveConstants.kPigeonCANID);
    
    // Reset Pigeon heading to 0 degrees on startup
    m_pigeon.reset();

    double positionFactor = (Math.PI * kWheelDiameterMeters) / kGearRatio;
    double velocityFactor = positionFactor / 60.0;
    SparkMaxConfig motortopleftconfig = new SparkMaxConfig();
    SparkMaxConfig motortoprightconfig = new SparkMaxConfig();
    SparkMaxConfig motorbottomleftconfig = new SparkMaxConfig();
    SparkMaxConfig motorbottomrightconfig = new SparkMaxConfig();

    motortopleftconfig.encoder
        .positionConversionFactor(positionFactor)
        .velocityConversionFactor(velocityFactor);

    motortoprightconfig.encoder
        .positionConversionFactor(positionFactor)
        .velocityConversionFactor(velocityFactor);

    // Set Smart Current Limits
    motortopleftconfig.smartCurrentLimit(DriveConstants.kstall);
    motortoprightconfig.smartCurrentLimit(DriveConstants.kstall);
    motorbottomleftconfig.smartCurrentLimit(DriveConstants.kstall);
    motorbottomrightconfig.smartCurrentLimit(DriveConstants.kstall);

    // Motor Inversions
    motortopleftconfig.inverted(false);
    motortoprightconfig.inverted(true);

    // Followers
    motorbottomleftconfig.follow(motortopleft, true);
    motorbottomrightconfig.follow(motortopright, true);

    // Apply Configurations to Hardware
    motorbottomleft.configure(
        motorbottomleftconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motortopleft.configure(
        motortopleftconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorbottomright.configure(
        motorbottomrightconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motortopright.configure(
        motortoprightconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Reset Encoder Positions to 0 on boot
    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);

    // Setup Differential Drive
    drive = new DifferentialDrive(motortopleft::set, motortopright::set);

    // Initialize Odometry at (0,0) facing 0 degrees
    
    m_odometry = new DifferentialDriveOdometry(
        m_pigeon.getRotation2d(),
        m_leftEncoder.getPosition(), 
        m_rightEncoder.getPosition()
    );

    // Send 2D Field Widget to SmartDashboard
    SmartDashboard.putData("Field", m_field);
  }

  /** Drives the robot using arcade controls. */
  public void arcade(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  /** Stops the drive motors. */
  public void stop() {
    drive.stopMotor();
  }
  public void zeroHeading() {
    m_pigeon.reset();
  }
  public Rotation2d getHeading() {
    return m_pigeon.getRotation2d();
  }

  /** Gets the current estimated robot pose (position & heading). */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  @Override
  public void periodic() {
    // Read encoder values (now returned in meters)
    double leftDistance = m_leftEncoder.getPosition();
    double rightDistance = m_rightEncoder.getPosition();

    // Calculate heading angle in radians from difference in wheel travel
    // double calculatedHeadingRadians = (rightDistance - leftDistance) / kTrackWidthMeters;
    // Rotation2d currentHeading = new Rotation2d(calculatedHeadingRadians);

    // Update Odometry position tracker
    m_odometry.update(getHeading(), leftDistance, rightDistance);

    // Update Field2d visualizer position
    m_field.setRobotPose(m_odometry.getPoseMeters());

    // Dashboard Telemetry
    SmartDashboard.putNumber("Left Distance (m)", leftDistance);
    SmartDashboard.putNumber("Right Distance (m)", rightDistance);
    SmartDashboard.putNumber("Estimated Heading (deg)", getHeading().getDegrees());
  }
}