package com.spartronics4915.frc2020;

import com.spartronics4915.lib.hardware.CANCounter;
import com.spartronics4915.lib.util.Logger;
import com.spartronics4915.frc2020.subsystems.LED;
import com.spartronics4915.frc2020.subsystems.LED.BlingState;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class Robot extends TimedRobot
{
    private Command mAutonomousCommand;
    private RobotContainer mRobotContainer;

    private static final String kRobotLogVerbosity = "Robot/Verbosity";

    @Override
    public void robotInit()
    {
        Logger.logRobotInit();
    
        try (InputStream manifest =
                getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF"))
        {
            // build a version string
            Attributes attributes = new Manifest(manifest).getMainAttributes();
            String buildStr = "by: " + attributes.getValue("Built-By") +
                    "  on: " + attributes.getValue("Built-At") +
                    "  (" + attributes.getValue("Code-Version") + ")";
            SmartDashboard.putString("Build", buildStr);
            SmartDashboard.putString(kRobotLogVerbosity, "DEBUG"); // Verbosity level

            Logger.notice("=================================================");
            Logger.notice(Instant.now().toString());
            Logger.notice("Built " + buildStr);
            Logger.notice("=================================================");

        }
        catch (IOException e)
        {
            SmartDashboard.putString("Build", "version not found!");
            Logger.warning("Build version not found!");
            DriverStation.reportError(e.getMessage(), false);
        }

        var shed = CommandScheduler.getInstance();
        shed.onCommandInitialize((c) -> Logger.info(c.getName() + " initialized"));
        shed.onCommandFinish((c) -> Logger.info(c.getName() + " finished"));
        shed.onCommandInterrupt((c) -> Logger.info(c.getName() + " interrupted"));

        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our autonomous chooser on the dashboard.
        mRobotContainer = new RobotContainer();
        Logger.notice("@robotInit: Requested BlingState.BLING_COMMAND_OFF");
        LED.getInstance().setBlingState(BlingState.BLING_COMMAND_OFF);


        SmartDashboard.putString("CANBusStatus", CANCounter.getStatusMessage());
        Logger.info("CAN bus status: " + CANCounter.getStatusMessage());

        // print out available serial ports for information
        LED.getInstance().enumerateAvailablePorts();
    }

    @Override
    public void robotPeriodic()
    {
        // robotPeriodic runs in all "match epochs".  
        // Oddly, the scheduler is *not* operational during "disabled epoch" 
        // because it follows the LiveWindow disabled state.  
        // The scheduler is responsible for invoking all Subsystem's periodic
        // method so we don't expect dashboard updates without this running.
        // IterativeRobotBase is the one that controls the LiveWindow state 
        // and it explicitly disables LiveWindow traffic when the robot is 
        // disabled.  Contrast this with the "test epoch". In this mode, the 
        // scheduler does run as do all LiveWindow functions.
        CommandScheduler.getInstance().run();

        //SmartDashboard.putNumber("Robot/BatteryVoltage", RobotController.getBatteryVoltage());

    }    

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit()
    {
        LED.getInstance().setBlingState(BlingState.BLING_COMMAND_DISABLED);
    }

    @Override
    public void disabledPeriodic()
    {
    }

    /**
     * This autonomous runs the autonomous command selected by our {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit()
    {
        mAutonomousCommand = mRobotContainer.getAutonomousCommand();

        if (mAutonomousCommand != null)
        {
            mAutonomousCommand.schedule();
        }

        LED.getInstance().setBlingState(BlingState.BLING_COMMAND_AUTOMODE);
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic()
    {
    }

    @Override
    public void teleopInit()
    {
        if (mAutonomousCommand != null)
        {
            mAutonomousCommand.cancel();
        }

        LED.getInstance().setBlingState(BlingState.BLING_COMMAND_TELEOP);
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic()
    {
    }

    @Override
    public void testInit()
    {
        CommandScheduler.getInstance().cancelAll();
		LED.getInstance().setBlingState(BlingState.BLING_COMMAND_DRIVE_SLOW);
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic()
    {
    }
}
