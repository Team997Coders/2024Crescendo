package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;

public class VisionSubsystem {
    private PhotonCamera camera;
    private AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();
    private PhotonPoseEstimator photonPoseEstimator;

    public VisionSubsystem(String cameraName, Transform3d robotToCamera){
        this.camera = new PhotonCamera(cameraName);

        this.photonPoseEstimator = new PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, robotToCamera);
    }

    public void update(SwerveDrivePoseEstimator poseEstimator){
        var results = this.camera.getLatestResult();
        if (results.hasTargets()){
            Optional<EstimatedRobotPose> estimatedRobotPose = this.photonPoseEstimator.update(results);
            poseEstimator.addVisionMeasurement(estimatedRobotPose.orElseThrow().estimatedPose.toPose2d(), results.getTimestampSeconds());
        }
    }
    public int get_tag_id(){
            // Handle the case where there is no target
        PhotonTrackedTarget bestTarget = this.camera.getLatestResult().getBestTarget();
        if (bestTarget != null) {
            return bestTarget.getFiducialId();
        } else {
            // Handle the case where there is no target
            System.err.println("No target found!");
            return -1; // Or another appropriate value or error handling
        }
    }
}