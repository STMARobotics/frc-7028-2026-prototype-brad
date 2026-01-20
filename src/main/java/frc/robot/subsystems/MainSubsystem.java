package frc.robot.subsystems;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MainSubsystem extends SubsystemBase {

    private final TalonFX shooterMotorOne = new TalonFX(0);
    private final TalonFX shooterMotorTwo = new TalonFX(1);
    private final TalonFX backSpinMotor = new TalonFX(2);

    private final SlotConfigs shooterPid = new SlotConfigs();
    private final SlotConfigs backSpinPid = new SlotConfigs();

    private final double targetVelocity = 30;
    private final double backSpinVelocity = 10;

    private final VelocityTorqueCurrentFOC shooterControl = new VelocityTorqueCurrentFOC(0.0);
    private final VelocityTorqueCurrentFOC backSpinControl = new VelocityTorqueCurrentFOC(0.0);
    private final DutyCycleOut shootCycle = new DutyCycleOut(0);
    private final DutyCycleOut backSpinCycle = new DutyCycleOut(0);


    
    public MainSubsystem() {
        shooterPid.kV = .085;
        shooterPid.kP = 11;
        shooterPid.kI = 0;
        shooterPid.kD = 0;
        shooterMotorOne.getConfigurator().apply(shooterPid);
        shooterMotorTwo.getConfigurator().apply(shooterPid);

        backSpinPid.kV = .1;
        backSpinPid.kP = 5;
        backSpinPid.kI = 0;
        backSpinPid.kD = 0;
        backSpinMotor.getConfigurator().apply(backSpinPid);

        shooterMotorTwo.setControl(new Follower(0, MotorAlignmentValue.Opposed));
    }

    public void runMotors() {
        shooterMotorOne.setControl(shooterControl.withVelocity(targetVelocity));
    }

    public void runBackSpin() {
        backSpinMotor.setControl(backSpinControl.withVelocity(backSpinVelocity));
    }

    public void runShootDutyCycle() {
        shooterMotorOne.setControl(shootCycle.withOutput(0.5));
    }

    public void runBackSpinDutyCycle() {
        backSpinMotor.setControl(backSpinCycle.withOutput(0.5));
    }

    public void stop() {
        shooterMotorOne.stopMotor();
        backSpinMotor.stopMotor();
    }
}
