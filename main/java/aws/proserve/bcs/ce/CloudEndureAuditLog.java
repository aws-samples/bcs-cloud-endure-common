// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;

@JsonSerialize(as = ImmutableCloudEndureAuditLog.class)
@JsonDeserialize(as = ImmutableCloudEndureAuditLog.class)
@Value.Immutable
public interface CloudEndureAuditLog {

    /**
     * @apiNote "CloudEndure Service" does not have username.
     */
    @Value.Default
    default String getUsername() {
        return "CloudEndure Service";
    }

    String getEventName();

    String getDescription();

    ZonedDateTime getTimestamp();

    ChangedField[] getChangedFields();

    AuditLogMachine[] getParticipatingMachines();

    @JsonSerialize(as = ImmutableAuditLogMachine.class)
    @JsonDeserialize(as = ImmutableAuditLogMachine.class)
    @Value.Immutable
    interface AuditLogMachine {
        String getMachineCloudId();

        @Nullable
        @Value.Default
        default String getMachineCloudName() {
            return null;
        }

        @Nullable
        @Value.Default
        default String getMachineCloudEndureId() {
            return null;
        }
    }

    @JsonSerialize(as = ImmutableChangedField.class)
    @JsonDeserialize(as = ImmutableChangedField.class)
    @Value.Immutable
    interface ChangedField {
        String getFieldName();

        String getNewValue();

        String getOldValue();
    }
}
