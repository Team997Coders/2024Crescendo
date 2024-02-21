package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class Shooter extends Command {
    private ShooterSubsystem Shooter;
    private double voltage;
    public Shooter(ShooterSubsystem Shooter, double voltage){
        this.Shooter = Shooter;
        this.voltage = voltage;
    }
    @Override
    public void initialize(){
        Shooter.setLeftMotorVoltage(voltage);
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
