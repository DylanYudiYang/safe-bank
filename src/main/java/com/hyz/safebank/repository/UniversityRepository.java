package com.hyz.safebank.repository;

import com.hyz.safebank.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
    boolean existsByUniversityName(String universityName);
}
