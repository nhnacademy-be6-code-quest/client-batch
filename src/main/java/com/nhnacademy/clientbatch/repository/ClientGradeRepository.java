package com.nhnacademy.clientbatch.repository;

import com.nhnacademy.clientbatch.entity.ClientGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface ClientGradeRepository extends JpaRepository<ClientGrade, Long> {

    /**
     *
     * @param clientGradeName entity to be saved. Must not be {@literal null}.
     * @return
     */
    ClientGrade findByClientGradeName(String clientGradeName);

}
