package edu.tku.db.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "SYS_bank")
@Entity

public class Bank {
    @Id
    @Column(name = "bank_id")
    private String bankId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_address")
    private String bankAddress;
    @Column(name = "bank_tel")
    private String bankTel;
    @Column(name = "bank_enabled")
    private boolean bankEnabled;

    @Transient
    private String action;
}
