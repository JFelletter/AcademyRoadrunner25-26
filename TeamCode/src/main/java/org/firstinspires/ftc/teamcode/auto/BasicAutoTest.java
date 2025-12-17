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


import org.firstinspires.ftc.teamcode.setup.MecanumDrive;

@Config
@Autonomous(name = "Basic Auto Test", group = "Autonomous")
public class BasicAutoTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        //Define Poses
        Pose2d initialPose = new Pose2d(63.75,-17, Math.toRadians(180));
        Pose2d middlePose = new Pose2d(-23, -23, Math.toRadians(225));
        Pose2d shootingPose = new Pose2d( -36, -36, Math.toRadians(225));
        Pose2d intake1Pose = new Pose2d( -12, -31, Math.toRadians(270));
        Pose2d finished1Pose = new Pose2d( -12, -42, Math.toRadians(270));
        Pose2d intake2Pose = new Pose2d( 12, -31, Math.toRadians(270));
        Pose2d finished2Pose = new Pose2d( 12, -42, Math.toRadians(270));
        Pose2d intake3Pose = new Pose2d( 36, -31, Math.toRadians(270));
        Pose2d finished3Pose = new Pose2d( 36, -42, Math.toRadians(270));

        //Define any stored vectors (X/Y coordinate)

        //Initialize hardware systems
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                //Initial Shot
                .strafeToSplineHeading(middlePose.position, middlePose.heading)
                .strafeToSplineHeading(shootingPose.position, shootingPose.heading)
                .waitSeconds(1)
                // Intake 1 and shoot
                .strafeToSplineHeading(intake1Pose.position, intake1Pose.heading)
                .waitSeconds(1)
                .lineToY(finished1Pose.position.y)
                .strafeToSplineHeading(shootingPose.position, shootingPose.heading)
                .waitSeconds(1)
                //Intake 2 and shoot
                .strafeToSplineHeading(intake2Pose.position, intake2Pose.heading)
                .waitSeconds(1)
                .lineToY(finished2Pose.position.y)
                .strafeToSplineHeading(shootingPose.position, shootingPose.heading)
                .waitSeconds(1)
                //Intake 3 and shoot
                .strafeToSplineHeading(intake3Pose.position, intake3Pose.heading)
                .waitSeconds(1)
                .lineToY(finished3Pose.position.y)
                .strafeToSplineHeading(shootingPose.position, shootingPose.heading)
                .waitSeconds(1);

    // actions that need to happen on init; for instance, a claw tightening.
        //Builds out trajectories
        Action tab1Built = tab1.build();



        //Wait for start
        waitForStart();

        if (isStopRequested()) return;

        //Runs a sequence of actions.
        Actions.runBlocking(
                new SequentialAction(
                        tab1Built
                )
        );
    }
}