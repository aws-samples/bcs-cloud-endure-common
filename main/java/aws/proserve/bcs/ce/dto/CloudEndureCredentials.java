// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import aws.proserve.bcs.ce.CloudEndureCredential;
import aws.proserve.bcs.dr.dto.Group;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureCredentials.class)
@JsonDeserialize(as = ImmutableCloudEndureCredentials.class)
@Value.Immutable
public interface CloudEndureCredentials extends Group<CloudEndureCredential> {

    @Override
    CloudEndureCredential[] getItems();
}
