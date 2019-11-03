package com.mobile.freeforfun.persistence.model;

import com.mobile.freeforfun.persistence.enums.ERoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role{

    private static final String ID_ROLE_COLUMN = "id_roles";
    private static final String NAME_COLUMN = "name";

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name=ID_ROLE_COLUMN)
    @Id
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name=NAME_COLUMN)
    private ERoleType name;
}