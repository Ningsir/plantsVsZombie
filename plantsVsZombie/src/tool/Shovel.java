package tool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import config.file.Config;
import gameui.MusicPlayer;

public class Shovel {
	   protected int x;
	   protected int y;
	   protected int height;
	   protected int width;
	   protected Image shovelImage;
	   private MusicPlayer playerForShovel;
	   private boolean isDragged;
	   private boolean musicHasPlayed;
	   public Shovel(int x, int y) {
		this.shovelImage = Config.shovel;
		this.width = 70;
		this.height = 30;
		this.x = x;
		this.y = y;
		this.isDragged = false;
		this.musicHasPlayed = false;
	}
    
	public void draw(Graphics g, ImageObserver o) {
		   g.drawImage(this.shovelImage,x, y,this.width,this.height, o); 
	   }
	public boolean isDragged() {
		return isDragged;
	}
	public void setDragged(boolean isDragged) {
		this.isDragged = isDragged;
	}

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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isMusicHasPlayed() {
		return musicHasPlayed;
	}

	public void setMusicHasPlayed(boolean musicHasPlayed) {
		this.musicHasPlayed = musicHasPlayed;
	}

	public MusicPlayer getPlayerForShovel() {
		return playerForShovel;
	}

	public void setPlayerForShovel(MusicPlayer playerForShovel) {
		this.playerForShovel = playerForShovel;
	}
    
}
