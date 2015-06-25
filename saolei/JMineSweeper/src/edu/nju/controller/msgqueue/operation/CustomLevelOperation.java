package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.model.service.GameModelService;

public class CustomLevelOperation extends MineOperation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int height,width,mineNum;
	
	public CustomLevelOperation(int w,int h,int m){
		height = h;
		width  = w;
		mineNum = m;
	}

	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		GameModelService game = OperationQueue.getGameModel();		
		game.setGameLevel(null);
		game.setGameSize(width, height, mineNum);
		game.startGame();
	}

}
