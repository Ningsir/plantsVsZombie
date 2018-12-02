package tool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class ProgressBar {
     private int x;
	  Image progressBar1;
	  Image progressBar2;
	  Image progressBar3;
	  Image progressBar4;
      
     public ProgressBar(int x) {
    	  
    	 this.x = x;
      ImageIcon icon3 = new ImageIcon("src/img/progressBar/FlagMeterEmpty.png");
      progressBar1 = icon3.getImage();
   	  ImageIcon icon4 = new ImageIcon("src/img/progressBar/FlagMeterLevelProgress.png");
   	  progressBar2 = icon4.getImage();
   	  ImageIcon icon5 = new ImageIcon("src/img/progressBar/FlagMeterParts2.png");
   	  progressBar3 = icon5.getImage();
   	  ImageIcon icon6 = new ImageIcon("src/img/progressBar/FlagMeterParts1.png");
   	  progressBar4 = icon6.getImage();
     }
     public void draw(Graphics g, ImageObserver o) {
    	 g.drawImage(progressBar1,800, 530, o);
 	     g.drawImage(progressBar2,800, 550, o);
 	     g.drawImage(progressBar3,805, 520, o);
 	     g.drawImage(progressBar4,this.x, 525, o);
	   }
     
     public void modProgressX() {
    	 Timer timer = new Timer();
			TimerTask task = new TimerTask() {
	    		@Override
				public void run() {
	    			if(x > 805)
		    			x -= 4;
		    			else {
		    				timer.cancel();
		    			}
				}
	    	};
	    	timer.schedule(task,0,5000);
	    }
	public void setX(int i) {
		 this.x = i;	
	}
}
