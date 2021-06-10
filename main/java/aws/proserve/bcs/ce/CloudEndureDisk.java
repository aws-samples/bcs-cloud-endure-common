// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.HasName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureDisk.class)
@JsonDeserialize(as = ImmutableCloudEndureDisk.class)
@Value.Immutable
public interface CloudEndureDisk extends HasName {

    boolean isProtected();

    long getSize();
}
