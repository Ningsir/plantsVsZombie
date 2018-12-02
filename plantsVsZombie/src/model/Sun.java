package model;

 
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import game_main.GameInformation;


public class Sun extends PlantsItem{  
	private int keep_time;//阳光存在时长
	public Sun(int x, int y) {
		super(x, y);
		this.keep_time = 4000;
		this.setItemImage(Toolkit.getDefaultToolkit().createImage("src/img/plantItems/Sun.gif"));
	    this.disappear();
	}
	@Override
	public void disappear() {
		Sun sun = this;
		 Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				Boolean judgeDisappear = false;
	    		@Override
				public void run() {
	    			if(judgeDisappear|| GameInformation.ifNewGame) {
	    				sun.setIs_disappear(true);
		    			timer.cancel();
	    			}else {
	    				judgeDisappear = true;
	    			}
				}
	    	};
	    	timer.schedule(task,0,keep_time); 	 
	}
	
}