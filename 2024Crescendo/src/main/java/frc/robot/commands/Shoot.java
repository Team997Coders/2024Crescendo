package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends Command{
    private Timer timer;
    private ShooterSubsystem leftFlywheel;
    private ShooterSubsystem rightFlywheel;
    private ShooterSubsystem shooterStart;
    private boolean isFilled;
    public Shoot (ShooterSubsystem leftFlywheel, ShooterSubsystem rightFlywheel, ShooterSubsystem shooterStart){
       
        isFilled = new IndexerSubsystem().isFilled;

    }

    @Override
    public void initialize() {
        shooterStart.shooterOn = true;
    }
    
    @Override 
    public void execute() {
        while (isFilled){
            if (shooterStart.shooterOn == true){
                timer.start();
                leftFlywheel.setLeftMotorVoltage();
                rightFlywheel.setRightMotorVoltage();
                if (timer.get() > 5){
                    shooterStart.shooterOn = false;
                    timer.reset();
                }
            }
            if(!isFilled){
                break;
            }
        }
       
        
    }

    @Override
    public void end (boolean interrupted) {

    }

    @Override 
    public boolean isFinished() {
        return false;
    }

}
