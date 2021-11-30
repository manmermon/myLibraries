package GUI.buttom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import image.BasicPainter2D;

public class GeometricButtom extends JButton
{
	public static final int OTHER = -1;
	public static final int RECTANGLE = 0;
	public static final int ELLIPSE = 1;
	public static final int ROUNDED_RECTANBLE = 2;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type = RECTANGLE;
	private Shape figure;
	
	private BasicStroke bstr;
	
	private boolean autoFontSize = false;
	
	private Font defaultFont = null;
	
	public GeometricButtom( )
	{
		super( );
		
		this.setBorderThickness( 1 );
		
		this.figure = new Rectangle( super.getLocation()
									, super.getPreferredSize() );
		
	    setContentAreaFilled( false );		
	}
	
	public GeometricButtom( int type, String text )
	{
		this( );
		
		switch ( type ) 
		{
			case ELLIPSE:
			{
				this.type = ELLIPSE;
				
				this.figure = new Ellipse2D.Double( super.getLocation().x
													, super.getLocation().y
													, super.getPreferredSize().width
													, super.getPreferredSize().height );
				break;
			}
			case ROUNDED_RECTANBLE:
			{ 
				this.type = ROUNDED_RECTANBLE;
				this.figure = new RoundRectangle2D.Double( super.getLocation().x
															, super.getLocation().y
															, super.getPreferredSize().width
															, super.getPreferredSize().height
															, super.getPreferredSize().width / 2
															, super.getPreferredSize().height );
				break;
			}
			default:
				break;
		}
		
		super.setText( text );	
	}
	
	public GeometricButtom( Shape shape, String text )
	{
		this( );
		
		this.figure = shape;
		
		this.type = OTHER;
		
		super.setText( text );	
	}	
	
	public void setAutoFontSize( boolean auto )
	{
		this.autoFontSize = auto;
		
		if( this.autoFontSize )
		{
			this.adjustFont();
		}
		else if( this.defaultFont != null )
		{
			super.setFont( this.defaultFont );
		}		
	}
	
	private void adjustFont()
	{
		Font f = super.getFont();
		
		this.defaultFont = f;
				
		FontMetrics fm = super.getFontMetrics( f );
		Dimension d = super.getSize();
		Insets pad = super.getInsets();
		String txt = super.getText();

		int w = (int)( this.bstr.getLineWidth() * 2 );
		
		while (fm.stringWidth( txt ) > d.width - pad.left - pad.right - w)
		{
			f = new Font( f.getName(), f.getStyle(), f.getSize() - 1 );
			fm = super.getFontMetrics( f );
		}
		
		while ( fm.stringWidth( txt ) < d.width - pad.left - pad.right - w )
		{
			f = new Font(f.getName(), f.getStyle(), f.getSize() + 1);
			fm = super.getFontMetrics( f );
		}

		if (fm.stringWidth(txt) > d.width - pad.left - pad.right - w )
		{
			f = new Font(f.getName(), f.getStyle(), f.getSize() - 1);
			fm = super.getFontMetrics(f);
		}

		super.setFont( f );
	}
	
	public void setBorderThickness( int thickness)
	{
		this.bstr = new BasicStroke( thickness ); 
	}
	
	@Override
	public void setSize( Dimension d ) 
	{
		super.setSize( d );
		
		if( this.autoFontSize )
		{
			this.adjustFont();
		}
		
		switch ( this.type )
		{
			case ELLIPSE:
			{
				this.figure = new Ellipse2D.Double( super.getLocation().x
												, super.getLocation().y
												, super.getSize().width
												, super.getSize().height );
				break;
			}
			case ROUNDED_RECTANBLE:
			{ 
				this.figure = new RoundRectangle2D.Double( super.getLocation().x
															, super.getLocation().y
															, super.getSize().width
															, super.getSize().height
															, super.getSize().width / 2
															, super.getSize().height );
				
				break;
			}
			case OTHER:
			{
				break;
			}
			default:
			{
				this.figure = new Rectangle( super.getLocation(), d );
				break;
			}
		}
	}
	
	protected void paintComponent( Graphics g )
	{
		if( getModel().isArmed() )
		{			
			Color c = super.getBackground();
			
			if( c != null )
			{
				g.setColor( c.darker() );
			}
		}
		else
		{
			g.setColor( super.getBackground() );
		}
		
		double w = (double)this.bstr.getLineWidth();
		Dimension size = this.figure.getBounds().getSize();
		
		AffineTransform at = new AffineTransform();		
		at.scale( ( size.width - w ) / size.width, ( size.height - w ) / size.height );		
		at.translate( w / 2, w / 2 );
		
		Shape fig = at.createTransformedShape( this.figure ); 
		
		((Graphics2D)g).fill( fig );
		
		super.paintComponent( g );
	}
	
	protected void paintBorder( Graphics g )
	{
		Color c = super.getBackground();
		if( c != null )
		{
			g.setColor( c.darker() );
		}
		
		((Graphics2D)g).setStroke( this.bstr );
		
		double w = (double)this.bstr.getLineWidth();
		
		Dimension size = this.figure.getBounds().getSize();
		
		AffineTransform at = new AffineTransform();		
		at.scale( ( size.width - w ) / size.width, ( size.height - w ) / size.height );		
		at.translate( w / 2, w / 2 );
		
		Shape outline = at.createTransformedShape( this.figure ); 
		
		((Graphics2D)g).draw( outline );
	}
	
	public boolean contains( int x, int y )
	{
		if( this.figure == null || this.figure.getBounds().equals( super.getBounds() ) )
		{
			this.figure = new Rectangle( super.getLocation(), super.getSize() );
		}
		
		return this.figure.contains( x, y );
	}
}
