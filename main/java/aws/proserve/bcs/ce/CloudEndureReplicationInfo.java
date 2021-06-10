// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;

@JsonSerialize(as = ImmutableCloudEndureReplicationInfo.class)
@JsonDeserialize(as = ImmutableCloudEndureReplicationInfo.class)
@Value.Immutable
public interface CloudEndureReplicationInfo {

    long getBackloggedStorageBytes();

    /**
     * @apiNote at the beginning, this property is missing from cloud-endure.
     */
    @Value.Default
    default long getReplicatedStorageBytes() {
        return 0;
    }

    long getTotalStorageBytes();

    /**
     * @apiNote will be <code>null</code> before the first restorable point is created.
     */
    @Nullable
    @Value.Default
    default ZonedDateTime getLastConsistencyDateTime() {
        return null;
    }

    /**
     * @apiNote initial data may not contain this info.
     */
    @Nullable
    @Value.Default
    default ZonedDateTime getLastSeenDateTime() {
        return null;
    }
}
