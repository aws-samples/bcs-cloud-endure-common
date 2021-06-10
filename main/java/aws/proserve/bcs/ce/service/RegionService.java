// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import aws.proserve.bcs.ce.CloudEndureRegion;
import aws.proserve.bcs.ce.dto.CloudEndureRegions;
import aws.proserve.bcs.dr.project.Region;
import com.amazonaws.regions.Regions;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Named
public class RegionService {

    private final RestTemplate rest;
    private final Map<Regions, String> regionMap;

    RegionService(RestTemplate rest) {
        this.rest = rest;
        this.regionMap = new EnumMap<>(Regions.class);
        regionMap.put(Regions.US_EAST_1, "Northern Virginia");
        regionMap.put(Regions.US_EAST_2, "Ohio");
        regionMap.put(Regions.US_WEST_1, "Northern California");
        regionMap.put(Regions.US_WEST_2, "Oregon");
        regionMap.put(Regions.EU_WEST_1, "Ireland");
        regionMap.put(Regions.EU_WEST_2, "London");
        regionMap.put(Regions.EU_WEST_3, "Paris");
        regionMap.put(Regions.EU_CENTRAL_1, "Frankfurt");
        regionMap.put(Regions.EU_NORTH_1, "Stockholm");
        regionMap.put(Regions.AP_EAST_1, "Hong Kong");
        regionMap.put(Regions.AP_SOUTH_1, "Mumbai");
        regionMap.put(Regions.AP_SOUTHEAST_1, "Singapore");
        regionMap.put(Regions.AP_SOUTHEAST_2, "Sydney");
        regionMap.put(Regions.AP_NORTHEAST_1, "Tokyo");
        regionMap.put(Regions.AP_NORTHEAST_2, "Seoul");
        regionMap.put(Regions.SA_EAST_1, "Sao Paulo");
        regionMap.put(Regions.CN_NORTH_1, "Beijing");
        regionMap.put(Regions.CN_NORTHWEST_1, "Ningxia");
        regionMap.put(Regions.CA_CENTRAL_1, "Central");
        regionMap.put(Regions.ME_SOUTH_1, "Bahrain");
    }

    private Optional<Regions> findRegion(String name) {
        return Arrays.stream(Regions.values())
                .filter(i -> regionMap.containsKey(i) && name.contains(regionMap.get(i)))
                .findFirst();
    }

    public Optional<Region> find(String credentialId, String regionId) {
        return Stream.of(findAllRegions(credentialId))
                .filter(i -> i.getId().equals(regionId))
                .findFirst()
                .map(CloudEndureRegion::getName)
                .flatMap(this::findRegion)
                .map(Region::new);
    }

    public CloudEndureRegion find(String credentialId, Regions region) {
        final var name = Objects.requireNonNull(regionMap.get(region));
        return Stream.of(findAllRegions(credentialId))
                .filter(item -> item.getName().contains(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unable to find region " + region));
    }

    public CloudEndureRegion[] findAllRegions(String credentialId) {
        return rest.getForObject("/cloudCredentials/{credId}/regions", CloudEndureRegions.class, credentialId).getItems();
    }
}
