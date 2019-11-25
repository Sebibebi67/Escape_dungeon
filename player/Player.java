package player;

import java.awt.*;

public class Player{



    private double posX;
    private double posY;

    private double speed = 10;

    public Player(){

        this.posX = 30;
        this.posY = 30;

    }

    public void display(Graphics g){

        g.setColor(Color.RED);
        g.fillRect((int)posX, (int)posY, 20, 20);
    }

    public void move(Boolean[] keys){
        if (keys[0]){
            this.posY = this.posY - this.speed;
        }

        if (keys[2]){
            this.posY = this.posY + this.speed;
        }

        if (keys[1]){
            this.posX = this.posX + this.speed;
        }

        if (keys[3]){
            this.posX = this.posX - this.speed;
        }
    }

    public void update(Boolean[] keys){
        this.move(keys);
    }
}