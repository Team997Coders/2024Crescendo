package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class IndexAndShoot extends Command{

    private Indexer indexer;
    private Shooter shooter;
    private Timer timer;

    private double defaultVoltage = 1;
    private boolean bool;
    
    @Override
    public void initialize() {
    bool = false;
    }

    @Override
    public void execute() {
        if (indexer.getSensorStatus() == false && bool == false) {
        indexer.setIntakeVoltage(defaultVoltage);
        indexer.setFeederVoltage(defaultVoltage);
        bool = !bool;
        } else if(indexer.getSensorStatus() == true && bool == true) {
            timer.start();
            indexer.setIntakeVoltage(0);
            indexer.setFeederVoltage(0);
            shooter.setMotorVoltage(defaultVoltage);
           
        } else if (indexer.getSensorStatus() == false && timer.get() > 0) {
             indexer.setIntakeVoltage(0);
             indexer.setFeederVoltage(0);
             shooter.setMotorVoltage(defaultVoltage);
        }

        if (timer.get() >= 5) {
            indexer.setFeederVoltage(defaultVoltage);

            if (indexer.getSensorStatus() == false && timer.get() >= 9) {
            indexer.setFeederVoltage(0);
            shooter.setMotorVoltage(0);
            timer.stop();
            timer.reset();
        }
            } 

        
        
    }

    @Override 
    public void end(boolean interrupted) {
    }

    @Override 
    public boolean isFinished() {
        return (timer.get() >= 10);
    }
}
