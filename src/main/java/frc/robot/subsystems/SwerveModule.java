package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule {
    private final TalonFX driveMotor;
    private final TalonFX turnMotor;
    private final CANcoder canCoder;
    
    private final VelocityVoltage driveVelocity = new VelocityVoltage(0);
    private final PositionVoltage turnPosition = new PositionVoltage(0);
    
    // Gear ratios - adjust these to match your robot
    private static final double DRIVE_GEAR_RATIO = 6.75; // L2 gear ratio
    private static final double TURN_GEAR_RATIO = 12.8; // MK4i turn ratio
    private static final double WHEEL_CIRCUMFERENCE = 0.1 * Math.PI; // 4 inch wheel in meters
    
    public SwerveModule(int driveMotorId, int turnMotorId, int canCoderId, double angleOffset) {
        driveMotor = new TalonFX(driveMotorId);
        turnMotor = new TalonFX(turnMotorId);
        canCoder = new CANcoder(canCoderId);
        
        // Configure drive motor
        TalonFXConfiguration driveConfig = new TalonFXConfiguration();
        driveConfig.Slot0.kP = 0.1;
        driveConfig.Slot0.kI = 0.0;
        driveConfig.Slot0.kD = 0.0;
        driveConfig.Slot0.kV = 0.12;
        driveConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        driveMotor.getConfigurator().apply(driveConfig);
        
        // Configure turn motor
        TalonFXConfiguration turnConfig = new TalonFXConfiguration();
        turnConfig.Slot0.kP = 100.0;
        turnConfig.Slot0.kI = 0.0;
        turnConfig.Slot0.kD = 0.5;
        turnConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        turnConfig.Feedback.FeedbackRemoteSensorID = canCoderId;
        turnConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        turnConfig.Feedback.RotorToSensorRatio = TURN_GEAR_RATIO;
        turnMotor.getConfigurator().apply(turnConfig);
        
        // Configure CANcoder
        CANcoderConfiguration canCoderConfig = new CANcoderConfiguration();
        canCoderConfig.MagnetSensor.MagnetOffset = angleOffset;
        canCoder.getConfigurator().apply(canCoderConfig);
    }
    
    public void setDesiredState(SwerveModuleState desiredState) {
        // Optimize the state to avoid spinning more than 90 degrees
        @SuppressWarnings("deprecation")
        SwerveModuleState optimizedState = SwerveModuleState.optimize(
            desiredState, 
            getState().angle
        );
        
        // Convert velocity to rotations per second
        double velocityRPS = optimizedState.speedMetersPerSecond / WHEEL_CIRCUMFERENCE * DRIVE_GEAR_RATIO;
        driveMotor.setControl(driveVelocity.withVelocity(velocityRPS));
        
        // Convert angle to rotations
        double angleRotations = optimizedState.angle.getRotations();
        turnMotor.setControl(turnPosition.withPosition(angleRotations));
    }
    
    public SwerveModuleState getState() {
        double velocityMPS = driveMotor.getVelocity().getValueAsDouble() / DRIVE_GEAR_RATIO * WHEEL_CIRCUMFERENCE;
        Rotation2d angle = Rotation2d.fromRotations(turnMotor.getPosition().getValueAsDouble());
        return new SwerveModuleState(velocityMPS, angle);
    }
    
    public SwerveModulePosition getPosition() {
        double distanceMeters = driveMotor.getPosition().getValueAsDouble() / DRIVE_GEAR_RATIO * WHEEL_CIRCUMFERENCE;
        Rotation2d angle = Rotation2d.fromRotations(turnMotor.getPosition().getValueAsDouble());
        return new SwerveModulePosition(distanceMeters, angle);
    }
    
    public void stop() {
        driveMotor.set(0);
        turnMotor.set(0);
    }
}
