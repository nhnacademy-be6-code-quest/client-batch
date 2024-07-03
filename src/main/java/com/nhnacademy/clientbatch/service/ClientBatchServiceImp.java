package com.nhnacademy.clientbatch.service;

import com.nhnacademy.clientbatch.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientBatchServiceImp implements ClientBatchService {
    private final ClientRepository clientRepository;

    @Override
    public void updateInactiveClients() {
        int updatedRecords;
        do {
            updatedRecords = clientRepository.updateClientIsDeletedIfInactive();
            log.info("batch start: delete row({}) ", updatedRecords);
        } while (updatedRecords > 0);
    }
}
