// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import aws.proserve.bcs.ce.CloudEndureLicense;
import aws.proserve.bcs.dr.dto.Group;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureLicenses.class)
@JsonDeserialize(as = ImmutableCloudEndureLicenses.class)
@Value.Immutable
public interface CloudEndureLicenses extends Group<CloudEndureLicense> {

    @Override
    CloudEndureLicense[] getItems();
}
