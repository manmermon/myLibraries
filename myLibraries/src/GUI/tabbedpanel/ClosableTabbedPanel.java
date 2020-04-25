/**
 * 
 */
package GUI.tabbedpanel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

/**
 * @author manuel merino-monge
 *
 */
public class ClosableTabbedPanel extends JTabbedPane
{	
	private static final long serialVersionUID = 9137134063802566617L;
	/**
	 * 
	 */
	
	private List< CollectionListener > collectionListenerList = new ArrayList<CollectionListener>();;
	
	public ClosableTabbedPanel()
	{
		super( );
		super.setUI( new ClosableTabbedPaneUI() );
	}
	
	public ClosableTabbedPanel( int tabPlacement )
	{
		super( tabPlacement );
		super.setUI( new ClosableTabbedPaneUI() );
	}
	
	public ClosableTabbedPanel( int tabPlacement, int tabLayoutPolicy )
	{
		super( tabPlacement, tabLayoutPolicy );
		super.setUI( new ClosableTabbedPaneUI() );
	}
	
	public void addTabbedPanelListener( CollectionListener listener )
	{
		if( listener != null )
		{
			this.collectionListenerList.add( listener );
		}
	}
	
	public void removeTabbedPanelListener( CollectionListener listener )
	{
		if( listener != null )
		{
			this.collectionListenerList.remove( listener );
		}
	}
	
	/*(non-Javadoc)
	 * @see @see javax.swing.JTabbedPane#insertTab(java.lang.String, javax.swing.Icon, java.awt.Component, java.lang.String, int)
	 */
	@Override
	public void insertTab(String title, Icon icon, Component component, String tip, int index)
	{	
		super.insertTab(title, icon, component, tip, index);
		
		this.fireCollectionListener( CollectionEvent.INSERT_ELEMENT, index, component );
	}
	
	/*(non-Javadoc)
	 * @see @see javax.swing.JTabbedPane#removeTabAt(int)
	 */
	@Override
	public void removeTabAt(int index)
	{
		Component c = super.getComponentAt( index );
		
		super.removeTabAt( index );
		
		this.fireCollectionListener( CollectionEvent.REMOVE_ELEMENT, index, c );
	}
	
	private void fireCollectionListener( int type, int index, Object element )
	{
		CollectionEvent ev = new CollectionEvent( this, type, index, element );
		
		for( CollectionListener listener : this.collectionListenerList )
		{
			listener.collectionChange( ev );
		}
	}
}


