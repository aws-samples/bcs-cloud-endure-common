// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import aws.proserve.bcs.ce.CloudEndureBlueprint;
import aws.proserve.bcs.ce.dto.CloudEndureBlueprints;
import aws.proserve.bcs.ce.dto.ConfigureCloudEndureBlueprintInput;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

@Named
public class BlueprintService {

    private final RestTemplate rest;

    BlueprintService(RestTemplate rest) {
        this.rest = rest;
    }

    public CloudEndureBlueprint[] findAll(String projectId) {
        return rest.getForObject("/projects/{id}/blueprints", CloudEndureBlueprints.class, projectId).getItems();
    }

    public CloudEndureBlueprint configure(
            String projectId, String blueprintId, ConfigureCloudEndureBlueprintInput input) {
        return rest.patchForObject("/projects/{projectId}/blueprints/{blueprintId}",
                input, CloudEndureBlueprint.class, projectId, blueprintId);
    }
}
