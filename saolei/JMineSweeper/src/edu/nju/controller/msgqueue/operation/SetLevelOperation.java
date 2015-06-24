package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.model.service.GameModelService;

public class SetLevelOperation extends MineOperation{

	String level;
	
	public SetLevelOperation(String s){
		this.level = s;
	}

	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		GameModelService game = OperationQueue.getGameModel();		
		game.setGameLevel(level);
		game.startGame();
	}

}
