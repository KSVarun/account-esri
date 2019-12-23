package com.esri.account.exceptions;

public class AccountExceptionResponse {
    private String AccountNo;

    public AccountExceptionResponse(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }
}
