/**
 * 
 */
package GUI.tabbedpanel;

import java.util.EventObject;

/**
 * @author manuel
 *
 */
public class CollectionEvent extends EventObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3800737571898413342L;
	
	public static final int CLEAR_ELEMENT = -1;
	public static final int INSERT_ELEMENT = 0;	
	public static final int REMOVE_ELEMENT = 1;	

	private int eventType = INSERT_ELEMENT;
	private Object element = null;
	private int index;
	
	/**
	 * @param source
	 */
	public CollectionEvent( Object source, int type, int index, Object element )
	{
		super( source );
		
		this.eventType = type;
		this.element = element;
		this.index = index;
	}
	
	/**
	 * @return the eventType
	 */
	public int getEventType()
	{
		return this.eventType;
	}
	
	public Object getElement( )
	{
		return this.element;
	}
	
	/**
	 * @return the index
	 */
	public int getElementIndex()
	{
		return this.index;
	}
}



