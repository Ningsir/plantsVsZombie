package tool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;


public class LawnCleaner {
	 private int x;
	 private int y;
	 private Image lawnImage;
	public LawnCleaner(int x, int y) {
		 this.x = x;
		 this.y = y;
		this.lawnImage = Toolkit.getDefaultToolkit().createImage("src/img/tool/LawnCleaner.png");
	}
	public void draw(Graphics g, ImageObserver o) {
		   g.drawImage(this.lawnImage ,x, y, o); 
	   }
/**
 * 移动小推车
 */
   public void move() {
	   Timer timer = new Timer();
		TimerTask task = new TimerTask() {
 		@Override
			public void run() {
 			 setX(x+20);
			 if(1000 < x) {
				 timer.cancel();
			 }
			}
 	};
 	timer.schedule(task,0,200);
   }
public void setX(int x) {
	   this.x = x;
   }
public int getX() {
	return x;
}
   
}
