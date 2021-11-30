package image.icon;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;

import org.jgrasstools.gears.utils.math.interpolation.splines.Bspline;

import com.vividsolutions.jts.geom.Coordinate;

import image.BasicPainter2D;

public class GeneralAppIcon
{
	public static final int SMALL_SIZE_ICON = 16;
	public static final int REGULAR_SIZE_ICON = 32;
	public static final int BIG_SIZE_ICON = 48;
	public static final int BIG_PLUS_SIZE_ICON = 64;
	public static final int HUGE_SIZE_ICON = 128;

	public static ImageIcon clear( int size, Color color )
	{
		ImageIcon icon = null;
		
		if( color != null && size > 0 )
		{
			Image img = BasicPainter2D.createEmptyCanva( size, size, null );
			
			int[] xs = new int[] { 0, size / 2, size, size, size / 2, 0 };
			int[] ys = new int[] { size / 2, 0, 0, size, size, size / 2 };
			
			BasicPainter2D.fillPolygon( xs, ys, color, img );
			
			float[] hsb = Color.RGBtoHSB( color.getRed(), color.getGreen(), color.getBlue(), null );
			
			Color crossColor = Color.WHITE;
			if( hsb[ 2 ] >= 0.75 )
			{
				crossColor = Color.BLACK;
			}
			
			float thickness = size / 8;
			if( thickness < 1 )
			{
				thickness = 1;
			}
			
			int wCross = size / 2;
			int hCross  = size / 2;
			
			BasicPainter2D.line( (int)(wCross - thickness), ( img.getHeight( null ) - hCross ) / 2 
								, (int)( size - thickness), ( img.getHeight( null ) + hCross ) / 2
								, thickness, crossColor, img );
			
			BasicPainter2D.line( (int)( size - thickness), ( img.getHeight( null ) - hCross ) / 2 
								, (int)(wCross - thickness), ( img.getHeight( null ) + hCross ) / 2
								, thickness, crossColor, img );
			
			icon = new ImageIcon( img );
		}	
		
		return icon;
	}
	
	public static ImageIcon Exit( int size, Color color )
	{
		ImageIcon ico = null;

		if( size > 0 )
		{	
			int w = size - 2;
			int l =  w / 3 ;

			int thickness = l / 3;

			thickness /= 2;
			
			if( thickness < 1 )
			{
				thickness = 1;
			}			
			
			Image img = BasicPainter2D.createEmptyCanva( w, w, null );
			
			int pI = (2 * w) / 3;
			
			BasicPainter2D.fillPolygon( new int[] { pI, w, pI }
														, new int[] { 0, w / 2, w}
														, color, img);
			
			Image r = BasicPainter2D.rectangle( pI - 2 * thickness 
															, ( 2 * w ) / 5
															, thickness
															, color
															, color );
			
			BasicPainter2D.composeImage( img, pI - r.getWidth( null ) + thickness * 2, w /2 - r.getHeight( null ) / 2, r );
			
			int thickness2 = thickness / 2;
			
			if( thickness2 < 1 )
			{
				thickness2 = 1;
			}
			
			BasicPainter2D.polygonLine( new int[] { pI - 2 * thickness, thickness2, thickness2, pI - 2 * thickness}
														, new int[] { thickness2, thickness2, w - thickness2, w - thickness2 }
														, thickness
														, color, img );
			
			
			ico = new ImageIcon( img );
		}

		return ico;
	}
	
	public static ImageIcon Add( int size, Color color )
	{	
		int thickness = size / 10;
		if( thickness < 1 )
		{
			thickness = 1;
		}
		
		Image img = BasicPainter2D.circumference( 0, 0, size, thickness, color, null );
		
		int w = img.getWidth( null );
		int h = img.getHeight( null );
				
		int pad = w / 4;
		BasicPainter2D.line( pad, h / 2
							,w - pad, h / 2
							, thickness, color , img );
		
		BasicPainter2D.line( w / 2, pad 
							, w / 2, h - pad
							, thickness, color , img );
		
		return new ImageIcon( img ); 
	}
	
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

