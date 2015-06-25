package edu.nju.network.modelProxy;

import edu.nju.model.impl.UpdateMessage;
import edu.nju.model.service.ParameterModelService;
import edu.nju.network.client.ClientService;

public class ParameterModelProxy extends ModelProxy  implements ParameterModelService{
	
	private int maxMine;
	private int mineNum;
	public ParameterModelProxy(ClientService client){
		this.net = client;
	}

	@Override
	public boolean setMineNum(int num) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		mineNum = num;
		maxMine = num;
				
		super.updateChange(new UpdateMessage("mineNum", mineNum));
		return true;
	}

	@Override
	public boolean minusMineNum() {
		mineNum++;
		
		if(mineNum>maxMine){
			mineNum--;
			return false;
		}
		
		super.updateChange(new UpdateMessage("mineNum", mineNum));
		return true;
	}

	@Override
	public boolean addMineNum() {
		
		mineNum--;
		
		if(mineNum<0){
			mineNum++;
			return false;
		}
		
		super.updateChange(new UpdateMessage("mineNum", mineNum));
		return true;
	}
	

}
