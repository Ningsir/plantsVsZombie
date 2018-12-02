package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class ConeheadZombie extends Zombie{
	public ConeheadZombie(int x,int y,int rows) {
		// TODO Auto-generated constructor stub
		this.life = 30;
		this.speed = 4;
		this.x = x;
		this.y = y;
		this.attack = 1;
		this.rows = rows;
		this.imageMove = Toolkit.getDefaultToolkit().createImage("src/img/Zombie/ConeheadZombie.gif");
		this.imageEat = Toolkit.getDefaultToolkit().createImage("src/img/Zombie/ConeheadZombieAttack.gif");
	}
	
	public void drawByStatus(Graphics g,ImageObserver o) {
		if(0 == this.status) {
//			ImageIcon piIcon = new ImageIcon("src/img/Zombie/Zombie.gif");
//			this.image = piIcon.getImage();	
			//System.out.println(image);
			g.drawImage(this.imageMove,this.x,this.y,o);
		}
		else if(1 == this.status) {
//			ImageIcon piIcon = new ImageIcon("src/img/Zombie/ZombieAttack.gif");
//			this.image = piIcon.getImage();
			g.drawImage(this.imageEat,this.x,this.y,o);
		}else if(2 == this.status){	
//			ImageIcon piIcon = new ImageIcon("src/img/Zombie/ZombieDie.gif");
//			this.image = piIcon.getImage();
			g.drawImage(this.imageDie1,this.x,this.y,o);
		}else if(3 == this.status){
//			ImageIcon piIcon = new ImageIcon("src/img/Zombie/BoomDie.gif");
//			this.image = piIcon.getImage();
			g.drawImage(this.imageDie2,this.x,this.y,o);
		}
	}
}
