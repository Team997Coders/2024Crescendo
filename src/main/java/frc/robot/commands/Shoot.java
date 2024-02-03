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

    // constructor
    public Shoot (ShooterSubsystem shooterStart, IndexerSubsystem indexer){
        this.m_shooter = shooterStart;
        this.m_indexer = indexer;
        
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
        while (m_indexer.isFilled()){ //while there is a notes inside
            m_indexer.setFeederVoltage(m_indexer.getFeederMotorVoltage()); // turn off the feeder
            m_indexer.setIntakeVoltage(m_indexer.getIntakeMotorVoltage()); // turn off the intake
            if (m_shooter.isShooterButtonPressed() == true){// the shooter button pressed
                timer.start(); //start time
                if (timer.get() > 5){ // wait 5 second
                    m_shooter.setMotorOutput(m_shooter.getLeftFlywheelEncoderVelocity()); //maybe. need to test this
                    m_shooter.setMotorOutput(m_shooter.getRightFlywheelEncoderVelocity());
                    timer.reset();// that's the error we have. before the time reset we need to start the shoot
                }
            }else{ // else turn off the shooter motor
                m_shooter.setLeftMotorVoltage(0);
                m_shooter.setRightMotorVoltage(0);
            }
          
            if(!m_indexer.isFilled()){ // if no notes, break
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
