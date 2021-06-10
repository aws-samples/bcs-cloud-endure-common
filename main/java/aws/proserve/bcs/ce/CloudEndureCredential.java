// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce;

import aws.proserve.bcs.dr.dto.Identifiable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableCloudEndureCredential.class)
@JsonDeserialize(as = ImmutableCloudEndureCredential.class)
@Value.Immutable
public interface CloudEndureCredential extends Identifiable {

    /**
     * @return the AWS access key.
     */
    String getPublicKey();

    String getCloud();

    /**
     * @return the AWS account number.
     */
    String getAccountIdentifier();
}
