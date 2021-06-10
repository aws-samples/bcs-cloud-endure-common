// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.Duration;
import java.time.ZonedDateTime;

@JsonSerialize(as = ImmutableCloudEndureLicense.class)
@JsonDeserialize(as = ImmutableCloudEndureLicense.class)
@Value.Immutable
public interface CloudEndureLicense extends Identifiable {

    enum Type {
        DR,
        MIGRATION;

        public boolean is(String value) {
            return name().equals(value);
        }
    }

    String getType();

    int getCount();

    @Value.Default
    default Duration getDurationFromStartOfUse() {
        return Duration.ZERO;
    }

    int getUsed();

    ZonedDateTime getExpirationDateTime();

    default boolean isNotExpired() {
        return getExpirationDateTime().isAfter(ZonedDateTime.now());
    }

    default boolean isEnough() {
        return getCount() > getUsed();
    }
}
