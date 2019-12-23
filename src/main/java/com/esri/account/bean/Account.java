package com.esri.account.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long accountNo;
    @JsonProperty("date")
    @Column(name = "date")
    @JsonFormat(pattern="dd MMM yy")
    private Date date;
    private String transactionDetails;

    @JsonProperty("valueDate")
    @Column(name = "valueDate")
    @JsonFormat(pattern="dd MMM yy")
    private Date valueDate;
    private String withdrawalAMT;
    private String depositAMT;
    private String balanceAMT;

    public Account() {
    }

    public Account(Long id, Long accountNo, java.util.Date date, String transactionDetails, java.util.Date valueDate, String withdrawalAMT, String depositAMT, String balanceAMT) {
        this.id = id;
        this.accountNo = accountNo;
        this.date = date;
        this.transactionDetails = transactionDetails;
        this.valueDate = valueDate;
        this.withdrawalAMT = withdrawalAMT;
        this.depositAMT = depositAMT;
        this.balanceAMT = balanceAMT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public java.util.Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(java.util.Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getWithdrawalAMT() {
        return withdrawalAMT;
    }

    public void setWithdrawalAMT(String withdrawalAMT) {
        this.withdrawalAMT = withdrawalAMT;
    }

    public String getDepositAMT() {
        return depositAMT;
    }

    public void setDepositAMT(String depositAMT) {
        this.depositAMT = depositAMT;
    }

    public String getBalanceAMT() {
        return balanceAMT;
    }

    public void setBalanceAMT(String balanceAMT) {
        this.balanceAMT = balanceAMT;
    }

    @Override
    public String toString() {
        return "account{" +
                "Id=" + id +
                ", AccountNo=" + accountNo +
                ", Date=" + date +
                ", TransactionDetails='" + transactionDetails + '\'' +
                ", ValueDate=" + valueDate +
                ", WithdrawalAMT=" + withdrawalAMT +
                ", DepositAMT=" + depositAMT +
                ", BalanceAMT=" + balanceAMT +
                '}';
    }
}
