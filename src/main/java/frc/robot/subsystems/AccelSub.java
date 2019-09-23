/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Add your docs here.
 */
public class AccelSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Accelerometer accel;
  private double xVal=0;
  private double yVal=0;
  private double zVal=0;
  private double xAvg = 0;
  private double yAvg = 1;
  private double zAvg = 0;
  private double rollAngle = 0;
  private double pitchAngle = 0;
  private double kRecAvgWeight = 0.05;

  public AccelSub(){
    super();
    accel = new BuiltInAccelerometer(Accelerometer.Range.k2G);
    
  }

public void updateValues(){
xVal = accel.getX();
yVal = accel.getY();
zVal = accel.getZ();

SmartDashboard.putNumber("Accel X", xVal);
SmartDashboard.putNumber("Accel Y", yVal);
SmartDashboard.putNumber("Accel Z", zVal);

xAvg = kRecAvgWeight*xVal + (1 - kRecAvgWeight)*xAvg;
yAvg = kRecAvgWeight*yVal + (1 - kRecAvgWeight)*yAvg;
zAvg = kRecAvgWeight*zVal + (1 - kRecAvgWeight)*zAvg;

SmartDashboard.putNumber("Average Accel X", xAvg);
SmartDashboard.putNumber("Average Accel Y", yAvg);
SmartDashboard.putNumber("Average Accel Z", zAvg);

pitchAngle = Math.atan2(yAvg, zAvg)/1.5*100-103.89;
rollAngle = Math.atan2(-xAvg, Math.sqrt(yAvg*yAvg+zAvg*zAvg))*50-.19;

SmartDashboard.putNumber("Pitch Angle", pitchAngle);
SmartDashboard.putNumber("Roll Angle", rollAngle);

}

/**
 * @return the pitchAngle
 */
public double getPitchAngle() {
  return pitchAngle;
}

/**
 * @return the rollAngle
 */
public double getRollAngle() {
  return rollAngle;
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
