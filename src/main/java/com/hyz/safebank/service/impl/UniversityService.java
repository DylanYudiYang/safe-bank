package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.University;

import java.util.List;

public interface UniversityService {
    BankResponse createUniversity(UniversityRequest universityRequest);

    BankResponse getUniversity(UniversityIdRequest universityIdRequest);

    BankResponse updateUniversity(UniversityUpdateRequest universityUpdateRequest);

    BankResponse deleteUniversity(UniversityIdRequest universityIdRequest);

    List<UniversityInfo> getAllUniversities();

    UniversityInfo convertToInfo(University university);
}
