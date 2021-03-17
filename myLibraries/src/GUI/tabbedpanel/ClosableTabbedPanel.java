/**
 * 
 */
package GUI.tabbedpanel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author manuel merino-monge
 *
 */
public class ClosableTabbedPanel //extends JPanel 
{
	private List< CollectionListener > collectionListenerList = new ArrayList<CollectionListener>();;

	private final JTabbedPane tabPane;
	
	public ClosableTabbedPanel()
	{
		this.tabPane = new JTabbedPane();
	}
	
	public JTabbedPane getTabbedPane()
	{
		return this.tabPane;
	}

	public int getTabCount()
	{
		return this.tabPane.getTabCount();
	}
	
	public void insertTab( Component comp )
	{
		this.insertTab( null, comp );
	}

	public void insertTab( String title, Component comp )
	{
		this.insertTab( title, comp, this.getTabCount() );
	}

	public void insertTab( String title, Component comp, int index )
	{
		this.insertTab( title, null, comp, null, index );
	}

	public void insertTab( String title, Icon ic, Component comp, String tip, int index )
	{
		this.tabPane.insertTab( title, ic, comp, tip, index );
		//this.tabPane.setTabComponentAt( this.tabPane.indexOfComponent( comp ), this.getTitlePanel( comp, title ) );
		TabClosableContainer tcc = this.getTitlePanel( this, comp, title );
		this.tabPane.setTabComponentAt( this.tabPane.indexOfComponent( comp ), tcc );

		this.fireCollectionListener( CollectionEvent.INSERT_ELEMENT, index, comp );
	}
	
	/**
	 * Sets the title at index to title which can be null. The title is not shown if a tab component for this 
	 * tab was specified. An internal exception is raised if there is no tab at that index. 
	 * 
	 * @param index: the tab index where the title should be set.
	 * @param title the title to be displayed in the tab.
	 * 
	 * @throws: IndexOutOfBoundsException - if index is out of range (index < 0 || index >= tab count)
	 * 
	 */
	public void setTitleAt( int index, String title )
	{
		if( index < 0 || index >= this.tabPane.getTabCount() )
		{
			throw new IndexOutOfBoundsException();
		}
		
		Component panel = this.tabPane.getTabComponentAt( index );
		
		if( panel instanceof TabClosableContainer )
		{
			((TabClosableContainer)panel).setTitle( title );
		}
		else
		{
			this.tabPane.setTitleAt( index, title );
		}  
	}
	
	private TabClosableContainer getTitlePanel( ClosableTabbedPanel tab, Component panel, String title)
	{
		TabClosableContainer tcc = new TabClosableContainer( tab, panel, title );
		
		/*
		JPanel titlePanel = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0) );
		titlePanel.setName( "closableTab" );
		titlePanel.setOpaque( false );
		
		JLabel titleLbl = new JLabel( title );
		titleLbl.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 5 ) );
				
		JButton closeButton = new JButton( " X " );
		closeButton.setVerticalAlignment( SwingConstants.CENTER );		
		closeButton.setForeground( Color.BLACK );
		closeButton.setFont( new Font( Font.DIALOG, Font.BOLD, 11 ));
		closeButton.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 0 ) );
		closeButton.setFocusPainted( false );
		
		closeButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int index = tabPane.indexOfComponent( panel );				
				removeTabAt( index );
			}
		});
		
		titlePanel.add( titleLbl );
		titlePanel.add( closeButton );	

		return titlePanel;
		//*/
		
		return tcc;
	}
	
	public void showTabCloseButton( boolean show, int index )
	{
		JPanel c = (JPanel)this.tabPane.getTabComponentAt( index );
		
		c.getComponents()[ 1 ].setVisible( show );
	}	

	public Component getTabAt( int index )
	{
		Component c = this.tabPane.getComponentAt( index );
		
		return c;
	}
	
	public void removeTabAt( int index )
	{
		Component c = this.tabPane.getComponentAt( index );

		this.tabPane.removeTabAt( index );

		this.fireCollectionListener( CollectionEvent.REMOVE_ELEMENT, index, c );
	}
	
	public void removeAll()
	{
		this.tabPane.removeAll();
		this.fireCollectionListener( CollectionEvent.REMOVE_ELEMENT, -1, null );
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
	
	private void fireCollectionListener( int type, int index, Object element )
	{
		CollectionEvent ev = new CollectionEvent( this, type, index, element );

		for( CollectionListener listener : this.collectionListenerList )
		{
			listener.collectionChange( ev );
		}
	}
}


