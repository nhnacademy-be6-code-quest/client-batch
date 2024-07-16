package com.nhnacademy.clientbatch.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class ClientTest {

    @Test
    void testNoArgsConstructor() {
        // Create an instance using no-args constructor and set values manually
        Client client = new Client();
        client.setClientId(1L);
        client.setClientGradeId(2L);
        client.setClientEmail("test@example.com");
        client.setClientPassword("pass123");
        client.setClientName("John Doe");
        client.setClientBirth(LocalDate.now());
        client.setClientCreatedAt(LocalDateTime.now());
        client.setLastLoginDate(LocalDateTime.now());
        client.setDeleted(true);
        client.setClientDeleteDate(LocalDateTime.now());

        // Assert that all fields are set correctly
        assertThat(client.getClientId()).isEqualTo(1L);
        assertThat(client.getClientGradeId()).isEqualTo(2L);
        assertThat(client.getClientEmail()).isEqualTo("test@example.com");
        assertThat(client.getClientPassword()).isEqualTo("pass123");
        assertThat(client.getClientName()).isEqualTo("John Doe");
        assertThat(client.isDeleted()).isTrue();
    }

    @Test
    void testAllArgsConstructor() {
        // Test all-args constructor
        LocalDateTime time = LocalDateTime.now();
        Client client = new Client(1L, 2L, "email@example.com", "password", "Jane Doe", LocalDate.now(), time, time, false, time);

        assertThat(client).isNotNull();
        assertThat(client.getClientId()).isEqualTo(1L);
        assertThat(client.getClientEmail()).isEqualTo("email@example.com");
        assertThat(client.getClientName()).isEqualTo("Jane Doe");
    }

    @Test
    void testBuilder() {
        // Testing Builder
        Client client = Client.builder()
                .clientId(1L)
                .clientGradeId(2L)
                .clientEmail("build@example.com")
                .clientPassword("builder123")
                .clientName("Builder Name")
                .clientBirth(LocalDate.of(1980, 1, 1))
                .clientCreatedAt(LocalDateTime.now())
                .lastLoginDate(LocalDateTime.now())
                .isDeleted(true)
                .clientDeleteDate(LocalDateTime.now())
                .build();

        assertThat(client.getClientId()).isEqualTo(1L);
        assertThat(client.getClientGradeId()).isEqualTo(2L);
        assertThat(client.getClientEmail()).isEqualTo("build@example.com");
        assertThat(client.getClientPassword()).isEqualTo("builder123");
        assertThat(client.getClientName()).isEqualTo("Builder Name");
        assertThat(client.isDeleted()).isTrue();
    }

    @Test
    void testEqualsAndHashCode() {
        // Test equals and hashCode methods to cover Lombok-generated implementations
        Client client1 = new Client(1L, null, "email@example.com", null, null, null, null, null, false, null);
        Client client2 = new Client(1L, null, "email@example.com", null, null, null, null, null, false, null);

        assertThat(client1).isEqualTo(client2);
        assertThat(client1.hashCode()).hasSameHashCodeAs(client2.hashCode());
    }

    @Test
    void testToString() {
        // Test toString method to ensure it includes relevant fields
        Client client = new Client(1L, 2L, "email@example.com", "pass", "John", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), false, null);
        assertThat(client.toString()).contains("email@example.com");
    }
}
