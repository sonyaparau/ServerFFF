package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.LocalDto;
import com.mobile.freeforfun.business.mapper.LocalMapper;
import com.mobile.freeforfun.persistence.enums.ELocalType;
import com.mobile.freeforfun.persistence.repo.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocalServiceImpl implements LocalService {

	private LocalRepository localRepository;
	private LocalMapper localMapper;

	@Autowired
	public LocalServiceImpl(LocalRepository localRepository, LocalMapper localMapper) {
		this.localRepository = localRepository;
		this.localMapper = localMapper;
	}

	@Override public List<LocalDto> getAllLocals() {
		return localMapper.toDtoList(localRepository.findAll());
	}

	@Override
	public List<LocalDto> filterLocalAfterType(ELocalType localType) {
		return localMapper.toDtoList(localRepository.findAllByType(localType));
	}
}
