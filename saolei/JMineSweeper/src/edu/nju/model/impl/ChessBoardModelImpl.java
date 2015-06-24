package edu.nju.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nju.model.po.BlockPO;
import edu.nju.model.service.ChessBoardModelService;
import edu.nju.model.service.GameModelService;
import edu.nju.model.service.ParameterModelService;
import edu.nju.model.state.BlockState;
import edu.nju.model.state.GameResultState;
import edu.nju.model.state.GameState;
import edu.nju.model.vo.BlockVO;

public class ChessBoardModelImpl extends BaseModel implements ChessBoardModelService{
	
	private GameModelService gameModel;
	private ParameterModelService parameterModel;
	
	private BlockPO[][] blockMatrix;
	private int mineNum;

	
	public ChessBoardModelImpl(ParameterModelService parameterModel){
		this.parameterModel = parameterModel;
	}

	@Override
	public boolean initialize(int width, int height, int mineNum) {
		blockMatrix = new BlockPO[width][height];
		setBlock(mineNum);		
		this.parameterModel.setMineNum(mineNum);
		this.mineNum = mineNum;
		//this.printBlockMatrix();
		
		return false;
	}

	@Override
	public boolean excavate(int x, int y) {
		/********************简单示例挖开方法，待完善********************/
		if(blockMatrix == null)
			return false;		
		
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		BlockPO block = blockMatrix[x][y];
		
		BlockState state = block.getState();
		if(state == BlockState.FLAG){
			this.parameterModel.addMineNum();
		}		
		block.setState(BlockState.CLICK);
		blocks.add(block);
		

		GameState gameState = GameState.RUN;

		if(block.isMine()){
			gameState = GameState.OVER;
			this.gameModel.gameOver(GameResultState.FAIL);
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
			block.setState(BlockState.FLAG);
			this.parameterModel.minusMineNum();
		}
		else if(state == BlockState.FLAG){
			block.setState(BlockState.UNCLICK);
			this.parameterModel.addMineNum();
		}		
		
		
		blocks.add(block);
		GameState gameState = GameState.RUN;
		if (checkEnd()) {
			gameState = GameState.OVER;
			this.gameModel.gameOver(GameResultState.SUCCESS);
		}
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, gameState)));		
		return true;
	}

	private boolean checkEnd(){
		int width = blockMatrix.length;
		int height = blockMatrix[0].length;
		
		int index = 0;
		
		for(int i = 0 ; i<width; i++){
			for (int j = 0 ; j< height; j++){
				
				if(blockMatrix[i][j].isMine()&&BlockState.FLAG==blockMatrix[i][j].getState()) index++;
				
			}
		}
		
		System.out.println(index+" index");
		if(index==this.mineNum) return true;
		
		return false;
	}
	
	@Override
	public boolean quickExcavate(int x, int y) {
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
						if(BlockState.FLAG==blockMatrix[tempI][tempJ].getState()) flag++;
					}
				}
			}
			System.out.println(flag);
			if (flag==block.getMineNum())
			for(int tempI = x-1;tempI<=x+1;tempI++){
				int tempJ = y-1;
				for(;tempJ<=y+1;tempJ++){
					if((tempI>-1&&tempI<width)&&(tempJ>-1&&tempJ<height)){					
						if(BlockState.FLAG!=blockMatrix[tempI][tempJ].getState())excavate(tempI,tempJ);
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
