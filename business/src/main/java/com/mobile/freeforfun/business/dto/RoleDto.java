package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.enums.ERoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {

    private Long id;
    private ERoleType name;
}
