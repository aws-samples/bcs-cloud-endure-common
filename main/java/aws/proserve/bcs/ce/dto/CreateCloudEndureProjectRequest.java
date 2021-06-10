// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import aws.proserve.bcs.dr.dto.request.CreateProjectRequest;
import aws.proserve.bcs.dr.secret.Credential;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * From GWT.
 */
@JsonSerialize(as = ImmutableCreateCloudEndureProjectRequest.class)
@JsonDeserialize(as = ImmutableCreateCloudEndureProjectRequest.class)
@Value.Immutable
public interface CreateCloudEndureProjectRequest extends CreateProjectRequest {

    /**
     * @apiNote must provide this default method for boolean value because GWT omits the boolean value if it is false,
     * therefore, JSON will complain that the value is missing.
     * <p>
     * Must use prefix of <code>get</code> instead of <code>is</code> as JSON generates <code>setIs</code> for
     * <code>is</code> prefix methods.
     */
    @Value.Default
    default boolean getPublicNetwork() {
        return false;
    }

    /**
     * @apiNote GWT does not pass this value. It is computed before sending to step functions.
     */
    @Nullable
    @Value.Default
    default String getStagingSubnetId() {
        return null;
    }

    /**
     * @apiNote it will be nulled before executing step functions executions.
     */
    @Nullable
    Credential getSourceCredential();

    /**
     * @apiNote GWT does not pass this value. It is computed before creating the project.
     */
    @Nullable
    String getSourceCredentialId();

    String getSourceVpcId();

    /**
     * @apiNote possible null target vpc for wizard.
     */
    @Nullable
    String getTargetVpcId();

    String getSourceInstanceType();

    String getTargetInstanceType();
}
