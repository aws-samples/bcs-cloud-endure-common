// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.regex.Pattern;

@Named
public class Session {
    private static final Pattern TOKEN = Pattern.compile("XSRF-TOKEN=\"(.*)\"");

    private final Logger log = LoggerFactory.getLogger(getClass());
    private String secret;

    public String getSecret() {
        return secret;
    }

    /**
     * @param cookie a cookie which looks like below
     *               <code>XSRF-TOKEN="e1Z1f0amNQu8Um11eefZOQ==\012"; Secure; Path=/</code>
     */
    void setSecret(String cookie) throws IllegalStateException {
        final var matcher = TOKEN.matcher(cookie);
        if (matcher.find()) {
            log.info("Session updated.");
            secret = matcher.group(1);
        } else {
            throw new IllegalStateException("Unable to login");
        }
    }
}
