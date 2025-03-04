package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String uuid;
    private String referralNumber;
    private Double amount;
    private String currency;
    private String terminalId;
}
