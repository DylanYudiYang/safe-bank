package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.UniversityIdRequest;
import com.hyz.safebank.dto.UniversityRequest;
import com.hyz.safebank.dto.UniversityUpdateRequest;

public interface UniversityService {
    BankResponse createUniversity(UniversityRequest universityRequest);

    BankResponse getUniversity(UniversityIdRequest universityIdRequest);

    BankResponse updateUniversity(UniversityUpdateRequest universityUpdateRequest);

    BankResponse deleteUniversity(UniversityIdRequest universityIdRequest);
}
