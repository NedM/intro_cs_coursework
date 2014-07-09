package test;

import java.util.*;
import junit.framework.Assert;
import org.junit.Test;
import shapePackage.*;

/**
 * 
 * Class for testing Shape package
 * @author nedmurp, JVen
 *
 */

/*
 * 
 * We don't want to see unused variable warnings for 2 reasons:
 * 
 * (1) our path variables below are used depending on who is
 * running the tests, so we will necessarily have unused path strings
 * floating around
 * (2) some of our test cases involve throwing exceptions while initializing
 * a variable, even if the variable is not ever used again
 * 
 * -JVen
 * 
 */

@SuppressWarnings("unused")
public class TestShapes 
{
	
	private final String nedPath = "C:\\Users\\nedmurp\\Desktop\\Postscript\\";
	private final String juliaPath = "/Users/juliaboortz/Documents/6.005/Project1Files/";
	private final String jvenPath = "C:\\Users\\Justin\\Documents\\My Dropbox\\6.005\\Project1\\Tests\\";
	
    ////////////////////////////////////////////////////////////////////////
	//																	  //
	// change the following lines to designate how to output test results //
	//																	  //
	
	private final boolean printToConsole = false;
	private final boolean outputToFile = true;
	//private final String path = jvenPath;
	//private final String path = juliaPath;
	private final String path = nedPath;
	
	//																	  //
	// NOTE: We compare against files known to generate the shapes as     //
	//      expected. Those file should be stored below at the path above //
	////////////////////////////////////////////////////////////////////////
	
	private ArrayList<Shape> GenerateListOfShapes()
	{
		return GenerateListOfShapes(5);
	}
	
	private ArrayList<Shape> GenerateListOfShapes(int numberOfShapesInList)
	{
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for(int i = 0; i < numberOfShapesInList; i++)
		{
			shapes.add(new Circle(18));
		}
		
		return shapes;
	}
	
	private ArrayList<Shape> GenerateListOfDiffShapes(int numberOfShapesInList)
	{
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for(int i = 1; i <= numberOfShapesInList; i++)
		{
			shapes.add(new Rectangle(18*i, 9*i));
		}
		
		return shapes;
	}
	
	private ArrayList<Shape> GenerateListOfDiverseShapes()
	{
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		
		shapes.add(new Sierpinsky(3, 50));
		shapes.add(new Layered(this.GenerateListOfDiffShapes(4)));
		shapes.add(new Mountain(30, 50, 5));
		shapes.add(new Spacer(20, 20));
		shapes.add(new Star(36, 4));
		shapes.add(new Circle(25));		
		shapes.add(new Vertical(this.GenerateListOfDiffShapes(4)));
		shapes.add(new Polygon(9, 25));
		shapes.add(new Scaled((new Triangle(35)), 1, 2));
		shapes.add(new Horizontal(this.GenerateListOfDiffShapes(6)));
		shapes.add(new Rotated((new Rectangle(20, 40)), 90));
		
		return shapes;
	}
	
