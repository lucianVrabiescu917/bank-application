package com.example.bankapplication.dto;


import com.example.bankapplication.enums.Status;
import com.example.bankapplication.enums.UnitEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto extends BaseDto{
    private ImporterDto importer;
    private BankDto bank;
    private ExporterDto exporter;
    private Status status;
    private Long bankAccountNumber;
    private String goodsType;
    private UnitEnum unit;
    private String address;
    private String phone;
    private String email;
    private String comment;
    private Float netPrice;
    private String exporterComment;
    private Date deliveryDate;
}
