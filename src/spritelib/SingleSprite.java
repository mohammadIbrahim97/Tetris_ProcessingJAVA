package spritelib;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Kai Hinkelmann, kai.hinkelmann@fh-kiel.de
 * The type Single sprite.
 */
public class SingleSprite extends Sprite
{
    private PImage image;

    /**
     * Instantiates a new Single sprite.
     *
     * @param image the image of the sprite. The size of this image will be used as the size of the superclass.
     */
    public SingleSprite( PImage image )
    {
        super( image.width, image.height );
        this.image = image;
    }

    @Override
    public void draw( PApplet applet, Point newPos )
    {
        super.draw( applet, newPos );
        if ( isVisible() )
            applet.image( image, newPos.getX(), newPos.getY() );
    }
}
