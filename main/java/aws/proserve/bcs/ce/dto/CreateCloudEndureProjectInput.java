// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import aws.proserve.bcs.dr.dto.HasName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * For CloudEndure api.
 */
@JsonSerialize(as = ImmutableCreateCloudEndureProjectInput.class)
@JsonDeserialize(as = ImmutableCreateCloudEndureProjectInput.class)
@Value.Immutable
public interface CreateCloudEndureProjectInput extends HasName {

    String getType();

    String getTargetCloudId();

    String[] getLicensesIDs();

    String[] getCloudCredentialsIDs();
}
