package model;

import java.util.ArrayList;

import config.file.Config;
import game_main.GameInformation;

public class AddPlant {
   public static AbstractPlants[][] plants = new AbstractPlants[5][9];
   public static ArrayList<ArrayList<Zombie>> zombies = new ArrayList<>();
   public static Boolean zombieWon = false;
   /**
    * 添加植物，根据行和列将植物加到对应位置
    * @param rows
    * @param column
    * @param plantType
    */
   public static boolean addPlant(int rows,int column, int plantType) {	   
		  int x = Config.map[rows][column].getX();
	      int y = Config.map[rows][column].getY();
	     
	   if(plantType == 0 && GameInformation.energy >= 50) {
		   if(!Config.map[rows][column].isPlanted()) { //判断是否已被种植
				  Config.map[rows][column].setPlanted(true);//该方块设置为已种
			   plants[rows][column] = new  SunFlower(x, y, rows, column,0);
			   GameInformation.energy -= 50;
			   return true;
		   } 
	   }
	   if(plantType == 1 && GameInformation.energy >= 100) {
		    if(!Config.map[rows][column].isPlanted()) { //判断是否已被种植
				  Config.map[rows][column].setPlanted(true);//该方块设置为已种
		   plants[rows][column] = new  PeaShooter(x, y, rows, column,1);
		   GameInformation.energy -= 100;
		   plants[rows][column].shoot();
		   return true;
	   } 
	  }
	   //坚果墙
	   if(plantType == 2 && GameInformation.energy >= 50) {
		    if(!Config.map[rows][column].isPlanted()) {  //判断是否已被种植
				  Config.map[rows][column].setPlanted(true);//该方块设置为已种
		   plants[rows][column] = new WallNut(x, y, rows, column);
		   GameInformation.energy -= 50;
		   return true;
	   } 
	  }
	 //樱桃炸弹
	   if(plantType == 3 && GameInformation.energy >= 150) {
		    if(!Config.map[rows][column].isPlanted()) {  //判断是否已被种植
		   Config.map[rows][column].setPlanted(true);//该方块设置为已种
		   plants[rows][column] = new CherryBomb(x, y, rows, column);
		   GameInformation.energy -= 150;
		   return true;
	   } 
	  }
	   //双发豌豆射手
	   if(plantType == 4 && GameInformation.energy >= 200) {
		    if(!Config.map[rows][column].isPlanted()) { //判断是否已被种植
				  Config.map[rows][column].setPlanted(true);//该方块设置为已种
		   plants[rows][column] = new PeaShooter(x, y, rows, column,4);
		   GameInformation.energy -= 200;
		   plants[rows][column].shoot();
		   return true;
	   } 
	  }
	   //阳光菇
	   if(plantType == 5 && GameInformation.energy >= 25) {
		    if(!Config.map[rows][column].isPlanted()) { //判断是否已被种植
				  Config.map[rows][column].setPlanted(true);//该方块设置为已种
		   plants[rows][column] = new SunFlower(x, y, rows, column,5);
		   GameInformation.energy -= 25;
		   return true;
	   } 
	  }
	   //粉扑菇
	   if(plantType == 6 && GameInformation.energy >= 0) {
		    if(!Config.map[rows][column].isPlanted()) { //判断是否已被种植
				  Config.map[rows][column].setPlanted(true);//该方块设置为已种
		   plants[rows][column] = new PuffShroom(x, y, rows, column,6);
		   if(GameInformation.stage == 2) {
			    plants[rows][column].shoot();
		   }	  
		   return true;
	   } 
	  }
	 //食人花
	   if(plantType == 7 && GameInformation.energy >= 0) {
		    if(!Config.map[rows][column].isPlanted()) { //判断是否已被种植
				  Config.map[rows][column].setPlanted(true);//该方块设置为已种
		   plants[rows][column] = new Chomper(x, y, rows, column);
		   if(GameInformation.stage == 2) {
			    plants[rows][column].shoot();
		   }	  
		   return true;
	   } 
	  }
	   return false;
	}
}
