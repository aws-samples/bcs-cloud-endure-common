// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import aws.proserve.bcs.ce.CloudEndureAccount;
import aws.proserve.bcs.ce.dto.ImmutableLoginInput;
import aws.proserve.bcs.dr.ce.CloudEndureConstants;
import aws.proserve.bcs.dr.secret.SecretManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

@Named
public class SessionService {

    private final SecretManager secretManager;
    private final Session session;
    private final RestTemplate rest;

    SessionService(
            SecretManager secretManager,
            Session session,
            RestTemplate rest) {
        this.secretManager = secretManager;
        this.session = session;
        this.rest = rest;
    }

    public String getToken() {
        return secretManager.getSecret(CloudEndureConstants.PARAM_TOKEN);
    }

    public void setToken(String token) {
        secretManager.saveSecret(CloudEndureConstants.PARAM_TOKEN, token);
    }

    /**
     * @apiNote a cookie which looks like below
     * <code>XSRF-TOKEN="e1Z1f0amNQu8Um11eefZOQ==\012"; Secure; Path=/</code>
     */
    public CloudEndureAccount login() throws IllegalStateException {
        final ResponseEntity<CloudEndureAccount> response =
                rest.postForEntity("/login",
                        ImmutableLoginInput.builder().userApiToken(getToken()).build(),
                        CloudEndureAccount.class);
        session.setSecret(response.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
        return response.getBody();
    }
}
