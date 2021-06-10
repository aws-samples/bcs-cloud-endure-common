// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * For CloudEndure api.
 */
@JsonSerialize(as = ImmutableLaunchMachinesInput.class)
@JsonDeserialize(as = ImmutableLaunchMachinesInput.class)
@Value.Immutable
public interface LaunchMachinesInput {

    MachineAndPointInTime[] getItems();

    @Value.Default
    default String getLaunchType() {
        return "TEST";
    }

    @JsonSerialize(as = ImmutableMachineAndPointInTime.class)
    @JsonDeserialize(as = ImmutableMachineAndPointInTime.class)
    @Value.Immutable
    interface MachineAndPointInTime {
        String getMachineId();

//        @Nullable
//        @Value.Default
//        default String getPointInTimeId() {
//            return null;
//        }
    }
}
