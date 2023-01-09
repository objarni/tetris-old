package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import source.Block;
import source.Location;

import java.awt.*;


public class TestBlock {
    Block block;

    @Before
    public void Before() {
        block = new Block();
    }

    @Test
    public void TestInitialColor() {
        assertEquals(block.getColor(), Color.BLUE);
    }

    @Test
    public void TestSettingAColor() {
        block.setColor(Color.RED);
        assertEquals(block.getColor(), Color.RED);
    }

    @Test
    public void TestStringRepresentation() {
        block.setColor(Color.YELLOW);
        block.moveTo(new Location(1, 2));
        assertEquals(
                "source.Block[location=null,color=java.awt.Color[r=255,g=255,b=0]]",
                block.toString());
    }
}
