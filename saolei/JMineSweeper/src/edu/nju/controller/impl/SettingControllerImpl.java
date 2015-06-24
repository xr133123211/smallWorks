package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.CustomLevelOperation;
import edu.nju.controller.msgqueue.operation.SetLevelOperation;
import edu.nju.controller.service.SettingControllerService;

public class SettingControllerImpl implements SettingControllerService{

	@Override
	public boolean setEasyGameLevel() {
		OperationQueue.addMineOperation(new SetLevelOperation("小"));
		return true;
		}

	@Override
	public boolean setHardGameLevel() {
		OperationQueue.addMineOperation(new SetLevelOperation("中"));
		return true;
	}

	@Override
	public boolean setHellGameLevel() {
		OperationQueue.addMineOperation(new SetLevelOperation("大"));
		return true;
	}

	@Override
	public boolean setCustomizedGameLevel(int height, int width, int nums) {
		OperationQueue.addMineOperation(new CustomLevelOperation(height,width,nums));
		return true;
	}

}
