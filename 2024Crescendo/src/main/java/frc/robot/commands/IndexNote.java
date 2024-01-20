package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;

public class IndexNote extends Command{

    private Indexer indexer;

    private Timer timer;
    
    private double defaultVoltage = 0;
    
    @Override
    public void initialize() {
        timer.start();
    }

    @Override
    public void execute() {
        indexer.setIntakeVoltage(defaultVoltage);
        indexer.setFeederVoltage(defaultVoltage);

        if(indexer.getSensorStatus() == true) {
            indexer.setIntakeVoltage(0);
            indexer.setFeederVoltage(0);
            //flywheel spin command for set time when finished
            
        }
    }

    @Override 
    public void end(boolean interrupted) {
    }

    @Override 
    public boolean isFinished() {
        return false;
    }
}
