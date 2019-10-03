/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap.GripStage;
import frc.robot.commands.CargoButtonPressed;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimbDriveUp;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.GoHigh;
import frc.robot.commands.GoLow;
import frc.robot.commands.GoMid;
import frc.robot.commands.GripButtonPressed;
import frc.robot.commands.HatchButtonPressed;
import frc.robot.commands.RetractClimb;
import frc.robot.commands.RetractClimb2;
import frc.robot.commands.SetDefenseMode;
import frc.robot.commands.StopClimb;
import frc.robot.commands.StopClimbDrive;
import frc.robot.commands.setDriveDirection;
import frc.robot.commands.setGripStage;
import frc.robot.commands.setPrecisionMode;
import frc.robot.commands.stopArm;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  Joystick driver = new Joystick(0);
  Joystick operator = new Joystick(1);
  
  Button DriverA = new JoystickButton(driver, 1);
  Button DriverB = new JoystickButton(driver, 2);
  Button DriverX = new JoystickButton(driver, 3);
  Button DriverY = new JoystickButton(driver, 4);
  Button DriverL1 = new JoystickButton(driver, 5);
  Button DriverR1 = new JoystickButton(driver, 6);
  Button DriverSelect = new JoystickButton(driver, 7);
  Button DriverStart = new JoystickButton(driver, 8);

  Button hatchButton = new JoystickButton(operator, 2);
  Button cargoButton = new JoystickButton(operator, 1);
  Button gripButton = new JoystickButton(operator, 5);
  Button inButton = new JoystickButton(operator, 7);
  Button lowButton = new JoystickButton(operator, 3);
  Button midButton = new JoystickButton(operator, 4);
  Button hiButton = new JoystickButton(operator, 6);
  Button overButton = new JoystickButton(operator, 8);

	public OI(){

    DriverY.whenPressed(new SetDefenseMode());
    DriverL1.whenPressed(new setPrecisionMode(true));
    DriverL1.whenReleased(new setPrecisionMode(false));
    DriverR1.whenPressed(new setDriveDirection(RobotMap.DriveDirection.reverse));
    DriverR1.whenReleased(new setDriveDirection(RobotMap.DriveDirection.forward));
   
    DriverStart.whileHeld(new ClimbUp());
    DriverStart.whenReleased(new StopClimb());
    DriverX.whileHeld(new ClimbDriveUp());
    DriverX.whenReleased(new StopClimbDrive());
    DriverA.whileHeld(new RetractClimb());
    DriverA.whenReleased(new StopClimb());
    DriverB.whileHeld(new RetractClimb2());    
    DriverB.whenReleased(new StopClimb());

    DriverSelect.whileHeld(new ClimbDown());
    DriverSelect.whenReleased(new StopClimb());


    inButton.whenPressed(new setGripStage(GripStage.cargoDunk));

    hatchButton.whenPressed(new HatchButtonPressed());
    cargoButton.whenPressed(new CargoButtonPressed());
    gripButton.whenPressed(new GripButtonPressed());
    lowButton.whenPressed(new GoLow());
    midButton.whenPressed(new GoMid());
    hiButton.whenPressed(new GoHigh());

    overButton.whenPressed(new stopArm());

    //lowButton.whenPressed(new ArmMove(RobotMap.ArmPositions.start));
    //midButton.whenPressed(new ArmMove(RobotMap.ArmPositions.midCargo));
    //hiButton.whenPressed(new ArmMove(RobotMap.ArmPositions.highHatch));

  }

  public Joystick getJoystick() {
    return driver;
  }
  
  public Joystick getOperator(){
    return operator;
  }
	
}