import processing.core.PApplet;
import processing.core.PImage;
import spritelib.Point;
import spritelib.SingleSprite;

/**
 * Ein Subklasse von PApplet
 */
public  class Shape extends PApplet {


    private PImage block;
    private int[][] coords;

    private int id;

    /**
     * Ein Getter
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    private GameGrid board;

    /**
     * Gibt an, wo im Grid neue Shapes spawnen soll.
     */
    private int x ,y;

    /**
     * Ein Getter
     *
     * @return x
     */
    public int getX() {return x;}

    /**Ein Getter
     *
     * @return y
     */
    public int getY( ) {
        return y ;
    }

    /**
     * die eingegebenen Werte sind in millisekunde
     */
    private int normalSpeed =600, speedDown =60, currentSpeed= normalSpeed;
    private long time=0;


    private int deltaX;
    private boolean collision;
    private int color;

    /**
     * Ein Getter
     *
     * @return colur
     */

    public int getColor() {
        return color;
    }

    /**
     * Ein Setter
     *
     *
     * @param deltaX  Verantwortlich für die Bewegung nach rechts oder links
     */
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    /**
     * Ein Konstructor
     *
     * @param block die Block Image
     * @param coords die Matrix der Shape
     * @param board die Game grid
     * @param color die richtige Farbe
     */

    public Shape(PImage block, int[][] coords, GameGrid board, int color) {
        this.block = block;
        this.coords = coords;
        this.board = board;
        this.color = color;

    x=3;
    y=0;
    }
    float frame = 1000.0f;

    /**
     *update ist hier dafür verantwortlich für die Änderungen, was zu den verschieden Shapes erfolgen können.
     */
    public void update (){

        /**
         *Das hier hilft dabei, die Geschwindigkeit der vertikalen Bewegung von der Shapes zu prüfen.
         * frameRate mit dem Wert 60 wird von PApplet gegeben, ein Grund warum Shape von PApplet abgeleitet ist.
         */
        time +=frame/ frameRate;

        /**
         * Color sichert dass, die richtige Farbe bei Kollision gezeichnet werden.
         */
        /**
         * Es wurde versucht, ein score zu zeichnen
         */
        if (collision){
           for (int row=0; row < coords.length; row++)
               for (int col = 0; col < coords[row].length;  col++)
                   if(coords[row][col] != 0)
                       board.getGrid()[y + row][x+col] = color;
           checkLine();
            board.getScore();
           board.setNextshape();
       }


        /**
         *Wenn die erste Bedingung gleich 10 ist, das heißt es hat die rechte Wand erreicht.
         *Wenn die zweite Bedingung gleich 0 ist, das heißt es hat die linke Wand erreicht
         */
        if( !(x+deltaX+ coords[0].length>10) && !(x+deltaX<0) )
       {x+=deltaX;}
        deltaX= 0;

        /**
        *
         * Hier wird alles bestimmt, was in dem vertikalen gültigen Bereich des Spiels erlaubt ist.
         * */

        if(!(y+1+ coords.length>20)){
            /**
             * Es wird hier nach Collision geprüft, indem es nachgeguckt wird, ob die nächte Cell leer ist oder nicht
             */
            for (int row=0; row < coords.length; row++)
                for (int col = 0; col < coords[row].length;  col++)
                    if(coords[row][col] != 0)
                        if(board.getGrid()[y +1+ row ][x+col] != 0)
                            collision = true;
            /**
             *Es wird geprüft, ob time größer ist als currentSpeed, dann wird der Shape nach unten gehen.
             */
            if(time> currentSpeed){
                y++;
                time=0;}
        }else{
            collision = true;
        }
    }

    /**
     * Ein Getter
     *
     * @return coords
     */
    public int[][] getCoords() {return coords;}

    /**
     * Ein Getter
     *
     * @return block
     */
    public PImage getBlock() {return block;}

