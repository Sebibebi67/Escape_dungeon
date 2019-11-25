package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.awt.geom.Area;
import java.awt.*;

public class Map {

    private int sizeX;
    private int sizeY;
    private int cellSize;
    private char[][] tab;
    private int nbMap;

    private File file;
    private Area walls;

    private boolean finished = false;





    public Map(File file, int x, int y, int size, int nbMap){

        this.file = file;

        this.sizeX = x;
        this.sizeY = y;
        this.cellSize = size;

        this.nbMap = nbMap;

        this.readFile();

        this.makeWalls();
    }


    public void readFile(){
        tab = new char[sizeY/cellSize][sizeX/cellSize];

        try {
            //opening file for reading in Java
            FileInputStream fileIS = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileIS));
         
            //reading file content line by line
            String line = reader.readLine();
            int lineNb = 0;
            while(line != null){
                int charNb = 0;
                for (char c : line.toCharArray()) {
                    tab[lineNb][charNb] = c;
                    charNb++;
                }
                lineNb++;
                line = reader.readLine();
            }
                 
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeWalls(){
        walls = new Area();
        for (int i=0; i<sizeY/cellSize ; i++) {
            for (int j=0; j<sizeX/cellSize; j++){
                if (tab[i][j] == '*'){
                    walls.add(new Area(new Rectangle(j*cellSize, i*cellSize, cellSize, cellSize)));
                }
            }
        }
    }

    public void display(Graphics g){
        for (int i=0; i<sizeY/cellSize ; i++) {
            for (int j=0; j<sizeX/cellSize; j++){
                switch(tab[i][j]){
                    case '*': //Wall
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
                        break;
                    case '.': //Durt
                        g.setColor(Color.GRAY);
                        g.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
                        break;
                }
            }
        }
    }


    public Boolean wallCollision(Rectangle r){
        if (walls.intersects(r)){return true;}
        return false;
    }

    public void changeMap(){
        Random r = new Random();
        int nb = r.nextInt(nbMap) + 1;
        this.file =  new File("./Map/Map"+nb);
        this.readFile();
        this.makeWalls();
    }

    public void update(){
        finished = true;
    }

    public boolean isFinished(){
        return finished;
    }
}