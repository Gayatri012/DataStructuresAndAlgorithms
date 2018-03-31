import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * @author Gayatri
 * Class to generate a random grid of characters of given dimensions and find if it contains any word in the dictionary
 * @param <AnyType>
 */

public class WordPuzzle<AnyType> {	
	/**
	 * Construct default WordPuzzle
	 */
	public WordPuzzle() {
		this(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE);
	}
	
	/**
	 * Construct WordPuzzle of mentioned dimensions
	 * @param row - number of rows of grid of WordPuzzle
	 * @param col - number of columns of grid of WordPuzzle
	 */
	public WordPuzzle(int row, int col) {
		rowCount = row;
		colCount = col;
	}
	
	/**
	 * Generates a grid of 2D characters having number of rows and columns as mentioned by user
	 * @return a 2D character array of random characters
	 */
	public char[][] generateGrid() {
		wordGrid = new char[rowCount][colCount];
		
		Random ran = new Random();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				wordGrid[i][j] = (char)(ran.nextInt(26) + 'a');	//Random lower case alphabets
			}
		}
		return wordGrid;
	}
	
	/**
	 * Sets the wordGrid to the array passed as parameter inthe method
	 * @param array 2D character array word grid for the WordPuzzle
	 */
	public void setWordGrid(char[][] array){
		if (array != null && array[0] != null) {
			this.rowCount = array.length;
			this.colCount = array[0].length; 
		}
		else {
			this.rowCount = array.length;
			this.colCount = array[0].length;
		}
		wordGrid = array;
	}
	
	/**
	 * 
	 * @return 2D character array which is the word grid for WordPuzzle
	 */
	
	public char[][] getWordGrid() {
		return wordGrid;
	}
	
	/**
	 * Prints the WordPuzzle character array grid
	 */
	public void printGrid() {
		printGrid(wordGrid);
	}
	
	/**
	 * Prints the WordPuzzle character array grid
	 * @param grid 2D character array to be printed 
	 */
	private void printGrid(char[][] grid) {
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Finds words from the grid that matches with the words in dictionary
	 * @param dictionary A hash table consisting of all words from Dictionary text file
	 * @return A list of all words that matches from grid to dictionary
	 */
	public ArrayList<String> findWords(MyHashTable<String> dictionary) {
		return findWords(wordGrid, dictionary);
	}
	
	/**
	 * Finds words from the grid that matches with the words in dictionary
	 * @param grid 2D character array of WordPuzzle
	 * @param dictionary A hash table consisting of all words from Dictionary text file
	 * @return  A list of all words that matches from grid to dictionary
	 */
	private ArrayList<String> findWords(char[][] grid, MyHashTable<String> dictionary) {
		
		ArrayList<String> wordsFound = new ArrayList<String>();
		
		for (int rowNoTemp = 0; rowNoTemp < rowCount; rowNoTemp++) {
			
			for (int colNoTemp = 0; colNoTemp < colCount; colNoTemp++) {
				int numberOfChars = 1;

				//while ((colNoTemp + numberOfChars) <= colCount && (rowNoTemp + numberOfChars) <= rowCount) {
				while (numberOfChars <= colCount || numberOfChars <= rowCount) {
					// Forward direction					
					String word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, forwardDirection);
					//System.out.println(word);
					if (!word.equalsIgnoreCase("") && dictionary.contains(word))
						wordsFound.add(word);
				
					
					if (numberOfChars > 1) {
						// Bottom right direction
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, bottomRightDirection);
						//System.out.println(word);
						if (dictionary.contains(word))
							wordsFound.add(word);
						
						//Downward direction
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, downwardDirection);
						//System.out.println(word);
						if (dictionary.contains(word))
							wordsFound.add(word);
						
						//Bottom left direction
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, bottomLeftDirection);
						//System.out.println(word);
						if (dictionary.contains(word))
							wordsFound.add(word);
						
						//Backward direction
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, backwardDirection);
						//System.out.println(word);
						if (dictionary.contains(word))
							wordsFound.add(word);
						
						//Top left direction
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, topLeftDirection);
						//System.out.println(word);
						if (dictionary.contains(word))
							wordsFound.add(word);
						
						// Upward direction
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, upwardDirection);
						//System.out.println(word);
						if (dictionary.contains(word))
							wordsFound.add(word);
						
						//Top right direction
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, topRightDirection);
						//System.out.println(word);
						if (dictionary.contains(word))
							wordsFound.add(word);
					}
					numberOfChars++;
				}
			}
		}
		
		return wordsFound;
	}
	
	/**
	 * Returns a new String from character sub array
	 * @param array 2D character array grid of WordPuzzle
	 * @param rowNo row number of the element from where a new String is to be formed
	 * @param colNo column number of the element from where a new String is to be formed 
	 * @param count number of characters or length of the elements of subarray
	 * @param orientation One out of the eight directions in which to find the subarray
	 * @return new String from the character sub array starting with the element at rowNo
	 * 			and colNo, in the given orientation having count number of characters
	 */
	private String makeString(char[][] array, int rowNo, int colNo, int count, int orientation) {
		StringBuffer s = new StringBuffer();
		int tempCount = count;
		switch(orientation){
			case 1:	//forward direction
				while (count > 0 && colNo < colCount){
				//while (count > 0 && (count + colNo) < colCount){
					s.append(array[rowNo][colNo]);
					colNo += 1;
					count--;
				}
				break;
				
			case 2:	//bottom-right direction
				while (count > 0 && rowNo < rowCount && colNo < colCount){
			//while (count > 0 && (rowNo + count) < rowCount && (colNo + count) < colCount){
					s.append(array[rowNo][colNo]);
					rowNo += 1;
					colNo += 1;
					count--;
				}
				break;
			case 3:		//downward direction
				while (count > 0 && rowNo < rowCount){
				//while (count > 0 && (count + rowNo) < rowCount){
					s.append(array[rowNo][colNo]);
					rowNo += 1;
					count--;
				}
				break;
			case 4:	//bottom-left direction
				while (count > 0 && rowNo < rowCount && colNo >= 0){
				//while (count > 0 && (count + rowNo) < rowCount && (count + colNo) > 0){
					s.append(array[rowNo][colNo]);
					rowNo += 1;
					colNo -= 1;
					count--;
				}
				break;
			case 5:	//backward direction
				while (count > 0 && colNo >= 0){
				//while (count > 0 && (count + colNo) > 0){
					s.append(array[rowNo][colNo]);
					colNo -= 1;
					count--;
				}
				break;
			case 6:	//top-left direction
				while (count > 0 && rowNo >= 0 && colNo >= 0){
				//while (count > 0 && (count + rowNo) > 0 && (count + colNo) > 0){
					s.append(array[rowNo][colNo]);
					rowNo -= 1;
					colNo -= 1;
					count--;
				}
				break;
			case 7:	//upward direction
				while (count > 0 && rowNo >= 0){
				//while (count > 0 && (count + rowNo) > 0){
					s.append(array[rowNo][colNo]);
					rowNo -= 1;
					count--;
				}
				break;
			case 8:	//top-right direction
				while (count > 0 && rowNo >= 0 && colNo < colCount){
				//while (count > 0 && (count + rowNo) > 0 && (count + colNo) < colCount){
					s.append(array[rowNo][colNo]);
					rowNo -= 1;
					colNo += 1;
					count--;
				}
				break;
			default:
				break;
		}
		
		if (s.toString().length() != tempCount)
			return "";
		else
			return s.toString();
	}
	
	/**
	 * Finds words from the grid that matches with the words in dictionary, by first checking if prefixes of words match. If not, that word is not checked
	 * @param dictionary A hash table consisting of all words and their prefixes from Dictionary text file
	 * @return A list of all words that matches from grid to dictionary
	 */
	public ArrayList<String> findWordsUsingPrefix(MyHashTable<String> prefixDictionary) {
		return findWordsUsingPrefix(wordGrid, prefixDictionary);
	}
	
	/**
	 * Finds words from the grid that matches with the words in dictionary. If prefix of a word is not found, then that word in not searched again
	 * @param grid 2D character array of WordPuzzle
	 * @param dictionary A hash table consisting of all words and their prefixes from Dictionary text file
	 * @return A list of all words that matches from grid to dictionary
	 */
	private ArrayList<String> findWordsUsingPrefix(char[][] grid, MyHashTable<String> dictionary) {
		
		ArrayList<String> wordsFound = new ArrayList<String>();
		
		for (int rowNoTemp = 0; rowNoTemp < rowCount; rowNoTemp++) {
			
			for (int colNoTemp = 0; colNoTemp < colCount; colNoTemp++) {
				int numberOfChars = 1;
				
				boolean moveFwd = true;
				boolean moveBottomRight = true;
				boolean moveDown = true;
				boolean moveBottomLeft = true;
				boolean moveBack = true;
				boolean moveTopLeft = true;
				boolean moveUp = true;
				boolean moveTopRight = true;
				
				
				//while ((colNoTemp + numberOfChars) <= colCount && (rowNoTemp + numberOfChars) <= rowCount) {
				while (numberOfChars <= colCount || numberOfChars <= rowCount) {
					// Forward direction
					String word = null;
					String val = null;
					
					if (moveFwd) {
						word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, forwardDirection);
						//System.out.println(word);
						val = dictionary.get(word);
						if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
							wordsFound.add(word);
						}
						else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
							moveFwd = false;
						}	
					}
					
					//If a letter is not found in the dictionary prefixes, then no need to check for words that start with this letter in any orientation 
					if (numberOfChars == 1 && !moveFwd) {
						numberOfChars += colCount;
						break;
					}
					
					if (numberOfChars > 1) {
						// Bottom right direction
							if (moveBottomRight) {
								word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, bottomRightDirection);
								//System.out.println(word);
								
								val = dictionary.get(word);
								if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
									wordsFound.add(word);
								}
								else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
									moveBottomRight = false;
								}
								
							}
							
							//Downward direction
							if (moveDown) {
								word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, downwardDirection);
								//System.out.println(word);
								
								val = dictionary.get(word);
								if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
									wordsFound.add(word);
								}
								else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
									moveDown = false;
								}
							}
							
							
							//Bottom left direction
							if (moveBottomLeft) {
								word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, bottomLeftDirection);
								//System.out.println(word);
								
								val = dictionary.get(word);
								if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
									wordsFound.add(word);
								}
								else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
									moveBottomLeft = false;
								}
							}
							
							//Backward direction
							if (moveBack) {
								word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, backwardDirection);
							//System.out.println(word);
								val = dictionary.get(word);
								if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
									wordsFound.add(word);
								}
								else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
									moveBack = false;
								}
							}
							
							//Top left direction
							if (moveTopLeft) {
								word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, topLeftDirection);
								//System.out.println(word);
								val = dictionary.get(word);
								if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
									wordsFound.add(word);
								}
								else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
									moveTopLeft = false;
								}
							}
							
							// Upward direction
							if (moveUp) {
								word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, upwardDirection);
								val = dictionary.get(word);
								if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
									wordsFound.add(word);
								}
								else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
									moveUp = false;
								}
							}
							
							//Top right direction
							if (moveTopRight) {
								word = makeString(grid, rowNoTemp, colNoTemp, numberOfChars, topRightDirection);
								val = dictionary.get(word);
								if (word != null && !word.equalsIgnoreCase("") && (val != null) && val.equalsIgnoreCase(WORD) ) {
									wordsFound.add(word);
								}
								else if (word != null && !word.equalsIgnoreCase("") && (val == null)) {	
									moveTopRight = false;
								}
							}
						//}
					}
					numberOfChars++;
				}
			}
		}
		
		return wordsFound;
	}	
	/**
	 * 
	 * @param wordList The list of words found in dictionary to be printed
	 */
	public static int print(ArrayList<String> wordList) {
		int count = 0;
		for(String word: wordList) {
			System.out.println(word);
			count++;
		}
		return count;
	}
	
	private int rowCount = 0;
	private int colCount = 0;
	private static final int DEFAULT_GRID_SIZE = 16;
	private char[][] wordGrid;
	
	private static final int forwardDirection = 1;
	private static final int bottomRightDirection = 2;
	private static final int downwardDirection = 3;
	private static final int bottomLeftDirection = 4;
	private static final int backwardDirection = 5;
	private static final int topLeftDirection = 6;
	private static final int upwardDirection = 7;
	private static final int topRightDirection = 8;
	
	private final String WORD = "w";
	private final String PREFIX = "p";
	
	
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			System.out.println("Please enter number of rows and columns for word puzzle grid : ");
			int row = sc.nextInt();
			int col = sc.nextInt();
			
			if (row < 1 || col < 1)
				throw new InputMismatchException();
			System.out.println("Loading data. Please wait.....");
			
			WordPuzzle<String> puzzle = new WordPuzzle<String>(row, col);
			puzzle.generateGrid();
			
			long startTime = System.currentTimeMillis();
			MyHashTable<String> dictionary = new MyHashTable<String>();
			
			sc = new Scanner(new File("src\\Dictionary.txt"));
			//sc = new Scanner(new File("src\\Dictionary_Tiny.txt"));
			
			while (sc.hasNext()){
				//Load data to the MyHashTable
				dictionary.insert(sc.next());
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Dictionary size : " + dictionary.size() + ". It took " + (endTime - startTime) + " ms for the data in text file to load into the MyHashTable");
			
			System.out.println("\nData loaded. Here's your " + row + " x " + col + " word puzzle grid. Let's start playing!!!");
			
			//Print grid here
			puzzle.printGrid();
			
			startTime =  System.currentTimeMillis();
			ArrayList<String> matchingWords = puzzle.findWords(dictionary);
			endTime =  System.currentTimeMillis();
			
			System.out.println("\nIt took " + (endTime - startTime) + " ms to complete searching words");
			
			System.out.println("\nMatching words from grid found in dictionary");
			//print the words found
			int count = print(matchingWords);
			System.out.println("Total count of matching words : " + count);

			/** Prefix method begins here **/
			startTime = System.currentTimeMillis();
			MyHashTable<String> prefixDictionary = new MyHashTable<String>();
			

			sc = new Scanner(new File("src\\Dictionary.txt"));
			
			//In the prefix method of hashing data, key is prefixes and value is their corresponding words 
			while (sc.hasNext()){
				//Load data to the MyHashTable
				String dictionaryWord = sc.next();
				//Assumption: Words that are a part of prefix of some other word appear before the word in dictionary.
				// E.g.: bar as a word appears before bark in dictionary. So prefix 'bar' is stored as word, instead of prefix.
				for (int i = 1; i <= (dictionaryWord.length() - 1); i++) {
					String isWord = prefixDictionary.get(dictionaryWord.substring(0, i)); 
					if ((isWord != null && !isWord.equalsIgnoreCase(puzzle.WORD)) || isWord == null)
						prefixDictionary.put(dictionaryWord.substring(0, i), puzzle.PREFIX);
				}
				prefixDictionary.put(dictionaryWord, puzzle.WORD);
			}
			endTime = System.currentTimeMillis();
			System.out.println("Prefix dictionary size : " + prefixDictionary.size() + ". It took " + (endTime - startTime) + " ms for the data in text file to load into the MyHashTable");
			
			startTime =  System.currentTimeMillis();
			ArrayList<String> matchingWordsPrefixMethod = puzzle.findWordsUsingPrefix(prefixDictionary);
			endTime =  System.currentTimeMillis();
			
			System.out.println("\nIt took " + (endTime - startTime) + " ms to complete searching words using prefix method");
			
			System.out.println("\nMatching words from grid found in dictionary using prefix method");
			//print the words found
			count = print(matchingWordsPrefixMethod);		
			System.out.println("Total count of matching words : " + count);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (InputMismatchException e) {
			System.out.println("Please enter only integers greater than 0 for word grid");
			e.printStackTrace();
		}
		
		finally {
			if (sc != null)
				sc.close();
		}
	}
}
