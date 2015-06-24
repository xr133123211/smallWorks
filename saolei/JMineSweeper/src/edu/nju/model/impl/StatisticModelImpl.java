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
		if (level==null) this.level = "自定义";
		else this.level = level;
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
	public void showStatistics() {
		// TODO Auto-generated method stub
		System.out.println(statisticDao.getStatistic(level).getSum());
	}

}
