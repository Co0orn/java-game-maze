package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mouse {
	private int x;
	private int y;
	private Image mouse;
	private ImageView player;
	Mouse(int xx,int yy){
		x=xx;y=yy;
		mouse = new Image("file:image/mouse.jpg");
		player = new ImageView(mouse);
	}
	
	public void setX(int a) {
		x=a;
	}
	
	public void setY(int a) {
		y=a;
	}
	
	public Image getImage() {
		return mouse;
	}
	
	public ImageView getImageView() {
		return player;
	}
	
	public int getX() {
		return x ;
	}

	public int getY() {
		return y;
	}
	
	public void show() {
		System.out.println("("+x+","+y+")");
	}
}

