package model;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import config.file.Config;
import game_main.GameInformation;
import gameui.MusicPlayer;

public class CherryBomb extends AbstractPlants {

	public CherryBomb(int x, int y, int row, int column) {
		super(x, y, row, column);
		this.setEnergy(150);
		this.setPlantType(3);
		this.setLife(100);
		this.setPlantImage(Config.cherryBomb);
		boom();
	}
	public void plant(Graphics g,  ImageObserver o) {
		super.plant(g, o);
	}
	/**
	 * 樱桃炸弹爆炸
	 */
	public void boom() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			 int count = 0;
    		@Override
			public void run() {
    			   if(1 == count) {
				     setPlantImage(Toolkit.getDefaultToolkit().createImage("src/img/plants/Boom.gif"));
				     new MusicPlayer("music/cherrybomb.wav",true,1).musicTimer();
				     for(ArrayList<Zombie> zList : AddPlant.zombies) {
  					   for(Zombie z : zList) {
  						   if(Math.abs(z.getRows() - getRow() - 1) <= 1 && Math.abs(GameInformation.getColumnByX(z.x) - getColumn()) <= 1) {
  							   z.setStatus(3);
  						   }
  					   }
  				   }
    			}
    			   if(2 == count) { 	   
    				   setLife(-1);
    				   Config.map[getRow()][getColumn()].setPlanted(false);
    				   AddPlant.plants[getRow()][getColumn()] = null;
    				   timer.cancel();
    			   }
					count++;
			}
    	};
    	timer.schedule(task,0,500);
	}
	@Override
	public void shoot() {
		 
	}

}
