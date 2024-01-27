package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class IndexAndShoot extends Command{

    
    private Shooter shooter;
    private Indexer indexer;
    private Timer timer;

    private FlywheelCommand flywheelCommand = new FlywheelCommand(5, 1.0, shooter);

    private double defaultVoltage;
    private boolean bool;
    
    public IndexAndShoot(Indexer indexer, Shooter shooter) {
        this.indexer = indexer;
        this.shooter = shooter;
    }

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
