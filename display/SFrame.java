package display;

import javax.swing.JFrame;

public class SFrame extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = -2380861990343462037L;


    private int sizeX;
    private int sizeY;
    private int cellSize;

    private SPanel panel;

    public SFrame(int sizeX, int sizeY, int cellSize) {

        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cellSize = cellSize;

        this.setTitle("Escape Dungeon");
	    //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    this.setSize(sizeX, sizeY);
	    this.setResizable(false);
	    this.setUndecorated(true);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new SPanel();
        
        this.setContentPane(panel);

        this.setVisible(true);
    }


    public SPanel getPanel(){return panel;}




    
}