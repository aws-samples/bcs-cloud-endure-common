// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureLaunchResult.class)
@JsonDeserialize(as = ImmutableCloudEndureLaunchResult.class)
@Value.Immutable
public interface CloudEndureLaunchResult extends Identifiable {

    String getInitiatedBy();

//    ZonedDateTime getCreationDateTime();

    String[] getParticipatingMachines();

    String getStatus();

    String getType();
}
