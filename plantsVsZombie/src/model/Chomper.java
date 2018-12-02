package model;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import config.file.Config;

public class Chomper extends AbstractPlants {
	  private int status;//0为正常状态，1攻击状态，2正在吃状态
	public Chomper(int x, int y, int row, int column) {
		super(x, y, row, column);
		this.status = 0;
		this.setPlantType(7);
		this.setEnergy(150);
		this.setLife(100);
		this.setPlantImage(Config.chomper);
	}
	@Override
	public void plant(Graphics g,  ImageObserver o) {
		if(status == 0) {
			g.drawImage(Config.chomper,getX(),getY(),90,80,o);
		}
		if(status == 1) {
			g.drawImage(Config.chomperAttack,getX(),getY(),90,80,o);
		}
		if(status == 2) {
			g.drawImage(Config.chomperDigest,getX(),getY(),90,80,o);
		}
	}
	public void returnToAttackStatus() {
		   Timer timer = new Timer();
		TimerTask task = new TimerTask() {
    		int count =  15;
			@Override
			public void run() {
				if(14 == count) {
					setStatus(2);
				}				
			 if(count == 0) {
				 setStatus(0);
				 timer.cancel();
			 }	
			  count --;
    		}
    	};
    	timer.schedule(task,0,1000);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public void shoot() {
		 
	}
}
