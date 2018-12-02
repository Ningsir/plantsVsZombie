package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import gameui.MusicPlayer;

public class PeaShooter extends AbstractPlants{
       
	public PeaShooter(int x, int y, int row, int column, int plantType) {
		super(x, y, row, column);
		this.setEnergy(100);
		this.setPlantType(plantType);
		this.setLife(50);
		this.setTime(3000);	
		//单发射手
		if(plantType == 1) {
			this.setPlantImage(Toolkit.getDefaultToolkit().createImage("src/img/plants/PeaShooter.gif"));	
		}
		//双发射手
		if(plantType == 4) {
			this.setPlantImage(Toolkit.getDefaultToolkit().createImage("src/img/plants/doublePeaShooter.gif"));	
		}
		if(this.getLife() > 0) {
			this.moveBullet();
		}
		
	}
	@Override
	public void plant(Graphics g,  ImageObserver o) {
		super.plant(g, o);
		if(this.getLife() > 0 ) {
			this.drawBullet(g, o);
		}	
	}
	/**
	 * 子弹移动
	 */
    public void moveBullet() {
    	int delay = 100;  
    	ArrayList<PlantsItem> plantsItemList = this.getPlantsItemList();
    	ArrayList<Zombie> zombieList = AddPlant.zombies.get(this.getRow());
		  ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterator<PlantsItem> ite =  plantsItemList.iterator();
				while(ite.hasNext()) {
					PlantsItem p = (PlantsItem) ite.next();
				     int x = p.getX();
				     p.setX(x + p.getMove_speed());//改变x坐标
				     if(800 < x) {
				    	 ite.remove();//移除子弹
				     }
				     for(Zombie z : zombieList) {
				    	 if(z.getX() - p.getX() <= -20 && z.getRows() - 1 == row && 0 < getLife()&& x <= 800) {
				    		 new MusicPlayer("music/jizhong.wav",true,1).musicTimer();
				    		 ite.remove();
				    		 z.decLife(p.getDamage());
				    		 break;
				    	 }
				     } 
				 }	
			}
		  };
		  new Timer(delay, taskPerformer).start();
    }
	/**
	 * 每隔一段时间产生子弹
	 */
	public void shoot() {
			ArrayList<PlantsItem> plantsItemList = this.getPlantsItemList();
			ArrayList<Zombie> zombieList = AddPlant.zombies.get(this.getRow());
			PeaShooter p = this;
			int x = this.getX() + 30;
			int y = this.getY();
			ActionListener task = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(p.getLife() > 0) {
					boolean isProduce = false;//是否产生子弹
					for(Zombie z : zombieList) {
						if(z.rows - 1 == p.row && z.getX() <= 800 && -20 <= z.getX() - p.getX()) {
							isProduce = true;
							break;
						}	
					}
				if(isProduce && plantType == 1) {
						  //添加子弹
						plantsItemList.add(new PeaShooterBullet(x, y));
						  }
				if(isProduce && plantType == 4) {
				plantsItemList.add(new PeaShooterBullet(x + 30, y)); //添加子弹
				plantsItemList.add(new PeaShooterBullet(x, y));
				  }
				 }
				}
			  };
			  new Timer(this.getTime(),task).start();	  
	}	
	/**
	 * 在种植植物时并且画子弹
	 * @param g
	 * @param o
	 */
	private void drawBullet(Graphics g, ImageObserver o) {
		for(PlantsItem p :  this.getPlantsItemList()) {
		    Image peaShooterBullet =  p.getItemImage();
		    //子弹出界
		    if(p.getX() > 800) {
		    	p.setIs_disappear(true);
		    }
		    if(peaShooterBullet != null && !p.getIs_disappear()) {
		    	g.drawImage(peaShooterBullet, p.getX(),p.getY(), o);
		    }	     
	  } 
	}
}
