package player;

import java.awt.*;
import java.util.ArrayList;

import main.*;

public class Player{

    private Boolean[] canGo = {false, false, false, false};

    private double posX;
    private double posY;
    private double alpha;

    private int size = 20 ;
    private double speed = 5;
    private int countDown;
    private int countDownMax =10;

    private Map map;

    private ArrayList<Shot> shots = new ArrayList<>();

    public Player(Map map){

        this.posX = 590;
        this.posY = 400;
        this.map = map;

        this.alpha = 0;

        this.countDown = this.countDownMax;


    }

    public void display(Graphics g){

        for (int i = 0; i<shots.size(); i++){
            shots.get(i).display(g);
        }
        g.setColor(Color.RED);
        g.fillRect((int)posX, (int)posY, size, size);
        
    }

    public void update(Boolean[] keys, boolean mouse){
        this.checkWalls();
        this.move(keys);
        if (this.countDown > 0){
            this.countDown--;
        }
        if (mouse && this.countDown == 0){
            this.shot();
            this.countDown = this.countDownMax;
        }
        for (int i = 0; i<shots.size(); i++){
            shots.get(i).update();
            if (map.wallCollision(shots.get(i).getShape())){
                shots.remove(i);
            }
        }
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
            shots = new ArrayList<>();
            map.changeMap();
            this.setPosY(780);
        }
    }

    public void setPosX(int x){this.posX = x;}
    public void setPosY(int y){this.posY = y;}
    public void setAlphaCanon(double alpha){this.alpha = alpha;}
    public void setAlphaCanon2(int xMouse, int yMouse){
        this.alpha = Math.atan2(yMouse - this.posY, xMouse - this.posX);
        // System.out.println(alpha);
    }

    public void shot(){
        Shot shot = new Shot(this.posX+size/2, this.posY+size/2, this.alpha);
        shots.add(shot);
    }
}