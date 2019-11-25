package main;

import java.io.File;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import display.*;
import player.*;

public class Game implements Runnable{


    private int sizeX = 1200;
    private int sizeY = 800;
    private int cellSize = 20;

    private SPanel panel;
    private Map map;
    private Graphics finalG = null;

    private File file = new File("./Map/Map0");
    private SFrame frame;

    private Player player;

    private volatile Boolean[] activKey = { false, false, false, false};
    private Boolean activMouse = false;
    private int xMouse = 0;
    private int yMouse = 0;

    private int nbMap = 1;

    public Game(){

        initFrame();
        
        init();
        
        new Thread(this).start();
        wait(100);
        this.display();      

    }

    public void initFrame(){
        frame = new SFrame(sizeX, sizeY, cellSize);
        frame.addKeyListener(new SKAdapter());
        frame.addMouseListener(new SMAdapter());
        frame.addMouseMotionListener(new SMAdapter());
    }

    public void init(){
        this.panel = frame.getPanel();
        this.finalG = panel.getGraphics();
        map = new Map(file, sizeX, sizeY, cellSize, nbMap);
        player = new Player(map);
    }

    public void display(){

        while (true){
            // frame.update();
            Toolkit.getDefaultToolkit().sync();

            Image image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            map.display(g);
            player.display(g);
            finalG.drawImage(image, 0, 0, sizeX, sizeY, null, null);
        }

    }

    public void wait(int time){
        try{Thread.sleep(time);}
        catch (InterruptedException e) {}
    }


    @Override
    public void run(){
        wait(100);
        while(true){
            map.update();
            player.update(activKey);
            wait(20);
        }
    }



    private class SKAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            switch(key){
            	case KeyEvent.VK_LEFT:
                    activKey[3]=true;
            		break;

            	case KeyEvent.VK_RIGHT:
            		activKey[1]=true;
            		break;

            	case KeyEvent.VK_UP:
            		activKey[0]=true;
            		break;

            	case KeyEvent.VK_DOWN:
            		activKey[2]=true;
                    break;

                case KeyEvent.VK_SPACE:
                    // map.changeMap();
                    // player.setPosX(590);
                    // player.setPosY(780);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            switch(key){
            	case KeyEvent.VK_LEFT:
            		activKey[3]=false;
            		break;

            	case KeyEvent.VK_RIGHT:
            		activKey[1]=false;
            		break;

            	case KeyEvent.VK_UP:
            		activKey[0]=false;
            		break;

            	case KeyEvent.VK_DOWN:
            		activKey[2]=false;
            		break;
            }
        }

    }

    private class SMAdapter extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            int button = e.getButton();
            if (button == MouseEvent.BUTTON1) {
                // player.shot();
                activMouse = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int button = e.getButton();
            if (button == MouseEvent.BUTTON1) {
                // player.shot();
                activMouse = false;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            xMouse = e.getX();
            yMouse = e.getY();
        }
    }
}