package neonal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class SudokuChecker {

	private static final int SIZE = 9;
	private static final int TOTAL_SIZE = 81;

	private static Path path;
	
	private int[] sqIndexes = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	
	public SudokuChecker(String pathString) {
		path = FileSystems.getDefault().getPath(pathString);
	}

	public void validate() {
		try {
			List<String> allLines = Files.readAllLines(path,
					Charset.forName("UTF-8"));

			int invCount = 0;


			for (String string : allLines) {
				if (string.startsWith("#")) {
					continue;
				}

				int[][] rows = new int[SIZE][SIZE];
				int[][] columns = new int[SIZE][SIZE];
				int[][] squares = new int[SIZE][SIZE];

				parseString(string, rows, columns, squares);

				boolean valid = true;
				for (int i = 0; valid && i < SIZE; i++) {
					valid &= validate(rows[i]) && validate(columns[i])
							&& validate(squares[i]);
				}

				if (!valid) {
					System.out.println(string);
					invCount++;
				}

				Arrays.fill(sqIndexes, 0);
			}

			System.out.println(invCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean validate(int[] check) {
		Arrays.sort(check);
		for (int i = 0; i < check.length; i++) {
			if (check[i] != i + 1) {
				return false;
			}
		}
		return true;
	}
	
	public void parseString(String string, int[][] rows, int[][] columns, int[][] squares){
		for (int i = 0; i < TOTAL_SIZE; i++) {
			int number = Character.getNumericValue(string.charAt(i));

			rows[i / 9][i % 9] = number;
			columns[i % 9][i / 9] = number;

			// This expression finds the square index of a given element
			int sqIndex = ((i / 3) % 3) + ((i / 27) * 3);

			squares[sqIndex][sqIndexes[sqIndex]++] = number;
		}
	}
	
	public static void main(String[] args) {
		if(args.length<1){
			System.err.println("Usage: java -cp sudoku-<version>.jar neonal.SudokuChecker path/to/file");
			System.exit(-1);
		}
		
		SudokuChecker sc = new SudokuChecker(args[0]);

		sc.validate();
	}

}
