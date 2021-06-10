// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import aws.proserve.bcs.dr.project.Side;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * From GWT.
 */
@JsonSerialize(as = ImmutableManageCloudEndureMachinesRequest.class)
@JsonDeserialize(as = ImmutableManageCloudEndureMachinesRequest.class)
@Value.Immutable
public interface ManageCloudEndureMachinesRequest {

    @Value.Default
    default LaunchType getLaunchType() {
        return LaunchType.TEST;
    }

    Side getSide();

    /**
     * Map from CE machine ID to AWS instance ID.
     *
     * @apiNote Note that machine is to refer CE machines and instance is to refer AWS instances.
     */
    Map<String, String> getMachineIdMap();

    @JsonIgnore
    default Set<String> getMachineIds() {
        return getMachineIdMap().keySet();
    }

    @JsonIgnore
    default Collection<String> getInstanceIds() {
        return getMachineIdMap().values();
    }
}
