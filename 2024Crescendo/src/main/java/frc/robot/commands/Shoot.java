package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IndexerSubsystem;   
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends Command{
    private Timer timer;
    private ShooterSubsystem flywheel;
    private IndexerSubsystem pushNotes;
    private double feederVoltage=2;



    public Shoot(ShooterSubsystem flwyheel, IndexerSubsystem pushNotes) {
        this.pushNotes = pushNotes;
        this.flywheel = flywheel;
        this.execute();
        
    }

    @Override
    public void initialize() {
    }
    
    @Override 
    public void execute() {
        while (pushNotes.isFilled){
            if (flywheel.isShooterButtonPressed() == true){
                timer.start();    
                if (timer.get() > 3){
                    pushNotes.setFeederVoltage(feederVoltage);
                    pushNotes.isFilled();
                    timer.reset();
                }
            }else{
                flywheel.setRightMotorVoltage(0);
            }
            if(!pushNotes.isFilled){
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
