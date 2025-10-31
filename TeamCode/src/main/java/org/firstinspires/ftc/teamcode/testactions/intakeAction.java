package org.firstinspires.ftc.teamcode.testactions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;

public class intakeAction {
    private DcMotor intake;     // 1 - expansion hub

    private double power;

    private boolean hasBeenRun = false; // To ensure it only runs once

    public  intakeAction(DcMotor DCMOTOR,double pow) {
        intake = DCMOTOR;

        if (pow < 0.0) {
            power = 0.0;
        } else if (power > 1.0) {
                power = 1.0;
        } else {
            power = pow;
        }

    }

    //@Override
    public boolean run(TelemetryPacket t) { // Changed parameter type here
        // This action is instantaneous, so it only needs to run once.
        if (!hasBeenRun) {
            intake.setPower(power);
            hasBeenRun = true;
            // Optional: Log the action
            // System.out.println("SetServoPositionAction: Setting servo " + servo.getPortNumber() + " to " + targetPosition);
            // if (t != null) { // Check if TelemetryPacket is provided
            //     t.put("Servo [" + servo.getPortNumber() + "] Target", targetPosition);
            // }
            return false; // Action is complete after the first run
        }
        return false; // Already run, so it's complete
    }

    // Optional: Call this if you want to reuse the same action instance
    // in a sequence (though creating a new instance is often clearer).
    public void reset() {
        hasBeenRun = false;
    }
}
