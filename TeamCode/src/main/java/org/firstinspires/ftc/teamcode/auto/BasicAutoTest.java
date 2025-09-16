package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.setup.MecanumDrive;
import org.firstinspires.ftc.teamcode.testactions.SetServoPositionAction;

@Config
@Autonomous(name = "Basic Auto Test", group = "Autonomous")
public class BasicAutoTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        //Define any stored poses (X/Y coordinates with heading)
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(90));
        //Define any stored vectors (X/Y coordinate)

        //Initialize hardware systems
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Servo Light = (hardwareMap.get(Servo.class, "Light"));

        //Define trajectories
        //Trajectories must be given a start pose (not vector)
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .waitSeconds(3)
                .strafeTo(new Vector2d(12, 0))
                .waitSeconds(3)
                .strafeTo(new Vector2d(12, 12))
                .waitSeconds(3)
                .strafeTo(new Vector2d(5, 5))
                .waitSeconds(3);


        TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
                .waitSeconds(3)
                .strafeTo(new Vector2d(12, 0))
                .waitSeconds(3)
                .strafeTo(new Vector2d(12, 12))
                .waitSeconds(3)
                .strafeTo(new Vector2d(0, 0))
                .waitSeconds(3);

    // actions that need to happen on init; for instance, a claw tightening.
        //Builds out trajectories
        Action tab1Built = tab1.build();
        Action tab2Built = tab2.build();

        // Build out light colors
        Action lightRed = new SetServoPositionAction(Light, 0.227);
        Action lightBlue =new SetServoPositionAction(Light, 0.611);
        Action lightWhite = new SetServoPositionAction(Light, 1.0);



        //Wait for start
        waitForStart();

        if (isStopRequested()) return;

        //Runs a sequence of actions.
        Actions.runBlocking(
                new SequentialAction(
                        lightWhite,
                        new SleepAction(0.25),
                        tab1Built,
                        lightBlue,
                        tab2Built,
                        lightRed
                )
        );
    }
}