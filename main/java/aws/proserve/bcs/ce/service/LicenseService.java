// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import aws.proserve.bcs.ce.CloudEndureLicense;
import aws.proserve.bcs.ce.dto.CloudEndureLicenses;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.stream.Stream;

@Named
public class LicenseService {

    private final RestTemplate rest;

    LicenseService(RestTemplate rest) {
        this.rest = rest;
    }

    private CloudEndureLicense[] findAllLicenses() {
        return rest.getForObject("/licenses", CloudEndureLicenses.class).getItems();
    }

    public CloudEndureLicense findMigrationLicense() {
        return Stream.of(findAllLicenses())
                .filter(l -> CloudEndureLicense.Type.MIGRATION.is(l.getType()))
                .filter(CloudEndureLicense::isEnough)
                .filter(CloudEndureLicense::isNotExpired)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No migration license available"));
    }
}
