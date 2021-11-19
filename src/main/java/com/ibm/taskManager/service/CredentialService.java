package com.ibm.taskManager.service;

import com.ibm.taskManager.entity.Credential;
import com.ibm.taskManager.model.CredentialModel;
import com.ibm.taskManager.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    @Autowired
    CredentialRepository credentialRepo;

    public void addCredential(CredentialModel credentialModel) {
        Credential credentialEntity = mapModelToEntity(credentialModel);

        if (credentialEntity != null) {
            credentialRepo.save(credentialEntity);
        }
    }

    protected Credential mapModelToEntity(CredentialModel credentialModel) {
        if (credentialModel == null) {
            return null;
        }

        return new Credential(
                credentialModel.getUsername(),
                credentialModel.getPassword(),
                credentialModel.getDateLastAccess(),
                credentialModel.getDateCreateAccount(),
                null
        );
    }

    protected CredentialModel mapEntityToModel(Credential credentialEntity) {
        if (credentialEntity == null) {
            return null;
        }

        return new CredentialModel(
                credentialEntity.getCredentialId(),
                credentialEntity.getUsername(),
                credentialEntity.getPassword(),
                credentialEntity.getDateLastAccess(),
                credentialEntity.getDateCreateAccount(),
                null
        );
    }
}
