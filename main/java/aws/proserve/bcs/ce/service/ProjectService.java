// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import aws.proserve.bcs.ce.CloudEndureAuditLog;
import aws.proserve.bcs.ce.CloudEndureReplicationConfiguration;
import aws.proserve.bcs.ce.dto.CloudEndureAuditLogs;
import aws.proserve.bcs.ce.dto.CloudEndureItems;
import aws.proserve.bcs.ce.dto.CloudEndureReplicationConfigurations;
import aws.proserve.bcs.ce.dto.ConfigureCloudEndureProjectInput;
import aws.proserve.bcs.ce.dto.CreateCloudEndureProjectInput;
import aws.proserve.bcs.ce.dto.UpdateCloudEndureProjectInput;
import aws.proserve.bcs.dr.ce.CloudEndureItem;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

@Named("CEProjectService")
public class ProjectService {

    private final RestTemplate rest;

    ProjectService(RestTemplate rest) {
        this.rest = rest;
    }

    public CloudEndureItem save(CreateCloudEndureProjectInput project) {
        return rest.postForObject("/projects", project, CloudEndureItem.class);
    }

    public CloudEndureItem[] findAll() {
        return rest.getForObject("/projects", CloudEndureItems.class).getItems();
    }

    public CloudEndureItem findOne(String id) {
        return rest.getForObject("/projects/{id}", CloudEndureItem.class, id);
    }

    public CloudEndureItem update(String id, UpdateCloudEndureProjectInput input) {
        return rest.patchForObject("/projects/{id}", input, CloudEndureItem.class, id);
    }

    public CloudEndureReplicationConfiguration configure(String id, ConfigureCloudEndureProjectInput input) {
        return rest.postForObject("/projects/{id}/replicationConfigurations",
                input, CloudEndureReplicationConfiguration.class, id);
    }

    public void delete(String id) {
        rest.delete("/projects/{id}", id);
    }

    public CloudEndureAuditLog[] findAllAuditLogs(String id) {
        return rest.getForObject("/projects/{id}/auditLog", CloudEndureAuditLogs.class, id).getItems();
    }

    public CloudEndureReplicationConfiguration[] findAllReplicationConfigurations(String id) {
        return rest.getForObject("/projects/{id}/replicationConfigurations", CloudEndureReplicationConfigurations.class, id).getItems();
    }
}
