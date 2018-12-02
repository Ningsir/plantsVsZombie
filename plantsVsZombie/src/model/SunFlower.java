package model;
//能量值增加11.23
//收集阳光判断范围加大11.23
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

import javax.swing.Timer;

import config.file.Config;
import game_main.GameInformation;
import gameui.MusicPlayer;

public class SunFlower extends AbstractPlants {
   private int sunshine_per_min;
   private int sun;
public SunFlower(int x, int y, int row, int column, int plantType) {
	   super(x,y, row, column);
	   this.setPlantType(plantType);
	   this.setEnergy(50);
	   this.setLife(40);
	   if(GameInformation.stage == 2) {
		   this.setTime(18000);
	   } else {
		   this.setTime(12000);
	   }
	   this.sunshine_per_min = 5;
	   if(0 == plantType) {
		   this.setPlantImage(Toolkit.getDefaultToolkit().createImage("src/img/plants/SunFlower.gif"));         
	       this.sun = 50;
	       this.produceSun();
	   }
	   if(5 == plantType) {
		   if(GameInformation.stage == 2) {
			  this.setPlantImage(Config.sunShroom1);
		      this.sun = 25;
		      grow(); 
		      this.produceSun(); 
		         } 
		   else {
		     this.setPlantImage(Config.sunShroomSleep);
	   }  	   
	} 
}

@Override
public void plant(Graphics g, ImageObserver o) {
	super.plant(g, o);
    if(this.getLife() > 0) {
    	this.drawSun(g, o);
    }	
}
public void grow() {
	java.util.Timer timer = new java.util.Timer();
	TimerTask task = new TimerTask() {
		 int count = 0;
		public void run() {
			 if(count == 2) {
				 setPlantImage(Config.sunShroom2);
				 sun = 50;
				 cancel();
			 }
			 count++;	 
		}
	};
	timer.schedule(task,0,10000);
}
/**
 * 画阳光
 * @param g
 * @param o
 */
private void drawSun(Graphics g, ImageObserver o) {
	for(PlantsItem p :  this.getPlantsItemList()) {
	    Image sun =  p.getItemImage();
	    if(sun != null && p.getIs_disappear() == false) {
	    	g.drawImage(sun, p.getX(),p.getY(), o);
	    }	     
  } 
}
/**
 * 产生阳光
 */
public void produceSun() {
	ArrayList<PlantsItem> plantsItemList = this.getPlantsItemList();
	int x = this.getX();
	int y = this.getY() + 20;
	SunFlower sunFlower = this;
	ActionListener task = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(sunFlower.getLife() > 0) {
				Sun sun = new Sun(x, y);
				plantsItemList.add(sun);//生成阳光
			}	
			
			 Iterator<PlantsItem> ite = plantsItemList.iterator();
			 while(ite.hasNext()) {
				 if(ite.next().getIs_disappear()) {
					 ite.remove();//删除消失的阳光
				 }
			 }
		}	  
	  };
	  new Timer(this.getTime(),task).start();	 
}

/**
 * 是否收集阳光
 * @return
 */
public boolean isCollectSun(int x , int y) {
	for(PlantsItem sun : this.getPlantsItemList()) {
		if(x > sun.getX() - 10 && x < sun.getX() + 90 && y > sun.getY() - 10 && y < sun.getY() + 90) {				
			GameInformation.energy += this.sun;//收集阳光后增加能量值
			new MusicPlayer("music/suncollect.wav",true,1).musicTimer();
			this.moveSun(sun);
			return true;
		}
	}
	return false;
}

/**
 * 收集阳光时阳光坐标移动
 * @return
 */
public void moveSun(PlantsItem sun) {
	ActionListener task = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			sun.setX((sun.getX() + 80)/2);
			sun.setY(sun.getY()/2);
			if(sun.getY() < 5) {
				sun.setIs_disappear(true);
				//避免在阳光消失后点击相应位置还会增加能量值
				sun.setX(-100);
				sun.setY(-100);
			}
		}
	};
	new Timer(100,task).start();
}
public int getSunshine_per_min() {
	return sunshine_per_min;
}

public void setSunshine_per_min(int sunshine_per_min) {
	this.sunshine_per_min = sunshine_per_min;
  }

@Override
public void shoot() {
	 
}	 
}