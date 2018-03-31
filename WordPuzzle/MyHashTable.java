import java.util.ArrayList;


/**
 * 
 * @author Gayatri
 * Linear Probing HashTable
 * Adapted from Author Mark Weiss' QuadraticProbingHashTable class
 *
 */

public class MyHashTable<AnyType> {
	
	 /**
     * Construct the hash table.
     */
    public MyHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public MyHashTable( int size )
    {
        allocateArray( size );
        doClear( );
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     * @param x the item to insert.
     */
    public boolean insert( AnyType x )
    {       
        int currentPos = findPos( x );
        
        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry<>(x);
        
        theSize++;
        
            // Rehash
        if( occupied > array.length / 2 )
            rehash( );
        
        return true;
    }
    
    /**
     * Insert into the hash table.
     * @param key Key of the Hash Table
     * @param value Value corresponding to the key in HashTable
     * 
     */
    
    public boolean put( AnyType key , AnyType value)
    {       
        int currentPos = findPos( key );	//TODO: test this
        
        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry<>(key, value);
        
        theSize++;
        
            // Rehash
        if( occupied > array.length / 2 )
            rehash( );
        
        return true;
    }
    
    /**
     * Return the value to which the key corresponds to in the Hashtable
     * @param key The key whose value is to be found
     * @return value corresponding to the key
     */
    public AnyType get(AnyType key) {
    	if (contains(key)) {
    		int currentPos = findPos( key );
    			return ((array[currentPos] != null) ? array[currentPos].value : null); 
    	}
    	else {
    		return null;
    	}
    }

    /**
     * Expand the hash table.
     */
    private void rehash( )
    {
        HashEntry<AnyType> [ ] oldArray = array;

            // Create a new double-sized, empty table
        allocateArray( 2 * oldArray.length );
        occupied = 0;
        theSize = 0;

            // Copy table over
        for( HashEntry<AnyType> entry : oldArray )
            if( entry != null) {
            	put(entry.key, entry.value);
            }
   }

    /**
     * Method that performs quadratic probing resolution.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos( AnyType x )
    {
        int offset = 1;
        int currentPos = myhash( x );
        
        while( array[ currentPos ] != null &&
                !array[ currentPos ].key.equals( x ) )
        {
            currentPos += offset;  // Linear probing.
            if( currentPos >= array.length )
                currentPos -= array.length;
        }
        
        return currentPos;
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     * @return true if item removed
     */
    public boolean remove( AnyType x )
    {
        int currentPos = findPos( x );
            array[ currentPos ] = null;
            theSize--;
            return true;
    }
    
    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( AnyType x )
    {
        int currentPos = findPos( x );
        if (array[currentPos] != null && array[currentPos].key.equals(x))
        	return true;		//TODO : Test this method
        else
        	return false;
        
    }
    
    public boolean contains(AnyType k, AnyType val) {
    	int currentPos = findPos( k );
        if (array[currentPos] != null && array[currentPos].key.equals(k)) {
         		return true;		//TODO : Test this method
        }
        else
        	return false;
    	
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        doClear( );
    }

    private void doClear( )
    {
    	//TODO: Why doesn't this set theSize to 0?
        occupied = 0;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }
    
    private int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }
    
    private static class HashEntry<AnyType>
    {
    	public AnyType  key;   // the key
        public AnyType  value;   // the value - either "word" or "prefix"
        
        public HashEntry( AnyType e )
        {
        	key = e;
        }
        
        public HashEntry( AnyType k , AnyType v )
        {
        	value = v;
        	key = k;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<AnyType> [ ] array; // The array of elements
    private int occupied;                 // The number of occupied cells
    private int theSize;                  // Current size

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }    
}
