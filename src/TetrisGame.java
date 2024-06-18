
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import spritelib.Point;
import spritelib.SingleSprite;

/**
 * Diese Klasse zeigt wird das gesamte Spiel zusammengebracht werden, durch Instanzen und Logik um das darzustellen
 *
 * @author Mohammad Alhajji
 * @version 1.0
 */

/**
 * Tetris von PApplet abgeleitet
 */
public class TetrisGame extends PApplet {

    private GameGrid gameGrid;



    private PImage block;

    SingleSprite spritetiles;

    private PImage bgImg;
    private Score score; // = new Score();


    /**
     * Hier wird die Größe des Bildschirms festgelegt
     */

    @Override
    public void settings() {
        setSize(406, 600);
    }

    /**
     * Hier wird alles in Setup erst ausgeführt,
     * bevor das eigentliche Spiel dargestellt wird.
     *
     * Es wird ein Bild tiles.png in block geladen.
     *
     * Es wird dann auch gamegrid, eine Instanz von der Klasse
     * GameGrid instanziert
     *
     */
    @Override
    public void setup() {
        super.setup();
        block = loadImage("tiles.png");
        gameGrid = new GameGrid(this, block);
        //bgImg= loadImage("background.png");
        //image(bgImg,0,0,406,600);
        drawBackground();
       // drawScore();
      this.score = new Score(); // initialisiert den Score
    }


    /**
     * Es wird den Hintergrund gezeichnet, die verschiedene Shapes die dargestellt wird
     * und wie die sich in dem Spiel verhalten
     */
    @Override
    public void draw() {

        drawBackground();
        gameGrid.update();
        drawCurrentShape();
        drawScore();





    }


    /**
     * Hier wird Tastaturfunktionalitäten angewendet und die Methode
     * KeyPressed von PApplet überschreiben.
     *
     * setDeltaX wird entweder auf 1 oder -1 um nach jeweils rechts oder links zu bewegen
     */

    @Override
    public void keyPressed() {
        super.keyPressed();
        if (keyCode == LEFT) {
            gameGrid.getCurrentShape().setDeltaX(-1);
            System.out.println("Left");
        } else if (keyCode == RIGHT) {
            gameGrid.getCurrentShape().setDeltaX(1);
            System.out.println("Right");
        } else if (keyCode == DOWN) {
            gameGrid.getCurrentShape().speedDown();
        }else if (keyCode == UP) {
            gameGrid.getCurrentShape().rotate();
        }


    }

    /**
     * Hier wird sichergestellt, nach dem Loslassen von dem Down-taste zurück auf normale Geschwindigkeit gestellt wird
     */

    @Override
    public void keyReleased() {
        super.keyReleased();
        if (keyCode == DOWN) {
            gameGrid.getCurrentShape().normalSpeed();
        }
    }

    /**
     * Es wird in die coords array geguckt, wo ein 1 steht, und dann wird ein Shape gezeichnet
     */
    private void drawCurrentShape(){
        int[][] shapeCoords=gameGrid.getCurrentShape().getCoords();
        for(int row=0;row< shapeCoords.length;row++){
            for (int col =0; col<shapeCoords[row].length;col++)
                if(shapeCoords[row][col]!=0)
                {int x= col* gameGrid.getCellSize() + gameGrid.getCurrentShape().getX()*gameGrid.getCellSize() ;
                    int y=row* gameGrid.getCellSize() +  gameGrid.getCurrentShape().getY()*gameGrid.getCellSize();
                     spritetiles= new SingleSprite(gameGrid.getCurrentShape().getBlock());
                    spritetiles.draw(this, new Point(x,y));
                }

        }
    }

    /**
     * Es wurde versucht, ein score zu zeichnen
     */

    public void drawScore(){
        textSize(20);
        fill(0);
        text("Score: " + score.getPoints(), width - 100,  40);



    }

    /**
     * Das sorgt dafür, dass ein Hintergrund gezeichnet wird.
     */
      private void drawBackground(){
          background(150, 149, 149);
          gameGrid.drawGrid();
  /**
   * Sorgt dafür dass, die Tiles bei Kollision gezeichnet wird
   */

          for(int row =0; row < gameGrid.getGrid().length;row++){
              for(int col=0;col < gameGrid.getGrid()[row].length; col++){
                  if(gameGrid.getGrid()[row][col] != 0 ){
                      SingleSprite subimagesprite = new SingleSprite(block.get((gameGrid.getGrid()[row][col]-1)*gameGrid.getCellSize(),0,gameGrid.getCellSize(),gameGrid.getCellSize()));
                      subimagesprite.draw(this, new Point(col* gameGrid.getCellSize(),row* gameGrid.getCellSize()));
                  }
              }
          }
      }


  public Score getScore() {
        return this.score;
  }
}
