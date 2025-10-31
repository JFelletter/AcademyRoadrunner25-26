package org.firstinspires.ftc.teamcode.testactions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.dfrobot.HuskyLens;

public class scanAprilTagAction {
    private HuskyLens huskyLens;

    HuskyLens.Block[] tags = huskyLens.tags();

	huskyLens.selectAlgorithm(HuskyLens.Algorithm.TAG_RECOGNITION);
//sets huskylense to april tags


}
