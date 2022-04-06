/**
 * 
 */
package general;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author manuel
 * @param <X>
 * @param <Y>
 *
 */
public class ArrayMap< X, Y > extends AbstractMap< X, List< Y > > implements Cloneable 
{
	protected Map< X, List< Y > > map;
	
	/**
	 * Constructs a new, empty tree map, using the natural ordering of its keys. 
	 * All keys inserted into the map must implement the Comparable interface. 
	 * Furthermore, all such keys must be mutually comparable: k1.compareTo(k2) 
	 * must not throw a ClassCastException for any keys k1 and k2 in the map. If 
	 * the user attempts to put a key into the map that violates this constraint 
	 * (for example, the user attempts to put a string key into a map whose keys 
	 * are integers), the put(Object key, Object value) call will throw a 
	 * ClassCastException.
	 */
	public ArrayMap() 
	{
		this.map = new HashMap< X, List< Y > >();
	}

	/**
	 * Copies all of the mappings from the specified ArrayTreeMap to this ArrayTreeMap.
	 * @param arrayMap - ArrayTreeMap to be stored in this map
	 * @throws ClassCastException - if the specified key cannot be compared with the keys currently in the map
	 * @throws NullPointerException - if the specified key is null and this map uses natural ordering, 
	 * 								or its comparator does not permit null keys
	 */
	public void putAll( ArrayMap< X, Y > arrayMap )
	{
		for( X key : arrayMap.keySet() )
		{
			List< Y > vals = arrayMap.get( key );
			if( vals != null )
			{
				for( Y val : vals )
				{
					this.put( key, val );
				}
			}
		}
	}
		
	/**
	 * Removes the first instance of the value for this key from this TreeMap if present.
	 * @param key - key for which mapping should be removed	  			
	 * @param val - value for which mapping should be removed 
	 * @return Returns true if this map contains more instance of the value for for the specified key.
	 * @throws ClassCastException - if the specified key cannot be compared with the keys currently in the map
	 * @throws NullPointerException - if the specified key is null and this map uses natural ordering, or its comparator does not permit null keys
	 */
	public boolean removeValue( X key, Y val )
	{
            List< Y > VALS = this.map.get( key );

            if( VALS != null )
            {
                    VALS.remove( val );
            }

            boolean cont = (VALS != null ) && ( val != null ) && VALS.contains( val );  
            
            return cont;
	}
		
	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException 
	{
		Map< X, List< Y > > clon = new HashMap< X, List< Y > >( );
		
		for( X key : this.map.keySet() )
		{
			List< Y > copy = new ArrayList< Y >();
			List< Y > org = this.map.get( key );
			if( org != null )
			{
				Collections.copy( copy, org );
				clon.put( key, copy );
			}			
		}
		
		return clon;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override        
	public List< Y > put( X key, List< Y > values ) 
	{
		List< Y > VALS = this.createValuesList( key );
		
		VALS.addAll( values );
		
		return VALS;
	}
      
	/**
	 * Associates the specified value with the specified key in this map. 
	 * If the map previously contained a mapping for the key, 
	 * the old value is replaced.
	 * @param key - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 * @return  the previous value associated with key, or null if there 
	 * 			was no mapping for key. (A null return can also indicate 
	 * 			that the map previously associated null with key).
	 * @throws ClassCastException - if the specified key cannot be compared 
	 * 									with the keys currently in the map
     * @throws NullPointerException - if the specified key is null and this 
     *									map uses natural ordering, or its 
     *									comparator does not permit null keys
	 */
	public List< Y > put( X key, Y value )
	{
		List< Y > VALS = this.createValuesList( key );

		VALS.add( value );		

		return VALS;
	}

	/**
	 * Associates the specified value with the specified key in this map. 
	 * If the map previously contained a mapping for the key, 
	 * the old value is replaced.
	 * @param key - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 * @param pos - index at which the specified element is to be inserted. 
	 * 				If it is greater than the number of items stored, it 
	 * 				will be inserted last. If it is negative, it will be 
	 * 				inserted as the first element. 
	 * @return  the previous value associated with key, or null if there 
	 * 			was no mapping for key. (A null return can also indicate 
	 * 			that the map previously associated null with key).
	 * @throws ClassCastException - if the specified key cannot be compared 
	 * 									with the keys currently in the map
     * @throws NullPointerException - if the specified key is null and this 
     *									map uses natural ordering, or its 
     *									comparator does not permit null keys
	 */
	public List< Y > put( X key, Y value, int pos )
	{
		List< Y > VALS = this.createValuesList( key );
		
		if( pos < 0 )
		{
			VALS.add( 0, value );
		}
		else if( pos < VALS.size() )
		{
			VALS.add( pos, value );
		}
		else
		{
			VALS.add( value );
		}
		
		return VALS;
	}
	
	/**
	 *  Create a value list for the key.
	 * @param key  - key with which the specified value is to be associated
	 * @return List of values for the key.
	 */
	private List< Y > createValuesList( X key )
	{
		List< Y > VALS = this.map.get( key );
		
		if( VALS == null )
		{
			VALS = new ArrayList< Y >();
			this.map.put( key, VALS );
		}
		
		return VALS;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see java.util.AbstractMap#remove(java.lang.Object)
	 */
	@Override
	public List< Y > remove( Object key ) 
	{
		return this.map.remove( key );
	}

	/**
	 * Clear the list of specified values associate with the specified key in this map.
	 * 
	 * @param key - key with which the specified value is to be associated
	 * @throws ClassCastException - if the specified key cannot be compared 
	 * 									with the keys currently in the map
     * @throws NullPointerException - if the specified key is null and this 
     *									map uses natural ordering, or its 
     *									comparator does not permit null keys
	 */
	public void emptyArray( X key )
	{
		List< Y > values = this.map.get( key );
		if( values != null )
		{
			values.clear();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#size()
	 */
	@Override
	public int size() 
	{
		return this.map.size();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey( Object key ) 
	{
		return this.map.containsKey( key );
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue( Object val ) 
	{
		boolean cont = false;	
		
		for( List< Y > values : this.map.values() )
		{
			cont = values.contains( val );
			
			if( cont )
			{
				break;
			}
		}
		
		return cont;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#get(java.lang.Object)
	 */
	@Override
	public List<Y> get( Object key ) 
	{
		return this.map.get( key );
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#entrySet()
	 */
	@Override
	public Set< Entry< X, List< Y > > > entrySet() 
	{
		return this.map.entrySet();
	}
}
