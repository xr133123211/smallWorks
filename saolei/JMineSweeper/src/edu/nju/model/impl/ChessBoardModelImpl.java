package edu.nju.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nju.model.po.BlockPO;
import edu.nju.model.service.ChessBoardModelService;
import edu.nju.model.service.ClientParameterModelService;
import edu.nju.model.service.GameModelService;
import edu.nju.model.service.ParameterModelService;
import edu.nju.model.state.BlockState;
import edu.nju.model.state.GameResultState;
import edu.nju.model.state.GameState;
import edu.nju.model.vo.BlockVO;

public class ChessBoardModelImpl extends BaseModel implements ChessBoardModelService{
	
	private GameModelService gameModel;
	private ParameterModelService parameterModel;
	private ClientParameterModelService parameterModel2;
	
	private BlockPO[][] blockMatrix;
	private int mineNum;

	
	public ParameterModelService getParameterModelService(){
		return parameterModel;
	}
	public ClientParameterModelService getParameterModelService2(){
		return parameterModel2;
	}
	
	public ChessBoardModelImpl(ParameterModelService parameterModel){
		this.parameterModel = parameterModel;
	}
	public ChessBoardModelImpl(ParameterModelService parameterModel,ClientParameterModelService parameterModel2){
		this.parameterModel = parameterModel;
		this.parameterModel2 = parameterModel2;
	}

	@Override
	public boolean initialize(int width, int height, int mineNum) {
		blockMatrix = new BlockPO[width][height];
		setBlock(mineNum);		
		this.parameterModel.setMineNum(mineNum);
		if(this.parameterModel2!=null) this.parameterModel2.setMineNum(mineNum);
		this.mineNum = mineNum;
		//this.printBlockMatrix();
		
		return false;
	}

