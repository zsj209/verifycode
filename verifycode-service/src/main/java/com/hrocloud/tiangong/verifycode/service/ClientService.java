package com.hrocloud.tiangong.verifycode.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hrocloud.tiangong.verifycode.mapper.ClientMapper;
import com.hrocloud.tiangong.verifycode.model.Client;

@Service("clientService")
public class ClientService{

	@Resource
	private ClientMapper clientMapper;

	public Client findClientById(String clientId) {
		return clientMapper.getClientById(clientId);
	}

	public Client createClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteClient(Long clientId) {
		// TODO Auto-generated method stub
		return false;
	}

	public int updateVersion(String clientId, Integer version) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clientId", clientId);
		map.put("version", version);
		return clientMapper.update(map);
	}

}