			Image img = BasicPainter2D.createEmptyCanva( size, size, null );

			BasicPainter2D.polygonLine( new int[ ]{ size / 2 - thick, thick, thick, size - thick/2, size - thick/2 }, 
					new int[ ]{ thick/2, thick/2, size - thick/2, size - thick/2, size / 2 + thick}, 
					thick, color, 
					img);

			BasicPainter2D.fillPolygon( new int[]{ size / 2, size, size }, 
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

			Image img = BasicPainter2D.createEmptyCanva( size, size, null );

			BasicPainter2D.line( 0, size - thick, size, size - thick, thick, color, img);

			Image imgTr = BasicPainter2D.triangle( size / 3 + thick, 1, color, color, BasicPainter2D.SOUTH );
			BasicPainter2D.composeImage( img, ( size - imgTr.getWidth( null ) ) / 2 , size - imgTr.getHeight( null ) - thick, imgTr );

			BasicPainter2D.line( size / 2 - thick / 2 , 0, size / 2 - thick / 2, size / 2, thick, color, img );
			
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

			Image img = BasicPainter2D.createEmptyCanva( size, size, null );

			Image r = BasicPainter2D.rectangle( ( 3 * size ) / 4, ( 3 * size ) / 4, thick, color, null );

			BasicPainter2D.composeImage( img, 0, size - r.getHeight( null), r );
			BasicPainter2D.line( size / 2 - thick / 2, size - r.getHeight( null), size / 2 - thick / 2, 0, thick, color, img );
			BasicPainter2D.line( size / 2 - thick / 2, thick / 2, size, thick / 2, thick, color, img );
			BasicPainter2D.line( size - thick / 2, 0, size - thick / 2, size / 2, thick, color, img );
			BasicPainter2D.line( r.getWidth( null ), size / 2, size, size / 2, thick, color, img );
			
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
			
			Image img = BasicPainter2D.createEmptyCanva( size, size, null );
			
			Image circ = BasicPainter2D.circle( 0, 0, radio, color, null );
			Image circf = BasicPainter2D.circumference( 0, 0, radio, thick, color, null );
			
			BasicPainter2D.composeImage( img, 0, 0, circ );
			BasicPainter2D.composeImage( img, img.getWidth( null ) - circf.getWidth( null ), img.getHeight( null ) - circf.getHeight( null ), circf );
						
			BasicPainter2D.line( circ.getWidth( null ) - thick, circ.getHeight( null ) - thick
												, img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) + thick
												, thick, color, img );
			
