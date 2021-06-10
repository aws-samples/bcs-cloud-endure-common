// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.dto;

import org.immutables.value.Value;

/**
 * For CloudEndure api.
 */
@Value.Immutable
public interface LoginInput {

    String getUserApiToken();
}
