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
import frc.robot.RobotMap.LoadingMode;

/**
 * Add your docs here.
 */
public class Grip extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Solenoid hatch;
  private Solenoid cargo;
  private Solenoid noodleArms;
  private DoubleSolenoid cargoTop;
  private LoadingMode mode = LoadingMode.hatch;
  private DigitalInput hatchBumper;
 
 

  public Grip(){
    super();
    hatch = new Solenoid(6);
    cargo = new Solenoid(5);
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
  public boolean getCargoState(){
    return cargo.get();
  }
  public boolean getHatchState(){
    return hatch.get();
  }
  
  public LoadingMode getMode(){
    return this.mode;
  }

  public boolean getHatchBumper(){
    return !hatchBumper.get();
  }


  public void setMode(LoadingMode mode){
    this.mode = mode;

    if (mode == LoadingMode.cargo){
      //starting state for cargo
      openCargoArms();
      openHatch();
      deployNoodles();
    }
    else if (mode == LoadingMode.hatch){
      //starting state for hatch
      openCargoArms();
      closeHatch();
      retractNoodles();
    }
  }
//this should not be used, use NewGripNextAction instead
  public void nextAction(){
    //cycles to next action depending on current state of solenoids & loading mode
    if (mode == LoadingMode.cargo){
      if(cargo.get()){
        closeHatch();
        //need a pause in here, somehow...
        openCargoArms();
        retractNoodles();
      }
      else {
        closeCargoArms();
        openHatch();
      }
    }
    else if (mode == LoadingMode.hatch){
      if(hatch.get()){
        openHatch();
      }
      else{
        closeHatch();
        deployNoodles();
        //need a pause here too
        retractNoodles();
      }
    }
  }

  public void openHatch(){
    hatch.set(false);
  }

  public void closeHatch(){
    hatch.set(true);
  }

  public void openCargoArms(){
    cargo.set(false);
  }

  public void closeCargoArms(){ 
    cargo.set(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