	@Override
	public boolean excavate(int x, int y,int player) {
		/********************简单示例挖开方法，待完善********************/
		if(blockMatrix == null)
			return false;		
		
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		BlockPO block = blockMatrix[x][y];
		
		BlockState state = block.getState();
		if(state == BlockState.FLAG){
			this.parameterModel.addMineNum();
		}else if(state == BlockState.FLAGClient){
			this.parameterModel2.addMineNum();
		}		
		block.setState(BlockState.CLICK);
		blocks.add(block);
		

		GameState gameState = GameState.RUN;

		if(block.isMine()){
			gameState = GameState.OVER;
			if(player==0)
			this.gameModel.gameOver(GameResultState.FAIL);
			else this.gameModel.gameOver(GameResultState.FAIL2);
		}
				
		
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, gameState)));			
		return true;
	}
	
	@Override
	public boolean mark(int x, int y) {
		if(blockMatrix == null)
			return false;
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		
		BlockPO block = blockMatrix[x][y];
		
		 
		BlockState state = block.getState();
		
		
		if(state == BlockState.UNCLICK){
			boolean r = this.parameterModel.minusMineNum();
			if(!r) return false;
			block.setState(BlockState.FLAG);

		}
		else if(state == BlockState.FLAG){
			boolean r =this.parameterModel.addMineNum(); 
			if(!r) return false;
			block.setState(BlockState.UNCLICK);
			

		}		
		
		
		blocks.add(block);
		GameState gameState = GameState.RUN;
		

		if(block.isMine()){
			gameState = GameState.OVER;			
			this.gameModel.gameOver(GameResultState.FAIL);
		}
		if (checkEnd()) {
			gameState = GameState.OVER;
			//this.gameModel.gameOver(GameResultState.SUCCESS);
		}
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, gameState)));		
		return true;
	}
	
	public boolean markClient(int x, int y) {
		if(blockMatrix == null)
			return false;
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		
		BlockPO block = blockMatrix[x][y];
		 
		BlockState state = block.getState();
		if(state == BlockState.UNCLICK){
			boolean r = this.parameterModel2.minusMineNum();
			if(!r) return false;
			block.setState(BlockState.FLAGClient);
			
		}
		else if(state == BlockState.FLAGClient){
			boolean r =this.parameterModel2.addMineNum(); 
			if(!r) return false;
			block.setState(BlockState.UNCLICK);			
		}		
		

		blocks.add(block);
		GameState gameState = GameState.RUN;
		if(block.isMine()){
			gameState = GameState.OVER;			
			this.gameModel.gameOver(GameResultState.FAIL2);
		}
		if (checkEnd()) gameState = GameState.OVER;
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, gameState)));		
		return true;
	}

	private boolean checkEnd(){
		int width = blockMatrix.length;
		int height = blockMatrix[0].length;
		
		int index = 0;
		int index2 = 0;
		
		for(int i = 0 ; i<width; i++){
			for (int j = 0 ; j< height; j++){
				
				if(blockMatrix[i][j].isMine()&&BlockState.FLAG==blockMatrix[i][j].getState()) index++;
				if(blockMatrix[i][j].isMine()&&BlockState.FLAGClient==blockMatrix[i][j].getState()) index2++;
				
			}
		}
		
		System.out.println(index+" index");
		if(index+index2==this.mineNum) {
			if (index<index2) this.gameModel.gameOver(GameResultState.Sucess2);
			else this.gameModel.gameOver(GameResultState.SUCCESS);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean quickExcavate(int x, int y,int player) {
		if(blockMatrix == null)
			return false;
		
		BlockPO block = blockMatrix[x][y];
		BlockState state = block.getState();
		if(state == BlockState.CLICK){
			int flag = 0;
			int width = blockMatrix.length;
			int height = blockMatrix[0].length;
			for(int tempI = x-1;tempI<=x+1;tempI++){
				int tempJ = y-1;
				for(;tempJ<=y+1;tempJ++){
					if((tempI>-1&&tempI<width)&&(tempJ>-1&&tempJ<height)){					
						if(BlockState.FLAG==blockMatrix[tempI][tempJ].getState()||BlockState.FLAGClient==blockMatrix[tempI][tempJ].getState())
							flag++;
					}
				}
			}
			System.out.println(flag);
			if (flag==block.getMineNum())
			for(int tempI = x-1;tempI<=x+1;tempI++){
				int tempJ = y-1;
				for(;tempJ<=y+1;tempJ++){
					if((tempI>-1&&tempI<width)&&(tempJ>-1&&tempJ<height)){					
						if(BlockState.FLAG!=blockMatrix[tempI][tempJ].getState())excavate(tempI,tempJ,player);
					}
				}
			}
		}
		
		
		return true;
	}

	/**
	 * 示例方法，可选择是否保留该形式
	 * 
	 * 初始化BlockMatrix中的Block，并随机设置mineNum颗雷
	 * 同时可以为每个Block设定附近的雷数
	 * @param mineNum
	 * @return
	 */
	private boolean setBlock(int mineNum){
		int width = blockMatrix.length;
		int height = blockMatrix[0].length;
		
		int index = 0;
		
		//初始化及布雷
		for(int i = 0 ; i<width; i++){
			for (int j = 0 ; j< height; j++){
				blockMatrix[i][j] = new BlockPO(i,j);
				//放置雷，并设定block附近雷数，现有放置方法为固定方法，请添加随机实现
				index ++;
				if(index == 2){
					if(mineNum>0){
						if(i>3&&j>3){
							blockMatrix[i-1][j-1].setMine(true);
						
							addMineNum(i-1,j-1);
							mineNum--;
						}
					}
					index = 0;
				}
				
			}
		}
		
		
		return false;
	}
	
	
	/**
	 * 示例方法，可选择是否保留该形式
	 * 
	 * 将(i,j)位置附近的Block雷数加1
	 * @param i
	 * @param j
	 */
	private void addMineNum(int i, int j){
		int width = blockMatrix.length;
		int height = blockMatrix[0].length;
		
		int tempI = i-1;		
		
		for(;tempI<=i+1;tempI++){
			int tempJ = j-1;
			for(;tempJ<=j+1;tempJ++){
				if((tempI>-1&&tempI<width)&&(tempJ>-1&&tempJ<height)){
//					System.out.println(i+";"+j+":"+tempI+";"+tempJ+":");
					blockMatrix[tempI][tempJ].addMine();
				}
			}
		}
		
	}
	
	/**
	 * 将逻辑对象转化为显示对象
	 * @param blocks
	 * @param gameState
	 * @return
	 */
	private List<BlockVO> getDisplayList(List<BlockPO> blocks, GameState gameState){
		List<BlockVO> result = new ArrayList<BlockVO>();
		for(BlockPO block : blocks){
			if(block != null){
				BlockVO displayBlock = block.getDisplayBlock(gameState);
				if(displayBlock.getState() != null)
				result.add(displayBlock);
			}
		}
		return result;
	}

	@Override
	public void setGameModel(GameModelService gameModel) {
		// TODO Auto-generated method stub
		this.gameModel = gameModel;
	}
	
	/**
	private void printBlockMatrix(){
		for(BlockPO[] blocks : this.blockMatrix){
			for(BlockPO b :blocks){
				String p = b.getMineNum()+"";
				if(b.isMine())
					p="*";
				System.out.print(p);
			}
			System.out.println();
		}
	}
	*/
}
