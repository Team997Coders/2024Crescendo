package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BarSubsystem extends SubsystemBase{
    private final CANSparkMax barNEO = new CANSparkMax(Constants.Bar.BAR_MOTOR_ID, MotorType.kBrushless);
    private final RelativeEncoder barMotorEncoder;
    public BarSubsystem(){
        barMotorEncoder = barNEO.getEncoder();
    }
    public void setBarVoltage(double voltage){
        barNEO.setVoltage(voltage);

    }
    public double getBarMotorVoltage(){
        return barNEO.getEncoder().getVelocity();
    }
 
    public double getBarEncoderPosition() {
        return barMotorEncoder.getPosition();
    }

    
}
