package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;


public class Index extends Command {
    private final IndexerSubsystem indexer;
    //static IndexerSubsystem isFilled;
    private Timer timer;

    private double intakeVoltage = 2;
    private double feederVoltage = 2;

    public static boolean bool;

    public Index(IndexerSubsystem indexer) {
        this.indexer = indexer;
        indexer.isFilled = false;   
        timer = new Timer();
    }
    

    @Override
    public void initialize() {
        bool = false;
        execute();
    }

    /**
     * A series of actions to intake a note, migrate it through the feeder
     * A minor state maching:
     * 1. Intake the ball
     * a. If the sensor is not tripped, intake the ball
     * 2. Wait for the ball to reach the feeder
     */
    @Override
    public void execute() {
        if (indexer.getSensorStatus() == false && bool == false ) { // if the switch is off and no notes
            indexer.setIntakeVoltage(intakeVoltage);
            indexer.setFeederVoltage(feederVoltage);
            bool = !bool;
            timer.start();
            if (timer.get() < 3){
                indexer.isFilled(true);
            }
        }else if (indexer.getSensorStatus() == true && bool == true) {  // the switch is on and there is notes inside
            indexer.setIntakeVoltage(0);
            indexer.setFeederVoltage(0);
            indexer.isFilled = true;
        }

    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return (indexer.isFilled);
    }
}
