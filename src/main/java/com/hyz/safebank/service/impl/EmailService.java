package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
