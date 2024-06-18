package spritelib;

import java.util.ArrayList;

/**
 * The type Sequence is a named list of framenumbers and an optional name of the next sequence
 * which is used by using classes when the sequence has reached it's last number
 *
 */
public class Sequence
{
    private ArrayList<Integer> frames = new ArrayList<>();
    private String name;
    private String nextSequenceName = null;

    /**
     * Instantiates a new Sequence.
     *
     * @param name the name
     */
    public Sequence( String name )
    {
        this.name = name;
    }

    /**
     * Instantiates a new Sequence.
     *
     * @param name             the name
     * @param nextSequenceName the next sequence name
     */
    public Sequence( String name, String nextSequenceName )
    {
        this( name );
        this.nextSequenceName = nextSequenceName;
    }

    /**
     * Instantiates a new Sequence.
     *
     * @param name  the name
     * @param elems the elems
     */
    public Sequence( String name, int... elems )
    {
        this( name );
        for ( int i = 0; i < elems.length; i++ )
            frames.add( i, elems[i] );
    }

    /**
     * Instantiates a new Sequence.
     *
     * @param name             the name
     * @param nextSequenceName the next sequence name
     * @param elems            the elems
     */
    public Sequence( String name, String nextSequenceName, int... elems )
    {
        this( name, nextSequenceName );
        for ( int i = 0; i < elems.length; i++ )
            frames.add( i, elems[i] );
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets next sequence name.
     *
     * @return the next sequence name
     */
    public String getNextSequenceName()
    {
        return nextSequenceName;
    }

    /**
     * Sets next sequence name.
     *
     * @param nextSequenceName the next sequence name
     */
    public void setNextSequenceName( String nextSequenceName )
    {
        this.nextSequenceName = nextSequenceName;
    }

    /**
     * Add frame.
     * Adds a single framenumber to the sequence
     *
     * @param frame the frame
     */
    public void addFrame( int frame )
    {
        frames.add( frame );
    }

    /**
     * Add range.
     * Adds a range of framenumbers to the sequence
     *
     * @param from the from
     * @param to   the to
     */
    public void addRange( int from, int to )
    {
        for ( int i = from; i <= to; i++ )
            addFrame( i );
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength()
    {
        return frames.size();
    }

    /**
     * Gets frame.
     *
     * @param i the
     * @return the frame at position i
     */
    public int getFrame( int i )
    {
        return frames.get( i );
    }
}
