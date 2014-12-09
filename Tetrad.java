import java.awt.Color;
public class Tetrad
{
	private static int Locs1 = 0;
	private static int Locs2 = 1;
	private static int Locs3 = 2;
	private static int Locs4 = 3;
	private static int Locs5 = 4;
	private static int Locs6 = 5;
	private static int Locs7 = 6;

	private Block[] blocks;
	private MyBoundedGrid<Block> theGrid;
	public Tetrad(MyBoundedGrid<Block> grid)
	{
		blocks = new Block[4];
		theGrid = grid;

		Location[] locs = new Location[4];

		Block p1 = new Block();
		Block p2 = new Block();
		Block p3 = new Block();
		Block p4 = new Block();

		blocks[0] = p1;
		blocks[1] = p2;
		blocks[2] = p3;
		blocks[3] = p4;

		int randomNum = getRandom();

		//locs1 = I
		if(randomNum== Locs1)
		{
			p1.setColor(Color.RED);
			p2.setColor(Color.RED);
			p3.setColor(Color.RED);
			p4.setColor(Color.RED);
			locs[0] = new Location(1, 4);
			locs[1] = new Location(0, 4);
			locs[2] = new Location(2, 4);
			locs[3] = new Location(3, 4);
		}
		//locs2 = T
		if(randomNum == Locs2)
		{
			p1.setColor(Color.BLUE);
			p2.setColor(Color.BLUE);
			p3.setColor(Color.BLUE);
			p4.setColor(Color.BLUE);
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 3);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(1, 4);
		}
		//locs3 = O
		if(randomNum == Locs3)
		{
			p1.setColor(Color.GREEN);
			p2.setColor(Color.GREEN);
			p3.setColor(Color.GREEN);
			p4.setColor(Color.GREEN);
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 5);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(1, 5);
		}
		//locs4 = L
		if(randomNum == Locs4)
		{
			p1.setColor(Color.YELLOW);
			p2.setColor(Color.YELLOW);
			p3.setColor(Color.YELLOW);
			p4.setColor(Color.YELLOW);
			locs[0] = new Location(2, 4);
			locs[1] = new Location(1, 4);
			locs[2] = new Location(0, 4);
			locs[3] = new Location(2, 5);
		}
		//locs5 = J
		if(randomNum == Locs5)
		{
			p1.setColor(Color.GRAY);
			p2.setColor(Color.GRAY);
			p3.setColor(Color.GRAY);
			p4.setColor(Color.GRAY);
			locs[0] = new Location(2, 5);
			locs[1] = new Location(1, 5);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(2, 4);
		}
		//locs6 = S
		if(randomNum == Locs6)
		{
			p1.setColor(Color.MAGENTA);
			p2.setColor(Color.MAGENTA);
			p3.setColor(Color.MAGENTA);
			p4.setColor(Color.MAGENTA);
			locs[0] = new Location(1, 4);
			locs[1] = new Location(0, 5);
			locs[2] = new Location(0, 4);
			locs[3] = new Location(1, 3);
		}
		//locs7 = Z
		if(randomNum == Locs7)
		{
			p1.setColor(Color.CYAN);
			p2.setColor(Color.CYAN);
			p3.setColor(Color.CYAN);
			p4.setColor(Color.CYAN);
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 3);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(1, 5);
		}
		addToLocations(grid, locs);

	}
	private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
	{
		for(int i = 0; i< blocks.length; i++)
		{
			blocks[i].putSelfInGrid(grid, locs[i]);
		}

	}
	private int getRandom()
	{
		int i = (int)(Math.random() * 7);
		return i;
	}
	private Location[] removeBlocks()
	{
		Location[] oldBlocks = new Location[blocks.length];
		for(int i =0; i< blocks.length; i++)
		{
			oldBlocks[i] = blocks[i].getLocation();
		}
		for(int i=0; i< blocks.length; i++)
		{
			blocks[i].removeSelfFromGrid();
		}
		return oldBlocks;
	}
	private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
	{
		for(int i= 0; i < locs.length; i++)
		{
			if(!grid.isValid(locs[i]) || grid.get(locs[i]) != null)
			{
				return false;
			}
		}
		return true;
	}
	public boolean isIn(Location loc, Block[] blocks)
	{
		for(int i =0; i< blocks.length; i++)
		{
			if(loc == blocks[i].getLocation())
			{
				return true;
			}
		}
		return false;
	}
	public boolean translate(int deltaRow, int deltaCol)
	{
		Location[] nextPosition = new Location[4];
		for (int i= 0; i< blocks.length; i++)
		{
			int newRow = blocks[i].getLocation().getRow() + deltaRow;
			int newCol = blocks[i].getLocation().getCol() + deltaCol;
			Location newLocation = new Location(newRow, newCol);
			nextPosition[i] = newLocation;
		}
		Location[] oldPosition = removeBlocks();
		if(areEmpty(theGrid, nextPosition))
		{
			addToLocations(theGrid, nextPosition);
			return true;
		}
		else
		{
			addToLocations(theGrid, oldPosition);
			return false;
		}
	}
	public boolean rotate()
	{
		Location[] nextPosition = new Location[4];
		for(int i=0; i< blocks.length; i++)
		{
			int newRow = blocks[0].getLocation().getRow() - blocks[0].getLocation().getCol() + blocks[i].getLocation().getCol();
			int newCol = blocks[0].getLocation().getCol() + blocks[0].getLocation().getRow() - blocks[i].getLocation().getRow();
			Location newLocation = new Location(newRow, newCol);
			nextPosition[i] = newLocation;
		}
		Location[] oldPosition = removeBlocks();
		if(areEmpty(theGrid, nextPosition))
		{
			addToLocations(theGrid, nextPosition);
			return true;
		}
		else
		{
			addToLocations(theGrid, oldPosition);
			return false;
		}
	}



}
