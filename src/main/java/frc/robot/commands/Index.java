package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;


public class Index extends Command {
    private final IndexerSubsystem indexer;
    private Timer timer;

    private double intakeVoltage = 2;
    private double feederVoltage = 2;

    public static boolean bool;

    public Index(IndexerSubsystem indexer) {
        this.indexer = indexer;
        timer = new Timer();
    }
    

    @Override
    public void initialize() {
        bool = false;
    }

    /**
     * A series of actions to intake a note, migrate it through the feeder
     * and then shoot it.
     * A minor state maching:
     * 1. Intake the ball
     * a. If the sensor is not tripped, intake the ball
     * 2. Wait for the ball to reach the feeder
     * 3. Feed the ball into the shooter and shoot it
     */
    @Override
    public void execute() {
        if (!indexer.isFilled() && bool == false ) { //sensor is off and no notes
            indexer.setIntakeVoltage(intakeVoltage);
            indexer.setFeederVoltage(feederVoltage);
            bool = !bool;
        }else if (indexer.isFilled() && bool == true) { //sensor is on and index did run(which means stop intake and feeder)
            indexer.setIntakeVoltage(0);
            indexer.setFeederVoltage(0);
        }

        // if (timer.get() >= 5) {
        //     indexer.setFeederVoltage(feederVoltage);

        //     if (indexer.getSensorStatus() == false && timer.get() >= 9) {
        //         indexer.setFeederVoltage(0);
        //         timer.stop();
        //         timer.reset();
        //     }
        // }

    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return (indexer.isFilled());
    }
}
