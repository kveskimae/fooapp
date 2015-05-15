package com.foo.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foo.app.dao.AppUserDao;
import com.foo.app.domain.AppUser;
import com.foo.app.dto.AppUserDTO;
import com.foo.app.util.DozerListMapper;

@RestController
public class AppUserController {

	@Autowired
	private AppUserDao appUserDao;
	
	@Autowired
	private Mapper dozerBeanMapper;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppUserDTO>> getAll() throws IOException {
		List<AppUser> domainObjects = appUserDao.findAll();
		List<AppUserDTO> dtos = new ArrayList<AppUserDTO>();
		dtos = DozerListMapper.mapListToList(domainObjects, AppUserDTO.class, dozerBeanMapper);
		return new ResponseEntity<List<AppUserDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUserDTO> saveOne(@RequestBody(required = true) final AppUserDTO dto) {
		AppUser domainEntity = dozerBeanMapper.map(dto, AppUser.class);
		domainEntity.setLastChangedTime(new Date());
		domainEntity = appUserDao.save(domainEntity);
		appUserDao.flush();
		dto.setId(domainEntity.getId());
		return new ResponseEntity<AppUserDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUserDTO> getOne(@PathVariable("userid") final int userid) throws IOException {
		AppUser domainObject = appUserDao.findOne(userid);
		if (domainObject ==null) {
			return new ResponseEntity<AppUserDTO>(HttpStatus.NOT_FOUND);
		}
		 AppUserDTO dto = dozerBeanMapper.map(domainObject, AppUserDTO.class);
		return new ResponseEntity<AppUserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{userid}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUserDTO> updateOne(@PathVariable("userid") final Integer userid, @RequestBody(required = true) final AppUserDTO paramDto) throws IOException {
		AppUser domainObject = appUserDao.findOne(userid); // TODO throw error if user not found
		if (domainObject == null) {
		    return new ResponseEntity<AppUserDTO>(HttpStatus.NOT_FOUND);
		}
		domainObject.setActive(paramDto.getActive());
		domainObject.setBirthTime(paramDto.getBirthTime());
		domainObject.setFirstName(paramDto.getFirstName());
		domainObject.setLastName(paramDto.getLastName());
		domainObject.setLastChangedTime(new Date());
		appUserDao.save(domainObject);
		appUserDao.flush();
		AppUserDTO retDto = dozerBeanMapper.map(domainObject, AppUserDTO.class);
	    return new ResponseEntity<AppUserDTO>(retDto, HttpStatus.NO_CONTENT);
	}
	
}
