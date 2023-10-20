# Package com.team841.offseason2023

Contains all robot code.

## Structure

- [`com.team841.offseason2023.Auto`](./Auto/)

    Contains autonomous code and paths.

- [`Constants`](./Constants/)

    Contains robot constants. Values such as PID values, motor gains, everything that is constants.

- [`Drive`](./Drive/)

    Contains drivetrain code.

    > [`DriveStyle.java`](./Drive/DriveStyle.java): Drive module
    >
    > [`Drivetrain.java`](./Drive/Drivetrain.java): Drivetrain subsystem

- [`states`](./states/)

    Contains robot superstructure states:

    > [`States.java`](./states/States.java): Enum of all states
    >
    > [`SuperstructureStatePreset.java`](./states/SuperstructureStatePreset.java): Superstructure subsystem preset states used to store predetermined goal states.

- [`Superstructure`](./Superstructure/)

    Contains most suoperstructure code, includes superstructure and intake subsystems.

    > [`Intake.java`](./Superstructure/Intake.java): Intake subsystem
    >
    > [`Superstructure.java`](./Superstructure/Superstructure.java): Superstructure subsystem
    >
    > [`SuperstructureStateManager.java`](./Superstructure/SuperstructureStateManager.java): Superstructure state manager, a method inside called [`updateState()`](./Superstructure/SuperstructureStateManager.java#L25) is called every robot loop and updates the superstructure state into [`SC.java`](./Constants/SC.java#L22).
    >
    > [`commands/`](./Superstructure/commands/): Contains superstructure commands.
    >
    > [`factory/`](./Superstructure/factory/): Contains superstructure factory classes, used to move superstructure from driver inputs
    >> [`AbstractFactoryLogicBeta.java`](./Superstructure/factory/AbstractFactoryLogicBeta.java): Abstract class that extends interface[`AbstractFactoryMovement.java`](./Superstructure/factory/AbstractFactoryMovement.java) for superstructure factory command classes.
    >>
    >> [`SuperstructureFactoryBeta.java`](./Superstructure/factory/SuperstructureFactoryBeta.java): Superstructure factory command class, extends [`AbstractFactoryLogicBeta.java`](./Superstructure/factory/AbstractFactoryLogicBeta.java) and implements [`AbstractFactoryMovement.java`](./Superstructure/factory/AbstractFactoryMovement.java).
    >
    > [`GamePiece.java`](./Superstructure/GamePiece.java): Enum of all game pieces

- [`util`](./util/)

    Contains utility classes, inluding [`BioFalcon.java`](./util/BioFalcon.java) and [`Gains.java`](./util/Gains.java) to set falcon gains. Also contains [`tunableNumber.java`](./util/tunableNumber.java) to create tunable numbers in shuffleboard, which allows for faster PID tuning.

- [`Robot.java`](./Robot.java)

    Creates everything

- [`RobotContainer.java`](./RobotContainer.java)

    Where auto path is set and where controller buttons are binded. All subsystems are intialized here.
