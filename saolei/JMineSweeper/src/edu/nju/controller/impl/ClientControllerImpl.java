package edu.nju.controller.impl;

import edu.nju.controller.service.ClientControllerService;
import edu.nju.network.client.ClientInHandlerImpl;
import edu.nju.network.client.ClientServiceImpl;
import edu.nju.network.modelProxy.GameModelProxy;

public class ClientControllerImpl implements ClientControllerService{

	@Override
	public boolean setupClient(String ip){
		// TODO Auto-generated method stub
		ClientServiceImpl client = new ClientServiceImpl();
		ClientInHandlerImpl clientH = new ClientInHandlerImpl();
		
		GameModelProxy gameProxy = new GameModelProxy(client);
		clientH.addObserver(gameProxy);
		
		client.init("127.0.0.1", clientH);
		return true;
	}

	
}
