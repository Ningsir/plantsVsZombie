package model;

import config.file.Config;

public class ShroomBullet extends PlantsItem {
     private int distance;//射程
	public ShroomBullet(int x, int y,int distance) {
		  super(x, y);
		  this.distance = distance;
		  this.setDamage(1);
		  this.setMove_speed(10);
		  this.setItemImage(Config.shroomBullet);
	}
   
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public void disappear() {

	}

}
