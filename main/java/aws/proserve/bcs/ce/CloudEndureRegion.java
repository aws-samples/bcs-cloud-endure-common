// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@JsonSerialize(as = ImmutableCloudEndureRegion.class)
@JsonDeserialize(as = ImmutableCloudEndureRegion.class)
@Value.Immutable
public interface CloudEndureRegion extends Identifiable, HasName {

    String getCloud();

    @Nullable
    @Value.Default
    default String[] getIamRoles() {
        return null;
    }

    @Nullable
    @Value.Default
    default String[] getInstanceTypes() {
        return null;
    }

    @Nullable
    @Value.Default
    default CloudEndureSecurityGroup[] getSecurityGroups() {
        return null;
    }

    @Nullable
    @Value.Default
    default CloudEndureSubnet[] getSubnets() {
        return null;
    }
}
