package com.spartronics4915.frc2020.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;

import com.spartronics4915.frc2020.Constants;
import com.spartronics4915.frc2020.subsystems.Drive;

public class DriveCommands
{
    private final Drive mDrive;
    private final Joystick mJoystick;
    private boolean mInverted;
    private boolean mSlow;

    public DriveCommands(Drive drive, Joystick joystick)
    {
        mDrive = drive;
        mJoystick = joystick;
        mInverted = false;
        mSlow = false;
        mDrive.setDefaultCommand(new TeleOpCommand());
    }

    /**
     * Runs the standard arcadeDrive, but with conditions (inverted / in slow mode)
     * given by a set of toggleable booleans.
     */
    public class TeleOpCommand extends CommandBase
    {
        public TeleOpCommand()
        {
            addRequirements(mDrive);
        }

        @Override
        public void execute()
        {
            // universal joystick convention is that forward and left are negative
            // as such, the modifier starts negative
            double modifier = -1;

            if (mSlow)
                modifier *= Constants.Drive.kSlowModeMultiplier;
            if (mInverted)
                modifier *= -1;

            mDrive.arcadeDrive(modifier * mJoystick.getY(), modifier * mJoystick.getX());
        }
    }

    /**
     * Written exclusively for the trigger, this command sets
     * the drivetrain to slow mode.
     * A corresponding UnsetSlow does not exist - ToggleSlow functions fine.
     */
    public class SetSlow extends CommandBase
    {
        public SetSlow()
        {
            addRequirements(mDrive);
        }

        @Override
        public void initialize()
        {
            mSlow = true;
        }

        @Override
        public boolean isFinished()
        {
           return true;
        }
    }

    /**
     * Instantly ends, and goes back to the default TeleOpCommand.
     */
    public class ToggleSlow extends CommandBase
    {
        public ToggleSlow()
        {
            addRequirements(mDrive);
        }

        @Override
        public void initialize()
        {
            mSlow = !mSlow;
        }

        @Override
        public boolean isFinished()
        {
            return true;
        }
    }

    /**
     * Instantly ends, and goes back to the default TeleOpCommand.
     */
    public class ToggleInverted extends CommandBase
    {
        public ToggleInverted()
        {
            addRequirements(mDrive);
        }

        @Override
        public void initialize()
        {
            mInverted = !mInverted;
        }

        @Override
        public boolean isFinished()
        {
            return true;
        }
    }
}