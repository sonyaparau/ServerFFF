package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.LocalDto;
import com.mobile.freeforfun.persistence.enums.ELocalType;

import java.util.List;

public interface LocalService {

	List<LocalDto> getAllLocals();
	List<LocalDto> filterLocalAfterType(ELocalType localType);
}
