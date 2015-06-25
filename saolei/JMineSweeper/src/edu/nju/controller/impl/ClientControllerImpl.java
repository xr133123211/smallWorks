package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.service.ClientControllerService;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.network.client.ClientInHandlerImpl;
import edu.nju.network.client.ClientServiceImpl;
import edu.nju.network.modelProxy.ChessBoardModelProxy;
import edu.nju.network.modelProxy.ClientParameterModelProxy;
import edu.nju.network.modelProxy.GameModelProxy;
import edu.nju.network.modelProxy.ParameterModelProxy;
import edu.nju.view.NetMainFrame;

public class ClientControllerImpl implements ClientControllerService{

	@Override
	public boolean setupClient(String ip){
		// TODO Auto-generated method stub
		ClientServiceImpl client = new ClientServiceImpl();
		ClientInHandlerImpl clientH = new ClientInHandlerImpl();

		
		GameModelProxy gameProxy = new GameModelProxy(client);
		ChessBoardModelProxy chessProxy = new ChessBoardModelProxy(client);
		ParameterModelProxy parameterProxy = new ParameterModelProxy(client);
		ClientParameterModelProxy clientParameterProxy = new ClientParameterModelProxy(client);
		
		clientH.addObserver(gameProxy);
		clientH.addObserver(chessProxy);
		clientH.addObserver(parameterProxy);
		clientH.addObserver(clientParameterProxy);	
		
 	    GameModelImpl impl = (GameModelImpl) OperationQueue.getGameModel();
 	    ChessBoardModelImpl chess = (ChessBoardModelImpl) OperationQueue.getChessBoardModel();
 	    NetMainFrame ui = impl.getUI();
		ui.removeTime();
		client.init("127.0.0.1", clientH);
		OperationQueue q = new OperationQueue(chessProxy,gameProxy,OperationQueue.getStatistic());
		Thread operationThread = new Thread(q);
 		operationThread.start();
 	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		gameProxy.addObserver(ui);
		parameterProxy.addObserver(ui.getMineNumberLabel());
		clientParameterProxy.addObserver(ui.getMineNumberLabel2());
//		chess.getMine2().addObserver(ui.getMineNumberLabel2());
//		gameProxy.addObserver(ui.getMineBoard());
		
		//chessProxy.addObserver(ui);
		chessProxy.addObserver(ui.getMineBoard());

		return true;
	}

	
}
