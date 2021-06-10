// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureBlueprint.class)
@JsonDeserialize(as = ImmutableCloudEndureBlueprint.class)
@Value.Immutable
public interface CloudEndureBlueprint extends Identifiable {

    String getMachineId();

    String getRegion();

    boolean getRunAfterLaunch();

    CloudEndureTag[] getTags();
}
