package tool;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;

import config.file.Config;
import game_main.GameInformation;
import gameui.MusicPlayer;
import model.PlantCard;
import model.PlantsItem;
import model.Sun;

public class Tool {  
      private static Sun sun;
      public static Shovel shovel = new Shovel(500, 10);//铲子
          //小推车
	  public static LawnCleaner[] lawnList = {new LawnCleaner(40,80 + 0 * 95),new LawnCleaner(40,80 + 1 * 95),
			  new LawnCleaner(40,80 + 2 * 95),new LawnCleaner(40,80 + 3 * 95),new LawnCleaner(40,80 + 4 * 95)};
      public static ProgressBar progressBar = new ProgressBar(945);//进度条
      //选择槽中的植物卡片
      public static PlantCard[] plantCards = {
    		  new PlantCard(0, Config.sunFlowerCard,Config.sunFlower,410,125,5),
    		  new PlantCard(1, Config.peaShooterCard,Config.peaShooter,475,125,10),
    		  new PlantCard(2, Config.wallNutCard,Config.wallNut,540,125,25),
    		  new PlantCard(3, Config.cherryBombCard,Config.cherryBomb,605,125,25),
    		  new PlantCard(4, Config.doublePeaShooterCard,Config.doublePeaShooter,670,125,20),
    		  new PlantCard(5, Config.sunShroomCard,Config.sunShroom1,735,125,5),
    		  new PlantCard(6, Config.puffShroomCard,Config.puffShroom,410,200,10),
    		  new PlantCard(7, Config.chomperCard,Config.chomper,475,200,25)
      };
   public static void produceSun() {
	       Random rand = new Random();
	       int x = rand.nextInt(500);
	        x += 300;
	        int y = rand.nextInt(100);
	        y += 400;
	       sun = new Sun(x, y);//游戏开始就生成一个阳光，避免空指针异常
	   ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int x = rand.nextInt(500);
	        x += 300;
	        int y = rand.nextInt(100);
	        y += 400;
	       sun = new Sun(x, y);
		}  
	  };
	  new javax.swing.Timer(5000,taskPerformer).start();
   }
   
   public static void drawSun(Graphics g, ImageObserver o) {
	   if(sun instanceof Sun && !sun.getIs_disappear())
	   g.drawImage(sun.getItemImage(),sun.getX(),sun.getY(),o);
   }
   
   /**
    * 是否收集阳光
    * @return
    */
   public static boolean  isCollectSun(int x , int y) {  	 
   		if(x > sun.getX() && x < sun.getX() + 90 && y > sun.getY() && y < sun.getY() + 90) {				
   			GameInformation.energy += 25;//收集阳光后增加能量值
   			new MusicPlayer("music/suncollect.wav",true,1).musicTimer();
   			moveSun(sun);
   			return true;
   		} 	
   	return false;
   }

   /**
    * 收集阳光时阳光坐标移动
    * @return
    */
   public static void  moveSun(PlantsItem sun) {
	   Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				sun.setX((sun.getX() + 80)/2);
				sun.setY(sun.getY()/2);
				if(sun.getY() < 5) {
					sun.setIs_disappear(true);
					sun.setX(-100);
					sun.setY(-100);
					timer.cancel();
				}
			}
		};
		timer.schedule(task,0,100);
   }
}
