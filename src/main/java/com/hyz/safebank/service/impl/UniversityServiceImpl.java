package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.University;
import com.hyz.safebank.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService{

    @Autowired
    private UniversityRepository universityRepository;
    @Override
    public BankResponse createUniversity(UniversityRequest universityRequest) {
        if (universityRepository.existsByUniversityName(universityRequest.getUniversityName())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("University already exists")
                    .build();
        }

        University newUniversity = University.builder()
                .universityName(universityRequest.getUniversityName())
                .universityAbbreviation(universityRequest.getUniversityAbbreviation())
                .build();

        University savedUniversity = universityRepository.save(newUniversity);

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("University created successfully")
                .universityInfo(UniversityInfo.builder()
                        .universityId(savedUniversity.getId())
                        .universityName(savedUniversity.getUniversityName())
                        .universityAbbreviation(savedUniversity.getUniversityAbbreviation())
                        .build())
                .build();
    }

    @Override
    public BankResponse getUniversity(UniversityIdRequest universityIdRequest) {
        if (!universityRepository.existsById(universityIdRequest.getUniversityId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("University not found")
                    .build();
        }

        University university = universityRepository.findById(universityIdRequest.getUniversityId()).get();

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("University found")
                .universityInfo(UniversityInfo.builder()
                        .universityId(university.getId())
                        .universityName(university.getUniversityName())
                        .universityAbbreviation(university.getUniversityAbbreviation())
                        .build())
                .build();
    }

    @Override
    public BankResponse updateUniversity(UniversityUpdateRequest universityUpdateRequest) {
        if (!universityRepository.existsById(universityUpdateRequest.getUniversityId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("University not found")
                    .build();
        }

        University university = universityRepository.findById(universityUpdateRequest.getUniversityId()).get();
        university.setUniversityName(universityUpdateRequest.getUniversityName());
        university.setUniversityAbbreviation(universityUpdateRequest.getUniversityAbbreviation());

        University updatedUniversity = universityRepository.save(university);

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("University updated successfully")
                .universityInfo(UniversityInfo.builder()
                        .universityId(updatedUniversity.getId())
                        .universityName(updatedUniversity.getUniversityName())
                        .universityAbbreviation(updatedUniversity.getUniversityAbbreviation())
                        .build())
                .build();
    }

    @Override
    public BankResponse deleteUniversity(UniversityIdRequest universityIdRequest) {
        if (!universityRepository.existsById(universityIdRequest.getUniversityId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("University not found")
                    .build();
        }

        universityRepository.deleteById(universityIdRequest.getUniversityId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("University deleted successfully")
                .build();
    }
}
