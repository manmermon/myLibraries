package general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class ArrayTreeMap< X, Y > extends ArrayMap< X, Y > 
{
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
	public ArrayTreeMap() 
	{
		super.map = new TreeMap< X, List< Y > >();
	}
	
	/**
	 * Constructs a new, empty tree map, ordered according to the given comparator. 
	 * All keys inserted into the map must be mutually comparable by the given comparator: 
	 * comparator.compare(k1, k2) must not throw a ClassCastException for any keys k1 and k2 
	 * in the map. If the user attempts to put a key into the map that violates this constraint, 
	 * the put(Object key, Object value) call will throw a ClassCastException. 
	 * @param com - the comparator that will be used to order this map. If null, the natural ordering of the keys will be used.
	 */
	public ArrayTreeMap( Comparator< ? super X > com )
	{
		super.map = new TreeMap< X, List< Y > >( com );		
	}
	
	/**
	 * 
	 * @return Get the comparator uses to order the keys.
	 */
	public Comparator< ? super X > comparator()
	{
		return ((TreeMap< X, List< Y > >)super.map).comparator();
	}
			
	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractMap#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException 
	{
		TreeMap< X, List< Y > > clon = new TreeMap< X, List< Y > >( this.comparator() );
		
		for( X key : super.map.keySet() )
		{
			List< Y > copy = new ArrayList< Y >();
			List< Y > org = super.map.get( key );
			if( org != null )
			{
				Collections.copy( copy, org );
				clon.put( key, copy );
			}			
		}
		
		return clon;
	}
}
