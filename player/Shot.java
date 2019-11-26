package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
// import java.util.ArrayList;

public class Shot{

    private double x,y;
    private double alpha;
    private double speed;
    private static double shotSize = 7;
    private Rectangle rect;

    public Shot(double x, double y, double alpha) {
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.speed = 10;
        this.rect = newrect();
    }

    public void display(Graphics g){
        g.setColor(Color.BLACK);

        g.setColor(Color.WHITE);
        g.fillOval((int)(x-shotSize/2), (int)(y-shotSize/2), (int)shotSize, (int)shotSize);
    }

    public void update(){
        this.x = this.x + Math.cos(this.alpha)*this.speed;
        this.y = this.y + Math.sin(this.alpha)*this.speed;
        this.rect = newrect();
    }

    public Rectangle newrect(){
        return new Rectangle((int)(x-shotSize/2), (int)(y-shotSize/2), (int)shotSize, (int)shotSize);
    }

    public Rectangle getShape(){return rect;}
}