package model;

import java.awt.Image;

public abstract class PlantsItem {
    private Image itemImage;
    private int x;
    private int y;
    private boolean is_disappear;//判断阳光或者子弹是否消失
    private int damage;//伤害
    private int move_speed;//子弹移动速度
   
	public PlantsItem(int x, int y) {
    	this.x = x;
    	this.y = y;
    	this.is_disappear = false;
    }
    public abstract void disappear();//子弹或者阳光消失
    
	public int getX() {
		return x;
	}
	 public int getDamage() {
			return damage;
		}
		public void setDamage(int damage) {
			this.damage = damage;
		}
		public int getMove_speed() {
			return move_speed;
		}
		public void setMove_speed(int move_speed) {
			this.move_speed = move_speed;
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

	public boolean getIs_disappear() {
		return is_disappear;
	}


	public void setIs_disappear(boolean is_disappear) {
		this.is_disappear = is_disappear;
	}

	public Image getItemImage() {
		return itemImage;
	}
	public void setItemImage(Image itemImage) {
		this.itemImage = itemImage;
	}
}
