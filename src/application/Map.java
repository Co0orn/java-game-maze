package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {
	private int[][] map;
	private int[][] Hmap;
	private Image wall;
	private Image path;
	private Image exit;
	private int startR;
	private int startC;
	private int endR;
	private int endC;
	private int row;
	private int col;
	private ArrayList<int[]> Rightway;

	Map() throws FileNotFoundException {
		File in = new File("map.txt");
		Scanner input = new Scanner(in);
		int r = input.nextInt();
		int c = input.nextInt();
		int[][] mmap = new int[r][c];
		int[][] hmap = new int[r][c];
		for(int i=0;i<r;i++)
			for(int j=0;j<c;j++)
				{
					mmap[i][j]=input.nextInt();
					if(mmap[i][j]==1)
						hmap[i][j]=1;
					if(mmap[i][j]==3)
						{endR=i;endC=j;	}
					if(mmap[i][j]==2)
						{startR=i;startC=j;}			
				}
		
		row=r;col=c;
		map=mmap.clone();
		Hmap=hmap.clone();
		if(!search())
		{
			System.out.println("找不到成功路径！");
			System.exit(1);
		}
	}
	
	public Image getImage(int a) {
		if(a==1)
			return wall;
		else if(a==2)
			return path;
		else 
			return exit;
	}
	
	public int[][] getHmap(){
		return Hmap;
	}
	
	public int[][] getMap(){
		return map;
	}
	
	public int getlocation(int a,int b){
		int c =map[a][b];
		return c;
	}
	
	public int getStartR() {
		return startR;
	}

	public int getStartC() {
		return startC;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public int[] getwin() {
		return new int[] {endR,endC};
	}
	
	public boolean search() {
		ArrayList<int[]> a = new ArrayList<int[]>();
		a.add(new int[] {startR,startC});
		
		int[][] temp=new int[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				temp[i][j]=Hmap[i][j];
		
		Hmap[startR][startC]=1;
		while(map[a.get(a.size()-1)[0]][a.get(a.size()-1)[1]]!=3) {
			int[] tmp = new int[2];
			
			tmp=findW(a.get(a.size()-1));
			if(tmp[0]!=-1)
			{	a.add(tmp);}
			else
			{	
				a.remove(a.size()-1);
				if(a.size()==0)
					return false;
			}
		}
		
		Hmap=temp;
		return true;
	}
	
	public ArrayList<int[]> search(int xx,int yy) {
		ArrayList<int[]> a = new ArrayList<int[]>();
		a.add(new int[] {xx,yy});
		Hmap[xx][yy]=1;
		while(map[a.get(a.size()-1)[0]][a.get(a.size()-1)[1]]!=3) {
			int[] tmp = new int[2];
			
			tmp=findW(a.get(a.size()-1));
			if(tmp[0]!=-1)
				a.add(tmp);
			else
				a.remove(a.size()-1);
		}

		return a;
	}

	private int[] findW(int[] is) {
		int xx=is[0];int yy=is[1];
		
   		if(isLeagal(xx+1,yy)&& map[xx+1][yy]!=1 && Hmap[xx+1][yy]==0 )
			{Hmap[xx+1][yy]=1;return new int[] {xx+1,yy};}
		if(isLeagal(xx,yy+1)&& map[xx][yy+1]!=1 && Hmap[xx][yy+1]==0 )
			{Hmap[xx][yy+1]=1;return new int[] {xx,yy+1};}
		if(isLeagal(xx,yy-1)&& map[xx][yy-1]!=1 && Hmap[xx][yy-1]==0 )
			{Hmap[xx][yy-1]=1;return new int[] {xx,yy-1};}
		if(isLeagal(xx-1,yy)&& map[xx-1][yy]!=1 && Hmap[xx-1][yy]==0 )
			{Hmap[xx-1][yy]=1;return new int[] {xx-1,yy};}
	
		return new int[] {-1,-1};
	}
	
	public boolean isLeagal(int a,int b) {
		if(a<map.length && a>=0 && b<map[0].length && b>=0)
			return true;
		
		return false;
	}
	
	public boolean noway(int xx,int yy) {
		if(Hmap[xx+1][yy]==1 && Hmap[xx-1][yy]==1 && Hmap[xx][yy-1]==1 && Hmap[xx][yy+1]==1)
			return true;
		return false;
	}
}
