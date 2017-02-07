package com.hrocloud.tiangong.verifycode.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.tiangong.verifycode.model.Client;

public interface ClientMapper {
	Client getClientById(@Param("clientId") String id);
	
	Client insert(Client client);
	
	int update(Map<String, Object> map);
	
	int delete(@Param("clientId") String id);
	
	int active(@Param("clientId") String id);
}
