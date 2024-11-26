package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	
	@Test
	public void testNormalItem_DegradesQualityAndSellIn() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Normal Item", 10, 20));
		inn.oneDay();
		List<Item> items = inn.getItems();
		
		assertEquals(9, items.get(0).getSellIn());
		assertEquals(19, items.get(0).getQuality());
	}
	
	@Test
	public void testAgedBrie_QualityIncreasesAfterSellIn() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", -1, 48));
		inn.oneDay();
		List<Item> items = inn.getItems();
		
		assertEquals(-2, items.get(0).getSellIn());
		assertEquals(50, items.get(0).getQuality());
	}
	
	@Test
	public void testSulfuras_NoChangeInQualityAnSellIn() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		inn.oneDay();
		List<Item> items = inn.getItems();
		
		assertEquals(-1, items.get(0).getSellIn());
		assertEquals(80, items.get(0).getQuality());
	}
	
	@Test
	public void testBackstagePasses_IncreasesQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20));
        inn.oneDay();
        List<Item> items = inn.getItems();
        
        assertEquals(10, items.get(0).getSellIn());
        assertEquals(21, items.get(0).getQuality());
    }
	
	@Test
	public void testBackstagePasses_QualityZeroAfterConcert() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		inn.oneDay();
		List<Item> items = inn.getItems();
		
		assertEquals(-1, items.get(0).getSellIn());
        assertEquals(0, items.get(0).getQuality());
	}
	
	@Test
	public void testNormalItem_QualityNeverOver50 () {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Aged Brie", 5, 50));
	    inn.oneDay();
	    List<Item> items = inn.getItems();

	    assertEquals(50, items.get(0).getQuality());
	}
	
	@Test
	public void testNormalItem_QualityNeverNegative() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Normal Item", 5, 0));
		inn.oneDay();
		List<Item> items = inn.getItems();
		
		assertEquals(0, items.get(0).getQuality());
	}
	
	@Test
	public void testNormalItem_DegradationDoubleAfterSellIn() {
		GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Normal Item", -1, 10));
	    inn.oneDay();
	    List<Item> items = inn.getItems();

	    assertEquals(-2, items.get(0).getSellIn());
	    assertEquals(8, items.get(0).getQuality());
	}
	

	@Test
	public void testBackstagePasses_Increase2WhenSellIn10 () {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49));
	    inn.oneDay();
	    List<Item> items = inn.getItems();

	    assertEquals(9, items.get(0).getSellIn());
	    assertEquals(50, items.get(0).getQuality());
	}
	
	@Test
	public void testBackstagePasses_Increase3WhenSellIn5() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 47));
	    inn.oneDay();
	    List<Item> items = inn.getItems();

	    assertEquals(4, items.get(0).getSellIn());
	    assertEquals(50, items.get(0).getQuality());
	}
	
	@Test
	public void testOneDay_NoItems() {
		GildedRose inn = new GildedRose();
		inn.oneDay();
		
		assertTrue(inn.getItems().isEmpty());
	}
	
	@Test
	public void testOneDay_OneItem() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		List<Item> items = inn.getItems();
		assertEquals(9, items.get(0).getSellIn());
		assertEquals(19, items.get(0).getQuality());
	}
	
	@Test
	public void testOneDay_MultipleItems() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.setItem(new Item("Aged Brie", 2, 0));
		inn.oneDay();
		List<Item> items = inn.getItems();
		assertEquals(9, items.get(0).getSellIn());
		assertEquals(19, items.get(0).getQuality());
		assertEquals(1, items.get(1).getSellIn());
		assertEquals(1, items.get(1).getQuality());
	}

	@Test
	public void testBackstagePasses_QualityAtExactBoundaries() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 49));
	    inn.oneDay();
	    List<Item> items = inn.getItems();

	    assertEquals(0, items.get(0).getSellIn());
	    assertEquals(50, items.get(0).getQuality());
	}
}
