package frc.robot.commands;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveBaseSubsystem;
import frc.robot.Constants.RTAConstants;

// new command: rotateToAngle you rotate to a specific angle (45 degrees)
// it uses a PID
// it should be considered finished once you are within some angle
public class RotateToAngleCommands {
    private final DriveBaseSubsystem m_drive;
    private final double m_targetAngleDegrees;
    private final PIDController m_pid;

    private static final double kP = RTAConstants.kp2;
    private static final double kI = RTAConstants.ki2;
    private static final double kD = RTAConstants.kd2;
    private static final double kAngleToleranceDeg = RTAConstants.kAngleToleranceDeg;

  // Limits maximum turning speed so the robot doesn't spin out of control
    private static final double kMaxOutput = RTAConstants.kMaxOutput;
}
