package edu.nju.network.modelProxy;


import edu.nju.controller.msgqueue.operation.ClientRightClickOperation;
import edu.nju.controller.msgqueue.operation.DoubleClickOperation;
import edu.nju.controller.msgqueue.operation.LeftClickOperation;
import edu.nju.controller.msgqueue.operation.MineOperation;
import edu.nju.model.service.ChessBoardModelService;
import edu.nju.model.service.GameModelService;
import edu.nju.network.client.ClientService;
/**
 * GameModel的代理，在网络对战时替代Client端的相应Model。
 * @author 晨晖
 *
 */
public class ChessBoardModelProxy extends ModelProxy implements ChessBoardModelService{
	
	
	public ChessBoardModelProxy(ClientService client){
		this.net = client;
	}



	@Override
	public boolean initialize(int width, int height, int mineNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excavate(int x, int y,int player) {
		// TODO Auto-generated method stub			
		MineOperation op = new LeftClickOperation(x,y,1);	
		net.submitOperation(op);
		return true;
	}

	@Override
	public boolean mark(int x, int y) {
		// TODO Auto-generated method stub
		MineOperation op = new ClientRightClickOperation(x,y);
		net.submitOperation(op);
		
		return false;
	}

	@Override
	public boolean quickExcavate(int x, int y,int player) {
		// TODO Auto-generated method stub
		MineOperation op = new DoubleClickOperation(x,y,1);
		net.submitOperation(op);
		return false;
	}

	@Override
	public void setGameModel(GameModelService gameModel) {
		// TODO Auto-generated method stub
		
	}



}
