package player;

import java.awt.*;
import main.*;

public class Player{

    private Boolean[] canGo = {false, false, false, false};

    private double posX;
    private double posY;

    private int size = 20 ;

    private double speed = 5;

    private Map map;

    public Player(Map map){

        this.posX = 30;
        this.posY = 30;

        this.map = map;

    }

    public void display(Graphics g){

        g.setColor(Color.RED);
        g.fillRect((int)posX, (int)posY, size, size);
    }

    public void update(Boolean[] keys){
        this.checkWalls();
        this.move(keys);
        this.checkChangeMap();
    }

    public void checkWalls(){
        Rectangle rectPlayer0 = new Rectangle( 
            (int)(posX), (int)(this.posY - this.speed), size, size);
        
        Rectangle rectPlayer1 = new Rectangle( 
            (int)(this.posX + this.speed), (int)(posY), size, size);

        Rectangle rectPlayer2 = new Rectangle( 
            (int)(posX), (int)(this.posY + this.speed), size, size);

        Rectangle rectPlayer3 = new Rectangle( 
            (int)(this.posX - this.speed), (int)(posY), size, size);
        
        canGo[0] = !map.wallCollision(rectPlayer0);
        canGo[1] = !map.wallCollision(rectPlayer1);
        canGo[2] = !map.wallCollision(rectPlayer2) && (posY < 780);
        canGo[3] = !map.wallCollision(rectPlayer3);
    }

    public void move(Boolean[] keys){
        if (keys[0] && canGo[0]){
            this.posY = this.posY - this.speed;
        }

        if (keys[2] && canGo[2]){
            this.posY = this.posY + this.speed;
        }

        if (keys[1] && canGo[1]){
            this.posX = this.posX + this.speed;
        }

        if (keys[3] && canGo[3]){
            this.posX = this.posX - this.speed;
        }
    }

    public void checkChangeMap(){
        if (map.isFinished() && posY <= 0){
            map.changeMap();
            this.setPosX(590);
            this.setPosY(780);
        }
    }

    public void setPosX(int x){this.posX = x;}
    public void setPosY(int y){this.posY = y;}
}