package neonal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	private static final String PATH = "resources/samples.txt";
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testInvalidArray() {
		int[] invalid = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		SudokuChecker sc = new SudokuChecker(PATH);

		assertFalse(sc.validate(invalid));
	}

	public void testValidOrderedArray() {
		int[] invalid = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		SudokuChecker sc = new SudokuChecker(PATH);

		assertTrue(sc.validate(invalid));
	}

	public void testValidShuffledArray() {
		int[] invalid = { 9, 1, 6, 2, 8, 3, 4, 7, 5 };

		SudokuChecker sc = new SudokuChecker(PATH);

		assertTrue(sc.validate(invalid));
	}

	public void testParseString() {
		String s = "123456789123456789123456789123456789123456789123456789123456789123456789123456789";

		int[][] rows = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 } };
		int[][] columns = { { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3, 3, 3, 3, 3 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5 },
				{ 6, 6, 6, 6, 6, 6, 6, 6, 6 }, { 7, 7, 7, 7, 7, 7, 7, 7, 7 },
				{ 8, 8, 8, 8, 8, 8, 8, 8, 8 }, { 9, 9, 9, 9, 9, 9, 9, 9, 9 } };
		int[][] squares = { { 1, 2, 3, 1, 2, 3, 1, 2, 3 },
				{ 4, 5, 6, 4, 5, 6, 4, 5, 6 }, { 7, 8, 9, 7, 8, 9, 7, 8, 9 },
				{ 1, 2, 3, 1, 2, 3, 1, 2, 3 }, { 4, 5, 6, 4, 5, 6, 4, 5, 6 },
				{ 7, 8, 9, 7, 8, 9, 7, 8, 9 }, { 1, 2, 3, 1, 2, 3, 1, 2, 3 },
				{ 4, 5, 6, 4, 5, 6, 4, 5, 6 }, { 7, 8, 9, 7, 8, 9, 7, 8, 9 } };

		int[][] parsedRows = new int[9][9];
		int[][] parsedColumns = new int[9][9];
		int[][] parsedSquares = new int[9][9];

		SudokuChecker sc = new SudokuChecker(PATH);

		sc.parseString(s, parsedRows, parsedColumns, parsedSquares);

		boolean equal = true;
		for (int i = 0; equal && i < 9; i++) {
			for (int j = 0; equal && j < 9; j++) {
				equal &= parsedRows[i][j] == rows[i][j]
						&& parsedColumns[i][j] == columns[i][j]
						&& parsedSquares[i][j] == squares[i][j];
			}
		}

		assertTrue(equal);
	}
}
