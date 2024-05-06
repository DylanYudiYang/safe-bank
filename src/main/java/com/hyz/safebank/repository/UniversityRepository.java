package com.hyz.safebank.repository;

import com.hyz.safebank.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {
    boolean existsByUniversityName(String universityName);

    Optional<University> findByUniversityName(String universityName);
}
