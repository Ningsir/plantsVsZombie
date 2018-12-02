package config.file;
/**
 * 地图，用来存储每一个方块的信息
 * 并以此声明一个二维数组，下标为每个方块的行和列（从0开始）
 * @author 宁鑫
 *
 */
public class Map {
   int x;
   int y;
   boolean isPlanted;//此方块是否被种植
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public boolean isPlanted() {
	return isPlanted;
}
public void setPlanted(boolean isPlanted) {
	this.isPlanted = isPlanted;
}
}
