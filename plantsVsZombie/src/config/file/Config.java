package config.file;
//新增数字图片
import java.awt.Image;
import java.awt.Toolkit;

public class Config {
    public static Map[][] map;
    //背景图片
    public static Image background1 = Toolkit.getDefaultToolkit().createImage("src/img/background.jpg");
    public static Image background2 = Toolkit.getDefaultToolkit().createImage("src/img/background2.jpg");
    //卡片图片
    public static Image peaShooterCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/peaShooter.png");
    public static Image snowPeaShooterCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/snowPeaShooter.png");
    public static Image sunFlowerCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/sunFlower.png");
    public static Image wallNutCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/wallNut.png");
    public static Image cherryBombCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/cherryBomb.png");
    public static Image doublePeaShooterCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/doublePeaShooter.png");
    public static Image sunShroomCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/sunShroom.jpg");
    public static Image puffShroomCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/PuffShroom.jpg");
    public static Image chomperCard = Toolkit.getDefaultToolkit().createImage("src/img/plantCards/chomper.jpg");
    
    //植物图片
    public static Image peaShooter = Toolkit.getDefaultToolkit().createImage("src/img/plants/PeaShooter.gif");
    public static Image snowPeaShooter = Toolkit.getDefaultToolkit().createImage("src/img/plants/SnowPea.gif");
    public static Image sunFlower = Toolkit.getDefaultToolkit().createImage("src/img/plants/SunFlower.gif");
    public static Image wallNut = Toolkit.getDefaultToolkit().createImage("src/img/plants/WallNut.gif");
    public static Image wallNut_cracked1 = Toolkit.getDefaultToolkit().createImage("src/img/plants/WallNut_cracked1.gif");
    public static Image wallNut_cracked2 = Toolkit.getDefaultToolkit().createImage("src/img/plants/WallNut_cracked2.gif");
    public static Image cherryBomb = Toolkit.getDefaultToolkit().createImage("src/img/plants/CherryBomb.gif");
    public static Image boom = Toolkit.getDefaultToolkit().createImage("src/img/plants/Boom.gif");
    public static Image doublePeaShooter = Toolkit.getDefaultToolkit().createImage("src/img/plants/doublePeaShooter.gif");
    public static Image sunShroom1 = Toolkit.getDefaultToolkit().createImage("src/img/plants/SunShroom2.gif");
    public static Image sunShroom2 = Toolkit.getDefaultToolkit().createImage("src/img/plants/SunShroom.gif");
    public static Image sunShroomSleep = Toolkit.getDefaultToolkit().createImage("src/img/plants/SunShroomSleep.gif");
    public static Image puffShroom = Toolkit.getDefaultToolkit().createImage("src/img/plants/PuffShroom.gif");
    public static Image puffShroomSleep = Toolkit.getDefaultToolkit().createImage("src/img/plants/PuffShroomSleep.gif");
    public static Image chomper = Toolkit.getDefaultToolkit().createImage("src/img/plants/Chomper.gif");
    public static Image chomperAttack = Toolkit.getDefaultToolkit().createImage("src/img/plants/ChomperAttack.gif");
    public static Image chomperDigest = Toolkit.getDefaultToolkit().createImage("src/img/plants/ChomperDigest.gif");
    //工具图片
    public static Image shovel = Toolkit.getDefaultToolkit().createImage("src/img/tool/Shovel.png");
    public static Image ShovelBack = Toolkit.getDefaultToolkit().createImage("src/img/tool/ShovelBack.png");
    //数字图片
    public static Image []numImage;
    //植物子弹
    public static Image shroomBullet = Toolkit.getDefaultToolkit().createImage("src/img/plantItems/ShroomBullet.gif");
    //植物选择框
    public static Image seedChooser_Background = Toolkit.getDefaultToolkit().createImage("src/img/SeedChooser_Background.png");
    public static Image reset = Toolkit.getDefaultToolkit().createImage("src/img/reset.png");
    public static Image go = Toolkit.getDefaultToolkit().createImage("src/img/go.png");
    public Config() {
    	//初始化地图方块坐标
    	 map = new Map[5][9];
    	 for(int i = 0; i < 5; i++) {
    		 for(int j = 0; j < 9; j++) {
    			 map[i][j] = new Map();
    			 map[i][j].x = 105 + j * 80;
    			 map[i][j].y = 80 + i * 95;
    			 map[i][j].isPlanted = false;
    		 }
    	 }
    	 //初始化数字图片资源
    	 numImage = new Image[10];
    	 for(int i = 0; i < 10; i++) {
    		 numImage[i] = Toolkit.getDefaultToolkit().createImage("src/img/number/"+i+".png");
    	 }
    }
    
}
