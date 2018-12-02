package game_main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import config.file.Config;
import gameui.MusicPlayer;
import gameui.StartPage;
import model.AddPlant;
import model.BucketheadZombie;
import model.ConeheadZombie;
import model.FlagZombie;
import model.NormalZombie;
import model.PlantCard;
import model.SunFlower;
import model.Zombie;
import tool.LawnCleaner;
import tool.Tool;

public class GameStart extends JPanel implements MouseMotionListener,MouseListener{
	  Image background;
	  Image seedBank;
	  Image plantCard;
	  Image zombieWonImg;
	  ArrayList<PlantCard> cardList;
	  ArrayList<ArrayList<Zombie>> zombies = new ArrayList<>();
	  MusicPlayer gameMusic = new MusicPlayer("music/gaming.wav",true,1);
	  public GameStart(ArrayList<ArrayList<Zombie>> zombies) {
		  if(GameInformation.stage == 2) {
		  background = Config.background2;
		  } else {
			  background = Config.background1;
		  }	  
		  ImageIcon icon1 = new ImageIcon("src/img/SeedBank.png");
		  seedBank = icon1.getImage();
		  ImageIcon icon2 = new ImageIcon("src/img/ZombiesWon.png");
		  zombieWonImg = icon2.getImage();
		  cardList = new ArrayList<>();
		  this.addMouseMotionListener(this);
		  this.addMouseListener(this);	  
		  this.zombies = zombies;  
		  gameMusic.musicTimer();
	  }
	  @Override
	  public void paint(Graphics g) {
	    super.paint(g);   
	    
	    if(background != null) {
	    	g.drawImage(background, 0, 0, 1100, 600, 150, 0, 1250, 600, this);
	    } 
	    g.drawImage(seedBank,80, 0, 400, 80, this);//植物槽
	    g.drawImage(Config.ShovelBack,500, 5, 70, 50, this);
	    Tool.shovel.draw(g, this);//绘制铲子
	    //绘制小推车
	    for(LawnCleaner l : Tool.lawnList) {
	    	if(l != null) {
	    		l.draw(g, this);
	    	}	
	    }
	    //选择植物
	    if(!GameInformation.isStartPlant) {	    	  
	    	g.drawImage(Config.seedChooser_Background,400,100,400,450,this);
	    	for(PlantCard pc : Tool.plantCards) {
	    		pc.drawCard(g, this);
	    	}
	    	 if(cardList != null) {
	    		for(PlantCard pc : cardList) {
		    	pc.drawCard(g, this);
		     }
	    }  	
	    	g.drawImage(Config.reset,480,500,60,40,this);//重置按钮
	    	g.drawImage(Config.go,670,500,60,40,this);//go按钮
	    }
	    //植物选择完毕，开始游戏
	    if(GameInformation.isStartPlant) {
	    Tool.drawSun(g, this);//绘制工具中的阳光
	    Tool.progressBar.draw(g, this);//绘制进度条
	    for(PlantCard pc : cardList) {
	    	pc.drawCard(g, this);
	    }   
	   //绘制植物
	    for(int i = 0; i < 5; i++) {
	    	for(int j = 0; j < 9; j++) {
	    		if(AddPlant.plants[i][j] != null)
	    		AddPlant.plants[i][j].plant(g, this);
	    	}
	    }
	    //打印能量值
	    GameInformation.displayEnergy(g, this);	
	    //绘制僵尸
	    try {
	    	for(ArrayList<Zombie> zList : zombies) {
				for(Zombie z : zList) {
					z.drawByStatus(g, this);
				}
			} 
	    } catch(Exception e) {
	    	 
	    }    
	    if(AddPlant.zombieWon) {
	    	g.drawImage(zombieWonImg, 200, 100, this);
	     }
	    } 
	  }	 
	@Override
	public void mouseDragged(MouseEvent e) {
		//拖动植物
		     for(PlantCard pc : cardList) {
			 if(pc.isDragged()&&e.getX()>pc.getX2()&&e.getX()<(pc.getX2()+pc.getWidth())&&e.getY()>pc.getY2()&&e.getY()<(pc.getY2()+pc.getHeight())) {
				pc.setX2(e.getX()-pc.getWidth()/2);
	            pc.setY2(e.getY()-pc.getHeight()/2);			 
			}
		}
		  //拖动铲子
		     if(Tool.shovel.isDragged()&&e.getX()>Tool.shovel.getX()&&e.getX()<(Tool.shovel.getX()+Tool.shovel.getWidth())&&e.getY()>Tool.shovel.getY()&&e.getY()<(Tool.shovel.getY()+Tool.shovel.getHeight())) {
		    	 if(!Tool.shovel.isMusicHasPlayed()) {
		    		 Tool.shovel.setPlayerForShovel(new MusicPlayer("music/chanzi.wav",true, 1));
		    		 Tool.shovel.getPlayerForShovel().musicTimer();
		    		 Tool.shovel.setMusicHasPlayed(true);
		    	 }
		    	 Tool.shovel.setX(e.getX()-Tool.shovel.getWidth()/2);
		    	 Tool.shovel.setY(e.getY()-Tool.shovel.getHeight()/2);			 
				}
          repaint();
		}
		
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
 
