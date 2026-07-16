package frc.robot.subsystems;

import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TelemetryManager{
    private NetworkTableInstance inst;
    private NetworkTable intakeTable;
    private NetworkTable driveTable;
    private DoublePublisher intakeSpeedPub;
    private DoublePublisher intakeRPMPub;
    private DoublePublisher driveLeftSpeedPub;
    private DoublePublisher driveRightSpeedPub;

    public TelemetryManager() {
        setupTelemetry();
    }


    private void setupTelemetry() {
        inst = NetworkTableInstance.getDefault();
        
        intakeTable = inst.getTable("IntakeState");
        driveTable = inst.getTable("DriveState");
        
        intakeSpeedPub = intakeTable.getDoubleTopic("AppliedOutput").publish();
        intakeRPMPub = intakeTable.getDoubleTopic("VelocityRPM").publish();
        
        driveLeftSpeedPub = driveTable.getDoubleTopic("LeftAppliedOutput").publish();
        driveRightSpeedPub = driveTable.getDoubleTopic("RightAppliedOutput").publish();
    }
    public void telemeterizeIntake(double percentOutput, double velocityRPM) {
        intakeSpeedPub.set(percentOutput);
        intakeRPMPub.set(velocityRPM);
    }
    public void telemeterizeDrive(double leftPercent, double rightPercent) {
        driveLeftSpeedPub.set(leftPercent);
        driveRightSpeedPub.set(rightPercent);
    }
    
}



