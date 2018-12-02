package game_main;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import config.file.Config;

public class GameInformation {
     public static int energy = 1000;//能量值
     public static int stage = 1;//游戏阶段
     public static Boolean nextStage = false;
     public static boolean ifNewGame;
     public static boolean isStartPlant;//是否开始游戏
     public static int seedBankCapacity = 5;//植物槽的容量
     /**
      * 显示收集到的能量值
      */
	 public static void displayEnergy(Graphics g, ImageObserver o) {
		 ArrayList<Integer> list = resolveNum(energy);
		 for(int i = 0; i < list.size(); i++) {
			g.drawImage(Config.numImage[list.get(i)],90 + i * 15, 55,20,20,o);
		 }
	 }
	 /**
	  * 将能量值分解为个、十、百、千等位
	  * @return
	  */
	 public static ArrayList<Integer> resolveNum(int num){
		 ArrayList<Integer> list = new ArrayList<>();
		 String s = String.valueOf(num);
		 for(int i = 0; i < s.length(); i++) {
			 list.add(s.charAt(i) - 48);
		 }
		 return list;
	 }
	 /**
	  * 通过y坐标获取行
	  * @param y
	  * @return
	  */
	 public static int getRowByY(int y) {
			for(int i = 0; i < 5; i++) {
				if(y >= Config.map[i][0].getY() && y < Config.map[i][0].getY() + 95) {
					return i;
				}
			}
			return 0;
		}
	 /**
	  * 通过x坐标获取列
	  * @param x
	  * @return
	  */
	    public static int getColumnByX(int x) {
	    	for(int i = 0; i < 9; i++) {
				if(x >= Config.map[0][i].getX() && x < Config.map[0][i].getX() + 80) {
					return i;
				}
			}
			return 0;
		}
}
