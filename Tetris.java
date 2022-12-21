import source.ArrowListener;

public class Tetris implements ArrowListener
{
	//need to fix level; show next tetrad
	MyBoundedGrid<Block> blocks;
	BlockDisplay display;
	Tetrad activeTetrad;
	int points;
	int level;
	int speed;
	int rowsCompletedSet;
	int totalRowsCompleted;
	public Tetris()
	{
		blocks = new MyBoundedGrid<Block>(30,20);
		display = new BlockDisplay(blocks);
		display.setArrowListener(this);
		points =0;
		totalRowsCompleted = 0;
		level = 1;
		speed = 1000;
		rowsCompletedSet = 0;
		activeTetrad = new Tetrad(blocks);
	}
	public static void main(String[] args)
	{
		Tetris game = new Tetris();
		game.display.setTitle("Tetris-- Level: " + 1 + "  Score: " + 0);
		game.display.showBlocks();
		game.play();
	}
	public void play()
	{
		boolean lost = false;
		try
		{
			while(lost != true)
			{
				boolean next = activeTetrad.translate(1,0);
				if(next == false)
				{
					clearCompletedRows();
					clearCompletedRows();
					clearCompletedRows();
					rowsCompletedSet =0;
					if(totalRowsCompleted == 10)
					{
						level++;
						speed -= 200;
						totalRowsCompleted = 0;
					}
					display.setTitle("Tetris-- Level: " + level + "  Score: " + points);
					activeTetrad = new Tetrad(blocks);
					if(activeTetrad.translate(1,0) == false)
					{
						lost = true;
						display.setArrowListener(null);
						System.out.println("You lost!! How shameful...");
					}
				}
				display.showBlocks();
				Thread.sleep(speed);
			}
		}
		catch(InterruptedException e)
		{
		}
	}
	private int getLevel()
	{
		return level;
	}
	private int getPoints()
	{
		return points;
	}
	private void clearCompletedRows()
	{
		for(int i=blocks.getNumRows(); i>-1 ; i--)
		{
			if(isCompletedRow(i))
			{
				clearRow(i);
				rowsCompletedSet++;
				totalRowsCompleted++;
				if(rowsCompletedSet == 1)
				{
					points = points + (level* 40);
				}
				if(rowsCompletedSet == 2)
				{
					points = points + (level *60);
				}
				if(rowsCompletedSet == 3)
				{
					points = points + (level * 200);
				}
				if(rowsCompletedSet == 4)
				{
					points = points + (level * 1000);
				}
			}
		}
	}
	private boolean isCompletedRow(int row)
	{
		for(int i=0; i< blocks.getNumCols(); i++)
		{
			Location loc = new Location(row, i);
			if( blocks.get(loc) == null)
			{
				return false;
			}
		}
		return true;
	}
	private void clearRow(int row)
	{
		for(int i =0; i< blocks.getNumCols(); i++)
		{
			Location loc = new Location(row, i);
			if(blocks.get(loc) != null)
			{
				blocks.get(loc).removeSelfFromGrid();
			}
		}
		for(int i = row-1; i> -1; i--)
		{
			for(int j=0; j< blocks.getNumCols(); j++)
			{
				Location loc = new Location(i, j);
				Location next = new Location(i+1, j);
				if(blocks.get(loc) != null)
				{
					blocks.get(loc).moveTo(next);
				}
			}
		}

	}
	public void upPressed()
	{
		activeTetrad.rotate();
		display.showBlocks();
	}
	public void downPressed()
	{
		activeTetrad.translate(1,0);
		display.showBlocks();
	}
	public void leftPressed()
	{
		activeTetrad.translate(0,-1);
		display.showBlocks();
	}
	public void rightPressed()
	{
		activeTetrad.translate(0,1);
		display.showBlocks();
	}
	public void spacePressed()
	{
		speed -= 15;
		activeTetrad.translate(1, 0);
		display.showBlocks();
	}
}
