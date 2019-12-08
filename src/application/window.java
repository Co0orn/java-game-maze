package application;


import javafx.scene.shape.Shape;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class window{
	private GridPane gp;
	private Map gameMap;
	private Mouse player;
	private int Width = 800;
	private int Height = 800;
	private Image wall;
	private Image path;
	private Image exit;
	public GridPane getGp() {
		return gp;
	}
	
	public Mouse getPlayer() {
		return player;
	}
 
	window(int a,int b) throws FileNotFoundException{
		Width = a;
		Height =b;
		gp= new GridPane();
		gameMap = new Map();
		
		wall = new Image("file:image/wall.jpg");
		path = new Image("file:image/path.jpg");
		exit = new Image("file:image/exit.jpg");
		player = new Mouse(gameMap.getStartR(),gameMap.getStartC());
	}
	
	@SuppressWarnings("preview")
	public void drowmap() {
		ImageView a =new ImageView();
		int x = 0,y=0;
		for(int i=0;i<gameMap.getCol();i++)//5
			for(int j=0;j<gameMap.getRow();j++)//4
			{
				switch(gameMap.getlocation(j,i)) {
				case 0 ->{a =new ImageView();
				a.setFitHeight(Height/gameMap.getRow());
				a.setFitWidth(Width/gameMap.getCol());
				a.setImage(path);gp.add(a,i,j);
				}
				case 1 ->{a =new ImageView();
				a.setFitHeight(Height/gameMap.getRow());
				a.setFitWidth(Width/gameMap.getCol());
				a.setImage(wall);gp.add(a,i,j);
				}
				case 2 ->{a =new ImageView();
				a.setFitHeight(Height/gameMap.getRow());
				a.setFitWidth(Width/gameMap.getCol());
				a.setImage(path);gp.add(a,i,j);
				x=i;y=j;
				}
				case 3 ->{a =new ImageView();
				a.setFitHeight(Height/gameMap.getRow());
				a.setFitWidth(Width/gameMap.getCol());
				a.setImage(exit);gp.add(a,i,j);
				}
				default -> throw new IllegalArgumentException("Unexpected value: " + gameMap.getlocation(j,i));
				}
			}
		 
		player.getImageView().setFitHeight(Height/gameMap.getRow());
		player.getImageView().setFitWidth(Width/gameMap.getCol());
		player.getImageView().setImage(player.getImage());gp.add(player.getImageView(),x,y);
	}
	
	public void move(int a) {
		switch(a) {
			case 1->{
				if(gameMap.getlocation(player.getX()-1, player.getY())!=1 && isLeagal(player.getX()-1,player.getY())) {
					gp.setRowIndex(player.getImageView(), player.getX()-1);
					player.setX(player.getX()-1);
				}}
			case 2->{
				if(gameMap.getlocation(player.getX()+1, player.getY())!=1 && isLeagal(player.getX()+1,player.getY())) {
					gp.setRowIndex(player.getImageView(), player.getX()+1);
					player.setX(player.getX()+1);
				}}
			case 3->{
				if(gameMap.getlocation(player.getX(), player.getY()-1)!=1 && isLeagal(player.getX(),player.getY()-1)) {
					gp.setColumnIndex(player.getImageView(), player.getY()-1);
					player.setY(player.getY()-1);
				}}
			case 4->{
				if(gameMap.getlocation(player.getX(), player.getY()+1)!=1 && isLeagal(player.getX(),player.getY()+1)) {
					gp.setColumnIndex(player.getImageView(), player.getY()+1);
					player.setY(player.getY()+1);
				}
			}
			}
		player.show();
		
	}
	
	public boolean isWin() {
		int[] a=gameMap.getwin(); 
		
		if(player.getX()==a[0] && player.getY()==a[1])
		{
			ImageView win =new ImageView( new Image("file:over.png"));
			win.setFitHeight(Height/gameMap.getRow());
			win.setFitWidth(Width/gameMap.getCol());
			gp.add(win,player.getY(),player.getX());
			System.out.println("You Win!");
		}
		
		return false;
	}
	
	public boolean isLeagal(int a,int b) {
		if(a<gameMap.getMap().length && a>=0 && b<gameMap.getMap()[0].length && b>=0)
			return true;
		
		return false;
		}

	public void auto() {
		ArrayList<int[]> ans;
		ans=gameMap.search(player.getX(), player.getY());
		
		for(int i=1;i<ans.size();i++) {
			int xx=ans.get(i)[0]; int yy=ans.get(i)[1];
			if(player.getX()-1==xx)
				move(1);
			if(player.getX()+1==xx)
				move(2);
			if(player.getY()-1==yy)
				move(3);
			if(player.getY()+1==yy)
				move(4);
			
		}
	}

}
