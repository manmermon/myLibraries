/**
 * 
 */
package GUI.progressbar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * @author Manuel Merino
 *
 */
public class TwoWayProgressBar extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	
	private Color barColor;
	
	private double rightValue = 50;
	private double leftValue = -50;
	
	private double max = 100;
	private double middle = 0;
	private double min = -100;
	
	private float middleStickThickness = 2.5F;
	
	private boolean inverted = false;
	private boolean editable = false;
	private boolean paintValue = true;
	private boolean allowExceedExtrema = false;
	
	private boolean gradientColor = false;
	
	private int orientation = HORIZONTAL;
	
	private String fixedText = null; 
	
	private String text = "";
	
	private int sideInteraction = 0;
	
	private EventListenerList listenerList;
		
	public TwoWayProgressBar( )
	{
		super();
		
		this.listenerList = new EventListenerList();
		
		this.barColor = new Color( 25, 25, 255 );
		super.setBackground( Color.LIGHT_GRAY );
		super.setForeground( Color.BLACK );
				
		super.setPreferredSize( new Dimension( 100, 20 ) );
		super.setSize( super.getPreferredSize() );
		
		this.addInteractions();
	}
		
	public TwoWayProgressBar( double min, double middle, double max )
	{
		this.setExtremaBarValues( min, middle, max );
			
		this.addInteractions();
	}
	
	public double getBarMinValue()
	{
		return this.min;
	}
	
	public double getBarMaxValue()
	{
		return this.max;
	}
	
	public double getBarMiddleValue()
	{
		return this.middle;
	}
	
	public void setAllowExceedExtremaValue( boolean allow )
	{
		this.allowExceedExtrema = allow;
	}
	
	public boolean getAllowExceedExtremaValue( )
	{
		return this.allowExceedExtrema;
	}
	
	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getEditable( )
	{
		return this.editable;
	}
	
	/**
	 * 
	 * @param min
	 * @param middle
	 * @param max
	 * 
	 * @throws IllegalArgumentException if min > middle or middle > max
	 */
	public void setExtremaBarValues( double min, double middle, double max )
	{
		if( min > middle || middle > max )
		{
			throw new IllegalArgumentException( "Input value incorrect (Min <= Middle <= Max).");
		}
		
		this.min = min;
		this.middle = middle;
		this.max = max;
		
		this.repaint();
	}
	
	public double[] getExtremaBarValues( )
	{
		return new double[] { this.min, this.middle, this.max };
	}
		
	/**
	 * @return the barColor
	 */
	public Color getBarColor()
	{
		return this.barColor;
	}
	
	/**
	 * 
	 * @param color
	 */
	public void setBarColor( Color color )
	{
		this.barColor = color;
		
		this.repaint();
	}
	
	public void setOrientation( int orientation )
	{
		int aux = this.orientation;
		
		if( orientation != HORIZONTAL && orientation != VERTICAL )
		{
			this.orientation = HORIZONTAL;
		}
		else
		{
			this.orientation = orientation;
		}
		
		if( aux != this.orientation )
		{
			Dimension d = super.getPreferredSize();
			Dimension d2 = new Dimension( d.height, d.width );
			
			super.setPreferredSize( d2 );
			
			d = super.getSize();
			d2 = new Dimension( d.height, d.width );
			
			//super.setSize( d2 );
		}
		
		this.repaint();
	}
	
	public void setInverted( boolean invert)
	{
		this.inverted = invert;
		
		this.repaint();
	}
	
	public void setLeftValue( double val )
	{
		double v = this.leftValue;
		this.leftValue = val;
		
		if( this.leftValue < this.min && !this.allowExceedExtrema )
		{
			this.leftValue = this.min;
		}
		else if( this.leftValue > this.middle )
		{
			this.leftValue = this.middle;
		}
		
		if( v != this.leftValue )
		{
			this.repaint();
			
			ChangeEvent ev = new ChangeEvent( this );
			this.fireChangeEvent( ev );
		}
	}
	
	public double getLeftValue( )
	{
		return this.leftValue;
	}
	
	
	public void setRightValue( double val )
	{
		double v = this.rightValue;
		this.rightValue = val;
		
		if( this.rightValue < this.middle )
		{
			this.rightValue = this.middle;
		}
		else if( this.rightValue > this.max && !this.allowExceedExtrema)
		{
			this.rightValue = this.max;
		}
		
		if( v != this.rightValue )
		{	
			this.repaint();
			
			ChangeEvent ev = new ChangeEvent( this );
			this.fireChangeEvent( ev );
		}
	}
	
	public double getRightValue( )
	{
		return this.rightValue;
	}
	
	public void setPaintedText( boolean s )
	{
		this.paintValue = s;
		
		this.repaint();
	}
	
	public void setGradientColor( boolean gr )
	{
		this.gradientColor = gr;
		
		this.repaint();
	}
	
	public void setString( String text )
	{
		this.fixedText = text;
		
		super.repaint();
	}
	
	public String getString()
	{
		return this.text;
	}
	
	protected void paintComponent( Graphics g )
	{
		super.paintComponents( g );
		
		Insets inset = super.getInsets();
		
		Point loc = new Point( inset.left, inset.top );
		int Width = getWidth() - inset.left - inset.right;
		int Heigh = getHeight()- inset.top - inset.bottom;
		
		if( super.isEnabled() )
		{
			g.setColor( super.getBackground() );
			g.fillRect( loc.x, loc.y, Width, Heigh );
		
			double wide = this.max - this.min;
			
			double leftDist = ( this.leftValue - this.min ) / wide;
			double rigthDist = 1D - ( this.max - this.rightValue ) / wide;
			
			int x = loc.x + (int)( Width * leftDist );
			int y = loc.y;
			int w = (int)( Width * rigthDist ) - x; 
			int h = Heigh;
			
			int length = Heigh;
			
			boolean vertical = ( this.orientation == VERTICAL ); 
			if( vertical )
			{	
				leftDist = 1D - leftDist;
				rigthDist = 1D - rigthDist;
				x = loc.x;
				y = loc.y  + (int)( Heigh * rigthDist );
				w = Width; 
				h = (int)( Heigh * leftDist ) - y;
				
				length = Width;
			}
			
			this.drawProgressBar( g, x, y, w, h, this.barColor, vertical );			
			
			Point midLoc = this.getMiddleStickLocation();
			
			this.drawMiddleStick( g, midLoc.x, midLoc.y, length, vertical );
			
			BasicStroke b = new BasicStroke( 1 );			
			((Graphics2D)g).setStroke( b );	
			((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
			g.setColor( Color.BLACK );			
			g.drawRect( loc.x, loc.y, Width - 1, Heigh - 1 );
			
			if( this.paintValue )
			{
				this.drawValues( g );
			}
		}
	}
	
	private void addInteractions()
	{
		super.addMouseListener( new MouseAdapter()
		{
			/*(non-Javadoc)
			 * @see @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseReleased(MouseEvent e)
			{
				sideInteraction = 0;
			}
		});
		
		super.addMouseMotionListener( new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent arg0) 
			{
				if( editable && isEnabled() )
				{
					Insets i = getInsets();
						
					double valPerc = 0;
										
					if( orientation == HORIZONTAL )
					{
						int px = arg0.getX();						
						
						int w = getWidth() - i.left - i.right;
						
						if( px < i.left )
						{
							px = 0;
						}
						else if( px > getWidth() - i.right  )
						{
							px = w ;
						}
					
						valPerc = ( 1D * px )/ w;
					}
					else
					{
						int py = arg0.getY();
						
						int h = getHeight() - i.top - i.bottom;
						
						if( py < i.top )
						{
							py = 0;
						}
						else if( py > getHeight() - i.bottom  )
						{
							py = h ;
						}
						
						valPerc = ( 1D * py )/ h;						
					}	
																						
					double wide = ( max - min );
					
					if( orientation == VERTICAL )
					{
						valPerc = 1 - valPerc; 
					}
					
					double newValue = wide * valPerc + min;
					if( sideInteraction == 0)
					{	
						sideInteraction = 1;
						if( newValue > middle )
						{	
							setRightValue( newValue );
						}
						else
						{
							sideInteraction = -1;
							setLeftValue( newValue );
						}
					}
					else if( sideInteraction > 0 )
					{
						if( newValue > middle )
						{	
							setRightValue( newValue );
						}
						else
						{
							setRightValue( middle );
						}
					}
					else if( sideInteraction < 0 )
					{
						if( newValue < middle )
						{	
							setLeftValue( newValue );
						}
						else
						{
							setLeftValue( middle );
						}
					}
				}
			}

			public void mouseMoved(MouseEvent arg0)
			{				
			}
			
		});
	}
		
	private void drawProgressBar( Graphics g, int x, int y, int width, int height, Color c, boolean vertical )	
	{ 
		if( isEnabled() )
		{
			if( this.gradientColor )
			{
				float[] hsb = Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), null);

				float minSat = 0.2F; 
				if( vertical )
				{
					for( int i = 0; i < width; i++ )
					{
						float s = (i * ( 1.0F - minSat) / width) + minSat;
						g.setColor( new Color( Color.HSBtoRGB( hsb[ 0 ], s, hsb[ 2])));
						g.drawLine( x + i, y, x + i, y + height);
					}
				}
				else
				{
					for( int i = 0; i < height; i++ )
					{
						float s = (i * ( 1.0F - minSat) / height) + minSat;
						g.setColor( new Color( Color.HSBtoRGB( hsb[ 0 ], s, hsb[ 2])));
						g.drawLine( x, y + i, x + width, y + i);
					}
				}
			}
			else
			{
				g.setColor( c );
				g.fillRect( x, y, width, height);
			}
		}
	}
	
	private void drawMiddleStick( Graphics g, int x, int y, int length, boolean vertical )
	{
		Color c = super.getBackground();
		if( c == null )
		{
			c = Color.BLACK;
		}
		
		BasicStroke b = new BasicStroke( this.middleStickThickness );
		
		((Graphics2D)g).setStroke( b );	
		((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
		g.setColor( c );
		
		int x1 = x;
		int y1 = y;
		int x2 = x1;
		int y2 = y1 + length;
		
		if( vertical )
		{
			x2 += length;
			y2 -= length;
		}
		
		g.drawLine( x1, y1, x2, y2 );
	}
	
	public Point getMiddleStickLocation()
	{
		Insets inset = super.getInsets();
		Point loc = new Point( inset.left, inset.top );
		
		int Width = getWidth() - inset.left - inset.right;
		int Heigh = getHeight()- inset.top - inset.bottom;
		
		double wide = this.max - this.min;
		double midDist = ( this.middle - this.min ) / wide;
		
		int xm = loc.x + (int)( Width * midDist );
		int ym = loc.y;
		
		if( this.orientation == VERTICAL )
		{
			xm = loc.x;
			ym = loc.y + (int)(Heigh * midDist );
		}
		
		return new Point( xm, ym );
	}
	
	private void drawValues( Graphics g )
	{
		Color c = super.getForeground();		
		if( !isEnabled() )
		{
			c = Color.GRAY;
		}
		
		if( c == null )
		{
			c = Color.BLACK;
		}
		
		Font f = super.getFont();
		g.setFont( new Font( f.getFontName(), Font.BOLD, f.getSize() ) );
		FontMetrics fm = g.getFontMetrics( g.getFont() );
				
		this.text = "(%.2f, %.2f)";
		this.text = String.format(Locale.ROOT, this.text, this.leftValue, this.rightValue );
		if( this.fixedText != null )
		{
			this.text  = this.fixedText;
		}
		
		Point loc = super.getLocation();
		
		Insets ins = super.getInsets();
		
		int w = getWidth() - ins.left - ins.right;
		int h = getHeight() - ins.top - ins.bottom;
		int xs = ( w - fm.stringWidth( this.text ) ) / 2 + ins.left;
		int ys = ( h - fm.getHeight()) / 2 + fm.getAscent() + ins.top;
		
		
		AffineTransform defaultAT = ((Graphics2D)g).getTransform();
		if( this.orientation == VERTICAL )
		{
			AffineTransform at = new AffineTransform();
			at.setToRotation( -Math.PI / 2, w / 2, h / 2 );
			
			((Graphics2D)g).setTransform(at);
			xs -= ( fm.getHeight() - fm.getDescent() ) / 2;
			ys += loc.x;
			
		}
		
		g.setColor( c );
		g.drawString( this.text , xs , ys);
		((Graphics2D)g).setTransform( defaultAT );
	}
	
	public synchronized void addChangeListener( ChangeListener listener ) 
	{
		this.listenerList.add( ChangeListener.class, listener );
	}
	
	public synchronized void removeChangeListener( ChangeListener listener ) 
	{
		this.listenerList.remove( ChangeListener.class, listener );		
	}
	
	private synchronized void fireChangeEvent( ChangeEvent event )
	{
		ChangeListener[] listeners = this.listenerList.getListeners( ChangeListener.class );

		for (int i = 0; i < listeners.length; i++ ) 
		{
			listeners[ i ].stateChanged( event );
		}
	}
}
