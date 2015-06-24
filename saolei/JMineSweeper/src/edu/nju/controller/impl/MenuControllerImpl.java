package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.StartGameOperation;
import edu.nju.controller.service.MenuControllerService;

public class MenuControllerImpl implements MenuControllerService{

	@Override
	public boolean startGame() {
		// TODO Auto-generated method stub
		OperationQueue.addMineOperation(new StartGameOperation());
		return true;
	}

	@Override
	public int[] getRecord() {
		// TODO Auto-generated method stub
		String[] level = {"小","中","大",null};
		int result[] = new int[4];
		for (int i=0;i<4;i++)  result[i] = OperationQueue.getStatistic().showStatistics(level[i]);		
		return result;
	}

}
