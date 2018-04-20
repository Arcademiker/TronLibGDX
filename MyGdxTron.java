package com.mygdx.tron;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class MyGdxTron extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Pixmap pixmap;
	Texture pixmaptex;
	Player player1;
	Player player2;
	
	int contact; // 1=player 1 crashed,2=player 2 crashed,3=both crashed
	int playerSize;
	int playerSpeed;

	int tmp;
	
	@Override
	public void create () {
		playerSize = 5;
		playerSpeed = 2;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg"); //gegen Hintergrund ersetzen und vergrößern
		pixmap = new Pixmap( 1024, 600, Format.RGBA8888 );
		pixmaptex = new Texture( pixmap );
		player1 = new Player(1,playerSpeed,512-4,600-4,0,0-playerSpeed,playerSize,Keys.W,Keys.A,Keys.S,Keys.D);
		player2 = new Player(2,playerSpeed,1024-4,300-4,0-playerSpeed,0,playerSize,Keys.UP,Keys.LEFT,Keys.DOWN,Keys.RIGHT);
		contact = 0;
	}

	//	public void render (float delta) {
	
	@Override
	public void render () {
		//collision();
		
		if(contact==0) {
			update();
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Gdx.app.exit();
		}
		else {
			//end:
			//kill player1
			if(contact==1||contact==3) {
			pixmap.setColor( 1, 0, 0, 1);
			pixmap.fillRectangle(player1.PosX,player1.PosY, player1.size, player1.size); //todo size variable
			}
			//kill player2
			if(contact==2||contact==3) {
			pixmap.setColor( 1, 0, 0, 1);
			pixmap.fillRectangle(player2.PosX,player2.PosY, player2.size, player2.size);
			}
			pixmaptex.draw(pixmap, 0, 0); 
		}
		Gdx.gl.glClearColor(0, 0, 0, 1); //werden erst bei ende des spiels gebraucht
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//batch.draw(img, 0, 0);
		
		batch.draw(pixmaptex, 0, 0); //dynamische Texture übergeben an batch
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		pixmap.dispose();
		batch.dispose();
		img.dispose();
		pixmaptex.dispose();
	}


	public void update() {	
		tmp = player1.input();
		//spawn particle
		
		player1.move();
		tmp = player2.input();
		//spawn particle
		
		player2.move();
		

		collision();
		
		
		//draw player1
		pixmap.setColor( 0, 0, 1, 1);
		pixmap.fillRectangle(player1.PosX,player1.PosY, player1.size, player1.size);
		
		//draw player2
		pixmap.setColor( 0, 1, 0, 1);
		pixmap.fillRectangle(player2.PosX,player2.PosY, player2.size, player2.size);

		//Bildmatrix an textur uebergeben
		pixmaptex.draw(pixmap, 0, 0); 
		
		//return false;
	}

	public int collision() {
		//check all 8 player corners for collision:
		boolean p1crash = false;
		boolean p2crash = false;
		//collision player1
		if(player1.DirX!=0) {
			if(player1.DirX>0) {
				//right crash ?
				if(pixmap.getPixel(player1.PosX+player1.size, player1.PosY) != 0x00000000 || pixmap.getPixel(player1.PosX+player1.size, player1.PosY+player1.size) != 0x00000000) {
					p1crash = true;
				} 
			} 
			else {
				//left crash ?
				if(pixmap.getPixel(player1.PosX, player1.PosY+player1.size) != 0x00000000 || pixmap.getPixel(player1.PosX, player1.PosY) != 0x00000000) {
					p1crash = true;
				} 
			}
		}
		else {
			if(player1.DirY>0) {
				//down crash ?
				if(pixmap.getPixel(player1.PosX+player1.size, player1.PosY+player1.size) != 0x00000000 || pixmap.getPixel(player1.PosX, player1.PosY+player1.size) != 0x00000000) {
					p1crash = true;
				}	
			}
			else {
				//up crash ?
				if(pixmap.getPixel(player1.PosX, player1.PosY) != 0x00000000 || pixmap.getPixel(player1.PosX+player1.size, player1.PosY) != 0x00000000) {
					p1crash = true;
				} 
			}
		}
		
		//collision player2
		if(player2.DirX!=0) {
			if(player2.DirX>0) {
				//right crash ?
				if(pixmap.getPixel(player2.PosX+player2.size, player2.PosY) != 0x00000000 || pixmap.getPixel(player2.PosX+player2.size, player2.PosY+player2.size) != 0x00000000) {
					p2crash = true;
				} 
			} 
			else {
				//left crash ?
				if(pixmap.getPixel(player2.PosX, player2.PosY+player2.size) != 0x00000000 || pixmap.getPixel(player2.PosX, player2.PosY) != 0x00000000) {
					p2crash = true;
				} 
			}
		}
		else {
			if(player2.DirY>0) {
				//down crash ?
				if(pixmap.getPixel(player2.PosX+player2.size, player2.PosY+player2.size) != 0x00000000 || pixmap.getPixel(player2.PosX, player2.PosY+player2.size) != 0x00000000) {
					p2crash = true;
				}	
			}
			else {
				//up crash ?
				if(pixmap.getPixel(player2.PosX, player2.PosY) != 0x00000000 || pixmap.getPixel(player2.PosX+player2.size, player2.PosY) != 0x00000000) {
					p2crash = true;
				} 
			}
		}
		
		//pixmap.getPixel(player2.PosX, player2.PosY);        //up left corner    
		//pixmap.getPixel(player2.PosX+11, player2.PosY);     //up right corner   
		//pixmap.getPixel(player2.PosX+11, player2.PosY+11);  //down right corner 
		//pixmap.getPixel(player2.PosX, player2.PosY+11);     //down left corner  
		
		if(p1crash==true) { contact = 1; }
		if(p2crash==true) { contact = 2; }
		if(p1crash==true && p2crash==true) { contact = 3;}
		
		return contact;
	}
	
}