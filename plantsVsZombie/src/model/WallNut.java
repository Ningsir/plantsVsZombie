package model;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import config.file.Config;

public class WallNut extends AbstractPlants {

	public WallNut(int x, int y, int row, int column) {
		super(x, y, row, column);
		this.setLife(250);
		this.setEnergy(100);
		this.setPlantType(2);
		this.setPlantImage(Toolkit.getDefaultToolkit().createImage("src/img/plants/WallNut.gif"));    
	}
	@Override
	public void plant(Graphics g, ImageObserver o) {
	    if(this.getLife() < 150)
	    	this.setPlantImage(Config.wallNut_cracked1);
        if(this.getLife() < 100) {
        	this.setPlantImage(Config.wallNut_cracked2);
        }
		if(this.getPlantImage() != null && this.getLife() > 0) {
			g.drawImage(this.getPlantImage(),this.getX(), this.getY(), o);
		}
	}
	@Override
	public void shoot() {

	}

}
