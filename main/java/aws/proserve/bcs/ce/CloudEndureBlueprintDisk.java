// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.HasName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureBlueprintDisk.class)
@JsonDeserialize(as = ImmutableCloudEndureBlueprintDisk.class)
@Value.Immutable
public interface CloudEndureBlueprintDisk extends HasName {

    @Value.Default
    default String getType() {
        return "COPY_ORIGIN";
    }
}
