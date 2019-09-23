/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.ArmPositions;
import frc.robot.commands.ManualArmControl;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX armTalon;
  private ArmPositions currentPosition = ArmPositions.start;
  public DigitalInput bottomLimit;
  public DigitalInput topLimit;
  
  final int kTimeoutMs = 30;
  

  public Arm(){
    super();
    armTalon = new WPI_TalonSRX(0);

    armTalon.configFactoryDefault();

    armTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, kTimeoutMs);
    armTalon.setSensorPhase(false);
    armTalon.setInverted(false);
    armTalon.configNominalOutputForward(0, kTimeoutMs);
    armTalon.configNominalOutputReverse(0, kTimeoutMs);
    armTalon.configPeakOutputForward(1, kTimeoutMs);
    armTalon.configPeakOutputReverse(-0.5, kTimeoutMs);
    armTalon.configAllowableClosedloopError(0, 0, kTimeoutMs);

    armTalon.config_kF(0,0,kTimeoutMs);
    armTalon.config_kP(0, 40, kTimeoutMs);
    armTalon.config_kI(0, 0, kTimeoutMs);
    armTalon.config_kD(0, 0, kTimeoutMs);
  }

  public void moveToPos(double position){
    armTalon.set(ControlMode.Position, position);
  }

  public ArmPositions getPosition(){
    return currentPosition;
  }

  public int getError(){
    return armTalon.getClosedLoopError();
  }

  public double getTarget(){
    return armTalon.getClosedLoopTarget();
  }

  public void setArmPosition(ArmPositions position){
    switch(position){
      case start:
        armTalon.set(ControlMode.Position, 70);
        break;        
      case loadHatch:
        armTalon.set(ControlMode.Position, 123);
        break;
      case lowHatch:
        armTalon.set(ControlMode.Position, 123);
        break;
      case lowCargo:
        armTalon.set(ControlMode.Position,212);
        break;
      case loadCargo:
        armTalon.set(ControlMode.Position, 330);
        break;
      case midHatch:
        armTalon.set(ControlMode.Position,455);
        break;
      case midCargo:
        armTalon.set(ControlMode.Position,538);
        break;
      case highHatch:
        armTalon.set(ControlMode.Position, 833);
        break;
      case highCargo:
        armTalon.set(ControlMode.Position, 883);
        break;
    }
  }

  public void manualArmControl(double speed){

    armTalon.set(ControlMode.PercentOutput, speed*0.5);

    /*if ((speed > 0) && (topLimit.get())){
      
    armTalon.set(ControlMode.PercentOutput, speed*0.5);
    }
    else if  ((speed < 0) && (bottomLimit.get())){
      armTalon.set(ControlMode.PercentOutput, speed*0.5);
    }
    else{
      armTalon.set(ControlMode.PercentOutput, 0);
    }*/
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualArmControl());
  }
}
