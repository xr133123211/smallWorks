package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.model.service.ChessBoardModelService;

public class DoubleClickOperation extends MineOperation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int player;
	public DoubleClickOperation(int x ,int y,int player){
		this.x = x;
		this.y = y;
		this.player = player;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChessBoardModelService chess = OperationQueue.getChessBoardModel();
		chess.quickExcavate(x, y,player);
	}

}
