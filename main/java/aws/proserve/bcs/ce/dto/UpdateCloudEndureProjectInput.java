// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * For CloudEndure api.
 */
@JsonSerialize(as = ImmutableUpdateCloudEndureProjectInput.class)
@JsonDeserialize(as = ImmutableUpdateCloudEndureProjectInput.class)
@Value.Immutable
public interface UpdateCloudEndureProjectInput {

    String getSourceRegion();

    String getReplicationConfiguration();

}
