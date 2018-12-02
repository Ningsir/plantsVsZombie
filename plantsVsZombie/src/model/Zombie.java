package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import config.file.Config;
import game_main.GameInformation;
import tool.Tool;

public abstract class Zombie extends JPanel{
	//x,y 为僵尸坐标 speed 移动速度  life 生命  attack 攻击力
	protected int attack;
	protected int x;
	protected int y;
	protected int speed;
	protected int life;
	protected int rows;
	protected int status;//0走 1吃 2死3炸死
	//protected Image image;
	protected Image imageMove;
	protected Image imageEat;
	protected Image imageDie1 = Toolkit.getDefaultToolkit().createImage("src/img/Zombie/ZombieDie.gif");
	protected Image imageDie2 = Toolkit.getDefaultToolkit().createImage("src/img/Zombie/BoomDie.gif");;
	
	/**
	 * 僵尸移动函数,修改僵尸横坐标
	 */
	public void move() {
		if(0 == status) {
			this.x -= this.speed;
		}
	}
	
	/**
	 * 判断僵尸是否可以吃植物
	 * @param plants 植物的集合
	 */
	public  void eat(AbstractPlants[][] plants) {
		if(3 != this.status) {
			if(Tool.lawnList[rows - 1] != null && this.x < 40) {
				Tool.lawnList[rows - 1].move();
				if(Tool.lawnList[rows - 1].getX() > 1100)
				Tool.lawnList[rows - 1] = null;
			}
			if(Tool.lawnList[rows - 1] != null && (this.x - Tool.lawnList[rows - 1].getX())<0) {
				this.life = -1;
			}
		 
		if(plants[rows - 1] != null) {
			AbstractPlants[] rowPlants = plants[rows - 1];
			if(this.life > 0) {
				for(int i = 0;i < 9;i++) {
					    if(rowPlants[i] != null && rowPlants[i].plantType == 7 &&  (this.x - rowPlants[i].getX()) < 80) {
					     Chomper c = (Chomper)rowPlants[i];
					        if(c.getStatus() != 2 && c.getStatus() != 3) {
					        	c.setStatus(1);
					            c.returnToAttackStatus();
					        }       
					    	this.status = 4;
					    	break;
					    }
						if(rowPlants[i] != null&&-10 >= this.x - rowPlants[i].getX() && -60 <= this.x - rowPlants[i].getX() && 0 < rowPlants[i].getLife()) {
							this.status = 1;
							rowPlants[i].setLife(rowPlants[i].getLife() - this.attack);
							break;
						}
						else {
							this.status = 0;
						}
				
					}
				}
			}
		}
			
	}
	
	/**
	 * 判断僵尸是否死了
	 * @return 返回僵尸的状态
	 */
	public  Boolean die() {
		if(-100 == this.life) {
			this.status = 4;
			return true;
		}
		if(0 >= this.life) {
			this.status = 2;
			return true;
		}
		if(3 == this.status) {
			return true;
		}
		return false;
	}
	
	/**
	 * 僵尸受到伤害生命减少
	 * @param damage 受到的伤害
	 */
	public void decLife(int damage) {
		this.life -= damage;	
	}
	public void setLife(int life) {
		this.life = life;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	/*
	 * 根据僵尸状态来画图
	 */
	public void drawByStatus(Graphics g,ImageObserver o) {
		if(0 == this.status) {
 
			g.drawImage(this.imageMove,this.x,this.y,o);
		}
		else if(1 == this.status) {
 
			g.drawImage(this.imageEat,this.x,this.y,o);
		}else if(2 == this.status){	
 
			g.drawImage(this.imageDie1,this.x,this.y,o);
		}else if(3 == this.status){
 
			g.drawImage(this.imageDie2,this.x,this.y,o);
		}
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	/**
	 * 
	 * @param zombies 僵尸集合
	 * @param plants 植物集合
	 */
	public void ZombieTimer(ArrayList<ArrayList<Zombie>> zombies,AbstractPlants[][] plants) {
		Zombie z = this;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int i = 0;
    		@Override
			public void run() {
    			if(status != 4) {
    				move();
    				eat(plants);
    			}
				if(die()&&!GameInformation.ifNewGame){
					i++;
				}
				if(i == 15) {
					zombies.get(rows - 1).remove(z);
					timer.cancel();
				}			
			}
    	};
    	timer.schedule(task,0,100);
	}
	
}