    /**
     * Setzt currentspeed auf Speeddown
     */
    public void speedDown(){currentSpeed= speedDown;}

    /**
     * Setzt currentspeed auf normalSpeed
     */
    public void normalSpeed(){currentSpeed=normalSpeed;}

    /**
     *Die aktuellen Shape wird erst transponiert, und dann vertauscht
     */
    public void rotate(){
        /**
         * Wenn wir kollidieren, wollen wir nicht rotieren.
         */
    if(collision)
            return;

        int[][] rotatedMatrix;

        rotatedMatrix= getReverseMatrix(getTranspose(coords));

        /**
         * Hier wird geprüft, ob die rotierte Form außerhalb des Spielfelds ist.
         */

        if( x + rotatedMatrix[0].length > 10 || y + rotatedMatrix.length > 20)
            return;

        /**
         * Hier wird überprüft, ob die rotierte Form mit bereits platzierten Blöcken im Spielfeld kollidieren würde.
         */
        for(int row =0; row < rotatedMatrix.length; row++)
            for (int col=0; col< rotatedMatrix[0].length; col++){
                if(board.getGrid()[y+row][x+col] != 0)
                    return;
            }

        /**
         * Falls alle diese Bedingungen nicht wahr sind, erst dann wird die Rotation erfolgen
         */
        coords = rotatedMatrix;

    }

    /**
     * Height wird auf die unterste Position des Spielfelds gesetzt.
     *
     * In der Schleife wird nach oben iteriert, wobei i den aktuellen Zeilenindex bezeichnet.
     *
     * Count zählt wie viele Blöcke sind in der aktuellen Zeile, indem alle Spalten durchläuft wird um zu überprüfen, ob
     * die Position[i][j] ungleich NULL ist, dann wird count inkrementiert.
     *
     * Gleichzeitig wird die Werte des Blocks an der Position[i][j] in das entsprechende Feld
     * der untersten Zeile height des Spielfelds kopiert. Dadurch entsteht die Nachuntenverschiebung.
     *
     * Wenn count kleiner als spalten ist, wird die aktuelle Zeile übersprungen
     * und height um eins verringert.
     * Dadurch werden die nächsten Zeilen eine Position nach oben verschoben.
     */
    private  void checkLine(){
        int height = 0;
        try {
            height = board.getGrid().length-1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for ( int i=height; i>0; i--){
             int count =0;
             for (int j=0; j<board.getGrid()[0].length; j++){
                 if (board.getGrid()[i][j] != 0)
                     count++;
                 board.getGrid()[height][j]= board.getGrid()[i][j];
             }
             if (count < board.getGrid()[0].length)
                 height--;

         }

    }

    /**
     * Um die TransposeMatrix bekommen, ändern wir die Zeile in Spalten und umgekehrt.
     * Diese Methode ist private und wird nur über die Schnittstelle rotate() nach außen verwendet.
     *
     * @param matrix
     * @return newMatrix
     */
    private int[][] getTranspose(int [][] matrix){
        int [][] newMatrix = new int [matrix[0].length][matrix.length];
        for(int i =0; i < matrix.length; i++)
            for(int j= 0; j< matrix[0].length;j++)
                newMatrix[j][i]=matrix[i][j];
        return newMatrix;
    };


    /**
     * Diese Funktion gibt die umgekehrte Matrix zurück.
     * Läuft bis zur Mitte und vertauscht, die Zeilen mit der entsprechenden gegenüberliegenden Zeile.
     * Es wird eine temporäre Variable m verwendet, um die Werte der Zeilen zwischenzuspeichern
     *
     * @param matrix
     * @return matrix
     */

    private int[][] getReverseMatrix(int [][] matrix){
        int middle = matrix.length / 2 ;

        for (int i= 0; i < middle; i++){
            int[] m = matrix[i];
            matrix[i] = matrix[matrix.length-i-1];
            matrix[matrix.length-i-1]=m;
        }

        return matrix;
    };
    }




