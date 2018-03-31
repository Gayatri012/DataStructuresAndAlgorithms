# Word Puzzle using MyHashTable

Create own implmentation of Linear Probing HashTable. This MyHashTable is used to search a grid of letters for words from a given dictionary.

Searching the grid is implemented in two ways to check which ine runs faster and is more efficient.

The user can input a value for the rows and columns of the grid and the program will create a grid of random characters. The program will read in a dictionary file (provided) and use an algorithm to solve the word puzzle. 

Algorithm 1:
Check each word in the grid and moves cursor in all eight directions (taking care to consider only appropriate orientations for border letters) and checks in dictionary for each of the word thus formed by progressively adding a letter in each direction.

Algorithm 2:
When reading the input file of words, store each prefix of the word as well.
For example, if the word is "apple", store "a", "ap", "app", "appl", "apple".
In the algorithm, if a prefix is not found, the rest of this string can be treated as "not found".  For example, if the string is "apbum", and after checking and finding "a" and "ap" I find that "abp" is not in my dictionary, then there is no point in checking further in this direction.

Please run WordPuzzle.java as a stand alone program to get the prompt to input the number of rows and columns of the grid.

The program outputs the elapsed time in both cases. Algorithm 2 runs faster than Algorithm 1.
