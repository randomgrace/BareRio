package com.spartronics4915.frc2020;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.spartronics4915.frc2020.commands.LEDCommands;
import com.spartronics4915.frc2020.subsystems.LED;
import com.spartronics4915.frc2020.subsystems.LED.BlingState;
import com.spartronics4915.lib.util.Logger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer
{
    private static final String kAutoOptionsKey = "AutoStrategyOptions";

    public final NetworkTableEntry mAutoModeEntry = NetworkTableInstance.getDefault()
        .getTable("SmartDashboard").getEntry("AutoStrategy");

    /* subsystems */
    private final LED mLED;

    /* subsystem commands */
    private final LEDCommands mLEDCommands;


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {




        /* constructing subsystems */
        mLED = LED.getInstance();

        /* constructing subsystem commands */
        mLEDCommands = new LEDCommands(mLED);

        // mLauncherCommands.new Zero(mLauncher).schedule();
    }

    private void configureJoystickBindings()
    {
        /* toggle animation to indicate SLOW vs NORMAL drive speeds */
//         new JoystickButton(mJoystick, 1).whenPressed(mDriveCommands.new SetSlow()
//                 .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_DRIVE_SLOW)))
//             .whenReleased(mDriveCommands.new ToggleSlow()
//                 .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_TELEOP)));
//         new JoystickButton(mJoystick, 8).whenPressed(new InstantCommand(() -> mIndexer.setZero()));
// 
//         // Chris has expressed he doesn't want functionality on buttons 2, 4, and 5
//         new JoystickButton(mJoystick, 3).whenPressed(mDriveCommands.new ToggleInverted()); // TODO: alongWith Vision
// 
//         // Both JoystickButton 6 and 7 have the same functionality - they're close together + on passive hand side
//         /* animation for drive SLOW */
//         new JoystickButton(mJoystick, 6).whenPressed(mDriveCommands.new ToggleSlow() // TODO: alongWith Vision
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_DRIVE_SLOW)));
//         new JoystickButton(mJoystick, 7).whenPressed(mDriveCommands.new ToggleSlow() // TODO: alongWith Vision
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_DRIVE_SLOW)));

        /*
        new JoystickButton(mJoystick, 1).whenPressed(mIndexerCommands.new ZeroSpinnerCommand(true));
        new JoystickButton(mJoystick, 2).whenPressed(mIndexerCommands.new SpinIndexer(5));
        new JoystickButton(mJoystick, 4).whenPressed(mIndexerCommands.new StartTransfer())
            .whenReleased(mIndexerCommands.new EndTransfer());
        new JoystickButton(mJoystick, 5).whenPressed(mIndexerCommands.new StartKicker())
            .whenReleased(mIndexerCommands.new EndKicker());
        new JoystickButton(mJoystick, 6).whenPressed(mSuperstructureCommands.new LaunchSequence());
        */

        /*
        new JoystickButton(mJoystick, 1).toggleWhenPressed(mLauncherCommands.new ShootBallTest());
        new JoystickButton(mJoystick, 2).toggleWhenPressed(mLauncherCommands.new Zero());
        new JoystickButton(mJoystick, 3).toggleWhenPressed(mLauncherCommands.new HoodTest());
        new JoystickButton(mJoystick, 4).toggleWhenPressed(mPanelRotatorCommands.new Raise());
        new JoystickButton(mJoystick, 5).toggleWhenPressed(mPanelRotatorCommands.new Lower());
        new JoystickButton(mJoystick, 6).toggleWhenPressed(mPanelRotatorCommands.new SpinToColor());
        */

        /* Test Command that fires all balls after setting Flywheel/Hood values from SmartDashboard
        new JoystickButton(mJoystick, 4).toggleWhenPressed(new SequentialCommandGroup(
            new ParallelRaceGroup(
                new ParallelCommandGroup(mIndexerCommands.new SpinUpKicker(mIndexer),
                    mLauncherCommands.new ShootBallTest(mLauncher)),
                mLauncherCommands.new WaitForFlywheel(mLauncher)),
            new ParallelCommandGroup(mIndexerCommands.new LoadToLauncher(mIndexer, 5),
                mLauncherCommands.new ShootBallTest(mLauncher))));
        new JoystickButton(mJoystick, 5).toggleWhenPressed(new SequentialCommandGroup(
            new ParallelRaceGroup(
                new ParallelCommandGroup(mIndexerCommands.new SpinUpKicker(mIndexer),
                    mLauncherCommands.new ShootBallTestWithDistance(mLauncher)),
                mLauncherCommands.new WaitForFlywheel(mLauncher)),
            new ParallelCommandGroup(mIndexerCommands.new LoadToLauncher(mIndexer, 5),
                mLauncherCommands.new ShootBallTestWithDistance(mLauncher))));
        */

        /*
        new JoystickButton(mJoystick, 7).whileHeld(new TrajectoryTrackerCommand(mDrive, mDrive,
            this::throughTrench, mRamseteController, mStateEstimator.getEncoderRobotStateMap()));
        new JoystickButton(mJoystick, 7).whileHeld(new TrajectoryTrackerCommand(mDrive, mDrive,
            this::toControlPanel, mRamseteController, mStateEstimator.getEncoderRobotStateMap()));
        new JoystickButton(mJoystick, 3).toggleWhenPressed(mLauncherCommands.new AutoAimTurret(
            mLauncher,Constants.Launcher.goalLocation,mStateEstimator.getEncoderRobotStateMap()));
        */
    }

    private void configureButtonBoardBindings()
    {
        /* animate launch */
//         new JoystickButton(mButtonBoard, 4).whenPressed(mSuperstructureCommands.new LaunchSequence(1)
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_LAUNCH)));
//         /* TODO: validate multiple launch animations */
//         new JoystickButton(mButtonBoard, 3).whenPressed(mSuperstructureCommands.new LaunchSequence(5))
//             .whileActiveContinuous(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_LAUNCH));
//         /* animation for pickup: change bling state when command active/inactive */
//         // TODO: validate pickup animation
//         new JoystickButton(mButtonBoard, 2).toggleWhenPressed(mSuperstructureCommands.new IntakeRace())
//             .whenActive(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_INTAKE))
//             .whenInactive(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_TELEOP));
//         /* animation for eject: change bling state when command active/inactive */
//         // TODO: validate eject animation
//         new JoystickButton(mButtonBoard, 1).toggleWhenPressed(mIntakeCommands.new Eject())
//             .whenActive(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_EJECT))
//             .whenInactive(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_TELEOP));
// 
//         /* animation for climb -- note, we are not differentiating different climb states */
//         new JoystickButton(mButtonBoard, 8).whenHeld(mClimberCommands.new Winch()
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_CLIMBING)));
//         new JoystickButton(mButtonBoard, 9).whileHeld(mClimberCommands.new Retract()
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_CLIMBING)));
//         new JoystickButton(mButtonBoard, 10).whileHeld(mClimberCommands.new Extend()
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_CLIMBING)));
// 
//         // new JoystickButton(mButtonBoard, 6).toggleWhenPressed(new ConditionalCommand(mLauncherCommands.new Target());
//         // new JoystickButton(mButtonBoard, 7).whenPressed(LauncherCommands.new Launch());
// 
//         /* turning off LEDs for control panel actions to minimize interference */
//         new JoystickButton(mButtonBoard, 5).whenPressed(mPanelRotatorCommands.new Lower()
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_TELEOP)));
//         new JoystickButton(mButtonBoard, 6).whenPressed(mPanelRotatorCommands.new Raise()
//             .alongWith(mLEDCommands.new SetBlingState(BlingState.BLING_COMMAND_OFF)));
//         new JoystickButton(mButtonBoard, 7).whenPressed(mPanelRotatorCommands.new SpinToColor());

        // TODO: interface with the button board "joystick" potentially through GenericHID
        // new JoystickButton(mButtonBoard, 12).whenPressed(mClimberCommands.new ExtendMin());
        // new JoystickButton(mButtonBoard, 13).whenPressed(mClimberCommands.new ExtendMax());
        // new JoystickButton(mButtonBoard, 14).whenPressed(mPanelRotatorCommands.new AutoSpinRotation());
        // new JoystickButton(mButtonBoard, 15).whenPressed(mPanelRotatorCommands.new AutoSpinToColor());

        /* Four-way Joystick
        new JoystickButton(mButtonBoard, 15).whenHeld(new TurretRaiseCommand());
        new JoystickButton(mButtonBoard, 16).whenHeld(new TurretLowerCommand());
        new JoystickButton(mButtonBoard, 17).whenHeld(new TurretLeftCommand());
        new JoystickButton(mButtonBoard, 18).whenHeld(new TurretRightCommand());
        */
    }

    /**
     * configureTestCommands is not actually run. It is declared public to quell warnings.
     * Its use is to test out different construction idioms for externally defined commands.
     */
    public void configureTestCommands()
    {
        /**
         * in this style object construction happens in the CommandFactory
         * this.mExampleCommandFactory.MakeCmd(IndexerCommandFactory.CmdEnum.kTest1);
         *
         * in this mode we construct things here, we must pass in parameters
         * that are required during construction, since the outer class
         * member variables aren't accessible until after construction.
         * this.mExampleCommandFactory.new Test5(this.mLED); // an InstantCommand
         * this.mExampleCommandFactory.new Test6(this.mLED); // a StartEndCommand
         */
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
//         String selectedModeName = mAutoModeEntry.getString("NO SELECTED MODE!!!!");
//         Logger.notice("Auto mode name " + selectedModeName);
//         for (var mode : mAutoModes)
//         {
//             if (mode.name.equals(selectedModeName))
//             {
//                 return mode.command;
//             }
//         }
// 
//         Logger.error("AutoModeSelector failed to select auto mode: " + selectedModeName);
//         return TrajectoryContainer.kDefaultAutoMode.command;
        return null;
    }
}
