// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.HasName;
import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureCloud.class)
@JsonDeserialize(as = ImmutableCloudEndureCloud.class)
@Value.Immutable
public interface CloudEndureCloud extends Identifiable, HasName {

    enum Type {
        AWS,
        GENERIC,
        VCENTER;

        boolean is(String value) {
            return name().equals(value);
        }
    }

    String[] getRoles();

    default boolean isAws() {
        return Type.AWS.is(getName());
    }

    default boolean isGeneric() {
        return Type.GENERIC.is(getName());
    }
}
