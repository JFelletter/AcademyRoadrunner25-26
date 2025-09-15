package org.firstinspires.ftc.teamcode.testactions; // Or your preferred package

import com.acmerobotics.dashboard.telemetry.TelemetryPacket; // Import TelemetryPacket
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Servo;

public class SetServoPositionAction implements Action {
    private Servo servo;
    private double targetPosition;
    private boolean hasBeenRun = false; // To ensure it only runs once

    // Constructor
    public SetServoPositionAction(Servo servo, double targetPosition) {
        this.servo = servo;
        // Clamp the targetPosition to be between 0.0 and 1.0
        if (targetPosition < 0.0) {
            this.targetPosition = 0.0;
        } else if (targetPosition > 1.0) {
            this.targetPosition = 1.0;
        } else {
            this.targetPosition = targetPosition;
        }
    }

    @Override
    public boolean run(TelemetryPacket t) { // Changed parameter type here
        // This action is instantaneous, so it only needs to run once.
        if (!hasBeenRun) {
            servo.setPosition(targetPosition);
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