	/**
	 * 
	 * Tests Circle implementation
	 * 
	 * @author nedmurp
	 * 
	 */	
	@Test
	public void TestCircle()
	{		
		Circle testCircle = new Circle(72);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		ITranslateShape tpsLineWidth = new ToPostScript(10.0);
		String postScriptString = "";
		
		postScriptString = testCircle.translate(tps, 144, 144) + testCircle.translate(tpsFill, 144, 144*3) + testCircle.translate(tpsLineWidth, 144*3, 144);
		if ( printToConsole )
			System.out.println(postScriptString);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestCircle.ps");		
			Assert.assertTrue(Draw.CompareFiles(path + "TestCircle.ps", path + "GoodCircle.ps"));
		}
	}
	
	/**
	 * Tests the Star implementation
	 * @author nedmurp
	 */
	@Test
	public void TestStar()
	{
		Star testStar = new Star(144, 5);
		Star testStar2 = new Star(144, 9);
		Star testStar3 = new Star(144*2, 30);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		ITranslateShape tpsLineWidth = new ToPostScript(10.0);
		
		String psString = "";		
		psString = testStar.translate(tpsLineWidth, 144*2, 144) + testStar2.translate(tpsFill, 144, 144*3) + testStar3.translate(tps, 144*2, 144);
		
		if(printToConsole)
			System.out.println(psString);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(psString, path + "TestStar.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestStar.ps", path + "GoodStar.ps"));
		}
	}
	
	/**
	 * 
	 * Tests spacer implementation
	 * 
	 * @author jboortz
	 */
	
	@Test
	public void TestSpacer(){
		Spacer testSpacer = new Spacer(10, 10);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		String postScriptString = "";
		
		postScriptString = testSpacer.translate(tps, 20, 10) + testSpacer.translate(tpsFill, 100, 100);
		if (printToConsole)
			System.out.println(postScriptString);
		if (outputToFile)
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestSpacer.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestSpacer.ps", path + "GoodSpacer.ps"));
		}
	}
	
	/**
	 * 
	 * Tests rectangle implementation
	 * 
	 * @author jboortz
	 */
	@Test
	public void TestRectangle(){
		Rectangle testRectangle = new Rectangle(144, 72);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		String postScriptString = "";
		
		postScriptString = testRectangle.translate(tps, 100, 600) + testRectangle.translate(tpsFill, 300, 600);
		if (printToConsole)
			System.out.println(postScriptString);
		if (outputToFile)
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestRectangle.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestRectangle.ps", path + "GoodRectangle.ps"));
		}
	}
	
	/**
	 * 
	 * Tests rectangle implementation when rectangle will go into negative coordinates
	 * No error generated, but only the part within the positive coordinates is displayed.
	 * 
	 * @author jboortz
	 */
	@Test
	public void TestRectangleOut(){
		Rectangle testRectangle = new Rectangle(150, 75);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		String postScriptString = "";
		
		postScriptString = testRectangle.translate(tps, 100, 600) + testRectangle.translate(tpsFill, 150, 550);
		if (printToConsole)
			System.out.println(postScriptString);
		if (outputToFile)
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestRectangleOut2.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestRectangleOut2.ps", path + "GoodRectangleOut2.ps"));
		}
	}
	
	/**
	 * 
	 * Tests square implementation
	 * 
	 * @author jboortz
	 */
	@Test
	public void TestSquare(){
		Square testSquare = new Square(100);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		String postScriptString = "";
		
		postScriptString = testSquare.translate(tps, 100, 600) + testSquare.translate(tpsFill, 300, 550);
		if (printToConsole)
			System.out.println(postScriptString);
		if (outputToFile)
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestSquare.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestSquare.ps", path + "GoodSquare.ps"));
		}
	}
	
	/**
	 * 
	 * Tests Mountain implementation
	 * 
	 * @author jboortz
	 */
	@Test
	public void TestMountain(){
		Mountain testMountain = new Mountain(100, 300, 5);
		Rectangle boundingBox = new Rectangle(100, 300);
		ITranslateShape tpsThin = new ToPostScript(0.1);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		String postScriptString = "";
		
		postScriptString = testMountain.translate(tps, 300, 300) + boundingBox.translate(tpsThin, 300, 300) +
							testMountain.translate(tpsFill, 300, 600) + boundingBox.translate(tpsThin, 300, 600);
		if (printToConsole)
			System.out.println(postScriptString);
		if (outputToFile)
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestMountain.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestMountain.ps", path + "GoodMountain.ps"));
		}
	}
	
	/**
	 * 
	 * Tests Vertical implementation
	 * 
	 * @author JVen, NedMurp
	 * 
	 */
	
	@Test
	public void TestVertical()
	{
		ArrayList<Shape> listOfShapes = GenerateListOfShapes();
		ArrayList<Shape> listOfDifferentShapes = this.GenerateListOfDiffShapes(7);
		ArrayList<Shape> listOfDiverseShapes = this.GenerateListOfDiverseShapes();
		Vertical testVert = new Vertical(listOfShapes);
		Vertical testVert2 = new Vertical(listOfDifferentShapes);
		Vertical testVert3 = new Vertical(listOfDiverseShapes);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		ITranslateShape tpsLineWidth = new ToPostScript(5);
		String postScriptString = "";
		
		postScriptString = testVert.translate(tps, 100, 400) + testVert2.translate(tpsFill, 300, 400) + testVert3.translate(tpsLineWidth, 500, 400);
		if ( printToConsole )
			System.out.println(postScriptString);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestVertical.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestVertical.ps", path + "GoodVertical.ps"));
		}
	}
	
	/**
	 * 
	 * Tests Horizontal implementation
	 * 
	 * @author JVen, NedMurp
	 * 
	 */
	
	@Test
	public void TestHorizontal()
	{
		ArrayList<Shape> listOfShapes = GenerateListOfShapes();
		ArrayList<Shape> listOfDifferentShapes = this.GenerateListOfDiffShapes(7);
		ArrayList<Shape> listOfDiverseShapes = this.GenerateListOfDiverseShapes();
		Horizontal testHoriz = new Horizontal(listOfShapes);
		Horizontal testHoriz2 = new Horizontal(listOfDifferentShapes);
		Horizontal testHoriz3 = new Horizontal(listOfDiverseShapes);
		ITranslateShape tps = new ToPostScript();
		ITranslateShape tpsFill = new ToPostScript(true);
		ITranslateShape tpsLineWidth = new ToPostScript(5);
		String postScriptString = "";
		
		postScriptString = testHoriz.translate(tps, 300, 100) + testHoriz2.translate(tpsFill, 300, 300) + testHoriz3.translate(tpsLineWidth, 300, 500);
		if ( printToConsole )
			System.out.println(postScriptString);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestHorizontal.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestHorizontal.ps", path + "GoodHorizontal.ps"));
		}
	}

	/**
	 * 
	 * Tests layered implementation
	 * 
	 * @author jboortz
	 */
	@Test
	public void TestLayered(){
		ArrayList<Shape> listOfShapes = this.GenerateListOfDiffShapes(7);
		Layered testLayered = new Layered(listOfShapes);
		ITranslateShape tps = new ToPostScript();
		String postScriptString = "";
		
		postScriptString = testLayered.translate(tps, 300, 300);
		if ( printToConsole )
			System.out.println(postScriptString);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(postScriptString, path + "TestLayered.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestLayered.ps", path + "GoodLayered.ps"));
		}
	}
	
	/**
	 * 
	 * Tests Polygon implementation
	 * 
	 * @author JVen
	 * 
	 */
	
	@Test
	public void TestPolygon()
	{
		ITranslateShape ps = new ToPostScript();
		Polygon myShape = new Polygon(7, 60);
//		Polygon box = new Polygon(4, myShape.get_width());
		Polygon myShape2 = new Polygon(5, 120);
		String ans = "";
		ans = ans.concat(myShape.translate(ps, 300, 600) + "\n");
		//ans = ans.concat(ps.translate(box) + "\n");
		ans = ans.concat(myShape2.translate(ps, 300, 600) + "\n");
		
		if ( printToConsole )
			System.out.println(ans);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(ans, path + "TestPolygon.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestPolygon.ps", path + "GoodPolygon.ps"));
		}
	}
	
	/**
	 * 
	 * Tests Rotated implementation
	 * 
	 * @author JVen
	 * 
	 */
	
	@Test
	public void TestRotated()
	{
		ITranslateShape ps = new ToPostScript();
		
		double x = 200;
		double y = 400;
		
		Triangle t = new Triangle(100);
		Circle c = new Circle(2);
		Rotated rotated1 = new Rotated(t, 90);
		Circle c1 = new Circle(2);
		Rotated rotated2 = new Rotated(t, 180);
		Circle c2 = new Circle(2);
		Rotated rotated3 = new Rotated(t, 270);
		Circle c3 = new Circle(2);
		String ans = "";
		ans = ans.concat(t.translate(ps, x, y) + "\n");
		ans = ans.concat(c.translate(ps, x, y) + "\n");
		ans = ans.concat(rotated1.translate(ps, x, y) + "\n");
		ans = ans.concat(c1.translate(ps, x, y) + "\n");
		ans = ans.concat(rotated2.translate(ps, x, y) + "\n");
		ans = ans.concat(c2.translate(ps, x, y) + "\n");
		ans = ans.concat(rotated3.translate(ps, x, y) + "\n");
		ans = ans.concat(c3.translate(ps, x, y) + "\n");
		
		if ( printToConsole )
			System.out.println(ans);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(ans, path + "TestRotated.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestRotated.ps", path + "GoodRotated.ps"));
		}
	}
	
	/**
	 * 
	 * Tests Scaled implementation
	 * 
	 * @author JVen
	 * 
	 */
	
	@Test
	public void TestScaled()
	{
		ITranslateShape ps = new ToPostScript();
		
		double x1 = 300;
		double y1 = 200;
		
		Circle c = new Circle(100);
		Scaled s1 = new Scaled(c, 0.75, 3);
		
		double x2 = 300;
		double y2 = 700;
		
		Polygon p = new Polygon(7, 30);
		ArrayList<Shape> l1 = new ArrayList<Shape>();
		for ( int i = 0 ; i <= 3 ; i++ )
			l1.add(p);
		Horizontal h = new Horizontal(l1);
		ArrayList<Shape> l2 = new ArrayList<Shape>();
		for ( int i = 0 ; i <= 4 ; i++ )
			l2.add(h);
		Vertical v = new Vertical(l2);
		Scaled s2 = new Scaled(v, 0.2, 2);
		
		String ans = "";
		ans = ans.concat(c.translate(ps, x1, y1) + "\n");
		ans = ans.concat(s1.translate(ps, x1 + 100, y1) + "\n");
		ans = ans.concat(v.translate(ps, x2, y2) + "\n");
		ans = ans.concat(s2.translate(ps, x2 + 200, y2) + "\n");
		
		if ( printToConsole )
			System.out.println(ans);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(ans, path + "TestScaled.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestScaled.ps", path + "GoodScaled.ps"));
		}
	}
	
	/**
	 * 
	 * Tests Sierpinsky triangle implementation
	 * 
	 * @author JVen
	 * 
	 */
	
	@Test
	public void TestSierpinsky()
	{		
		ITranslateShape ps = new ToPostScript();
		Sierpinsky s = new Sierpinsky(8, 500);
		String ans = "";
		ans = ans.concat(s.translate(ps, 300, 400) + "\n");
		
		if ( printToConsole )
			System.out.println(ans);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(ans, path + "TestSierpinsky.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestSierpinsky.ps", path + "GoodSierpinsky.ps"));
		}
	}
	
	/**
	 * 
	 * Creates snowmen and does various operations on them!
	 * 
	 * @author JVen
	 * 
	 */
	
	@Test
	public void TestSnowmen()
	{
		ITranslateShape ps = new ToPostScript();
		ITranslateShape psFill = new ToPostScript(true);
		ITranslateShape psLineWidth = new ToPostScript(11);
		
		Circle head = new Circle(10);
		Circle body1 = new Circle(15);
		Scaled body2 = new Scaled(head, 2, 2);
		Sierpinsky hat = new Sierpinsky(2, 10);
		
		ArrayList<Shape> bodyparts = new ArrayList<Shape>();
		
		bodyparts.add(body2);
		bodyparts.add(body1);
		bodyparts.add(head);
		bodyparts.add(hat);
		Shape snowman = new Vertical(bodyparts);
		
		Scaled bigSnowman = new Scaled(snowman, 3, 3);
		Rotated toppledSnowman = new Rotated(snowman, 270);
		
		String ans = "";
		ans = ans.concat(snowman.translate(ps, 300, 100));
		ans = ans.concat(bigSnowman.translate(psLineWidth, 300, 400));
		ans = ans.concat(toppledSnowman.translate(psFill, 300, 600));
		
		if ( printToConsole )
			System.out.println(ans);
		if ( outputToFile )
		{
			Draw.CreateFile_PostScript(ans, path + "TestSnowmen.ps");
			Assert.assertTrue(Draw.CompareFiles(path + "TestSnowmen.ps", path + "GoodSnowmen.ps"));
		}
	}
		
		/**
		 * 
		 * Creates a CompoundShape and attempts to modify listOfShapes
		 * The resulting postscript file should not be modified
		 * 
		 * @author JVen
		 * 
		 */
		
		@Test
		public void TestListOfShapesModify()
		{
			ITranslateShape ps = new ToPostScript();
			
			Circle c = new Circle(100);
			Triangle t = new Triangle(150);
			Sierpinsky s = new Sierpinsky(3, 110);
			
			ArrayList<Shape> listOfShapes1 = new ArrayList<Shape>();
			listOfShapes1.add(t);
			listOfShapes1.add(s);
			Horizontal h = new Horizontal(listOfShapes1);
			
			String ans1 = h.translate(ps, 200, 200);
	
			listOfShapes1.set(0, c);			
			
			String ans2 = h.translate(ps, 200, 200);
			Assert.assertTrue(ans1.equals(ans2));
			
			
			ArrayList<Shape> listOfShapes2 = new ArrayList<Shape>();
			listOfShapes2.add(h);
			listOfShapes2.add(c);
			Vertical v = new Vertical(listOfShapes2);
			
			String ans3 = v.translate(ps, 300, 500);
			
			listOfShapes1.set(0, null);
			listOfShapes2.set(0, null);
			
			String ans4 = v.translate(ps, 300, 500);
			Assert.assertTrue(ans3.equals(ans4));
			
			if ( printToConsole )
				System.out.println(ans2 + ans4);
			if ( outputToFile )
			{
				Draw.CreateFile_PostScript(ans2 + ans4, path + "TestModify.ps");
				Assert.assertTrue(Draw.CompareFiles(path + "TestModify.ps", path + "GoodModify.ps"));
			}
		}
		
		/**
		 * 
		 * Tests the construction of a postscript translator with fill
		 * 
		 * @author JVen
		 * 
		 */
		
		@Test
		public void TestFill()
		{
			ITranslateShape psStroke = new ToPostScript(false, 1.5);
			ITranslateShape psFill = new ToPostScript(true, 1.0);
			
			Sierpinsky s = new Sierpinsky(5, 300);
			
			String ans1 = s.translate(psStroke, 300, 200);
			String ans2 = s.translate(psFill, 300, 500);
			
			Assert.assertFalse(ans1.equals(ans2)); // these should have different postscript code
			
			if ( printToConsole )
				System.out.println(ans1 + ans2);
			if ( outputToFile )
			{
				Draw.CreateFile_PostScript(ans1 + ans2, path + "TestFill.ps");
				Assert.assertTrue(Draw.CompareFiles(path + "TestFill.ps", path + "GoodFill.ps"));
			}
		}
		
		/**
		 * 
		 * Tests the construction of a postscript translator with arbitrary linewidth
		 * 
		 * @author JVen
		 * 
		 */
		
		@Test
		public void TestLinewidth()
		{
			ITranslateShape psThin = new ToPostScript(false, 0.1);
			ITranslateShape psNormal = new ToPostScript(false);
			ITranslateShape psThick = new ToPostScript(false, 6);
			ITranslateShape psFill = new ToPostScript(true);
			
			double radius = 20;
			double scale = 5;
			
			Circle c1 = new Circle(radius);
			Scaled c2 = new Scaled(c1, scale, scale);
			
			String ans =
				c1.translate(psThin, radius, radius) +
				c1.translate(psNormal, radius, 3*radius) +
				c1.translate(psThick, radius, 5*radius) +
				c1.translate(psFill, radius, 7*radius) +
				c2.translate(psThin, (scale + 2)*radius, scale*radius) +
				c2.translate(psNormal, (scale + 2)*radius, 3*(scale*radius)) +
				c2.translate(psThick, (scale + 2)*radius, 5*(scale*radius)) +
				c2.translate(psFill, (scale + 2)*radius, 7*(scale*radius));
			
			if ( printToConsole )
				System.out.println(ans);
			if ( outputToFile )
			{
				Draw.CreateFile_PostScript(ans, path + "TestLinewidth.ps");
				Assert.assertTrue(Draw.CompareFiles(path + "TestLinewidth.ps", path + "GoodLinewidth.ps"));
			}
		}
	
		/**
		 * 
		 * Tests the construction of a postscript translator with fill
		 * using compound shapes
		 * 
		 * @author JVen
		 * 
		 */
		
		@Test
		public void TestCompoundFill()
		{
			int x = 300;
			int y = 500;
			
			ITranslateShape psFill = new ToPostScript(true, 1.0);
			
			Sierpinsky s1 = new Sierpinsky(5, 1200);
			
			// go crazy!
			Scaled s2 = new Scaled(s1, 0.1, 0.25);
			Rotated s3 = new Rotated(s2, 180);
			
			ArrayList<Shape> l = new ArrayList<Shape>();
			l.add(s3);
			l.add(s2);
			Vertical s4 = new Vertical(l);
			Rotated s5 = new Rotated(s4, 270);
			
			String ans = s5.translate(psFill, x, y);
			
			if ( printToConsole )
				System.out.println(ans);
			if ( outputToFile )
			{
				Draw.CreateFile_PostScript(ans, path + "TestCompoundFill.ps");
				Assert.assertTrue(Draw.CompareFiles(path + "TestCompoundFill.ps", path + "GoodCompoundFill.ps"));
			}
		}
		
		/**
		 * 
		 * Tests the construction of nested CompoundShapes
		 * 
		 * @author JVen
		 * 
		 */
		
		@Test
		public void TestNestedCompound()
		{
			int x = 300;
			int y = 500;
			
			ITranslateShape ps = new ToPostScript();
			
			//Circle p1 = new Circle(10);
			Polygon p1 = new Polygon(4, 20);
			
			ArrayList<Shape> l1 = new ArrayList<Shape>();
			for ( int i = 0 ; i <= 3 ; i++ )
				l1.add(p1);
			Horizontal h1 = new Horizontal(l1);
			
			ArrayList<Shape> l2 = new ArrayList<Shape>();
			for ( int i = 0 ; i <= 5 ; i++ )
				l2.add(h1);
			Vertical v1 = new Vertical(l2);
			
			ArrayList<Shape> l3 = new ArrayList<Shape>();
			for ( int i = 0 ; i <= 2 ; i++ )
				l3.add(v1);
			Horizontal h2 = new Horizontal(l3);
			
			Scaled s1 = new Scaled(h2, 0.3, 5);
			Scaled s2 = new Scaled(s1, 0.8, 0.9);
			
			String ans = s2.translate(ps, x, y);
			
			if ( printToConsole )
				System.out.println(ans);
			if ( outputToFile )
			{
				Draw.CreateFile_PostScript(ans, path + "TestNestedCompound.ps");
				Assert.assertTrue(Draw.CompareFiles(path + "TestNestedCompound.ps", path + "GoodNestedCompound.ps"));
			}
		}
		
		/**
		 * 
		 * Tests the construction of nested Scaled shapes
		 * 
		 * @author JVen
		 * 
		 */
		
		@Test
		public void TestNestedScaled()
		{
			ITranslateShape psThin = new ToPostScript(false, 0.3);
			ITranslateShape psThick = new ToPostScript(false, 4);
			ITranslateShape psFill = new ToPostScript(true);
			
			int size = 100;
			int freq = 70;
			int comparisons = 5;
			double scaleFactor = 2;
			ArrayList<Shape> scaleds = new ArrayList<Shape>();
			
			//Star shape = new Star(size, 12);
			Circle shape = new Circle(size / 2.0);
			scaleds.add(shape);
			
			for ( int i = 0 ; i < freq * comparisons ; i++ )
			{
				scaleds.add(new Scaled(scaleds.get(2*i), scaleFactor, 1.0 / scaleFactor));
				scaleds.add(new Scaled(scaleds.get(2*i + 1), 1.0 / scaleFactor, scaleFactor));
			}
			
			String ans = "";
			
			for ( int i = 0 ; i < comparisons ; i++ )
			{
				Shape s = scaleds.get(2 * freq * i);
				ans += s.translate(psThin, size * (i + 1), 200) +
					   s.translate(psThick, size * (i + 1), 400) +
					   s.translate(psFill, size * (i + 1), 600);
			}
			
			ans += shape.translate(psThick, 150, 800) + (new Scaled(shape, 2, 2)).translate(psThick, 450, 800);
			
			if ( printToConsole )
				System.out.println(ans);
			if ( outputToFile )
			{
				Draw.CreateFile_PostScript(ans, path + "TestNestedScaled.ps");
				Assert.assertTrue(Draw.CompareFiles(path + "TestNestedScaled.ps", path + "GoodNestedScaled.ps"));
			}
			
		}
		
		/**
		 * 
		 * Tests the construction of Scaled shapes with large scaling factors
		 * 
		 * @author JVen
		 * 
		 */
		
		@Test
		public void TestLargeScaled()
		{
			ITranslateShape ps = new ToPostScript(false);
			
			double scale = 10;
			
			Star e1 = new Star(50, 10);
			//Circle e1 = new Circle(50);
			Scaled e2 = new Scaled(e1, scale, 1.0/scale);
			Scaled e3 = new Scaled(e1, 1.0/scale, scale);
			Scaled e4 = new Scaled(e2, 1.0/scale, scale);
			
			String e = e1.translate(ps, 100, 700) + e2.translate(ps, 300, 700) + 
				e3.translate(ps, 500, 700) + e4.translate(ps, 300, 300);
			
			if ( printToConsole )
				System.out.println(e);
			if ( outputToFile )
			{
				Draw.CreateFile_PostScript(e, path + "TestLargeScaled.ps");
				Assert.assertTrue(Draw.CompareFiles(path + "TestLargeScaled.ps", path + "GoodLargeScaled.ps"));
			}
			
		}
		
		
		/**
		 * Tests construction of ToPostScript with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidToPostScript()
		{
			try
			{
				ToPostScript ps = new ToPostScript(-2);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Circle with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidCircle()
		{
			try
			{
				Circle c = new Circle(-100);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of CompoundShapes with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidCompoundShape()
		{
			try
			{
				Horizontal h = new Horizontal(new ArrayList<Shape>());
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Vertical v = new Vertical(new ArrayList<Shape>());
				Assert.fail();
			}
			catch(RuntimeException e)
			{
				// should go here!
			}
			
			try
			{
				Layered l = new Layered(new ArrayList<Shape>());
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Mountain with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidMountain()
		{			
			try
			{
				Mountain m = new Mountain(100, 200, -2);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Polygon with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidPolygon()
		{
			try
			{
				Polygon p = new Polygon(5, -3.2);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Polygon p = new Polygon(-9, 10);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Rectangle with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidRectangle()
		{
			try
			{
				Rectangle r = new Rectangle(-3, 10.2);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Rectangle r = new Rectangle(9, 0);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Rotated with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidRotated()
		{
			try
			{
				Circle c = new Circle(10);
				Rotated r = new Rotated(c, 0);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Circle c = new Circle(10);
				Rotated r = new Rotated(c, 80);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Scaled with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidScaled()
		{
			try
			{
				Circle c = new Circle(10);
				Scaled s = new Scaled(c, 9, 0);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Circle c = new Circle(10);
				Scaled s = new Scaled(c, -1, 1);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Sierpinsky with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidSierpinsky()
		{
			try
			{
				Sierpinsky s = new Sierpinsky(3, -9);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Sierpinsky s = new Sierpinsky(0, 5);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Sierpinsky s = new Sierpinsky(13, 10);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Spacer with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidSpacer()
		{
			try
			{
				Spacer s = new Spacer(-10, 10);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Spacer s = new Spacer(10, -10);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Spacer s = new Spacer(-10, -10);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Square with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidSquare()
		{
			try
			{
				Square s = new Square(0);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Star with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidStar()
		{
			try
			{
				Star s = new Star(100, 2);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
			
			try
			{
				Star s = new Star(-30, 5);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
		
		/**
		 * Tests construction of Triangle with invalid params
		 * @author nedmurp, JVen
		 */
		@Test
		public void TestInvalidTriangle()
		{
			try
			{
				Triangle t = new Triangle(-100);
				Assert.fail();
			}
			catch ( RuntimeException e )
			{
				// should go here!
			}
		}
}
