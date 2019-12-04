package com.mobile.freeforfun.business.restControllers;

import com.mobile.freeforfun.business.dto.LocalDto;
import com.mobile.freeforfun.business.service.LocalServiceImpl;
import com.mobile.freeforfun.business.utils.ApiEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mobile.freeforfun.persistence.enums.ELocalType;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RequestMapping(ApiEndpoints.APPLICATION_NAME)
@RestController
public class LocalController {

	private LocalServiceImpl localServiceImpl;

	@Autowired
	public LocalController(LocalServiceImpl localServiceImpl) {
		this.localServiceImpl = localServiceImpl;
	}

	@GetMapping(value = ApiEndpoints.GET_ALL_LOCALS,
			produces = APPLICATION_JSON_VALUE)
	public ResponseEntity getAllLocals(){
		List<LocalDto> locals = localServiceImpl.getAllLocals();
		return new ResponseEntity<>(locals, HttpStatus.OK);
	}

	@GetMapping(value = ApiEndpoints.FILTER_LOCALS_AFTER_TYPE,
			produces = APPLICATION_JSON_VALUE)
	public List<LocalDto> filterAllLocals(@PathVariable ELocalType type)
	{
		return localServiceImpl.filterLocalAfterType(type);
	}
}
