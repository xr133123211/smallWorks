/*
 *
 TODO to start to program. this program are wrote base on MVC structure
 */
package edu.nju.main;

 
import edu.nju.controller.impl.MenuControllerImpl;
import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.service.MenuControllerService;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.ClientParameterModelImpl;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.model.impl.ParameterModelImpl;
import edu.nju.model.impl.StatisticModelImpl;
import edu.nju.view.NetMainFrame;

public class JMineSweeper {
	public NetMainFrame ui;
	
	static MenuControllerService menuController = new MenuControllerImpl();
	
	public void run(){
		ui = new NetMainFrame();
		StatisticModelImpl statisticModel = new StatisticModelImpl();
 		ParameterModelImpl mineNumberModel = new ParameterModelImpl();
 		ClientParameterModelImpl mineNumberModel2 = new ClientParameterModelImpl();
 		ChessBoardModelImpl mineBoardModel = new ChessBoardModelImpl(mineNumberModel,mineNumberModel2);
		GameModelImpl gameModel = new GameModelImpl(statisticModel,mineBoardModel);		
 		
		gameModel.addObserver(ui);
		gameModel.setUI(ui);
 		mineNumberModel.addObserver(ui.getMineNumberLabel());
 		mineNumberModel2.addObserver(ui.getMineNumberLabel2());
 		mineBoardModel.addObserver(ui.getMineBoard());
 		
 		OperationQueue operationQueue = new OperationQueue(mineBoardModel, gameModel,statisticModel);
 		Thread operationThread = new Thread(operationQueue);
 		operationThread.start();
 	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 		menuController.startGame();
	}
	public static void main(String[] args) {
		
		new JMineSweeper().run();
	}
}

