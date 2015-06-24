package edu.nju.model.impl;

import edu.nju.model.data.StatisticData;
import edu.nju.model.po.StatisticPO;
import edu.nju.model.service.StatisticModelService;
import edu.nju.model.state.GameResultState;

public class StatisticModelImpl extends BaseModel implements StatisticModelService{
	
	private StatisticData statisticDao;
	private String level;
	
	public StatisticModelImpl(){
		this(null);
	}
	
	public StatisticModelImpl(String level){
		//初始化Dao
		this.level = level;
		statisticDao = new StatisticData();
	}

	
	
	@Override
	public void recordStatistic(GameResultState result, int time) {
		 
		 StatisticPO po = statisticDao.getStatistic(level);
		 if (po==null) po = new StatisticPO();
		 po.setSum(po.getSum()+1);
		 if(result==GameResultState.SUCCESS) po.setWins(po.getWins()+1);
		 po.setWinrate(po.getWins()/(po.getSum()+0.0));
		 statisticDao.saveStatistic(po);
		 //showStatistics();

		
	}

	@Override
	public int showStatistics(String level) {
		// TODO Auto-generated method stub
		return (int)(statisticDao.getStatistic(level).getWinrate()*100);
		//System.out.println(statisticDao.getStatistic(level).getSum());
	}

}
