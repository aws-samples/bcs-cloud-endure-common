// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureReplicationConfiguration.class)
@JsonDeserialize(as = ImmutableCloudEndureReplicationConfiguration.class)
@Value.Immutable
public interface CloudEndureReplicationConfiguration extends Identifiable {

    /**
     * @apiNote Do not use "is" prefix for boolean here, as the deserializer does not support this prefix.
     */
    boolean getDisablePublicIp();

    boolean getUseDedicatedServer();

    boolean getUseLowCostDisks();

    boolean getUsePrivateIp();

    int getBandwidthThrottling();

    String getRegion();

    String getReplicationServerType();

    String getSubnetId();
}
