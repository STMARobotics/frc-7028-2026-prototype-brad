// package frc.robot.subsystems;

// import com.ctre.phoenix6.configs.TalonFXConfiguration;
// import com.ctre.phoenix6.hardware.TalonFX;
// import com.ctre.phoenix6.signals.InvertedValue;
// import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class SpindexerSubsystem extends SubsystemBase {
// private final TalonFX moterFive = new TalonFX(0);

// private double motorVelocity = 0;
// private final VelocityTorqueCurrentFOC oneControl = new VelocityTorqueCurrentFOC(0.0);

// public SpindexerSubsystem(){
//  var config = new TalonFXConfiguration();
//  config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
//  moterFive.getConfigurator().apply(config);
    
// }
// public void runMotors(){
//     moterFive.setControl(oneControl.withVelocity(motorVelocity));
// }
// public void stop(){
//     moterFive.stopMotor();
// }
// }
