package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class InfoDialog extends JDialog 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3453716688958678189L;
	private JTextPane textAreaInfo = null;

	public InfoDialog( String text ) 
	{
		this.init( text );
	}
	
	public InfoDialog( Frame winOwner, String text ) 
	{
		super( winOwner );
		this.init( text );
	}
	
	public InfoDialog( Window winOwner, String text ) 
	{
		super( winOwner );
		this.init( text );
	}
	
	public InfoDialog( Dialog winOwner, String text ) 
	{
		super( winOwner );
		this.init( text );
	}
	
	public InfoDialog( Frame winOwner, String text, boolean modal ) 
	{
		super( winOwner, modal );
		this.init( text );
	}
	
	public InfoDialog( Dialog winOwner, String text, boolean modal ) 
	{
		super( winOwner, modal );
		this.init( text );
	}
	
	private void init( String text )
	{		
		super.setUndecorated( true );
		super.setLayout( new BorderLayout() );
		super.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		
		JPanel areaTextPanel = new JPanel( new BorderLayout() );
		areaTextPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ));
		
		JScrollPane scr = new JScrollPane( this.getTextAreaInfo( text ) );
		areaTextPanel.add( scr, BorderLayout.CENTER );
		
		super.add( areaTextPanel );			
		
		super.addWindowFocusListener( new WindowFocusListener()
		{				
			@Override
			public void windowLostFocus(WindowEvent e) 
			{
				((JDialog)e.getSource()).dispose();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) 
			{					
			}
		});
		

		super.getRootPane().registerKeyboardAction( keyActions.getEscapeCloseWindows( "EscapeCloseWindow"), 
														KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), 
														JComponent.WHEN_IN_FOCUSED_WINDOW );
	}
	
	private JTextPane getTextAreaInfo( String text )
	{
		if( this.textAreaInfo == null )
		{
			this.textAreaInfo = new JTextPane();
			this.textAreaInfo.setEnabled(true);
			this.textAreaInfo.setEditable(false);						
			this.textAreaInfo.setBorder( BorderFactory.createEmptyBorder( 3, 3, 3, 3 ) );
			
			FontMetrics fm = this.textAreaInfo.getFontMetrics( this.textAreaInfo.getFont() );
			
			MutableAttributeSet set = new SimpleAttributeSet();
			StyleConstants.setSpaceBelow( set, fm.getHeight() * 0.5F );
			
			this.textAreaInfo.setParagraphAttributes( set, false );
						
			this.textAreaInfo.setText( text );
		}
		
		return this.textAreaInfo;
	}
	
}
