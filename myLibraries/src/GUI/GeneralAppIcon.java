package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import org.jgrasstools.gears.utils.math.interpolation.splines.Bspline;

import com.vividsolutions.jts.geom.Coordinate;

import image.basicPainter2D;

public class GeneralAppIcon
{
	public static final int SMALL_SIZE_ICON = 16;
	public static final int REGULAR_SIZE_ICON = 32;
	public static final int BIG_SIZE_ICON = 48;
	public static final int BIG_PLUS_SIZE_ICON = 64;
	public static final int HUGE_SIZE_ICON = 128;

	public static ImageIcon WindowMax( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 8;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.createEmptyCanva( size, size, null );

			basicPainter2D.polygonLine( new int[ ]{ size / 2 - thick, thick, thick, size - thick/2, size - thick/2 }, 
					new int[ ]{ thick/2, thick/2, size - thick/2, size - thick/2, size / 2 + thick}, 
					thick, color, 
					img);

			basicPainter2D.fillPolygon( new int[]{ size / 2, size, size }, 
					new int[]{ 0, 0, size / 2 }, 
					color, img);

			ico = new ImageIcon( img );
		}

		return ico;
	}
	
	public static ImageIcon WindowMinimize( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 8;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.createEmptyCanva( size, size, null );

			basicPainter2D.line( 0, size - thick, size, size - thick, thick, color, img);

			Image imgTr = basicPainter2D.triangle( size / 3 + thick, 1, color, color, basicPainter2D.SOUTH );
			basicPainter2D.composeImage( img, ( size - imgTr.getWidth( null ) ) / 2 , size - imgTr.getHeight( null ) - thick, imgTr );

			basicPainter2D.line( size / 2 - thick / 2 , 0, size / 2 - thick / 2, size / 2, thick, color, img );
			
			ico = new ImageIcon( img );
		}

		return ico;
	}
	
	public static ImageIcon WindowUndoMaximize( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 8;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.createEmptyCanva( size, size, null );

			Image r = basicPainter2D.rectangle( ( 3 * size ) / 4, ( 3 * size ) / 4, thick, color, null );

			basicPainter2D.composeImage( img, 0, size - r.getHeight( null), r );
			basicPainter2D.line( size / 2 - thick / 2, size - r.getHeight( null), size / 2 - thick / 2, 0, thick, color, img );
			basicPainter2D.line( size / 2 - thick / 2, thick / 2, size, thick / 2, thick, color, img );
			basicPainter2D.line( size - thick / 2, 0, size - thick / 2, size / 2, thick, color, img );
			basicPainter2D.line( r.getWidth( null ), size / 2, size, size / 2, thick, color, img );
			
			ico = new ImageIcon( img );
		}

		return ico;
	}

	public static ImageIcon Convert( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{			
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			int radio = size / 2 - thick;
			
			Image img = basicPainter2D.createEmptyCanva( size, size, null );
			
			Image circ = basicPainter2D.circle( 0, 0, radio, color, null );
			Image circf = basicPainter2D.circumference( 0, 0, radio, thick, color, null );
			
			basicPainter2D.composeImage( img, 0, 0, circ );
			basicPainter2D.composeImage( img, img.getWidth( null ) - circf.getWidth( null ), img.getHeight( null ) - circf.getHeight( null ), circf );
						
			basicPainter2D.line( circ.getWidth( null ) - thick, circ.getHeight( null ) - thick
												, img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) + thick
												, thick, color, img );
			
			basicPainter2D.line( img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) + thick
												, img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) - 2 * thick
												, thick, color, img );
			
			basicPainter2D.line( img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) + thick
												, img.getWidth( null ) - circf.getWidth( null ) - 2 * thick, img.getHeight( null ) - circf.getHeight( null ) + thick
												, thick, color, img );
			
			
			ico = new ImageIcon( img );
		}

		return ico;
	}
	
	public static ImageIcon NewFile( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.rectangle( size, size, thick, color, null );

			basicPainter2D.line( size / 4, size / 4, size - size / 4, size / 4, thick, color, img );
			basicPainter2D.line( size / 4, size / 2, size - size / 4, size / 2, thick, color, img );
			basicPainter2D.line( size / 4, ( 3 * size ) / 4, size - size / 4, ( 3 * size ) / 4, thick, color, img );

			ico = new ImageIcon( img );
		}

		return ico;
	}

	public static ImageIcon LoadFile( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			int w = size / 2;

			Image img = basicPainter2D.composeImage( 
					basicPainter2D.createEmptyCanva( size, size, null ), 
					0, 0,  
					basicPainter2D.rectangle( w - thick, size - thick, thick, color, null ) );

			basicPainter2D.line( w / 4, size / 4, w - w / 4 - thick, size / 4, thick, color, img );
			basicPainter2D.line( w / 4, size / 2, w - w / 4 - thick, size / 2, thick, color, img );
			basicPainter2D.line( w / 4, ( 3 * size ) / 4, w - w / 4 - thick, ( 3 * size ) / 4, thick, color, img );

			basicPainter2D.fillPolygon( new int[]{ w + thick, w + thick, size }, 
					new int[]{ size / 4, size - size / 4, size / 2 }, 
					color, img);

			ico = new ImageIcon( img );

		}

		return ico;
	}

	public static ImageIcon LoadFolder( int width, int height, Color borderColor, Color fillColor )
	{
		int w = width - 2;
		int h = height - 2;

		if( borderColor == null && fillColor == null )
		{
			borderColor = Color.BLACK;
		}

		Image img = basicPainter2D.createEmptyCanva( w, h, null );

		Image imgAux = Folder( 2 * w / 3, h, borderColor, fillColor ).getImage();
		Image imgAux2 = basicPainter2D.triangle( w / 3, 1, borderColor, borderColor, basicPainter2D.EAST );

		basicPainter2D.composeImage( img, 0, 0, imgAux );
		return new ImageIcon( basicPainter2D.composeImage( img, 2 * w / 3, h / 2 - w / 6, imgAux2 ) );
	}

	public static ImageIcon SaveFile( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			int w = size / 2;

			Image img = basicPainter2D.composeImage( 
					basicPainter2D.createEmptyCanva( size, size, null ), 
					0, 0,  
					basicPainter2D.rectangle( w - thick, size - thick, thick, color, null ) );

			basicPainter2D.line( w / 4, size / 4, w - w / 4 - thick, size / 4, thick, color, img );
			basicPainter2D.line( w / 4, size / 2, w - w / 4 - thick, size / 2, thick, color, img );
			basicPainter2D.line( w / 4, ( 3 * size ) / 4, w - w / 4 - thick, ( 3 * size ) / 4, thick, color, img );

			basicPainter2D.fillPolygon( new int[]{ size, size, w + thick }, 
					new int[]{ size / 4, size - size / 4, size / 2 }, 
					color, img);

			ico = new ImageIcon( img );

		}

		return ico;
	}

	public static ImageIcon Error( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.createEmptyCanva( size, size, null );

			basicPainter2D.line( 0, 0, size, size, thick, color, img );
			basicPainter2D.line( 0, size, size, 0, thick, color, img );

			basicPainter2D.composeImage( img, size / 4, size / 4, 
					basicPainter2D.rectangle( size / 2, size / 2, thick, color, null ) );

			ico = new ImageIcon( img );
		}

		return ico;
	}

	public static ImageIcon Question( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			//Image img = imagenPoligono2D.crearLienzoVacio( size, size, null );

			Image img = basicPainter2D.rectangle( size-thick/2, size-thick/2, thick, color, null );

			Font f = new Font( Font.DIALOG, Font.BOLD, 12 );
			FontMetrics fm = img.getGraphics().getFontMetrics( f );			
			while( fm.getHeight() < size )
			{
				f = new Font( f.getName(), Font.BOLD, f.getSize() + 1 );
				fm = img.getGraphics().getFontMetrics( f );
			}

			Image imgTxt = basicPainter2D.text( 0, 0, "?", fm, color, color, null );
			int x = (int)Math.round( ( size - imgTxt.getWidth( null ) ) / 2.0 );
			int y = 0;
			img = basicPainter2D.composeImage( img, x, y, imgTxt );

			ico = new ImageIcon( img );
		}

		return ico;
	}

	public static ImageIcon Warning( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{
			int thick = size / 8;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.createEmptyCanva( size, size, null );

			basicPainter2D.outlinePolygon( new int[]{ thick - 1, size / 2, size - thick + 1},
					new int[]{ size - thick/2, thick, size -thick/2},
					thick, color, img );

			Font f = new Font( Font.DIALOG, Font.PLAIN, 12 );
			FontMetrics fm = img.getGraphics().getFontMetrics( f );			
			while( fm.getHeight() < ( size * 3 ) / 4 )
			{
				f = new Font( f.getName(), Font.PLAIN, f.getSize() + 1 );
				fm = img.getGraphics().getFontMetrics( f );
			}

			while( fm.getHeight() > ( size * 3 ) / 4 )
			{
				f = new Font( f.getName(), Font.PLAIN, f.getSize() - 1 );
				fm = img.getGraphics().getFontMetrics( f );
			}

			Image imgTxt = basicPainter2D.text( 0, 0, "!", fm, color, color, null );
			int x = (int)Math.round( ( size - imgTxt.getWidth( null ) ) / 2.0 );
			int y = size - imgTxt.getHeight( null ) + 1;
			img = basicPainter2D.composeImage( img, x, y, imgTxt );

			ico = new ImageIcon( img );
		}

		return ico;
	}

	public static ImageIcon ImageIcon( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{	
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.rectangle( size, size, 1, color, null);

			int[] x = new int[ size ];
			int[] y = new int[ size ];
			int h = (int)( size * 0.75 );
			for( int i = 0; i < size; i++ )
			{
				x[ i ] = i;
				y[ i ] = (int)(( ( Math.sin( ( i * 4 * Math.PI ) / size ) + 1 ) / 2 ) * h ) + ( size - h ) / 2;
			}

			basicPainter2D.polygonLine( x, y, thick, color, img );

			ico = new ImageIcon( img );
		}

		return ico;
	}

	public static ImageIcon Close( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{	
			int thick = size / 3;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.createEmptyCanva( size, size, null  );

			basicPainter2D.line( 0, 0, size, size, thick, color, img );
			basicPainter2D.line( 0, size, size, 0, thick, color, img );

			ico = new ImageIcon( img );
		}

		return ico;
	}

	public static ImageIcon Pencil( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{	
			int thick = size / 16;

			if( thick < 1 )
			{
				thick = 1;
			}

			int shiftThick = thick / 2;
			if( shiftThick == 0 )
			{
				shiftThick = 1;
			}

			Image img = basicPainter2D.createEmptyCanva( size, size, null  );

			double div = size / 10.0;
			int[] xs = new int[]{ size / 2 , size - shiftThick, size / 2, shiftThick  };			
			int[] ys = new int[]{ shiftThick, size / 2, size - shiftThick, size / 2  };

			basicPainter2D.outlinePolygon(xs, ys, thick, color, img );

			basicPainter2D.line( (int)( div * 1.5 ) + shiftThick, (int)( div * 6.5 ) - shiftThick, (int)( div * 6.5 ) - shiftThick, (int)( div * 1.5 ) + shiftThick, thick, color, img);
			basicPainter2D.line( (int)( div * 3.5 ) + shiftThick, (int)( div * 8.5 ) - shiftThick, (int)( div * 8.5) - shiftThick, (int)( div * 3.5 ) + shiftThick, thick, color, img);

			basicPainter2D.line( shiftThick, size / 2 + shiftThick, shiftThick, size, thick, color, img);
			basicPainter2D.line( 0, size - shiftThick, size / 2 - shiftThick, size - shiftThick, thick, color, img);

			xs = new int[]{ shiftThick, shiftThick, (int)( 2.5 * div ) };
			ys = new int[]{ (int)( 7.5 * div ), size - shiftThick, size - shiftThick };

			basicPainter2D.fillPolygon(xs, ys, color, img );

			ico = new ImageIcon( img );			
		}

		return ico;
	}

	public static ImageIcon Config(  )
	{
		Image icono = null, aux = null;		
		Color colorRelleno = Color.LIGHT_GRAY;
		Color colorBorde = Color.BLACK;
		int width = 100,  height = 100;
		icono = basicPainter2D.createEmptyCanva( width, height, null );

		int xs[] = { 0, 0, width / 6, width / 6, 2 * width / 6 , 2 * width / 6, width / 2, width / 2 };
		int ys[] = { height / 4, 0, 0, height / 4, height / 4, 0, 0, height / 4 };

		basicPainter2D.fillPolygon( xs, ys, colorRelleno, icono );
		basicPainter2D.outlinePolygon( xs, ys, 1.0F, colorBorde, icono );		

		aux = basicPainter2D.rectangle( width / 6 , height / 2 -1, 1.0F, colorBorde, colorRelleno );		
		basicPainter2D.composeImage( icono, width / 6 , height / 2 , aux );

		basicPainter2D.arc( 0, 0, width / 2, height / 2, 
				180, 180, 1.0F, colorBorde, colorRelleno, icono );
		basicPainter2D.line( width / 6, height / 4, 2 * width / 6, height / 4 , 1.0F, colorBorde, icono );
		xs = new int[ 8 ];
		ys = new int[ 8 ];		
		xs[ 0 ] = width / 2 + width / 6; xs[ 1 ] = xs[ 0 ]; xs[ 2 ] = xs[ 1 ] - width / 12; xs[ 3 ] = xs[ 0 ]; xs[ 4 ] = xs[ 3 ] + width / 6 ; xs[ 5 ] = xs[ 4 ] + width / 12; xs[ 6 ] = xs[ 4 ]; xs[ 7 ] = xs[ 6 ];
		ys[ 0 ] = height / 2; ys[ 1 ] = ys[ 0 ] - height / 6; ys[ 2 ] = ys[ 1 ] - height / 4; ys[ 3 ] = 0; ys[ 4 ] = ys[ 3 ]; ys[ 5 ] = ys[ 2 ]; ys[ 6 ] = ys[ 1 ]; ys[ 7 ] = ys[ 0 ];

		basicPainter2D.fillPolygon( xs, ys, colorRelleno, icono );
		basicPainter2D.outlinePolygon( xs, ys, 1.0F, colorBorde, icono );


		aux = basicPainter2D.rectangle( width / 6 + 4, height / 2, 1.0F, colorBorde, Color.RED);
		basicPainter2D.composeImage( icono, width / 2 + width / 6 - 2, height / 2, aux );

		return new ImageIcon( icono );
	}
	
	public static Image Config2( Color color )
	{	
		if( color == null )
		{
			color = Color.BLACK;
		}
		
		int size = 512;
				
		Image base = basicPainter2D.createEmptyCanva( size, size, null );
		
		int r = size / 3;
		
		int thick = r / 8;
		
		if( thick < 1 )
		{
			thick = 1;
		}
		
		int numLines = 2;		
		
		//Image circ = imagenPoligono2D.circle( 0, 0, r, color, null );
		Image circ = basicPainter2D.dot( 0, 0, r, color, true, null );
		
		Color fill = new Color( ~color.getRGB() );
		//Image circ2 = imagenPoligono2D.circle( 0, 0, r / 2, fill, null );
		Image circ2 = basicPainter2D.dot( 0, 0, ( 2 * r ) / 3, fill, true, null );
		basicPainter2D.composeImage( circ, (int)Math.round( ( circ.getWidth( null ) - circ2.getWidth( null ) ) / 2.0 )
												, (int)Math.round( ( circ.getHeight( null ) - circ2.getHeight( null ) ) / 2.0 )												
												, circ2 );
		
		double h = size / ( 2.0 * numLines );
		int x = r / 2;
		int stepX = ( size - x ) / ( numLines + 1);
		double cc = circ.getHeight( null ) / 2.0;
		
		for( int i = 1; i <= numLines; i++ )
		{
			basicPainter2D.line( 0, (int)( h ), size, (int)( h ) , thick, color, base );
			basicPainter2D.composeImage( base, x, (int)Math.round( h - cc ), circ );
			
			h += size / numLines;
			x += stepX;
		}
		
		return base;
	}

	public static ImageIcon Incorrect(  )
	{
		Image icono = null;

		Color colorBorder = Color.BLACK;
		Color colorRelleno = Color.RED;
		int width = 100, height = 100;
		float thickness = width / 4.0F;

		icono = basicPainter2D.createEmptyCanva( width, height, null );

		basicPainter2D.line( 0+(int)thickness, 0+(int)thickness, width-(int)thickness, height-(int)thickness, thickness+2.0F, colorBorder, icono );
		basicPainter2D.line( width-(int)thickness, 0+(int)thickness, 0+(int)thickness, height-(int)thickness, thickness+2.0F, colorBorder, icono );
		basicPainter2D.line( 0+(int)thickness, 0+(int)thickness, width-(int)thickness, height-(int)thickness, thickness, colorRelleno, icono );
		basicPainter2D.line( width-(int)thickness, 0+(int)thickness, 0+(int)thickness, height-(int)thickness, thickness, colorRelleno, icono );

		return new ImageIcon( icono );
	}

	public static ImageIcon Correct(  )
	{
		Image icono = null;		
		int width = 100, height = 100;
		Color colorBorder = Color.BLACK;
		Color colorRelleno = Color.GREEN;
		float thickness = 1.0F;

		icono = basicPainter2D.createEmptyCanva( width, height, null );

		int x = (int)thickness, y = height / 2 + (int)thickness;

		int xs[ ] = { x, x + width / 4, width - (int)thickness, x + width / 4 };
		int ys[ ] = { y, y + height / 4 - (int)thickness, 0 + (int)thickness,  height - (int)thickness };  

		basicPainter2D.fillPolygon( xs, ys, colorRelleno, icono );
		basicPainter2D.outlinePolygon( xs, ys, thickness, colorBorder, icono );

		return new ImageIcon( icono );
	}

	public static ImageIcon Clock(  )
	{
		Image icono = null;		
		Image aux = null;
		int width = 100;
		int height = 100;

		Color colorBorder = Color.RED;
		float thickness = 2.5F;

		int x = (int)thickness, y = Math.abs( width - height ) / 2;
		int diametro = width - (int)Math.round( thickness );
		if( width > height )
		{
			diametro = height- (int)Math.round( thickness ); 
			x = y;
			y = (int)thickness;
		}

		icono = basicPainter2D.createEmptyCanva( width, height, null );

		aux = basicPainter2D.rectangle(  5 * width / 8, height - (int)(thickness * 2 ), thickness, colorBorder, colorBorder );
		basicPainter2D.composeImage( icono, ( width - aux.getWidth( null ) ) / 2 , ( height - aux.getHeight( null ) ) / 2 , aux); 

		basicPainter2D.circle( x, y, diametro, Color.WHITE, icono );
		basicPainter2D.circumference( x, y, diametro, thickness, colorBorder, icono );

		aux = basicPainter2D.rectangle( diametro / 12, diametro / 12, thickness, Color.BLACK, Color.BLACK );		
		basicPainter2D.composeImage( icono, width / 2 - aux.getWidth( null ) / 2, y + (int)(thickness * 2 ), aux );
		basicPainter2D.composeImage( icono, width / 2 - aux.getWidth( null ) / 2, y + diametro - (int)(thickness * 2 ) - aux.getHeight( null ), aux );
		basicPainter2D.composeImage( icono, 0 + (int)(thickness * 2 ), height / 2 - aux.getHeight( null ) / 2, aux );
		basicPainter2D.composeImage( icono, width - (int)(thickness * 2 ) - aux.getWidth( null ), height / 2 - aux.getHeight( null ) / 2, aux );

		basicPainter2D.line( width / 2, height / 2, width / 2 , height / 2 -  3 * ( diametro / 2 ) / 4, thickness*1.5F, colorBorder, icono );
		basicPainter2D.line( width / 2, height / 2, width / 2 + diametro / 4, height / 2, thickness*1.5F, colorBorder, icono );		

		return new ImageIcon( icono );
	}

	public static ImageIcon Socket( Color colorIn, Color colorOut )
	{
		Image imgIn = basicPainter2D.triangle( 14,  1, 
				Color.BLACK,
				colorIn,
				basicPainter2D.SOUTH );

		Image imgOut = basicPainter2D.triangle( 14,  1, 
				Color.BLACK,
				colorOut,
				basicPainter2D.NORTH );

		Image img = basicPainter2D.createEmptyCanva( 30,15, null );
		img = basicPainter2D.composeImage( img, 0, 0, imgIn );

		return new ImageIcon( basicPainter2D.composeImage( img, 12, 0, imgOut ) );
	}

	public static ImageIcon Folder( int width, int height, Color borderColor, Color fillColor )
	{
		int w = width - 2;
		int h = height - 2;
		int thickness = 1;

		if( borderColor == null && fillColor == null )
		{
			borderColor = Color.BLACK;
		}

		Image img = basicPainter2D.createEmptyCanva( w, h, null );

		//Image imgAux = imagenPoligono2D.rectangle( w / 2, h / 5, thickness, borderColor, fillColor );
		int x[] = { thickness, 3 * w / 7, 3 * w / 7, w - thickness, w - thickness, thickness };
		int y[] = { thickness, thickness, h / 5, h / 5, 2 * h / 5, 2 * h / 5 };

		if( fillColor != null )
		{
			basicPainter2D.fillPolygon( x, y, fillColor, img );
		}

		if( borderColor != null )
		{
			basicPainter2D.outlinePolygon( x, y, thickness, borderColor, img );
		}
		//imagenPoligono2D.componerImagen( img, 0, 0, imgAux );

		int xs[] = { thickness, w - thickness, w -thickness, thickness };
		int ys[] = { 2 * h / 5, 2 * h / 5, h - thickness, h - thickness };

		if( fillColor != null )
		{
			basicPainter2D.fillPolygon( xs, ys, fillColor, img );
		}

		if( borderColor != null )
		{
			basicPainter2D.outlinePolygon( xs, ys, thickness, borderColor, img );
		}

		//Image imgAux = imagenPoligono2D.rectangle( w - thickness, 3 * h / 5, thickness, borderColor, fillColor );
		//imagenPoligono2D.componerImagen( img, 0, 2 * h / 5, imgAux );

		return new ImageIcon( img );
	}

	public static ImageIcon Refresh( int width, int height, Color borderColor, Color fillColor )
	{
		int w = width - 2;
		int h = height - 2;
		int l = (int)( Math.min( w, h ) / 3 );

		int thickness = l / 3;

		if( thickness < 2 )
		{
			thickness = 2;
		}


		Image img = basicPainter2D.createEmptyCanva( w, h, null );

		if( borderColor == null )
		{
			borderColor = Color.BLACK;
		}

		Image imgAux = basicPainter2D.triangle( l, 1, borderColor, borderColor, basicPainter2D.EAST );

		basicPainter2D.composeImage( img, w / 2, 0, imgAux );

		basicPainter2D.arc( 0, 0, w - (thickness * 3 ) / 2 , h - 2 * l / 3 - thickness, 0, -270, thickness, borderColor, fillColor, img );

		return new ImageIcon( img );		
	}

	public static ImageIcon Plot( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{	
			int thick = size / 16;
			if( thick < 1 )
			{
				thick = 1;
			}

			Image img = basicPainter2D.rectangle( size, size, 1, color, null);

			int[] x = new int[ size ];
			int[] y = new int[ size ];
			int h = (int)( size * 0.75 );
			for( int i = 0; i < size; i++ )
			{
				x[ i ] = i;
				y[ i ] = (int)(( ( Math.sin( ( i * 4 * Math.PI ) / size ) + 1 ) / 2 ) * h ) + ( size - h ) / 2;
			}

			basicPainter2D.polygonLine( x, y, thick, color, img );

			ico = new ImageIcon( img );
		}

		return ico;		
	}
	
	public static ImageIcon getSmileIcon( int level, int side, Color borderColor, Color fillColor )
	{
		int thicknessBorder = 2;

		Image img = basicPainter2D.rectangle( side, side, thicknessBorder, borderColor, fillColor );

		Image eye = basicPainter2D.circle( 0, 0, side/6, borderColor, null );

		Image mouth = getSmile( level, side, thicknessBorder, borderColor, fillColor );

		int w = mouth.getWidth( null );
		int hg = mouth.getHeight( null );

		img = basicPainter2D.composeImage( img, side/6, side/6 , eye );
		img = basicPainter2D.composeImage( img, side - side/3, side/6 , eye );
		//img = imagenPoligono2D.componerImagen( img, ( 5 * side ) / 12, ( 5 * side) / 12, nose );

		img = basicPainter2D.composeImage( img, ( side - w ) / 2 , side - hg*2, mouth );

		return new ImageIcon( img );
		//return new ImageIcon( mouth );
	}

	private static Image getSmile( int level, int imgWidth, int thickness, Color borderColor, Color fillColor )
	{
		double x1 = 1;
		double x2 = ( 8 * imgWidth ) / 24.0 + x1, x3 = ( 8 * imgWidth ) / 12.0 + x1;
		double y1 = imgWidth / 6.0;
		double y2 = y1, y3 = y1;

		double h = ( imgWidth / 3.0 ) / 8.0; 

		int val = level - 5;

		y1 += -val * h;
		y3 = y1;
		y2 += val * h;

		if( y1 < 0 )
		{
			y1 = 0;
			y3 = y1;
			y2 = h * 8;
		}
		else if( y1 > h * 8 )
		{
			y2 = 0;
			y1 = h * 8;
			y3 = y1;
		}

		Bspline bspline = new Bspline();

		bspline.addPoint( -1, y1 );
		bspline.addPoint( 0, y1 );
		bspline.addPoint( x1, y1 );
		bspline.addPoint( x2, y2 );
		bspline.addPoint( x3, y3 );
		bspline.addPoint( x3, y3 );
		bspline.addPoint( x2, y2 );
		bspline.addPoint( x1, y1 );
		bspline.addPoint( 0, y1 );
		bspline.addPoint( -1, y1 );

		List< Coordinate > coords = bspline.getInterpolated();

		int n = coords.size();
		int[] xs = new int[ n ];
		int[] ys = new int[ xs.length ];

		int i = 0;
		int w = 0;
		int hg = 0;
		for( Coordinate c : coords )
		{
			xs[ i ] = (int)c.x;
			ys[ i ] = (int)c.y;

			if( w < xs[ i ] )
			{
				w = xs[ i ];
			}

			if( hg < ys[ i ] )
			{
				hg = ys[ i ];
			}

			i += 1;			
		}

		Image mouth = basicPainter2D.createEmptyCanva( w + 1, hg + 1, fillColor );

		for( i = 1; i < xs.length; i++ )
		{
			basicPainter2D.line( xs[i-1], ys[i-1]
					, xs[i], ys[i]
							, thickness
							, borderColor, mouth );
		}

		return mouth;
	}

	public static ImageIcon getSmileIcon( float level, int side, Color borderColor, Color fillColor )
	{
		int thicknessBorder = 2;

		Image img = basicPainter2D.rectangle( side, side, thicknessBorder, borderColor, fillColor );

		Image eye = basicPainter2D.circle( 0, 0, side/6, borderColor, null );

		Image mouth = getSmile( level, side, thicknessBorder, borderColor, fillColor );

		int w = mouth.getWidth( null );
		int hg = mouth.getHeight( null );

		img = basicPainter2D.composeImage( img, side/6, side/6 , eye );
		img = basicPainter2D.composeImage( img, side - side/3, side/6 , eye );
		//img = imagenPoligono2D.componerImagen( img, ( 5 * side ) / 12, ( 5 * side) / 12, nose );

		img = basicPainter2D.composeImage( img, ( side - w ) / 2 , side - hg*2, mouth );

		return new ImageIcon( img );
		//return new ImageIcon( mouth );
	}
	
	private static Image getSmile( float level, int imgWidth, int thickness, Color borderColor, Color fillColor )
	{
		double x1 = 1;
		double x2 = ( 8 * imgWidth ) / 24.0 + x1, x3 = ( 8 * imgWidth ) / 12.0 + x1;
		double y1 = imgWidth / 6.0;
		double y2 = y1, y3 = y1;

		double h = ( imgWidth / 3.0 ) / 8.0; 

		if( level < 0 || level > 1 )
		{
			level = 1F;
		}
		
		int val = Math.round( level * 9 ) - 5;

		y1 += -val * h;
		y3 = y1;
		y2 += val * h;

		if( y1 < 0 )
		{
			y1 = 0;
			y3 = y1;
			y2 = h * 8;
		}
		else if( y1 > h * 8 )
		{
			y2 = 0;
			y1 = h * 8;
			y3 = y1;
		}

		Bspline bspline = new Bspline();

		bspline.addPoint( -1, y1 );
		bspline.addPoint( 0, y1 );
		bspline.addPoint( x1, y1 );
		bspline.addPoint( x2, y2 );
		bspline.addPoint( x3, y3 );
		bspline.addPoint( x3, y3 );
		bspline.addPoint( x2, y2 );
		bspline.addPoint( x1, y1 );
		bspline.addPoint( 0, y1 );
		bspline.addPoint( -1, y1 );

		List< Coordinate > coords = bspline.getInterpolated();

		int n = coords.size();
		int[] xs = new int[ n ];
		int[] ys = new int[ xs.length ];

		int i = 0;
		int w = 0;
		int hg = 0;
		for( Coordinate c : coords )
		{
			xs[ i ] = (int)c.x;
			ys[ i ] = (int)c.y;

			if( w < xs[ i ] )
			{
				w = xs[ i ];
			}

			if( hg < ys[ i ] )
			{
				hg = ys[ i ];
			}

			i += 1;			
		}

		Image mouth = basicPainter2D.createEmptyCanva( w + 1, hg + 1, fillColor );

		for( i = 1; i < xs.length; i++ )
		{
			basicPainter2D.line( xs[i-1], ys[i-1]
					, xs[i], ys[i]
							, thickness
							, borderColor, mouth );
		}

		return mouth;
	}
	
	
	public static ImageIcon getDoll(int width, int height, Color borderColor, Color fillColor, Color bgColor)
	{
		Image base = basicPainter2D.createEmptyCanva(width, height, bgColor);

		double[] x = { 0.625D, 0.625D, 0.375D, 0.375D, 
				0.875D, 0.875D, 0.75D, 0.75D, 0.75D, 
				0.5D, 0.5D, 0.5D, 0.25D, 0.25D, 0.25D, 0.125D, 0.125D, 0.5D };

		double[] y = { 0.25D, 0.0D, 0.0D, 0.25D, 0.25D, 
				0.625D, 0.625D, 0.5D, 0.99D, 
				0.99D, 0.625D, 0.99D, 0.99D, 0.5D, 0.625D, 
				0.625D, 0.25D, 0.25D };

		int[] xs = new int[x.length];
		int[] ys = new int[y.length];

		for (int i = 0; i < xs.length; i++)
		{
			xs[i] = ((int)(x[i] * width));
			ys[i] = ((int)(y[i] * height));
		}


		basicPainter2D.fillPolygon(xs, ys, fillColor, base);
		basicPainter2D.outlinePolygon(xs, ys, 2.0F, borderColor, base);

		return new ImageIcon(base);
	}
	
	public static Image Quaver( int width, int height, Color fillColor, Color bgColor)
	{
		Image base = basicPainter2D.createEmptyCanva( width, height, bgColor );
		
		int s = Math.min( width, height );		
		int r = s / 3;
		int thick = r / 4;
		
		if( thick < 1 )
		{
			thick = 1;
		}
		
		Image circ = basicPainter2D.circle( 0, 0, r, fillColor, null );
		Image vert = basicPainter2D.rectangle( thick, height - circ.getHeight( null ) /2, 1, Color.BLACK, Color.BLACK );
		Image hor = basicPainter2D.rectangle( width - circ.getWidth( null ), 2 * thick, 1, Color.BLACK, Color.BLACK );
		
		basicPainter2D.composeImage( base, 0, base.getHeight( null ) - circ.getHeight( null ), circ );
		basicPainter2D.composeImage( base, base.getWidth( null ) - circ.getWidth( null )
												, base.getHeight( null ) - circ.getHeight( null ), circ );
		
		basicPainter2D.composeImage( base, circ.getWidth( null ) - vert.getWidth( null ), 0, vert );
		basicPainter2D.composeImage( base, base.getWidth( null ) - vert.getWidth( null ), 0, vert );
		
		basicPainter2D.composeImage( base
										,  circ.getWidth( null ) - vert.getWidth( null )
										, 0
										, hor );
		
		return base;
	}
	
	public static ImageIcon Sound( int width, int height, Color fillColor, Color bgColor)
	{
		Image base = basicPainter2D.createEmptyCanva( width, height, bgColor );
		
		int x[]  = new int[] { width / 8, width / 4, width / 2, width / 2, width / 4, width / 8 };
		int y[]  = new int[] { ( 3 * height ) / 8, ( 3 * height ) / 8, height / 4, ( 3 * height ) / 4, ( 5 * height ) / 8, ( 5 * height ) / 8 };

		int h = y[ 3 ] - y[ 2 ];
		
		int t = Math.max( width, height ) / 32;
		
		if( t < 1 )
		{
			t = 1;
		}
		
		basicPainter2D.fillPolygon( x, y, fillColor, base ); 
		
		int w =  width / 8;
		
		basicPainter2D.arc( width / 2, height / 2 - h / 8 - t, w / 2, h / 4, -90, 180, t, fillColor, null, base );
		
		if( width >= 32 )
		{		
			basicPainter2D.arc( width / 2, height / 2 - h / 4 - t, w, h / 2, -90, 180, t, fillColor, null, base );
		}
		
		basicPainter2D.arc( width  / 2, height / 2 - ( ( 4 * h ) / 5 ) /2 - t, ( 3 * w ) / 2, ( 4 * h ) / 5, -90, 180, t, fillColor, null, base );
		
		return new ImageIcon( base );
	}
	
	public static ImageIcon InterleavedIcon( int width, int height, Color border, Color fill, Color background )
	{
		Image base = basicPainter2D.createEmptyCanva( width, height, background );
		
		int thick = Math.min( width, height) / 16;
		if( thick < 1 )
		{
			thick = 1;
		}
		
		Color c = fill;
		if( c == null )
		{
			c = border;			
			
			if( c == null )
			{
				c = background;
			}
			
			if( c == null )
			{
				c = Color.RED;
			}
			else
			{			
				c = new Color( ~c.getRGB() );
			}
		}
					
		Image rect = basicPainter2D.rectangle( ( 3 * width ) / 10 - 1, ( 3 * height ) / 5, thick, border, c );
		
		int x = rect.getWidth( null ) / 3;
		int y = rect.getHeight( null ) / 3;
		basicPainter2D.composeImage( base, x * 2 + thick + 1, thick, rect );
		basicPainter2D.composeImage( base, x + thick + 1, y, rect );
		basicPainter2D.composeImage( base, thick + 1, 2 * y - thick, rect );
		
		x = rect.getWidth( null ) / 3;
		y = rect.getHeight( null ) / 3;
		basicPainter2D.composeImage( base, width / 2 + x * 2 - thick + 1, thick, rect );
		basicPainter2D.composeImage( base, width / 2 + x - thick + 1, y, rect );
		basicPainter2D.composeImage( base, width / 2 - thick + 1, 2 * y - thick, rect );
		
		if( fill == null )
		{
			Color n = new Color( c.getRed() / 255F, c.getGreen() / 255F , c.getBlue() / 255F, 0F );
			
			basicPainter2D.changeColorPixels( c, n, base );
		}
		
		
		return new ImageIcon( base );				
	}
}