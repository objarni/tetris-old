package source;

import java.awt.Color;

public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;

    //constructs a blue block, because blue is the greatest color ever!
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    //gets the color of this block
    public Color getColor()
    {
        return color;
    }

    //gets the color of this block to newColor.
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    //gets the grid of this block, or null if this block is not contained in a grid
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }

	//gets the location of this block, or null if this block is not contained in a grid
    public Location getLocation()
    {
        return location;
    }

	//removes this block from its grid
	//precondition:  this block is contained in a grid
    public void removeSelfFromGrid()
    {
		grid.remove(location);
        grid = null;
        location = null;
    }

	//puts this block into location loc of grid gr
	//if there is another block at loc, it is removed
	//precondition:  (1) this block is not contained in a grid
	//               (2) loc is valid in gr
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
		Block old = gr.get(loc);
		if(old != null)
		{
			old.grid = null;
			old.location = null;
		}
		grid = gr;
		location = loc;
        gr.put(loc, this);
    }

	//moves this block to newLocation
	//if there is another block at newLocation, it is removed
	//precondition:  (1) this block is contained in a grid
	//               (2) newLocation is valid in the grid of this block
    public void moveTo(Location newLocation)
    {
		Location oldLocation = location;
		putSelfInGrid(grid, newLocation);
        grid.remove(oldLocation);
        grid.put(newLocation, this);

    }

	//returns a string with the location and color of this block
    public String toString()
    {
        return "source.Block[location=" + location + ",color=" + color + "]";
    }
}