// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.HasName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@JsonSerialize(as = ImmutableCloudEndureSubnet.class)
@JsonDeserialize(as = ImmutableCloudEndureSubnet.class)
@Value.Immutable
public interface CloudEndureSubnet extends HasName {

    @Nullable
    @Value.Default
    default String getSubnetId() {
        return null;
    }

    /**
     * @return the VPC id.
     */
    @Nullable
    @Value.Default
    default String getNetworkId() {
        return null;
    }
}
