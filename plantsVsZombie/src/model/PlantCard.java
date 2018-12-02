package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import config.file.Config;
import game_main.GameInformation;

public class PlantCard {
     int plantType;//植物类型
     Image plantCard;
     Image plant;
     //植物卡片坐标
     int x1;
     int y1;
     //可拖动植物坐标
     private int x2;
     private int y2;
     //植物卡片2的坐标
     private int x3;
     private int y3;
     private boolean isDragged;//是否被拖动
     int width;
     int height;
     //loaded判断是否开始装填,loadedTime为植物装填的时间
     private Boolean loaded;
     private int loadedTime;
     public PlantCard(int plantType, Image plantCard,Image plant, int x1, int y1, int loadedTime) {
    	 this.plantType = plantType;
    	 this.plantCard = plantCard;
    	 this.plant = plant;
    	 //设置植物卡片长度与宽度
    	 this.width = 60;
    	 this.height = 70;
    	 this.x1 = x1;
    	 this.y1 = y1;
    	 this.x2 = x1;
    	 this.y2 = y1;
    	 this.x3 = x1;
    	 this.y3 = y1;
    	 loaded = false;
    	 this.loadedTime = loadedTime;
     }
     /**
        * 绘制卡片
      * @param g
      * @param o
      */
     public void drawCard(Graphics g,  ImageObserver o) {
    	 g.drawImage(plantCard,x1, y1, this.width, this.height, o);
    	 //绘制拖动的植物卡片
    	//绘制植物装载倒计时数字
    	 if(this.loaded) {
    		 ArrayList<Integer> num = GameInformation.resolveNum(loadedTime);
    		 for(int i = 0; i < num.size(); i++) {
    				g.drawImage(Config.numImage[num.get(i)],this.x1+15*(i + 1),this.y1+20,o);
    			 }		 
    	 }
    	    if(isDragged)
    		 g.drawImage(plant,x2, y2, this.width, this.height, o); 
    	  
     }
      
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public int getX3() {
		return x3;
	}
	public void setX3(int x3) {
		this.x3 = x3;
	}
	public int getY3() {
		return y3;
	}
	public void setY3(int y3) {
		this.y3 = y3;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isDragged() {
		return isDragged;
	}
	public void setDragged(boolean isDragged) {
		this.isDragged = isDragged;
	}
	public int getPlantType() {
		return plantType;
	}
	public void setPlantType(int plantType) {
		this.plantType = plantType;
	}
	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}
	public Boolean getLoaded(){
		return this.loaded;
	}
	public void decLoadedTime() {
		Timer timer = new Timer();
		final int temp = loadedTime; 
		TimerTask task = new TimerTask() {
    		@Override
			public void run() {
					loadedTime--;
					if(-1 == loadedTime) {
						loaded = false;
						loadedTime = temp;
						timer.cancel();
					}
			}
    	};
    	timer.schedule(task,0,1000);
	}
	
	public int getLoadedTime() {
		return this.loadedTime;
	}
}
