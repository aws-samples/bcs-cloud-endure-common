// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@JsonSerialize(as = ImmutableCloudEndureMachine.class)
@JsonDeserialize(as = ImmutableCloudEndureMachine.class)
@Value.Immutable
public interface CloudEndureMachine extends Identifiable {

    boolean isAgentInstalled();

    String getReplicationStatus();

    CloudEndureReplicationInfo getReplicationInfo();

    CloudEndureSourceProperties getSourceProperties();

    /**
     * @apiNote Added by DRPortal
     */
    @Nullable
    @Value.Default
    default Boolean getBlueprintConfigured() {
        return null;
    }

    /**
     * @apiNote Added by DRPortal
     */
    @Nullable
    @Value.Default
    default String getRegion() {
        return null;
    }
}
