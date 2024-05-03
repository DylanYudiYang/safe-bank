package com.hyz.safebank.service.impl;

import java.math.BigDecimal;

public interface CheckingAccountService {
    BigDecimal getCheckingAccountBalance(Long customerId);
}
