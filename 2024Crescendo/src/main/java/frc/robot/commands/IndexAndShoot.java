package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IndexAndShoot extends Command {

    private final IndexerSubsystem indexer;
    private final ShooterSubsystem shooter;
    private Timer timer;

    private double intakeVoltage = 6;
    private double feederVoltage = 6;
    private double shooterVoltage = 12;

    public static boolean bool;

    public IndexAndShoot(IndexerSubsystem indexer, ShooterSubsystem shooter, Boolean run_state) {
        this.indexer = indexer;
        this.shooter = shooter;
        this.bool = run_state;
        addRequirements(indexer, shooter);
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
        if (indexer.getSensorStatus() == false && bool == false) {
            indexer.setIntakeVoltage(intakeVoltage);
            indexer.setFeederVoltage(feederVoltage);
            bool = !bool;
        } else if (indexer.getSensorStatus() == true && bool == true) {
            timer.start();
            indexer.setIntakeVoltage(0);
            indexer.setFeederVoltage(0);
            shooter.setMotorVoltage(shooterVoltage);

        } else if (indexer.getSensorStatus() == false && timer.get() > 0) {
            indexer.setIntakeVoltage(0);
            indexer.setFeederVoltage(0);
            shooter.setMotorVoltage(shooterVoltage);
        }

        if (timer.get() >= 5) {
            indexer.setFeederVoltage(feederVoltage);

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
