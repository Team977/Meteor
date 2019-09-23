/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap.LoadingMode;

public class NewGripNextAction extends Command {
  private boolean finish = false;

  public NewGripNextAction() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.robotGrip);
    setTimeout(1);
    this.finish = false;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.finish = false;
    LoadingMode mode = Robot.robotGrip.getMode();
    if (mode == LoadingMode.cargo){
      if(Robot.robotGrip.getCargoState()){
        Robot.robotGrip.closeHatch();
        Robot.robotGrip.openCargoArms();
        Robot.robotGrip.releaseTop();
        //wait for isTimedOut
      }
      else {
        Robot.robotGrip.closeCargoArms();
        Robot.robotGrip.openHatch();
        Robot.robotGrip.grabTop();
        this.finish = true;
      }
    }
    else{// if (mode == LoadingMode.hatch){
      if(Robot.robotGrip.getHatchState()){
        Robot.robotGrip.openHatch();
        
        Robot.robotGrip.releaseTop();
        this.finish = true;
      }
      else {
        Robot.robotGrip.closeHatch();
        Robot.robotGrip.deployNoodles();
        Robot.robotGrip.releaseTop();
        //wait for isTimedOut
      }
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut() || this.finish;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.robotGrip.openCargoArms();
    Robot.robotGrip.retractNoodles();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
