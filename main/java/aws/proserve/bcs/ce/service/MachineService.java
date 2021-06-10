// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import aws.proserve.bcs.ce.CloudEndureLaunchResult;
import aws.proserve.bcs.ce.CloudEndureMachine;
import aws.proserve.bcs.ce.dto.CloudEndureMachines;
import aws.proserve.bcs.ce.dto.LaunchMachinesInput;
import aws.proserve.bcs.dr.secret.Credential;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.CloudWatchOutputConfig;
import com.amazonaws.services.simplesystemsmanagement.model.SendCommandRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Named("CEMachineService")
public class MachineService {
    private static final String INSTALL_DOCUMENT = "InstallCloudEndureAgent";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestTemplate rest;
    private final DocumentService documentService;

    MachineService(
            RestTemplate rest,
            DocumentService documentService) {
        this.rest = rest;
        this.documentService = documentService;
    }

    public CloudEndureMachine[] findAll(String projectId) {
        return rest.getForObject("/projects/{id}/machines", CloudEndureMachines.class, projectId).getItems();
    }

    public CloudEndureLaunchResult launch(String projectId, LaunchMachinesInput input) {
        return rest.postForObject("/projects/{id}/launchMachines", input, CloudEndureLaunchResult.class, projectId);
    }

    public void installAgent(Credential sourceCredential, String region, String token, String[] instanceIds) {
        final var ssm = AWSSimpleSystemsManagementClientBuilder.standard()
                .withCredentials(sourceCredential.toProvider())
                .withRegion(region)
                .build();

        final var document = documentService.getDocument(ssm, INSTALL_DOCUMENT);
        if (document == null) {
            throw new IllegalArgumentException("Unable to find document " + INSTALL_DOCUMENT);
        }

        log.info("Send command to install agent on machines [{}] with document [{}]",
                Arrays.toString(instanceIds), document);
        ssm.sendCommand(new SendCommandRequest()
                .withDocumentName(document)
                .withInstanceIds(instanceIds)
                .withTimeoutSeconds(600)
                .withParameters(Map.of("Token", Collections.singletonList(token)))
                .withCloudWatchOutputConfig(new CloudWatchOutputConfig()
                        .withCloudWatchOutputEnabled(true)));
    }
}
