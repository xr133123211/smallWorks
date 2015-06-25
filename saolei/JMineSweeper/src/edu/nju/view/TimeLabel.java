package edu.nju.view;

import javax.swing.JLabel;

public class TimeLabel extends JLabel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int time = 0;

	
	
	public void restart(){
		this.time = 0;
		this.setText(""+time);
		repaint();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			try {
				Thread.sleep(1000);
				time++;
				this.setText(""+time);
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
