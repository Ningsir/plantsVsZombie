package model;

import java.awt.Toolkit;

public class PeaShooterBullet extends PlantsItem{
        
	public PeaShooterBullet(int x, int y) {
		  super(x, y);
		  this.setDamage(3);
		  this.setMove_speed(10);
		  this.setItemImage(Toolkit.getDefaultToolkit().createImage("src/img/plantItems/PeaBullet.gif"));
	}
    
	/**
	 * 子弹与僵尸碰撞就会消失
	 */
	@Override
	public void disappear() {
	}  
}