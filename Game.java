import java.io.File;
import java.awt.*;
import java.awt.image.*;

import display.*;

public class Game implements Runnable{


    private int sizeX = 1200;
    private int sizeY = 800;
    private int cellSize = 20;

    private SPanel panel;
    private Map map;
    private Graphics finalG = null;

    private File file = new File("./Map/Map0");
    private SFrame frame;

    public Game(){


        frame = new SFrame(sizeX, sizeY, cellSize);
        this.panel = frame.getPanel();
        this.finalG = panel.getGraphics();
        map = new Map(file, sizeX, sizeY, cellSize);
        this.display();        

    }

    public void display(){

        while (true){
            // frame.update();
            Toolkit.getDefaultToolkit().sync();

            Image image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            map.display(g);
            finalG.drawImage(image, 0, 0, sizeX, sizeY, null, null);
        }

    }


    @Override
    public void run(){}


}