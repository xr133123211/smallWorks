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
		
		StatisticPO[] s = new StatisticPO[4]; 
		int l=3;
		if (level==null) l=3;
		else if(level.equals("中")) l = 1;
		else if(level.equals("大"))l=2;
		else if(level.equals("小"))l=0;
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			for(int i=0;i<4;i++) {
				
				s[i] = (StatisticPO)oi.readObject();
				if(s[i].getLevel()==l) return s[i];
			}
			oi.close();
			
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
		
		StatisticPO[] s = new StatisticPO[4]; 
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			for(int i=0;i<4;i++) {			
				Object o = oi.readObject();
				if (o==null) s[i]  = new StatisticPO();
				else s[i] = (StatisticPO) o;
				if(s[i].getLevel()==statistic.getLevel()) s[i] = statistic;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			for(int i=0;i<4;i++){
				System.out.println(s[i].getLevel());
				oo.writeObject(s[i]);	
			}
			oo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static void main(String args[]){
		/*
		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			for(int i=0;i<4;i++){
				StatisticPO po = new StatisticPO();
				po.setLevel(i);
				oo.writeObject(po);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		ObjectInputStream oi;
		try {
			oi = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			for(int i=0;i<4;i++) {
				Object o = oi.readObject();
				StatisticPO po = (StatisticPO)o; 
				System.out.println(po.getLevel()+" "+po.getWins());
				
			}
			oi.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}