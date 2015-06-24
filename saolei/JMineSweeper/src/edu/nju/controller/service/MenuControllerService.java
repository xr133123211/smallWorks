package edu.nju.controller.service;

public interface MenuControllerService {
	/**
	 * 开始游戏
	 * @return
	 */
	public boolean startGame();
	
	/**
	 * 查看记录
	 * @return
	 */
	public int[] getRecord();

}
