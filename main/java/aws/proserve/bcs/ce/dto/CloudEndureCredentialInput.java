// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureCredentialInput.class)
@JsonDeserialize(as = ImmutableCloudEndureCredentialInput.class)
@Value.Immutable
public interface CloudEndureCredentialInput {

    String getPublicKey();

    String getCloudId();

    String getPrivateKey();
}