			BasicPainter2D.line( img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) + thick
												, img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) - 2 * thick
												, thick, color, img );
			
			BasicPainter2D.line( img.getWidth( null ) - circf.getWidth( null ) + thick, img.getHeight( null ) - circf.getHeight( null ) + thick
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

			Image img = BasicPainter2D.rectangle( size, size, thick, color, null );

			BasicPainter2D.line( size / 4, size / 4, size - size / 4, size / 4, thick, color, img );
			BasicPainter2D.line( size / 4, size / 2, size - size / 4, size / 2, thick, color, img );
			BasicPainter2D.line( size / 4, ( 3 * size ) / 4, size - size / 4, ( 3 * size ) / 4, thick, color, img );

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

			Image img = BasicPainter2D.composeImage( 
					BasicPainter2D.createEmptyCanva( size, size, null ), 
					0, 0,  
					BasicPainter2D.rectangle( w - thick, size - thick, thick, color, null ) );

			BasicPainter2D.line( w / 4, size / 4, w - w / 4 - thick, size / 4, thick, color, img );
			BasicPainter2D.line( w / 4, size / 2, w - w / 4 - thick, size / 2, thick, color, img );
			BasicPainter2D.line( w / 4, ( 3 * size ) / 4, w - w / 4 - thick, ( 3 * size ) / 4, thick, color, img );

			BasicPainter2D.fillPolygon( new int[]{ w + thick, w + thick, size }, 
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

		Image img = BasicPainter2D.createEmptyCanva( w, h, null );

		Image imgAux = Folder( 2 * w / 3, h, borderColor, fillColor ).getImage();
		Image imgAux2 = BasicPainter2D.triangle( w / 3, 1, borderColor, borderColor, BasicPainter2D.EAST );

		BasicPainter2D.composeImage( img, 0, 0, imgAux );
		return new ImageIcon( BasicPainter2D.composeImage( img, 2 * w / 3, h / 2 - w / 6, imgAux2 ) );
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

			Image img = BasicPainter2D.composeImage( 
					BasicPainter2D.createEmptyCanva( size, size, null ), 
					0, 0,  
					BasicPainter2D.rectangle( w - thick, size - thick, thick, color, null ) );

			BasicPainter2D.line( w / 4, size / 4, w - w / 4 - thick, size / 4, thick, color, img );
			BasicPainter2D.line( w / 4, size / 2, w - w / 4 - thick, size / 2, thick, color, img );
			BasicPainter2D.line( w / 4, ( 3 * size ) / 4, w - w / 4 - thick, ( 3 * size ) / 4, thick, color, img );

			BasicPainter2D.fillPolygon( new int[]{ size, size, w + thick }, 
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

			Image img = BasicPainter2D.createEmptyCanva( size, size, null );

			BasicPainter2D.line( 0, 0, size, size, thick, color, img );
			BasicPainter2D.line( 0, size, size, 0, thick, color, img );

			BasicPainter2D.composeImage( img, size / 4, size / 4, 
					BasicPainter2D.rectangle( size / 2, size / 2, thick, color, null ) );

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

			//Image img = basicPainter2D.createEmptyCanva( size, size, null );

			Image img = BasicPainter2D.rectangle( size-thick/2, size-thick/2, thick, color, null );

			Font f = new Font( Font.DIALOG, Font.BOLD, 12 );
			FontMetrics fm = img.getGraphics().getFontMetrics( f );			
			while( fm.getHeight() < size )
			{
				f = new Font( f.getName(), Font.BOLD, f.getSize() + 1 );
				fm = img.getGraphics().getFontMetrics( f );
			}

			Image imgTxt = BasicPainter2D.text( 0, 0, "?", fm, color, color, null );
			int x = (int)Math.round( ( size - imgTxt.getWidth( null ) ) / 2.0 );
			int y = 0;
			img = BasicPainter2D.composeImage( img, x, y, imgTxt );

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

			Image img = BasicPainter2D.createEmptyCanva( size, size, null );

			BasicPainter2D.outlinePolygon( new int[]{ thick - 1, size / 2, size - thick + 1},
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

			Image imgTxt = BasicPainter2D.text( 0, 0, "!", fm, color, color, null );
			int x = (int)Math.round( ( size - imgTxt.getWidth( null ) ) / 2.0 );
			int y = size - imgTxt.getHeight( null ) + 1;
			img = BasicPainter2D.composeImage( img, x, y, imgTxt );

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

			Image img = BasicPainter2D.rectangle( size, size, 1, color, null);

			int[] x = new int[ size ];
			int[] y = new int[ size ];
			int h = (int)( size * 0.75 );
			for( int i = 0; i < size; i++ )
			{
				x[ i ] = i;
				y[ i ] = (int)(( ( Math.sin( ( i * 4 * Math.PI ) / size ) + 1 ) / 2 ) * h ) + ( size - h ) / 2;
			}

			BasicPainter2D.polygonLine( x, y, thick, color, img );

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

			Image img = BasicPainter2D.createEmptyCanva( size, size, null  );

			BasicPainter2D.line( 0, 0, size, size, thick, color, img );
			BasicPainter2D.line( 0, size, size, 0, thick, color, img );

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

			Image img = BasicPainter2D.createEmptyCanva( size, size, null  );

			double div = size / 10.0;
			int[] xs = new int[]{ size / 2 , size - shiftThick, size / 2, shiftThick  };			
			int[] ys = new int[]{ shiftThick, size / 2, size - shiftThick, size / 2  };

			BasicPainter2D.outlinePolygon(xs, ys, thick, color, img );

			BasicPainter2D.line( (int)( div * 1.5 ) + shiftThick, (int)( div * 6.5 ) - shiftThick, (int)( div * 6.5 ) - shiftThick, (int)( div * 1.5 ) + shiftThick, thick, color, img);
			BasicPainter2D.line( (int)( div * 3.5 ) + shiftThick, (int)( div * 8.5 ) - shiftThick, (int)( div * 8.5) - shiftThick, (int)( div * 3.5 ) + shiftThick, thick, color, img);

			BasicPainter2D.line( shiftThick, size / 2 + shiftThick, shiftThick, size, thick, color, img);
			BasicPainter2D.line( 0, size - shiftThick, size / 2 - shiftThick, size - shiftThick, thick, color, img);

			xs = new int[]{ shiftThick, shiftThick, (int)( 2.5 * div ) };
			ys = new int[]{ (int)( 7.5 * div ), size - shiftThick, size - shiftThick };

			BasicPainter2D.fillPolygon(xs, ys, color, img );

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
		icono = BasicPainter2D.createEmptyCanva( width, height, null );

		int xs[] = { 0, 0, width / 6, width / 6, 2 * width / 6 , 2 * width / 6, width / 2, width / 2 };
		int ys[] = { height / 4, 0, 0, height / 4, height / 4, 0, 0, height / 4 };

		BasicPainter2D.fillPolygon( xs, ys, colorRelleno, icono );
		BasicPainter2D.outlinePolygon( xs, ys, 1.0F, colorBorde, icono );		

		aux = BasicPainter2D.rectangle( width / 6 , height / 2 -1, 1.0F, colorBorde, colorRelleno );		
		BasicPainter2D.composeImage( icono, width / 6 , height / 2 , aux );

		BasicPainter2D.arc( 0, 0, width / 2, height / 2, 
				180, 180, 1.0F, colorBorde, colorRelleno, icono );
		BasicPainter2D.line( width / 6, height / 4, 2 * width / 6, height / 4 , 1.0F, colorBorde, icono );
		xs = new int[ 8 ];
		ys = new int[ 8 ];		
		xs[ 0 ] = width / 2 + width / 6; xs[ 1 ] = xs[ 0 ]; xs[ 2 ] = xs[ 1 ] - width / 12; xs[ 3 ] = xs[ 0 ]; xs[ 4 ] = xs[ 3 ] + width / 6 ; xs[ 5 ] = xs[ 4 ] + width / 12; xs[ 6 ] = xs[ 4 ]; xs[ 7 ] = xs[ 6 ];
		ys[ 0 ] = height / 2; ys[ 1 ] = ys[ 0 ] - height / 6; ys[ 2 ] = ys[ 1 ] - height / 4; ys[ 3 ] = 0; ys[ 4 ] = ys[ 3 ]; ys[ 5 ] = ys[ 2 ]; ys[ 6 ] = ys[ 1 ]; ys[ 7 ] = ys[ 0 ];

		BasicPainter2D.fillPolygon( xs, ys, colorRelleno, icono );
		BasicPainter2D.outlinePolygon( xs, ys, 1.0F, colorBorde, icono );


		aux = BasicPainter2D.rectangle( width / 6 + 4, height / 2, 1.0F, colorBorde, Color.RED);
		BasicPainter2D.composeImage( icono, width / 2 + width / 6 - 2, height / 2, aux );

		return new ImageIcon( icono );
	}
	
	public static Image Config2( Color color )
	{	
		if( color == null )
		{
			color = Color.BLACK;
		}
		
		int size = 512;
				
		Image base = BasicPainter2D.createEmptyCanva( size, size, null );
		
		int r = size / 3;
		
		int thick = r / 8;
		
		if( thick < 1 )
		{
			thick = 1;
		}
		
		int numLines = 2;		
		
		//Image circ = basicPainter2D.circle( 0, 0, r, color, null );
		Image circ = BasicPainter2D.dot( 0, 0, r, color, true, null );
		
		Color fill = new Color( ~color.getRGB() );
		//Image circ2 = basicPainter2D.circle( 0, 0, r / 2, fill, null );
		Image circ2 = BasicPainter2D.dot( 0, 0, ( 2 * r ) / 3, fill, true, null );
		BasicPainter2D.composeImage( circ, (int)Math.round( ( circ.getWidth( null ) - circ2.getWidth( null ) ) / 2.0 )
												, (int)Math.round( ( circ.getHeight( null ) - circ2.getHeight( null ) ) / 2.0 )												
												, circ2 );
		
		double h = size / ( 2.0 * numLines );
		int x = r / 2;
		int stepX = ( size - x ) / ( numLines + 1);
		double cc = circ.getHeight( null ) / 2.0;
		
		for( int i = 1; i <= numLines; i++ )
		{
			BasicPainter2D.line( 0, (int)( h ), size, (int)( h ) , thick, color, base );
			BasicPainter2D.composeImage( base, x, (int)Math.round( h - cc ), circ );
			
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

		icono = BasicPainter2D.createEmptyCanva( width, height, null );

		BasicPainter2D.line( 0+(int)thickness, 0+(int)thickness, width-(int)thickness, height-(int)thickness, thickness+2.0F, colorBorder, icono );
		BasicPainter2D.line( width-(int)thickness, 0+(int)thickness, 0+(int)thickness, height-(int)thickness, thickness+2.0F, colorBorder, icono );
		BasicPainter2D.line( 0+(int)thickness, 0+(int)thickness, width-(int)thickness, height-(int)thickness, thickness, colorRelleno, icono );
		BasicPainter2D.line( width-(int)thickness, 0+(int)thickness, 0+(int)thickness, height-(int)thickness, thickness, colorRelleno, icono );

		return new ImageIcon( icono );
	}

	public static ImageIcon Correct(  )
	{
		Image icono = null;		
		int width = 100, height = 100;
		Color colorBorder = Color.BLACK;
		Color colorRelleno = Color.GREEN;
		float thickness = 1.0F;

		icono = BasicPainter2D.createEmptyCanva( width, height, null );

		int x = (int)thickness, y = height / 2 + (int)thickness;

		int xs[ ] = { x, x + width / 4, width - (int)thickness, x + width / 4 };
		int ys[ ] = { y, y + height / 4 - (int)thickness, 0 + (int)thickness,  height - (int)thickness };  

		BasicPainter2D.fillPolygon( xs, ys, colorRelleno, icono );
		BasicPainter2D.outlinePolygon( xs, ys, thickness, colorBorder, icono );

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

		icono = BasicPainter2D.createEmptyCanva( width, height, null );

		aux = BasicPainter2D.rectangle(  5 * width / 8, height - (int)(thickness * 2 ), thickness, colorBorder, colorBorder );
		BasicPainter2D.composeImage( icono, ( width - aux.getWidth( null ) ) / 2 , ( height - aux.getHeight( null ) ) / 2 , aux); 

		BasicPainter2D.circle( x, y, diametro, Color.WHITE, icono );
		BasicPainter2D.circumference( x, y, diametro, thickness, colorBorder, icono );

		aux = BasicPainter2D.rectangle( diametro / 12, diametro / 12, thickness, Color.BLACK, Color.BLACK );		
		BasicPainter2D.composeImage( icono, width / 2 - aux.getWidth( null ) / 2, y + (int)(thickness * 2 ), aux );
		BasicPainter2D.composeImage( icono, width / 2 - aux.getWidth( null ) / 2, y + diametro - (int)(thickness * 2 ) - aux.getHeight( null ), aux );
		BasicPainter2D.composeImage( icono, 0 + (int)(thickness * 2 ), height / 2 - aux.getHeight( null ) / 2, aux );
		BasicPainter2D.composeImage( icono, width - (int)(thickness * 2 ) - aux.getWidth( null ), height / 2 - aux.getHeight( null ) / 2, aux );

		BasicPainter2D.line( width / 2, height / 2, width / 2 , height / 2 -  3 * ( diametro / 2 ) / 4, thickness*1.5F, colorBorder, icono );
		BasicPainter2D.line( width / 2, height / 2, width / 2 + diametro / 4, height / 2, thickness*1.5F, colorBorder, icono );		

		return new ImageIcon( icono );
	}

	public static ImageIcon Socket( Color colorIn, Color colorOut )
	{
		Image imgIn = BasicPainter2D.triangle( 14,  1, 
				Color.BLACK,
				colorIn,
				BasicPainter2D.SOUTH );

		Image imgOut = BasicPainter2D.triangle( 14,  1, 
				Color.BLACK,
				colorOut,
				BasicPainter2D.NORTH );

		Image img = BasicPainter2D.createEmptyCanva( 30,15, null );
		img = BasicPainter2D.composeImage( img, 0, 0, imgIn );

		return new ImageIcon( BasicPainter2D.composeImage( img, 12, 0, imgOut ) );
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

		Image img = BasicPainter2D.createEmptyCanva( w, h, null );

		//Image imgAux = basicPainter2D.rectangle( w / 2, h / 5, thickness, borderColor, fillColor );
		int x[] = { thickness, 3 * w / 7, 3 * w / 7, w - thickness, w - thickness, thickness };
		int y[] = { thickness, thickness, h / 5, h / 5, 2 * h / 5, 2 * h / 5 };

		if( fillColor != null )
		{
			BasicPainter2D.fillPolygon( x, y, fillColor, img );
		}

		if( borderColor != null )
		{
			BasicPainter2D.outlinePolygon( x, y, thickness, borderColor, img );
		}
		//basicPainter2D.composeImage( img, 0, 0, imgAux );

		int xs[] = { thickness, w - thickness, w -thickness, thickness };
		int ys[] = { 2 * h / 5, 2 * h / 5, h - thickness, h - thickness };

		if( fillColor != null )
		{
			BasicPainter2D.fillPolygon( xs, ys, fillColor, img );
		}

		if( borderColor != null )
		{
			BasicPainter2D.outlinePolygon( xs, ys, thickness, borderColor, img );
		}

		//Image imgAux = basicPainter2D.rectangle( w - thickness, 3 * h / 5, thickness, borderColor, fillColor );
		//basicPainter2D.composeImage( img, 0, 2 * h / 5, imgAux );

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
		if( borderColor == null )
		{
			borderColor = Color.BLACK;
		}

		Image imgTr = BasicPainter2D.triangle( l, 1, borderColor, borderColor, BasicPainter2D.EAST );
		Image imgArc = BasicPainter2D.arc( 0, 0, w - (thickness * 3 ) / 2 
											, h - 2 * l / 3 - thickness, 0
											, -270, thickness, borderColor, fillColor
											, null );
		

		Image img = BasicPainter2D.createEmptyCanva( imgArc.getWidth( null )
													, imgTr.getHeight( null ) / 2 + imgArc.getHeight( null ) - thickness / 2
													, null );
		

		BasicPainter2D.composeImage( img, 0, imgTr.getHeight( null ) / 2 - thickness / 2, imgArc );
		BasicPainter2D.composeImage( img, img.getWidth( null ) / 2, 0, imgTr );

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

			Image img = BasicPainter2D.rectangle( size, size, 1, color, null);

			int[] x = new int[ size ];
			int[] y = new int[ size ];
			int h = (int)( size * 0.75 );
			for( int i = 0; i < size; i++ )
			{
				x[ i ] = i;
				y[ i ] = (int)(( ( Math.sin( ( i * 4 * Math.PI ) / size ) + 1 ) / 2 ) * h ) + ( size - h ) / 2;
			}

			BasicPainter2D.polygonLine( x, y, thick, color, img );

			ico = new ImageIcon( img );
		}

		return ico;		
	}
	
	public static ImageIcon getSmileIcon( int level, int side, Color borderColor, Color fillColor )
	{
		int thicknessBorder = 2;

		Image img = BasicPainter2D.rectangle( side, side, thicknessBorder, borderColor, fillColor );

		Image eye = BasicPainter2D.circle( 0, 0, side/6, borderColor, null );

		Image mouth = getSmile( level, side, thicknessBorder, borderColor, fillColor );

		int w = mouth.getWidth( null );
		int hg = mouth.getHeight( null );

		img = BasicPainter2D.composeImage( img, side/6, side/6 , eye );
		img = BasicPainter2D.composeImage( img, side - side/3, side/6 , eye );
		//img = basicPainter2D.composeImage( img, ( 5 * side ) / 12, ( 5 * side) / 12, nose );

		img = BasicPainter2D.composeImage( img, ( side - w ) / 2 , side - hg*2, mouth );

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

		Image mouth = BasicPainter2D.createEmptyCanva( w + 1, hg + 1, fillColor );

		for( i = 1; i < xs.length; i++ )
		{
			BasicPainter2D.line( xs[i-1], ys[i-1]
					, xs[i], ys[i]
							, thickness
							, borderColor, mouth );
		}

		return mouth;
	}

	public static ImageIcon getSmileIcon( float level, int side, Color borderColor, Color fillColor )
	{
		int thicknessBorder = 2;

		Image img = BasicPainter2D.rectangle( side, side, thicknessBorder, borderColor, fillColor );

		Image eye = BasicPainter2D.circle( 0, 0, side/6, borderColor, null );

		Image mouth = getSmile( level, side, thicknessBorder, borderColor, fillColor );

		int w = mouth.getWidth( null );
		int hg = mouth.getHeight( null );

		img = BasicPainter2D.composeImage( img, side/6, side/6 , eye );
		img = BasicPainter2D.composeImage( img, side - side/3, side/6 , eye );
		//img = basicPainter2D.composeImage( img, ( 5 * side ) / 12, ( 5 * side) / 12, nose );

		img = BasicPainter2D.composeImage( img, ( side - w ) / 2 , side - hg*2, mouth );

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

		Image mouth = BasicPainter2D.createEmptyCanva( w + 1, hg + 1, fillColor );

		for( i = 1; i < xs.length; i++ )
		{
			BasicPainter2D.line( xs[i-1], ys[i-1]
					, xs[i], ys[i]
							, thickness
							, borderColor, mouth );
		}

		return mouth;
	}
	
	
	public static ImageIcon getDoll(int width, int height, Color borderColor, Color fillColor, Color bgColor)
	{
		Image base = BasicPainter2D.createEmptyCanva(width, height, bgColor);

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


		BasicPainter2D.fillPolygon(xs, ys, fillColor, base);
		BasicPainter2D.outlinePolygon(xs, ys, 2.0F, borderColor, base);

		return new ImageIcon(base);
	}
	
	public static ImageIcon Sound( int width, int height, Color fillColor, Color bgColor)
	{
		Image base = BasicPainter2D.createEmptyCanva( width, height, bgColor );
		
		int x[]  = new int[] { width / 8, width / 4, width / 2, width / 2, width / 4, width / 8 };
		int y[]  = new int[] { ( 3 * height ) / 8, ( 3 * height ) / 8, height / 4, ( 3 * height ) / 4, ( 5 * height ) / 8, ( 5 * height ) / 8 };

		int h = y[ 3 ] - y[ 2 ];
		
		int t = Math.max( width, height ) / 32;
		
		if( t < 1 )
		{
			t = 1;
		}
		
		BasicPainter2D.fillPolygon( x, y, fillColor, base ); 
		
		int w =  width / 8;
		
		BasicPainter2D.arc( width / 2, height / 2 - h / 8 - t, w / 2, h / 4, -90, 180, t, fillColor, null, base );
		
		if( width >= 32 )
		{		
			BasicPainter2D.arc( width / 2, height / 2 - h / 4 - t, w, h / 2, -90, 180, t, fillColor, null, base );
		}
		
		BasicPainter2D.arc( width  / 2, height / 2 - ( ( 4 * h ) / 5 ) /2 - t, ( 3 * w ) / 2, ( 4 * h ) / 5, -90, 180, t, fillColor, null, base );
		
		return new ImageIcon( base );
	}
	
	public static ImageIcon Mute( int width, int height, Color fillColor, Color bgColor )
	{
		BufferedImage img = (BufferedImage)Sound( width, height, fillColor, bgColor).getImage();
		
		int thick = (int)( Math.max( width, height) / 8);
		if( thick < 1 )
		{
			thick = 1;
		}
		
		BasicPainter2D.line( 0, img.getHeight(), img.getWidth(), 0
							, thick, fillColor, img );
		
		return new ImageIcon( img );
	}
	
	public static ImageIcon InterleavedIcon( int width, int height, Color border, Color fill, Color background )
	{
		Image base = BasicPainter2D.createEmptyCanva( width, height, background );
		
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
					
		Image rect = BasicPainter2D.rectangle( ( 3 * width ) / 10 - 1, ( 3 * height ) / 5, thick, border, c );
		
		int x = rect.getWidth( null ) / 3;
		int y = rect.getHeight( null ) / 3;
		BasicPainter2D.composeImage( base, x * 2 + thick + 1, thick, rect );
		BasicPainter2D.composeImage( base, x + thick + 1, y, rect );
		BasicPainter2D.composeImage( base, thick + 1, 2 * y - thick, rect );
		
		x = rect.getWidth( null ) / 3;
		y = rect.getHeight( null ) / 3;
		BasicPainter2D.composeImage( base, width / 2 + x * 2 - thick + 1, thick, rect );
		BasicPainter2D.composeImage( base, width / 2 + x - thick + 1, y, rect );
		BasicPainter2D.composeImage( base, width / 2 - thick + 1, 2 * y - thick, rect );
		
		if( fill == null )
		{
			Color n = new Color( c.getRed() / 255F, c.getGreen() / 255F , c.getBlue() / 255F, 0F );
			
			BasicPainter2D.changeColorPixels( c, n, base );
		}
		
		
		return new ImageIcon( base );				
	}
	

	public static ImageIcon Camera( int iconSize )
	{
		int thick = 1; 
		int thick2 = iconSize / 64;
		if( thick2 < 1 )
		{
			thick2 = 1;
		}
		
		int arrowWidth = (int)( (iconSize / 8) * 1.); 
	
		Image icon = BasicPainter2D.createEmptyCanva( iconSize, iconSize, null );
				
		
		int cameraWidth = iconSize;
		//cameraWidth = (cameraWidth * 2 ) / 3;
		
		int padding = cameraWidth / 10;		
		cameraWidth -= padding;
						
		Color back = Color.WHITE;
		Color border = Color.BLACK;
		
		Image cameraBody = BasicPainter2D.rectangle( cameraWidth, cameraWidth, cameraWidth / 8, border, back );
		
		Image cameraLen = BasicPainter2D.circumference( 0, 0, cameraWidth / 2, cameraWidth / 8, border, null );
		
		Image cameraFlash = BasicPainter2D.circle( 0, 0, cameraWidth / 6, border, null );
				
		int h = cameraBody.getHeight( null );		
		Image camera = BasicPainter2D.createEmptyCanva( cameraBody.getWidth( null ), cameraBody.getWidth( null ), null );
		BasicPainter2D.composeImage( camera, 0, 0, cameraBody );
		BasicPainter2D.composeImage( camera, cameraBody.getWidth( null )  / 2 - cameraLen.getWidth( null ) / 2
												, cameraBody.getHeight( null ) / 2 - cameraLen.getHeight( null ) / 2, cameraLen );
		
		BasicPainter2D.composeImage( camera, cameraFlash.getWidth( null )
										, (int)(cameraFlash.getHeight( null ) * 1 ), cameraFlash );
		
		int w = camera.getWidth( null );		
		
		BasicPainter2D.composeImage( icon, icon.getWidth( null ) / 2 - w / 2
												, icon.getHeight( null ) / 2 - h / 2, camera );
		
				
		return new ImageIcon( icon );	
	}

}