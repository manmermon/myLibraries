/**
 * 
 */
package GUI.tabbedpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Manuel Merino Monge
 *
 */
public class TabClosableContainer extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel titleLbl;
	private JButton closeButton;
	
	public TabClosableContainer( final ClosableTabbedPanel tabPane, final Component panel, final String title)
	{
		super( new FlowLayout( FlowLayout.LEFT, 0, 0) );
		super.setName( "closableTab" );
		super.setOpaque( false );
		
		this.titleLbl = new JLabel( title );
		this.titleLbl.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 5 ) );
				
		this.closeButton = new JButton( " X " );
		this.closeButton.setVerticalAlignment( SwingConstants.CENTER );		
		this.closeButton.setForeground( Color.BLACK );
		this.closeButton.setFont( new Font( Font.DIALOG, Font.BOLD, 11 ));
		this.closeButton.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 0 ) );
		this.closeButton.setFocusPainted( false );
		
		this.closeButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if( tabPane != null )
				{
					int index = tabPane.getTabbedPane().indexOfComponent( panel );				
					tabPane.removeTabAt( index );
				}
			}
		});
		
		super.add( titleLbl );
		super.add( closeButton );		
	}
		
	public void setTitle( String title )
	{
		this.titleLbl.setVisible( false );
		
		this.titleLbl.setText( title );
		
		this.titleLbl.setVisible( true );
	}
	
	public void showCloseButton( boolean show )
	{
		this.closeButton.setVisible( show );
	}
	
	public void setCloseButtonForegroundColor( Color c )	
	{
		this.closeButton.setVisible( false );
		
		this.closeButton.setForeground( c );
		
		this.closeButton.setVisible( true );
	}
	
	public void setCloseButtonBackgroundColor( Color c )	
	{
		this.closeButton.setVisible( false );
		
		this.closeButton.setBackground( c );
		
		this.closeButton.setVisible( true );
	}
}
