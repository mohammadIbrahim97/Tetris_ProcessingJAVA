import processing.core.PImage;
/**
 * Eine Unterklasse von Shape
 */

public class ZShape extends  Shape{

    @Override
    public int getId() {
        return super.getId();
    }
    /**
     * Ein Konstructor
     *
     * @param block die Block Image
     * @param coords die Matrix der Shape
     * @param board die Game grid
     * @param color die richtige Farbe
     */
    public ZShape(PImage block, int[][] coords, GameGrid board, int color) {
        super(block, coords, board, color);
    }
}