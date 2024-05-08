package com.example.bankapplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "importer")
public class Importer extends BaseEntity{

    @Column
    private String name;

    @OneToMany(mappedBy="importer", fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
