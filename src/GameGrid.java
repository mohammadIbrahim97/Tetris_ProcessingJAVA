import processing.core.PApplet;
import processing.core.PImage;
import spritelib.Point;
import spritelib.SingleSprite;

import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;

public class GameGrid extends TetrisGame {

    private PApplet parent;
    private int[][]grid;
    private int rows=20;

    private int columns=10;
    private int cellSize=30;

    /**
     *
     * @return cellsize
     */

    public int getCellSize() {
        return cellSize;
    }

    /**
     * Mit dem Array die verschiedene Shape-Instanzen zu speichern.
     */
    private Shape[] shapes = new Shape[7];


    private PImage block;

    private Shape currentShape;

    private boolean gameOver = false;

    private int score;




    /**
     * Damit wir die Hintergrundsraster gezeichnet
     */
    public void drawGrid(){
        parent.strokeWeight(2);


        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                int x = i * cellSize;
                int y = j * cellSize;
                parent.rect(x, y, cellSize, cellSize);
            }
        }

    };



    public GameGrid(PApplet parent, PImage parameterblock) {
        this.block = parameterblock;
        this.parent = parent;
        this.grid = new int[rows][columns];

/**
 * Die Subklassen von Shape wird eine Instanz von Shape zugewiesen. // "Upcasting"
 *
 * @param block wobei es das Bild je nach Position abgeschnitten wird.
 * @param coords wird ein zweidimensionaler Array mit richtigen Positionen für das Hinzufügen des abgeschnittenen Bildes festgelegt
 * @param board Das zeigt auf welchem Game grid, die verschiedene shapes dargestellt werden.
 * @param color
 *
 */
        shapes[0]= new IShape(block.get(0,0,cellSize,cellSize),
                new int[][]{
                        {1,1,1,1} //IShape
                },this,1);

        shapes[1]= new ZShape(block.get(cellSize,0,cellSize,cellSize),
                new int[][]{
                        {1,1,0},
                        {0,1,1} //ZShape
                },this,2);

        shapes[2]= new SShape(block.get(cellSize*2,0,cellSize,cellSize),
                new int[][]{
                        {0,1,1 },
                        {1,1,0} //S-Shape
                },this,3);

        shapes[3]= new JShape(block.get(cellSize*3,0,cellSize,cellSize),
                new int[][]{
                        {1,1,1},
                        {0,0,1} //J-Shape
                },this,4);
        shapes[4]= new LShape(block.get(cellSize*4,0,cellSize,cellSize),
                new int[][]{
                        {1,1,1},
                        {1,0,0} //L-Shape
                },this,5);

        shapes[5]= new TShape(block.get(cellSize*5,0,cellSize,cellSize),
                new int[][]{
                        {1,1,1},
                        {0,1,0} //T-Shape
                },this,6);
        shapes[6]= new OShape(block.get(cellSize*6,0,cellSize,cellSize),
                new int[][]{
                        {1,1},
                        {1,1} // O-Shape
                },this,7);



       setNextshape();

        }

        public void update(){

        if(gameOver)
            return;

        currentShape.update();
        }

    /**
     *
     * @return grid
     */
    public int[][] getGrid() {
        return grid;
    }


    public Shape getCurrentShape() {
        return currentShape;
    }

    /**
     * In setNextshape wird ein neue
     */
    public void setNextshape() {
        int index = (int) (Math.random() * shapes.length);
        Shape nextShape = new Shape(shapes[index].getBlock(), shapes[index].getCoords(), this, shapes[index].getColor());

        currentShape= nextShape;

        /**
         * Eine for-Schleife, das überprüft ob, es die Shapes ganz oben erreicht haben
         */

        for (int row=0; row < currentShape.getCoords().length; row++)
            for (int col = 0; col < currentShape.getCoords()[row].length;  col++)
                if(currentShape.getCoords()[row][col] != 0)
                     if (grid[row][col+3] !=0)
                         gameOver = true;

    }

   public int addScore(){
       score++;

       return score;

   }
}
