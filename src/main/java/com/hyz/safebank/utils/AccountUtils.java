package com.hyz.safebank.utils;

import java.time.Year;
import java.util.Random;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account created!";
    public static final String ACCOUNT_CREATION_SUCCESS = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account has been successfully created!";
//    public static final String ACCOUNT_NOT_EXIST_CODE = "003";
//    public static final String ACCOUNT_NOT_EXIST_MESSAGE = "User with the provided Account Number does not exist";
//    public static final String ACCOUNT_FOUND_CODE = "004";
//    public static final String ACCOUNT_FOUND_SUCCESS = "User Account Found";
//    public static final String ACCOUNT_CREDITED_SUCCESS = "005";
//    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "User Account was credited successfully";
//    public static final String INSUFFICIENT_BALANCE_CODE = "006";
//    public static final String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Balance";
//    public static final String ACCOUNT_DEBITED_SUCCESS = "007";
//    public static final String ACCOUNT_DEBITED_MESSAGE = "Account has been successfully debited";

    public static String generateAccountNumber(){
        //year + random 6 digits
        Year currentYear = Year.now();

        Random random = new Random();
        int randNumber = random.nextInt(1000000);

        String yearStr = String.valueOf(currentYear);
        String randNumberStr = String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append(yearStr).append(randNumberStr).toString();
    }




}
