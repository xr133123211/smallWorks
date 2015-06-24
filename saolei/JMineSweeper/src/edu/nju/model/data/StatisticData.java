package edu.nju.model.data;

import java.io.*;

import edu.nju.model.po.StatisticPO;

/**
 * 负责进行统计数据获取和记录操作
 * @author Wangy
 *
 */
public class StatisticData {
	
	public StatisticPO getStatistic(String level){
		
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			return (StatisticPO)oi.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean saveStatistic(StatisticPO statistic){
		
		try {
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			oo.writeObject(statistic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}