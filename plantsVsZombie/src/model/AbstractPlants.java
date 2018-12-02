package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import config.file.Config;
 
public abstract class AbstractPlants {
   private int life;
   private int energy;
   private int time;//产生阳光或者发射子弹的间隔时间
   private int x;
   private int y;
   protected int row;
   private int column;
   private Image plantImage;//植物图片
   private ArrayList<PlantsItem> plantsItemList;//阳光或者子弹
   protected ArrayList<Zombie> zombieList;
   protected int plantType;//植物类型，0为向日葵，1为豌豆射手,2为坚果墙，3为樱桃炸弹，4为双发豌豆射手，5为阳光菇，6为粉扑菇，7为食人花
   
public AbstractPlants(int x, int y, int row, int column) {
	   this.x = x;
	   this.y = y;
	   this.row = row;
	   this.column = column;
	   plantsItemList = new ArrayList<>();
	   zombieList = new ArrayList<>();
   }
public abstract void shoot();

public void plant(Graphics g, ImageObserver o) {
	if(plantImage != null && this.life > 0) {
		g.drawImage(this.plantImage, x, y, o);
	}
	  //改变地图方块状态
	 else {
		Config.map[row][column].setPlanted(false);
		AddPlant.plants[row][column] = null;
	}
}

public ArrayList<Zombie> getZombieList() {
	return zombieList;
}

public void setZombieList(ArrayList<Zombie> zombieList) {
	this.zombieList = zombieList;
}
public ArrayList<PlantsItem> getPlantsItemList() {
	return plantsItemList;
}

public void setPlantsItemList(ArrayList<PlantsItem> plantsItemList) {
	this.plantsItemList = plantsItemList;
}

public Image getPlantImage() {
	return plantImage;
}

public void setPlantImage(Image plantImage) {
	this.plantImage = plantImage;
}
public int getLife() {
	return life;
}
public void setLife(int life) {
	this.life = life;
}
public int getEnergy() {
	return energy;
}
public void setEnergy(int energy) {
	this.energy = energy;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}

public int getRow() {
	return this.row;
}
public int getColumn() {
	return this.column;
}
public int getPlantType() {
	return plantType;
}
public void setPlantType(int plantType) {
	this.plantType = plantType;
}
}