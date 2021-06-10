// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * For CloudEndure api.
 */
@JsonSerialize(as = ImmutableConfigureCloudEndureProjectInput.class)
@JsonDeserialize(as = ImmutableConfigureCloudEndureProjectInput.class)
@Value.Immutable
public interface ConfigureCloudEndureProjectInput {

    String getRegion();

    String getSubnetId();

    boolean getUsePrivateIp();

    /**
     * @apiNote set to {@code true} to prevent assigning public DNS and IP address to staging server.
     */
    boolean getDisablePublicIp();
}
