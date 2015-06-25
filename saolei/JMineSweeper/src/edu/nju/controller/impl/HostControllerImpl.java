package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.service.HostControllerService;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.ClientParameterModelImpl;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.model.impl.ParameterModelImpl;
import edu.nju.network.host.HostInHandlerImpl;
import edu.nju.network.host.HostServiceImpl;
import edu.nju.view.NetMainFrame;

public class HostControllerImpl implements HostControllerService{

	@Override
	public boolean serviceetupHost() {
		// TODO Auto-generated method stub
		GameModelImpl game = (GameModelImpl) OperationQueue.getGameModel();
		
		NetMainFrame ui = game.getUI();
		ui.removeTime();			
		game.addObserver(ui);
		game.addObserver(ui.getMineNumberLabel());
		game.addObserver(ui.getMineNumberLabel2());
		game.addObserver(ui.getMineBoard());
		HostServiceImpl host = new HostServiceImpl();
		HostInHandlerImpl hostH = new HostInHandlerImpl();
		ChessBoardModelImpl c = (ChessBoardModelImpl) OperationQueue.getChessBoardModel();
		ParameterModelImpl p = (ParameterModelImpl)c.getParameterModelService(); 
		ClientParameterModelImpl p2 = (ClientParameterModelImpl)c.getParameterModelService2();
		
		game.addObserver(host);
		c.addObserver(host);
		p.addObserver(host);
		p2.addObserver(host);
		
		
		if(host.init(hostH)){
			System.out.println("Connecting!!!");
			game.startGame();
		}
	
		return true;
	}
	
	
}
