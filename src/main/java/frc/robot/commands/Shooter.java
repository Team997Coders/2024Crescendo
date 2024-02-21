package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class Shooter extends Command {
    private ShooterSubsystem shooter;
    private double voltage;
    public Shooter(ShooterSubsystem shooter, double voltage){
        this.shooter = shooter;
        this.voltage = voltage;
    }
    @Override
    public void initialize(){
        shooter.setLeftMotorVoltage(voltage);
    }
    @Override
    public void execute(){
       
    }   
    @Override
    public void end(boolean interrupted){
    }
    
    @Override 
    public boolean isFinished(){
        return false;
    }
}
