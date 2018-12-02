package gameui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import config.file.Config;
import game_main.GameInformation;
import game_main.GameStart;
import model.AddPlant;
import tool.Tool;

public class StartPage extends JPanel implements Runnable,MouseListener,MouseMotionListener{
	ImageIcon Btn;
	ImageIcon surviveBtn;
	ImageIcon miniBtn;
	ImageIcon startBtnDown;
	ImageIcon startBtnUp;
	ImageIcon surviveBtnUp;
	ImageIcon surviveBtnDown;
	ImageIcon miniBtnUp;
	ImageIcon miniBtnDown;
	ImageIcon imgBackground;
	JFrame ptnThis;
	MusicPlayer musicplayer;
	public StartPage(JFrame jframe) {
		ptnThis = jframe;
		musicplayer = new MusicPlayer("music/welcome.wav",false,1);  //背景音乐
    	musicplayer.musicTimer();
    	
    	
    	startBtnDown = new ImageIcon("src/img/SelectorScreenAdventureDown1.png");
    	startBtnUp = new ImageIcon("src/img/SelectorScreenAdventureUP1.png");
    	
    	surviveBtnUp = new ImageIcon("src/img/survive_up.png");
    	surviveBtnDown = new ImageIcon("src/img/survive_down.png");
    	
    	miniBtnUp = new ImageIcon("src/img/mini_up.png");
    	miniBtnDown = new ImageIcon("src/img/mini_down.png");
    	
    	Btn = startBtnDown;
    	surviveBtn = surviveBtnUp;
    	miniBtn = miniBtnUp;
    	
    	imgBackground = new ImageIcon("src/img/Surface1.png");
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imgBackground.getImage(), 0, 0, 1100, 600, this);
		g.drawImage(Btn.getImage(), 600, 120, 370, 120, this);
		g.drawImage(miniBtn.getImage(),600,200,350,146,this);
		g.drawImage(surviveBtn.getImage(),600,290,331,146,this);
	}
	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		StartPage g = new StartPage(jframe);
		
	    jframe.setSize(1100,600);
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jframe.setResizable(false);
	    
        new Thread(g).start();
        jframe.add(g);
        jframe.addMouseListener(g);
        jframe.addMouseMotionListener(g);
        jframe.setVisible(true);
	}
    @Override
	public void mouseClicked(MouseEvent e) {
    	musicplayer.endPlay();
    	new MusicPlayer("music/evillaugh.wav",true,3000).musicTimer();
         
        if(e.getX()>600&&e.getX()<931&&e.getY()>120&&e.getY()<270) {
        	//第一关
        	 Tool.produceSun();//非向日葵产生的阳光
             Tool.progressBar.modProgressX();
             GameInformation.isStartPlant = false;
 			 new Config(); 
 			 JFrame frame = new JFrame();
 			 GameStart gameStart = new GameStart(AddPlant.zombies);
 			 frame.add(gameStart); 
 		     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 		     frame.setSize(1000, 600);
 		     frame.setResizable(false);
 		     frame.setVisible(true);
 		    Timer timer = new Timer();
		     TimerTask task = new TimerTask() {
		    		@Override
					public void run() {
		    		if(GameInformation.isStartPlant) {
		    			gameStart.judgeZombieWon();
		   		        try {
							gameStart.initialization();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		   		            gameStart.newGame(frame);
							timer.cancel();
		    			}
		    		}				
		    	};
		    	timer.schedule(task,0,100);
		    	ptnThis.dispose();
        }else if(e.getX()>600&&e.getX()<931&&e.getY()>260&&e.getY()<350) {
        	//第二关      
        	Tool.produceSun();//非向日葵产生的阳光
              Tool.progressBar.modProgressX();
  			 new Config(); 
  			 GameInformation.stage = 2;
  			 GameInformation.isStartPlant = false;
  			 JFrame frame = new JFrame();
  			 GameStart gameStart = new GameStart(AddPlant.zombies);
  			 frame.add(gameStart); 
  		     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		     frame.setSize(1000, 600);
  		     frame.setResizable(false);
  		     frame.setVisible(true);
  		    Timer timer = new Timer();
 		     TimerTask task = new TimerTask() {
 		    		@Override
 					public void run() {
 		    		if(GameInformation.isStartPlant) {
 		    			gameStart.judgeZombieWon();
 		   		        try {
 							gameStart.initialization();
 						} catch (FileNotFoundException e) {
 							e.printStackTrace();
 						}
 		   		            gameStart.newGame(frame);
 							timer.cancel();
 		    			}
 		    		}				
 		    	};
 		    	timer.schedule(task,0,100);	
 		    	ptnThis.dispose();
        	
		} else if (e.getX() > 600 && e.getX() < 931 && e.getY() > 340 && e.getY() < 435) {
			System.out.println("hello");
			// 第三关
			Tool.produceSun();// 非向日葵产生的阳光
			Tool.progressBar.modProgressX();
			new Config();
			GameInformation.stage = 3;
			 GameInformation.isStartPlant = false;
			JFrame frame = new JFrame();
			GameStart gameStart = new GameStart(AddPlant.zombies);
			frame.add(gameStart);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(1000, 600);
			frame.setResizable(false);
			frame.setVisible(true);
			Timer timer = new Timer();
		     TimerTask task = new TimerTask() {
		    		@Override
					public void run() {
		    			if(GameInformation.isStartPlant) {
		    			gameStart.judgeZombieWon();
		   		        try {
							gameStart.initialization();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		   		        gameStart.newGame(frame);
							timer.cancel();
		    			}
		    		}				
		    	};
		    	timer.schedule(task,0,100);	  
		    	ptnThis.dispose();
		} else if (e.getX() > 994 && e.getX() < 1072 && e.getY() > 528 && e.getY() < 570) {
			    System.exit(0);
		}

	}
    @Override
	public void mouseEntered(MouseEvent e) {
		 
    }
    @Override
	public void mouseExited(MouseEvent e) {
		 
	}
    @Override
	public void mousePressed(MouseEvent e) {
		 
		
	}
    @Override
	public void mouseReleased(MouseEvent e) {
		 
		
	}
    @Override
	public void run() {
		while(true){
			try{
				Thread.sleep(30);
			}
			catch(Exception e){}
			repaint();
			}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		 
	}
    @Override
	public void mouseMoved(MouseEvent arg0) {
		if(arg0.getX()>600&&arg0.getX()<931&&arg0.getY()>120&&arg0.getY()<270) {
			if(Btn==startBtnDown) {
    		new MusicPlayer("music/button.wav",true,1).musicTimer();
    		Btn = startBtnUp;
    		repaint();
			}
        }else {
        	Btn = startBtnDown;
        	repaint();
        }
		if(arg0.getX()>600&&arg0.getX()<931&&arg0.getY()>340&&arg0.getY()<435) {
			if(surviveBtn==surviveBtnUp) {
    		new MusicPlayer("music/button.wav",true,1).musicTimer();
    		surviveBtn = surviveBtnDown;
    		repaint();
			}
        }else {
        	surviveBtn = surviveBtnUp;
        	repaint();
        }
		if(arg0.getX()>600&&arg0.getX()<931&&arg0.getY()>260&&arg0.getY()<350) {
			if(miniBtn==miniBtnUp) {
				//System.out.println("hhh");
    		new MusicPlayer("music/button.wav",true,1).musicTimer();
    		miniBtn = miniBtnDown;
    		repaint();
			}
        }else {
        	miniBtn = miniBtnUp;
        	repaint();
        }
	}

}
