/**
 * 
 */
package GUI.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import image.BasicPainter2D;

/**
 * @author manuel
 *
 */
public class ExpandablePanel extends JPanel
{
	/**
	 * 
	 */
	/*
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
					
					ExpandablePanel exp = new ExpandablePanel();
					exp.setText( "test");
					exp.setContentPanel( new JButton( "PRUEBA 1" ) );
					
					ExpandablePanel exp1 = new ExpandablePanel();
					exp1.setExpandControlOretation( WEST );
					exp1.setContentPanel( new JButton( "PRUEBA 2" ) );
					
					frame.getContentPane().setLayout( new BorderLayout() );
					
					JPanel p = new JPanel( new BorderLayout() );
					p.add( exp, BorderLayout.NORTH );
					
					JPanel p1 = new JPanel( new BorderLayout() );
					p1.add( exp1, BorderLayout.NORTH );
					
					JPanel p3 = new JPanel( new BorderLayout() );
					p3.add( p, BorderLayout.NORTH );
					p3.add( p1, BorderLayout.CENTER );
					
					
					frame.getContentPane().add( p3, BorderLayout.CENTER );
					frame.setBounds( 0, 0, 300, 300 ); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	public static final int NORTH = -1;
	public static final int SOUTH = -2;
	public static final int EAST = 1;
	public static final int WEST = 2;
	
	private JPanel expandPanel;
	private JPanel containerPanel;
	
	private JButton expandBt;
	
	private int orentation = NORTH;
	
	public ExpandablePanel()
	{
		super( new BorderLayout(), true );

		super.setBorder( BorderFactory.createEtchedBorder() );
		
		super.add( this.getExpandPanel(), BorderLayout.NORTH );
		super.add( this.getContainertPanel() );
	}
	
	public void setText( String text)
	{
		this.getExpandButton().setText( text );
	}
		
	private JPanel getExpandPanel()
	{
		if( this.expandPanel == null )
		{
			this.expandPanel = new JPanel( new BorderLayout() );
			
			this.expandPanel.add( this.getExpandButton() );
		}
		
		return this.expandPanel;
	}
	
	private JButton getExpandButton()
	{
		if( this.expandBt == null )
		{
			this.expandBt = new JButton();
			
			this.expandBt.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ) );
			
			this.expandBt.setBackground( super.getBackground() );
			
			Dimension s = this.expandBt.getPreferredSize();
			
			s.height = 15;
			s.width = 20;
			
			this.expandBt.setPreferredSize( s );
			
			Font f = this.expandBt.getFont();
			FontMetrics fm = this.expandBt.getFontMetrics( f );
			final Image collapseIco = BasicPainter2D.text( "---", fm, Color.GRAY, Color.GRAY, null );
			
			this.expandBt.setFont( new Font( f.getName(), f.getStyle(), 10 ) );
			
			this.expandBt.setHorizontalAlignment( SwingConstants.LEFT );
			
			this.expandBt.setIcon( new ImageIcon( collapseIco ) );
			
			this.expandBt.addActionListener( new ActionListener()
			{				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					JPanel p = getContainertPanel();
					boolean vis = p.isVisible();
					p.setVisible( !vis );
				}
			});
		}
		
		return this.expandBt;
	}
	
	private JPanel getContainertPanel()
	{
		if( this.containerPanel == null )
		{
			this.containerPanel = new JPanel( new BorderLayout() );
		}
		
		return this.containerPanel;
	}
	
	public void setExpandControlOretation( int orentation )
	{
		JPanel expPanel = this.getExpandPanel();
		JButton ctr = this.getExpandButton();
		
		if( Math.abs( orentation - this.orentation ) > 1  )
		{
			Dimension ps = ctr.getPreferredSize();
			Dimension s = ctr.getSize();
			
			ctr.setPreferredSize( new Dimension( ps.height, ps.width ) );
			ctr.setSize( new Dimension( s.height, s.width ) );		
			
			ImageIcon ico = (ImageIcon)this.getExpandButton().getIcon();
			this.getExpandButton().setIcon( new ImageIcon( BasicPainter2D.rotate( (BufferedImage)ico.getImage(), 90 ) ) );
		}
				
		switch ( orentation )
		{
			case SOUTH:
			{
				super.add( expPanel, BorderLayout.SOUTH );
				
				break;
			}
			case EAST:
			{
				super.add( expPanel, BorderLayout.EAST );
				
				break;
			}
			case WEST:
			{
				super.add( expPanel, BorderLayout.WEST );
				
				break;
			}			
			default:
			{
				super.add( expPanel, BorderLayout.NORTH );
				
				break;
			}
		}
	}
	
	public void setContentPanel( Component c )
	{
		this.containerPanel.add( c, BorderLayout.CENTER );
	}
	
	public void removeContentPanel()
	{
		this.containerPanel.removeAll();
	}
}
