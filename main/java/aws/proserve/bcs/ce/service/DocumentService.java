// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.service;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.DocumentFilter;
import com.amazonaws.services.simplesystemsmanagement.model.DocumentFilterKey;
import com.amazonaws.services.simplesystemsmanagement.model.DocumentIdentifier;
import com.amazonaws.services.simplesystemsmanagement.model.ListDocumentsRequest;
import com.amazonaws.services.simplesystemsmanagement.model.ListDocumentsResult;

import javax.annotation.Nullable;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class DocumentService {

    @Nullable
    public String getDocument(AWSSimpleSystemsManagement ssm, String name) {
        return getDocuments(ssm)
                .stream()
                .filter(i -> i.getName().contains(name))
                .findFirst()
                .map(DocumentIdentifier::getName)
                .orElse(null);
    }

    public List<DocumentIdentifier> getDocuments(AWSSimpleSystemsManagement ssm) {
        final var documents = new ArrayList<DocumentIdentifier>();
        final var describeRequest = new ListDocumentsRequest()
                .withDocumentFilterList(
                        new DocumentFilter().withKey(DocumentFilterKey.Owner).withValue("Self"),
                        new DocumentFilter().withKey(DocumentFilterKey.DocumentType).withValue("Command"));
        ListDocumentsResult result;
        do {
            result = ssm.listDocuments(describeRequest);
            describeRequest.setNextToken(result.getNextToken());

            documents.addAll(result.getDocumentIdentifiers());
        } while (result.getNextToken() != null);

        return documents;
    }
}
