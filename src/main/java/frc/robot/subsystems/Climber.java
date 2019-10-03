/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.StopClimb;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {


private Spark frClimb, rrClimb, flClimb, rlClimb;
private VictorSP climbDriveR, climbDriveL;
private double upPower = 0.4;
private double downPower = -0.3;
private double kRollAdj = .05;
private double kPitchAdj =  .05;

  public Climber(){
    super();
    frClimb = new Spark(0);
    rrClimb = new Spark(1);
    flClimb = new Spark(2);
    rlClimb = new Spark(3);
    
    climbDriveR = new VictorSP(4);
    climbDriveL = new VictorSP(5);

    frClimb.setInverted(true);
    rrClimb.setInverted(true);
    flClimb.setInverted(true);
    rlClimb.setInverted(false);

  }
  public void liftUp(){
    frClimb.set(upPower - kPitchAdj*Robot.robotAccel.getPitchAngle());
    rrClimb.set(upPower);
    flClimb.set(upPower - kPitchAdj*Robot.robotAccel.getPitchAngle() - kRollAdj*Robot.robotAccel.getRollAngle());
    rlClimb.set(upPower - kRollAdj*Robot.robotAccel.getRollAngle());
  } 

  public void lowerDown(){
    frClimb.set(downPower);
    rrClimb.set(downPower);
    flClimb.set(downPower);
    rlClimb.set(downPower);
  }

  public void retractFront(){
    rrClimb.set(downPower);
    rlClimb.set(downPower);
  }
  
  public void retractBack(){
    rrClimb.set(downPower);
    rlClimb.set(downPower);
  }

  public void driveClimb(){
    climbDriveR.set(1);
    climbDriveL.set(1);
  }

  public void stopDriveClimb(){
    climbDriveR.set(0);
    climbDriveL.set(0);
  }

  public void stop(){
    frClimb.set(0);
    rrClimb.set(0);
    rlClimb.set(0);
    flClimb.set(0);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new StopClimb());
  }
}
