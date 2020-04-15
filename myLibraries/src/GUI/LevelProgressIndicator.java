/**
 * 
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

/**
 * @author manuel
 *
 */
public class LevelProgressIndicator extends JComponent 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	
	private int numLevels = 2;
	private Color[] levelsColors = { Color.BLUE, new Color( 238, 238, 238)};
	
	private double value = 0;
	
	private double max = 100;
	private double min = 0;
	
	private boolean inverted = false;
	private boolean editable = false;
	private boolean paintValue = true;
	
	private boolean gradientColor = false;
	
	private int orientation = HORIZONTAL;
	
	private Insets inset = new Insets( 0, 0, 0, 0);
	
	private double[] levels = null;
	
	private String fixedText = null; 
	
	private String text = "";
	
	public LevelProgressIndicator( )
	{
		this( 2 );
	}
	
	public LevelProgressIndicator( int levels )
	{
		super();
		
		if( levels < 2 )
		{
			throw new IllegalArgumentException( "Minimum value = 2.");
		}
		
		Dimension size = new Dimension( 100, 20 );
		super.setPreferredSize( size );
		super.setSize( size );
				
		this.numLevels = levels;
		this.levelsColors = new Color[ levels ];
		
		this.levels = new double[ levels - 1 ];
		double n = this.min;
		this.value = this.min;
		
		for( int i = 0; i < levels - 1; i++ )
		{
			n = n + ( this.max - this.min ) / levels;
			this.levels[ i ] = n;
		}
		
		int c = 0;
		for( int i = 0; i < levels; i++ )
		{	
			c = Color.HSBtoRGB( i * 1.0F / levels , 1, 1);
			this.levelsColors[ i ] = new Color( c );
		}		
		
		this.addInteractions();
	}	
	
	public LevelProgressIndicator( double min, double max, double[] levels )
	{
		if( levels.length == 0 || levels == null )
		{
			throw new IllegalArgumentException( "Input null/empty.");
		}
		
		Dimension size = new Dimension( 100, 20 );
		super.setPreferredSize( size );
		super.setSize( size );
			
		this.min = min;
		this.max = max;
		this.value = min;
		
		this.numLevels = levels.length + 1;
		this.levels = new double[ levels.length ];
		
		System.arraycopy( levels, 0, this.levels, 0, levels.length );
		this.levelsColors = new Color[ this.numLevels + 1];	
	
		int c = 0;
		for( int i = 0; i < this.levelsColors.length; i++ )
		{	
			c = Color.HSBtoRGB( i * 1.0F / this.levelsColors.length , 1, 1);
			this.levelsColors[ i ] = new Color( c );
		}		
		
		this.addInteractions();
	}
		
	public double[] getLevels( )
	{
		return this.levels;
	}
	
	public void setLevels( double[] levels )
	{
		if( levels.length != this.levels.length )
		{
			throw new IllegalArgumentException( "Array length incorrect. Input size must be = " + this.levels.length + ".");
		}
		
		System.arraycopy( levels, 0, this.levels, 0, levels.length );
		
		this.repaint();
	}
	
	public void setLevelColors( Color[] colors)
	{
	
		if( colors != null && colors.length > 0 )
		{		
			for( int i = 0; i < colors.length && i < this.levelsColors.length; i++)
			{
				Color c = colors[ i ];
				if( c != null )
				{
					this.levelsColors[ i ] = c;
				}
			}
			
			this.repaint();
		}
	}
		
	public void setOrientation( int orientation )
	{
		if( orientation != HORIZONTAL && orientation != VERTICAL )
		{
			this.orientation = HORIZONTAL;
		}
		else
		{
			this.orientation = orientation;
		}
		
		this.repaint();
	}
	
	public int getOrientation()
	{
		return this.orientation;
	}
	
	public void setInverted( boolean invert)
	{
		this.inverted = invert;
		
		this.repaint();
	}
	
	public void setValue( double val )
	{
		double v = this.value;
		this.value = val;
		
		if( this.value < this.min )
		{
			this.value = this.min;
		}
		else if( this.value > this.max )
		{
			this.value = this.max;
		}
		
		if( v != this.value )
		{
			this.repaint();
		}
	}
	
	public void setMaximum( double max )
	{	
		if( (this.max - this.min) != 0 )
		{
			double escala = (1.0 * Math.abs( max - this.min ) / Math.abs( this.max - this.min));
			for( int i = 0; i < this.levels.length; i++ )
			{
				this.levels[ i ] = this.min + this.levels[ i ] * escala; 
			}
			
			this.setValue( this.min + this.value * escala );
		}
		
		this.max = max;		
		
		this.repaint();
	}
	
	public void setMinimum( double min )
	{
		if( (this.max - this.min) != 0 )
		{	
			double escala = (1.0 * Math.abs( this.max - min ) / Math.abs( this.max - this.min));
			
			for( int i = 0; i < this.levels.length; i++ )
			{
				this.levels[ i ] = min + this.levels[ i ] * escala;
			}
			
			this.setValue( min + this.value * escala );
		}
		
		this.min = min;	
		
		this.repaint();
	}
	
	public void setPaintedText( boolean s )
	{
		this.paintValue = s;
		
		this.repaint();
	}
	
	public boolean isPaintedText()
	{
		return this.paintValue;
	}
	
	public void setGradientColor( boolean gr )
	{
		this.gradientColor = gr;
		
		this.repaint();
	}
	
	public void setEditable( boolean edit )
	{
		this.editable = edit;
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
	
	public Insets getInsets()
	{
		return this.inset;
	}	
	
	public void setInset( Insets inset )
	{
		if( inset != null )
		{
			this.inset = inset;
		}
	}
	
	public int getNumLevels()
	{
		return this.numLevels;
	}

	public double getValue( )
	{
		return this.value;
	}
	
	public double getMaximum()
	{
		return this.max;
	}
	
	public double getMinimum()
	{
		return this.min;
	}
	
	public boolean isEditable()
	{
		return this.editable;
	}
		
	/*
	public void repaint()
	{
		this.autoSetInsets();
		
		super.repaint();
	}
	//*/
	
	protected void paintComponent( Graphics g )
	{
		super.paintComponents( g );
		
		//this.autoSetInsets();		
			
		Point loc = this.valuePositionPixel( this.min ); //new Point( this.inset.left, this.inset.top );
		
		if( this.inverted && this.orientation != VERTICAL )
		{	
			loc = this.valuePositionPixel( this.max );
		}
		
		
		int w = getWidth() - ( inset.left + inset.right);
		int h = getHeight() - ( inset.top + inset.bottom );
		
		if( super.isEnabled() )
		{
			int indexLevel = 0;
			while( indexLevel < this.levels.length )
			{
				double lv = this.levels[ indexLevel ]; 
				if( this.value > lv )
				{
					Color c = this.levelsColors[ indexLevel ];
					
					Point lvLoc = this.valuePositionPixel( lv );
					
					if( this.orientation != VERTICAL )
					{
						if( !this.inverted )
						{
							this.drawGradientRect(g, loc.x, loc.y, Math.abs( lvLoc.x - loc.x ), h, c, false);
						}
						else
						{
							this.drawGradientRect(g, lvLoc.x, lvLoc.y, Math.abs( lvLoc.x - loc.x ), h, c, false);
						}						
						
						loc.x = lvLoc.x;
					}
					else
					{							
						if( !this.inverted )
						{	
							this.drawGradientRect(g, lvLoc.x, lvLoc.y, w,  Math.abs( loc.y - lvLoc.y ), c, false);
							
						}
						else
						{
							this.drawGradientRect(g, loc.x, loc.y, w,  Math.abs( loc.y - lvLoc.y ), c, false);
						}
						
						loc.y = lvLoc.y;
					}
					
					indexLevel++;
				}
				else
				{
					break;
				}				
			}
			
			Point lvLoc = this.valuePositionPixel( this.value );
			Color c = this.levelsColors[ indexLevel ];
			if( this.orientation != VERTICAL )
			{
				if( !this.inverted )
				{
					this.drawGradientRect(g, loc.x, loc.y, Math.abs( lvLoc.x - loc.x ), h, c, false);
				}
				else
				{
					this.drawGradientRect(g, lvLoc.x, lvLoc.y, Math.abs( lvLoc.x - loc.x ), h, c, false);
				}
			}
			else
			{
				if( !this.inverted )
				{	
					this.drawGradientRect(g, lvLoc.x, lvLoc.y, w,  Math.abs( loc.y - lvLoc.y ), c, false);
				}
				else
				{
					this.drawGradientRect(g, loc.x, loc.y, w,  Math.abs( loc.y - lvLoc.y ), c, false);
				}
			}
			
		}
		else
		{
			boolean ver = false;
			if( this.orientation == VERTICAL )
			{
				ver = true;
			}
			this.drawGradientRect( g , inset.left, inset.top, w, h, Color.lightGray, ver);
		}
		
		Color c = new Color( 238, 238, 238 ).darker();
		if( !isEnabled() )
		{
			c = Color.gray;
		}		
		g.setColor( c );	
		
		
		if( orientation == HORIZONTAL )
		{
			w -= 1;
		}
		else
		{
			h -= 1;
		}
		
		g.drawRect( inset.left, inset.top, w - 1, h - 1);
		
		if( this.paintValue )
		{
			this.drawValue( g );
		}
	}
	
	private void addInteractions()
	{
		super.addMouseMotionListener( new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent arg0) 
			{
				if( editable && isEnabled() )
				{
					if( orientation == HORIZONTAL )
					{
						int pxStick = arg0.getX();
						Insets i = getInsets();
						
						if( pxStick < i.left )
						{
							pxStick = i.left;
						}
						else if( pxStick > getWidth() - i.right  )
						{
							pxStick = getWidth() - i.right;
						}
						
						setValue( getValuePx( pxStick ));					
					}
					else
					{
						int pyStick = arg0.getY();
						Insets i = getInsets();
						
						if( pyStick < i.top )
						{
							pyStick = i.top;
						}
						else if( pyStick > getHeight() - i.bottom)
						{
							pyStick = getHeight() - i.bottom;
						}
						
						setValue( getValuePx( pyStick ));
					}	
				}
			}

			public void mouseMoved(MouseEvent arg0) {
 
				
			}
			
		});
	}
		
	/*
	private void autoSetInsets()
	{
		int w = this.getWidth(), h = super.getHeight();
		
		int top = ( int) ( h * 0.1);
		int bottom = top;
		int left = 0, right = 0;
		if( orientation == VERTICAL )
		{
			left = right = ( int) ( w * 0.1);
			top = bottom = 0;
		} 
		
		if( top > 20 )
		{
			top = bottom = 20;
		}
		
		if( left > 20 )
		{
			left = right = 20;
		}
		
		this.setInsets( top, left, bottom, right);
	}
	//*/
	
	protected void drawGradientRect( Graphics g, int x, int y, int width, int height, Color c, boolean vertical )	
	{ 
		if( isEnabled() && this.gradientColor )
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
	
	private void drawValue( Graphics g)
	{
		Color c = Color.black;		
		if( !isEnabled() )
		{
			c = Color.gray;
		}
		
		g.setFont( super.getFont() );
		FontMetrics fm = g.getFontMetrics( g.getFont() );
				
		this.text = String.format("%.2f", this.value );
		if( this.fixedText != null )
		{
			this.text  = this.fixedText;
		}
		
		int xs = ( getWidth() - fm.stringWidth( this.text ) ) / 2;
		int ys = ( getHeight() - fm.getHeight()) / 2 + fm.getAscent();
		
		g.setColor( c );						
		g.drawString( this.text , xs , ys);
	}
	
	protected Point valuePositionPixel( double val )
	{
		Point loc = new Point(); 
		
		loc.x = inset.left + (int)(( super.getWidth() - inset.left - inset.right ) * ( ( val - this.min ) / ( this.max - this.min)));
		loc.y = inset.top;
		
		if( this.inverted )
		{	
			loc.x = inset.left + (int)(( super.getWidth() - inset.left - inset.right ) * ( ( this.max - val ) / ( this.max - this.min)));
		}
		
		if( loc.x < inset.left )
		{
			loc.x = inset.left;
		}
		else if( loc.x > super.getWidth() - inset.right )
		{
			  loc.x = super.getWidth() - inset.right;
		}
				 		
		if( this.orientation == VERTICAL )
		{
			loc.x = inset.left;
			loc.y = inset.top + (int)(( super.getHeight() - inset.top - inset.bottom ) * ( (val - this.min) / ( this.max - this.min)));
			
			if( !this.inverted )
			{
				loc.y = inset.top + (int)(( super.getHeight() - inset.top - inset.bottom ) * ( (this.max - val) / ( this.max - this.min)));
			}
			
			if( loc.y > super.getHeight() - inset.bottom )
			{
				loc.y = super.getHeight() - inset.bottom;
			}
			else if( loc.y < inset.top )
			{
				  loc.y = inset.top;
			}
		}
		
		return loc;
	}
		
	protected double getValuePx( int coord )
	{
		int w = super.getWidth();
		int h = super.getHeight();
		
		double val =  1.0 * ( coord - inset.left) / ( w - inset.left - inset.right ); 
		val = this.min + val * ( this.max - this.min ) ;		
				
		if( coord > w - inset.right )
		{
			val = this.max;
		}
		else if( coord < inset.left )
		{
			val = this.min;
		}
		
		if( this.inverted )
		{
			val =  this.max - (val - this.min);
			
			if( coord > w - inset.right )
			{
				val = this.min;
			}
			else if( coord < inset.left )
			{
				val = this.max;
			}
		}
		
		if( this.orientation == VERTICAL )
		{
			val =  1.0 * ( coord - inset.top ) / ( h - inset.bottom - inset.top ); 
			val = max - val * ( this.max - this.min );
			
			if( coord < inset.top )
			{
				val = this.max;
			}
			else if( coord > h - inset.bottom )
			{
				val = this.min;
			}
			
			if( this.inverted )
			{
				val = min + max - val;
				
				if( coord < inset.top )
				{
					val = this.min;
				}
				else if( coord > h - inset.bottom )
				{
					val = this.max;
				}
			}
		}
		
		return val;
	}
}
