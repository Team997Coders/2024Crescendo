package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;



public class Shooter implements Subsystem{

    private CANSparkMax flywheelNEO = new CANSparkMax(Constants.Shooter.FLYWHEEL_MOTOR_ID, MotorType.kBrushless);  
    private CANSparkMax invertedFlywheelNEO = new CANSparkMax(Constants.Shooter.FLYWHEEL_MOTOR_ID, MotorType.kBrushless);
    private final RelativeEncoder ShooterEncoder;


    private Shooter() {
        invertedFlywheelNEO.setInverted(true);
        invertedFlywheelNEO.follow(flywheelNEO);

        ShooterEncoder = flywheelNEO.getEncoder(); 
    }
    

    public void setMotorVoltage(double voltage) {
        flywheelNEO.setVoltage(voltage);
        
    }

   

    public double getFlywheelEncoderPosition() {
        return ShooterEncoder.getPosition();
    }
    public double getFlywheelEncoderVelocity(){
        return ShooterEncoder.getVelocity();
    }
    
    

    @Override 
    public void periodic() { 
        //loggers eventually
        SmartDashboard.putNumber("", 0); 
        SmartDashboard.putNumber("", 0);

    }
}
