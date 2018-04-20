package com.mygdx.tron;
import com.badlogic.gdx.Gdx;


public class Player {
	int color; // definiert Spieler (0=Black) 1=Green, 2=Blue
	int speed;
	int PosX;
	int PosY;
	int DirX; // -1,0,+1 Player Control
	int DirY;
	int size;
	int up;
	int left;
	int down;
	int right;
	
	public Player(int color,int speed, int PosX, int PosY, int DirX, int DirY, int size, int up, int left, int down, int right) {
		this.color = color;
		this.speed = speed;
		this.PosX = PosX;
		this.PosY = PosY;
		this.DirX = DirX;
		this.DirY = DirY;
		this.size = size;
		this.up = up;
		this.left = left;
		this.down = down;
		this.right = right;
	}
	
	//move per frame
	public void move() {
		this.PosX = Math.floorMod(this.PosX+this.DirX,1024) ;
		this.PosY = Math.floorMod(this.PosY+this.DirY,600) ;
	}
	
	
	//return 0: nothing to spawn; return 1-4 spawn particle in direction (counterclockwise)
	public int input() {
		
		if(DirY==0) {
			if(Gdx.input.isKeyJustPressed(up)) {
				DirY = 0-speed;
				DirX = 0;
				return 0;
			}
			if(Gdx.input.isKeyJustPressed(down)) {
				DirY = speed;
				DirX = 0;
				return 0;
			}

		}
		else
		{
			if(Gdx.input.isKeyJustPressed(left)) {
				DirX = 0-speed;
				DirY = 0;
				return 0;
			}
				
			if(Gdx.input.isKeyJustPressed(right)) {
				DirX = speed;
				DirY = 0;
				return 0;
			}

		}
		return 0;
	}
	//todo key direction control
	//if key A -> diry=0 und dirx=-1 wenn er schon x fährt
}
