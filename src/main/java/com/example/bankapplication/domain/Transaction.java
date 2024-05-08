package com.example.bankapplication.domain;

import com.example.bankapplication.enums.Status;
import com.example.bankapplication.enums.UnitEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="importer", nullable=false)
    private Importer importer;

    @ManyToOne
    @JoinColumn(name="bank", nullable=false)
    private Bank bank;

    @ManyToOne
    @JoinColumn(name="exporter", nullable=false)
    private Exporter exporter;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Long bankAccountNumber;

    @Column
    private String goodsType;

    @Column
    private UnitEnum unit;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column
    private Float netPrice;

    @Column(columnDefinition = "TEXT")
    private String exporterComment;

    @Column
    private Date deliveryDate;

    @Column
    private Boolean vatComputed = false;

}
