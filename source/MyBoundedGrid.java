package source;

import java.util.ArrayList;

//A source.MyBoundedGrid is a rectangular grid with a finite number of rows and columns.
public class MyBoundedGrid<E>
{
	private Object[][] occupantArray;  // the array storing the grid elements

	//Constructs an empty source.MyBoundedGrid with the given dimensions.
	//(Precondition:  rows > 0 and cols > 0.)
	public MyBoundedGrid(int rows, int cols)
	{
		occupantArray = new Object[rows][cols];
	}

	//returns the number of rows
	public int getNumRows()
	{
		return occupantArray.length;
	}

	//returns the number of columns
	public int getNumCols()
	{
		return occupantArray[1].length;
	}

	//returns true if loc is valid in this grid, false otherwise
	//precondition:  loc is not null
	public boolean isValid(Location loc)
	{
		if(loc.getRow() < getNumRows() && loc.getCol() < getNumCols() && loc.getRow() >= 0 && loc.getCol() >=0)
		{
			return true;
		}
		else return false;
	}

	//returns the object at location loc (or null if the location is unoccupied)
	//precondition:  loc is valid in this grid
	public E get(Location loc)
	{
		for(int i=0; i< getNumRows(); i++)
		{
			for(int j=0; j < getNumCols(); j++)
			{
				if(i == loc.getRow() && j == loc.getCol())
				{
					return (E)occupantArray[i][j];
				}
			}
		}
		return null;

		//(You will need to promise the return value is of type E.)
	}

	//puts obj at location loc in this grid and returns the object previously at that location (or null if the
	//location is unoccupied)
	//precondition:  loc is valid in this grid
	public E put(Location loc, E obj)
	{
		Object former = get(loc);
		for(int i=0; i < getNumRows(); i++)
		{
			for(int j=0; j<getNumCols(); j++)
			{
				if(i == loc.getRow() && j == loc.getCol())
				{
					occupantArray[i][j] = obj;
				}
			}
		}
		return (E)former;
	}

	//removes the object at location loc from this grid and returns the object that was removed (or null if the
	//location is unoccupied
	//precondition:  loc is valid in this grid
	public E remove(Location loc)
	{
		for(int i=0; i< getNumRows(); i++)
		{
			for(int j=0; j< getNumCols(); j++)
			{
				if( i == loc.getRow() && j == loc.getCol())
				{
					Object former = occupantArray[i][j];
					occupantArray[i][j] = null;
					return (E)former;
				}
			}
		}
		return null;
	}

	//returns an array list of all occupied locations in this grid
	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> locations = new ArrayList<Location>();
		for(int i =0; i< getNumRows(); i++)
		{
			for(int j =0; j<getNumCols(); j++)
			{
				Location loc = new Location(i,j);
				if(get(loc) != null)
				{
					locations.add(loc);
				}
			}
		}
		return locations;

	}
}