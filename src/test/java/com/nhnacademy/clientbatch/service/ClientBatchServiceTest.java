package com.nhnacademy.clientbatch.service;

import com.nhnacademy.clientbatch.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class ClientBatchServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientBatchServiceImp clientBatchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateInactiveClients() {
        // Setup to return a decreasing number of records updated to simulate batch completion
        when(clientRepository.updateClientIsDeletedIfInactive()).thenReturn(10, 5, 1, 0);

        // Execute the service method
        clientBatchService.updateInactiveClients();

        // Verify that the repository was called multiple times until no more records needed updating
        verify(clientRepository, times(4)).updateClientIsDeletedIfInactive();
        verifyNoMoreInteractions(clientRepository); // Ensure no extra calls were made

        // You can also use ArgumentCaptor or additional checks if needed
    }
}
