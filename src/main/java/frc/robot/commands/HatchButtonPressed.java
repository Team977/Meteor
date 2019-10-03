/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap.ArmPositions;
import frc.robot.RobotMap.GripStage;
import frc.robot.RobotMap.LoadingMode;

public class HatchButtonPressed extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchButtonPressed() {

    addParallel(new setGripStage(GripStage.hatchStart));
    addSequential(new setLoadingMode(LoadingMode.hatch));
    addSequential(new ArmMove(ArmPositions.lowHatch));
    addSequential(new AutoHatchPickup());

  }
}
