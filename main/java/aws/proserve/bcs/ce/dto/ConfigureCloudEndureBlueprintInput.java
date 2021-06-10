// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import aws.proserve.bcs.ce.CloudEndureBlueprintDisk;
import aws.proserve.bcs.ce.CloudEndureTag;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * For CloudEndure api.
 */
@JsonSerialize(as = ImmutableConfigureCloudEndureBlueprintInput.class)
@JsonDeserialize(as = ImmutableConfigureCloudEndureBlueprintInput.class)
@Value.Immutable
public interface ConfigureCloudEndureBlueprintInput {

    String getMachineId();

    String getInstanceType();

    String getIamRole();

    @Value.Default
    default String getPrivateIPAction() {
        return "CUSTOM_IP";
    }

    @Value.Default
    default String getPublicIPAction() {
        return "AS_SUBNET";
    }

    @Value.Default
    default String getStaticIpAction() {
        return "IF_IN_ORIGIN";
    }

    @Value.Default
    default boolean getRunAfterLaunch() {
        return true;
    }

    /**
     * @apiNote As of Feb 2021, the API changed from recommendedPrivateIp to this name.
     */
    String[] getPrivateIPs();

    String[] getSecurityGroupIDs();

    String[] getSubnetIDs();

    CloudEndureBlueprintDisk[] getDisks();

    CloudEndureTag[] getTags();
}
