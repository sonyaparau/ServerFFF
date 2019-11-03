package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.mapper.RoleMapper;
import com.mobile.freeforfun.persistence.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{
    private RoleRepository roleRepository;
   // private RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        //this.roleMapper = roleMapper;
    }
}
