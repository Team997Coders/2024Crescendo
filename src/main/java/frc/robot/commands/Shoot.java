package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends Command{
    // define private fields
    private Timer timer;
    private ShooterSubsystem m_shooter;
    private IndexerSubsystem m_indexer;
    private double shooterVoltage;
    private boolean isShooterButtonPressed;
    private double feederVoltage= 3;
    // constructor
    public Shoot (ShooterSubsystem shooterStart, IndexerSubsystem indexer, double shooterVoltage, boolean isShooterButtonPressed){
        this.m_shooter = shooterStart;
        this.m_indexer = indexer;
        this.shooterVoltage = shooterVoltage;
        this.isShooterButtonPressed = isShooterButtonPressed;
    }



    @Override
    public void initialize() {
        timer = new Timer();
    }
    
    @Override
    /**
     * Shoot the note.
     * If we don't have a note loaded, then this command does nothing (just exit)
     * If we do have a note then:
     *      start up the shooter to a given speed (voltage)
     *      when wheels are up to speed (either via a pid, an encoder or just a wait)
     *      start the indexer (to push the note into the shooter)
     *      wait for the note sensor to show no more note
     *      turn off the indexer
     *      wait 2 seconds (approximately)
     *      turn off the shooter
     */
    public void execute() {
      
        
    }

    @Override
    public void end (boolean interrupted) {

    }

    @Override 
    public boolean isFinished() {
        return false;
    }

}
