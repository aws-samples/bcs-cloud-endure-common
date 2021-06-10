// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.HasName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureSecurityGroup.class)
@JsonDeserialize(as = ImmutableCloudEndureSecurityGroup.class)
@Value.Immutable
public interface CloudEndureSecurityGroup extends HasName {

    String getSecurityGroupId();

    /**
     * @return the VPC id.
     */
    String getNetworkId();
}
