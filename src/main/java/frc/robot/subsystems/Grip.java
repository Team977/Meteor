/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.GripStage;
import frc.robot.RobotMap.LoadingMode;

/**
 * Add your docs here.
 */
public class Grip extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Solenoid hatch;
  private Solenoid cargoArms;
  private Solenoid noodleArms;
  private DoubleSolenoid cargoTop;
  private LoadingMode mode = LoadingMode.hatch;
  private GripStage stage = GripStage.hatchGrab;
  private DigitalInput hatchBumper;
 
 

  public Grip(){
    super();
    hatch = new Solenoid(6);
    cargoArms = new Solenoid(5);
    cargoTop = new DoubleSolenoid(2,3);
    hatchBumper = new DigitalInput(0);
    noodleArms = new Solenoid(4);
    cargoTop.set(Value.kReverse);

  }

  public void grabTop(){
    cargoTop.set(Value.kForward);
  }
  public void releaseTop(){
    cargoTop.set(Value.kReverse);
  }
  public void deployNoodles(){
    noodleArms.set(true);
  }
  public void retractNoodles(){
    noodleArms.set(false);
  }
  public void openHatch(){
    hatch.set(false);
  }
  public void closeHatch(){
    hatch.set(true);
  }
  public void openCargoArms(){
    cargoArms.set(false);
  }
  public void closeCargoArms(){ 
    cargoArms.set(true);
  }
  
  
  public boolean getCargoState(){
    return cargoArms.get();
  }
  public boolean getHatchState(){
    return hatch.get();
  }
  public LoadingMode getMode(){
    return this.mode;
  }
  public GripStage getStage(){
    return this.stage;
  }
  public boolean getHatchBumper(){
    return !hatchBumper.get();
  }


  public void setMode(LoadingMode mode){
    this.mode = mode;
  }

  public void setStage(GripStage stage){
    this.stage = stage;
  }
 
  public void updateSolenoids(){
    switch(this.stage){
      case hatchStart:
        closeHatch();
        openCargoArms();
        retractNoodles();
        releaseTop();
        break;
      case hatchGrab:
        openHatch();
        openCargoArms();
        retractNoodles();
        releaseTop();
        break;
      case hatchRelease:
        closeHatch();
        openCargoArms();
        deployNoodles();
        releaseTop();
        break;
      case cargoStart:
        openHatch();
        openCargoArms();
        deployNoodles();
        releaseTop();
        break;
      case cargoGrab:
        openHatch();
        closeCargoArms();
        deployNoodles();
        grabTop();
        break;
      case cargoRelease:
        closeHatch();
        openCargoArms();
        deployNoodles();
        releaseTop();
        break;
      case cargoDunk:
        closeHatch();
        openCargoArms();
        retractNoodles();
        grabTop();
        break;
      case defense:
        openHatch();
        openCargoArms();
        retractNoodles();
        releaseTop();
        break;

    }
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
