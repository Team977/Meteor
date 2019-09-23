/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.RobotMap.DriveDirection;
import frc.robot.commands.ArcadeDriveMode;

/*** 
 * Add your docs here.
 */

public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  private WPI_TalonSRX leftTalon;
  private WPI_TalonSRX rightTalon;
  private VictorSPX leftVictor;
  private VictorSPX rightVictor;
  private DifferentialDrive Ddrive;

  private boolean precisionMode = false;
  private RobotMap.DriveDirection driveDirection = DriveDirection.forward;
 
  public Drivetrain() {
		super();
		leftTalon = new WPI_TalonSRX(2);
    rightTalon = new WPI_TalonSRX(1);
    leftVictor = new VictorSPX(0);
    rightVictor = new VictorSPX(1);
    Ddrive = new DifferentialDrive(leftTalon, rightTalon);
    rightVictor.follow(rightTalon);
    leftVictor.follow(leftTalon);
  }

  public void drive(Joystick joy) {
    if (driveDirection == DriveDirection.forward) {
      if(precisionMode){
        Ddrive.arcadeDrive(-joy.getRawAxis(1)*.4, joy.getRawAxis(4)*.4);
      }
      else{
        Ddrive.arcadeDrive(-joy.getRawAxis(1)*.67, joy.getRawAxis(4)*.6);
      }
      
    }
    else if (driveDirection == DriveDirection.reverse){
      if(precisionMode){
        Ddrive.arcadeDrive(joy.getRawAxis(1)*.4, joy.getRawAxis(4)*.4);
      }
      else{
        Ddrive.arcadeDrive(joy.getRawAxis(1)*.67, joy.getRawAxis(4)*.6);
      }
    }
  }

  public void stop() {
    Ddrive.arcadeDrive(0, 0);
  }

  public void setDriveDirection(DriveDirection direction){
    if (direction == RobotMap.DriveDirection.forward){
      driveDirection = DriveDirection.forward; 
    }
    else{
      driveDirection = DriveDirection.reverse;
    }
  }

  public void setPrecisionMode(boolean modeSet){
    if (modeSet == true) {
      precisionMode =true;
    }
    else {
      precisionMode = false;
    }
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDriveMode());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
