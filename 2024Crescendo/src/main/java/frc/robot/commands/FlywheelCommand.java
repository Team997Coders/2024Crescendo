package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.Shooter;
public class FlywheelCommand implements Subsystem{
    private Shooter shooter;
    private Timer timer;

    private FlywheelCommand (){
    }
    
    public void initialize() {
    }
    public void execute() {
        timer.start();
        while (timer.get() < 10){
            
        }
    }
    public void end(boolean interrupted) {
    }
    public boolean isFinished() {
        return false;
    }
}
