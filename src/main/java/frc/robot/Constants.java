// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

  }

  public final class Shooter {
    public static final int LEFT_FLYWHEEL_MOTOR_ID = 1;
    public static final int RIGHT_FLYWHELL_MOTOR_ID = 4;

    public static final boolean LEFT_FLYWHEEL_MOTOR_IS_INVERTED = false;
    public static final boolean RIGHT_FLYWHEEL_MOTOR_IS_INVERTED = true;
  }

  public final class Indexer {
    public static final int INTAKE_MOTOR_ID = 3;
    public static final int FEEDER_MOTOR_ID = 2;

    public static final int FEEDER_SENSOR_CHANNEL = 0;

      public static final boolean INTAKE_MOTOR_IS_INVERTED = false;
      public static final boolean FEEDER_MOTOR_IS_INVERTED = true; 
      public final boolean NOTE_SWITCH_ACTIVE = false;
    }

    public final class Climber {
      public static final int CLIMBER_MOTOR_ID = 5;

      public static final boolean CLIMBER_MOTOR_IS_INVERTED = false;
    }
}
