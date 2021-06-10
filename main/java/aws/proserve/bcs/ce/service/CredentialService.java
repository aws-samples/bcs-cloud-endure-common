// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import aws.proserve.bcs.ce.CloudEndureCredential;
import aws.proserve.bcs.ce.dto.CloudEndureCredentialInput;
import aws.proserve.bcs.ce.dto.CloudEndureCredentials;
import aws.proserve.bcs.ce.dto.ImmutableCloudEndureCredentialInput;
import aws.proserve.bcs.dr.ce.CloudEndureConstants;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersRequest;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Named
public class CredentialService {

    private final RestTemplate rest;
    private final AWSSimpleSystemsManagement ssm;

    CredentialService(
            RestTemplate rest,
            AWSSimpleSystemsManagement ssm) {
        this.rest = rest;
        this.ssm = ssm;
    }

    public String getAwsCloudId() {
        final var parameters = ssm.getParameters(new GetParametersRequest()
                .withNames(CloudEndureConstants.PARAM_AWS_CLOUD_ID)).getParameters();
        if (parameters.isEmpty()) {
            throw new IllegalStateException("Unable to find cloud-endure AWS cloud ID");
        }
        return parameters.get(0).getValue();
    }

    public CloudEndureCredential save(CloudEndureCredentialInput input) {
        final var base64 = ImmutableCloudEndureCredentialInput.builder()
                .from(input)
                .privateKey(Base64.getEncoder().encodeToString(input.getPrivateKey().getBytes(StandardCharsets.UTF_8)))
                .build();
        return rest.postForObject("/cloudCredentials", base64, CloudEndureCredential.class);
    }

    public CloudEndureCredential[] findAllCredentials() {
        return rest.getForObject("/cloudCredentials", CloudEndureCredentials.class).getItems();
    }
}
