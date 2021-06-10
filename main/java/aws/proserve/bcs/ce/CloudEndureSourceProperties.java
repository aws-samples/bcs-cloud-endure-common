// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.HasName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@JsonSerialize(as = ImmutableCloudEndureSourceProperties.class)
@JsonDeserialize(as = ImmutableCloudEndureSourceProperties.class)
@Value.Immutable
public interface CloudEndureSourceProperties extends HasName {

    CloudEndureCpu[] getCpu();

    CloudEndureDisk[] getDisks();

    String getMachineCloudId();

    String getMachineCloudState();

    long getMemory();

    String getOs();

    @Override
    @Nullable
    @Value.Default
    default String getName() {
        return null;
    }

    String[] getPublicIps();
}
