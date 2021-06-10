// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import aws.proserve.bcs.ce.CloudEndureMachine;
import aws.proserve.bcs.dr.dto.Group;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureMachines.class)
@JsonDeserialize(as = ImmutableCloudEndureMachines.class)
@Value.Immutable
public interface CloudEndureMachines extends Group<CloudEndureMachine> {

    @Override
    CloudEndureMachine[] getItems();
}