	static int count;
	@Override
	public void mouseClicked(MouseEvent e) {
		Tool.isCollectSun(e.getX(), e.getY());
		//收集阳光
				for(int i = 0; i < 5; i++) {
			    	for(int j = 0; j < 9; j++) {
			    		if(AddPlant.plants[i][j] != null && (AddPlant.plants[i][j].getPlantType() == 0 || AddPlant.plants[i][j].getPlantType() == 5)) {		    			
			    			SunFlower sunFlower = (SunFlower)AddPlant.plants[i][j];
			    			sunFlower.isCollectSun(e.getX(), e.getY());
			    		}  		 
			    	}
			    }
		if(!GameInformation.isStartPlant) {
			//选择植物
			for(PlantCard pc : Tool.plantCards) {
				if(count < GameInformation.seedBankCapacity && e.getX()>pc.getX2()&&e.getX()<(pc.getX2()+pc.getWidth())&&e.getY()>pc.getY2()&&e.getY()<(pc.getY2()+pc.getHeight())) {
					pc.setX1(150 + count * 65);
					pc.setX2(150 + count * 65);
					pc.setY1(5);
					pc.setY2(5);
					this.cardList.add(pc);
					count++;
					repaint();
				}
			}
			//点击go按钮，开始游戏	
			if(e.getX() > 670 && e.getX() < 730 && e.getY() > 500 && e.getY() < 540) {
				GameInformation.isStartPlant = true;
				repaint();
			}
			//点击reset按钮，重新选择植物
			if(e.getX() > 480 && e.getX() < 540 && e.getY() > 500 && e.getY() < 540) {
				this.cardList.clear();
				count = 0;
				for(PlantCard pc : Tool.plantCards) {
					pc.setX1(pc.getX3());
					pc.setY1(pc.getY3());
					pc.setX2(pc.getX3());
					pc.setY2(pc.getY3());
				}
				repaint();
			}
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
		for(PlantCard pc : cardList) {
			if(!pc.getLoaded()) {
			 if(e.getX()>pc.getX2()&&e.getX()<(pc.getX2()+pc.getWidth())&&e.getY()>pc.getY2()&&e.getY()<(pc.getY2()+pc.getHeight())) {
				 pc.setDragged(true);	
			  }
			}
		 }
		if(e.getX()>Tool.shovel.getX()&&e.getX()<(Tool.shovel.getX()+Tool.shovel.getWidth())&&e.getY()>Tool.shovel.getY()&&e.getY()<(Tool.shovel.getY()+Tool.shovel.getHeight())) {
			Tool.shovel.setDragged(true);			 
			}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//铲除植物
		if(Tool.shovel.isDragged()) {
			int row = GameInformation.getRowByY(e.getY());
			int column = GameInformation.getColumnByX(e.getX());
			if(AddPlant.plants[row][column] != null) {
				AddPlant.plants[row][column].setLife(0);
				Config.map[row][column].setPlanted(false);
			}
			Tool.shovel.setX(500);
			Tool.shovel.setY(10);
			Tool.shovel.setDragged(false);
			Tool.shovel.setMusicHasPlayed(false);
		}
		//种植植物
		for(PlantCard pc : cardList) {
			if(pc.isDragged()) {
				int row = GameInformation.getRowByY(e.getY());
				int column = GameInformation.getColumnByX(e.getX());
				//鼠标释放的y坐标小于75不执行addPlant操作
			    if(e.getY() > 75 && AddPlant.addPlant(row, column, pc.getPlantType())) {
			    	pc.setLoaded(true);
					pc.decLoadedTime();
			    }
			    pc.setDragged(false);
				pc.setX2(pc.getX1());//坐标还原
				pc.setY2(pc.getY1());		
				new MusicPlayer("music/plant_v.wav", true,1).musicTimer();
			}
		} 
	}
	public void initialization() throws FileNotFoundException {
		ArrayList<Zombie> zombies1 = new ArrayList<>();
		ArrayList<Zombie> zombies2 = new ArrayList<>();
		ArrayList<Zombie> zombies3 = new ArrayList<>();
		ArrayList<Zombie> zombies4 = new ArrayList<>();
		ArrayList<Zombie> zombies5 = new ArrayList<>();
		AddPlant.zombies.add(zombies1);
		AddPlant.zombies.add(zombies2);
		AddPlant.zombies.add(zombies3);
		AddPlant.zombies.add(zombies4);
		AddPlant.zombies.add(zombies5);
		File f = new File("src/config.txt");
		new MusicPlayer("music/zombiesAreComing.wav",true, 1).musicTimer();
		Scanner sc = new Scanner(f);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			Random random = new Random();
            boolean first = true;
			@Override
			public void run() {
				int breed = random.nextInt(4) + 1;
				if(first) {
				if (1 == breed) {
					int rows = random.nextInt(5);
					Zombie z = new NormalZombie(950, rows * 100 + 30, rows + 1);
					AddPlant.zombies.get(rows).add(z);
					z.ZombieTimer(AddPlant.zombies, AddPlant.plants);
				} else if (2 == breed) {
					int rows = random.nextInt(5);
					Zombie z = new FlagZombie(950, rows * 100 + 30, rows + 1);
					AddPlant.zombies.get(rows).add(z);
					z.ZombieTimer(AddPlant.zombies, AddPlant.plants);
				} else if (3 == breed) {
					int rows = random.nextInt(5);
					Zombie z = new BucketheadZombie(950, rows * 100 + 30, rows + 1);
					AddPlant.zombies.get(rows).add(z);
					z.ZombieTimer(AddPlant.zombies, AddPlant.plants);
				}else if (4 == breed) {
					int rows = random.nextInt(5);
					Zombie z = new ConeheadZombie(950, rows * 100 + 30, rows + 1);
					AddPlant.zombies.get(rows).add(z);
					z.ZombieTimer(AddPlant.zombies, AddPlant.plants);
				}
			}
				first = true;
				if (GameInformation.stage == 3) {
					try {
						Thread.sleep(breed * 2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					if (sc.hasNextLine() && !AddPlant.zombieWon) {
						String config = sc.nextLine();
						try {
							Thread.sleep(Integer.parseInt(config) * 1000);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else {
						timer.cancel();
						GameInformation.nextStage = true;
					}
				}
			}
		};
		timer.schedule(task, 0, 1);
	}
	public void judgeZombieWon() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int count = 0; 
	    	@Override
			public void run() {
	    		 for(ArrayList<Zombie> zombie : zombies) {
	    			for(Zombie z : zombie) {
	    				if(-10 >= z.getX()) {
	    					AddPlant.zombieWon = true;
	    					for(ArrayList<Zombie> zombie1 : AddPlant.zombies) {
		    					for(Zombie z1 : zombie1) {
		    						z1.setLife(-100);
		    						GameInformation.nextStage = false;
		    						}
	    					}
		    				for(int i = 0; i < 5;i++) {
		    					for(int j = 0;j < 9;j++) {
		    						if(Config.map[i][j].isPlanted()) {
		    							Config.map[i][j].setPlanted(false);
		    							AddPlant.plants[i][j].setLife(0);
			    						AddPlant.plants[i][j] = null;
		    						}
		    					}
		    				}
	    				}
	    			}
	    		 }
	    		 if(AddPlant.zombieWon) {
	    			 count++;
	    		 }
	    		 if(30 == count && AddPlant.zombieWon && !GameInformation.ifNewGame) { //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    			 timer.cancel();
	    			 GameInformation.ifNewGame = true;
	    				}
	    		 }
	    };
	    timer.schedule(task,0,100);
	 }
	public void newGame(JFrame jFrame) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				int count = 0;
				for(ArrayList<Zombie> zombie : AddPlant.zombies) {
					for(Zombie z : zombie) {
						count++;
					}
				}
				if (GameInformation.ifNewGame && AddPlant.zombieWon) {
					jFrame.dispose();
					GameInformation.ifNewGame = false;
					AddPlant.zombieWon = false;
					GameInformation.stage = 0;
					for (int i = 0; i < 5; i++) {
						Tool.lawnList[i] = new LawnCleaner(40, 80 + i * 95);
					}
					Tool.progressBar.setX(945);
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
				else if(GameInformation.nextStage && 0 == count){
					GameInformation.stage++;
					GameInformation.nextStage = false;
					GameInformation.energy = 50;
					GameInformation.isStartPlant = false;
					if(GameInformation.stage == 2) {
						jFrame.dispose();
						for(int i = 0; i < 5;i++) {
	    					for(int j = 0;j < 9;j++) {
	    						if(Config.map[i][j].isPlanted()) {
	    							Config.map[i][j].setPlanted(false);
	    							AddPlant.plants[i][j].setLife(0);
		    						AddPlant.plants[i][j] = null;
	    						}
	    					}
						}
						for (int i = 0; i < 5; i++) {
							Tool.lawnList[i] = new LawnCleaner(40, 80 + i * 95);
						}
			        	Tool.produceSun();//非向日葵产生的阳光
			              Tool.progressBar.modProgressX();
			  			 new Config(); 
			  			 GameInformation.stage = 2;
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
			        	
					}
					if(GameInformation.stage == 3) {
						jFrame.dispose();
						for(int i = 0; i < 5;i++) {
	    					for(int j = 0;j < 9;j++) {
	    						if(Config.map[i][j].isPlanted()) {
	    							Config.map[i][j].setPlanted(false);
	    							AddPlant.plants[i][j].setLife(0);
		    						AddPlant.plants[i][j] = null;
	    						}
	    					}
						}
						for (int i = 0; i < 5; i++) {
							Tool.lawnList[i] = new LawnCleaner(40, 80 + i * 95);
						}
						Tool.produceSun();// 非向日葵产生的阳光
						Tool.progressBar.modProgressX();
						new Config();
						GameInformation.stage = 3;
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
					}
				}
			}
		};
		timer.schedule(task, 0, 100);
	}
}

