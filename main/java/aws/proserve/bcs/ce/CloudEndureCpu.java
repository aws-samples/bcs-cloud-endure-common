// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureCpu.class)
@JsonDeserialize(as = ImmutableCloudEndureCpu.class)
@Value.Immutable
public interface CloudEndureCpu {

    int getCores();

    String getModelName();
}
