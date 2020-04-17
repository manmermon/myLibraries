package image.icon;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import image.basicPainter2D;

public class MusicInstrumentIcons 
{
	private static Map< Byte, String > INSTRUMENT_BYTE_TO_STRING = new HashMap<Byte, String>();
	private static Map< String, Byte > INSTRUMENT_STRING_TO_BYTE = new HashMap<String, Byte>();
	
	static // Based on JFugue-Library MidiDictionary
	{
		INSTRUMENT_BYTE_TO_STRING.put( (byte)0, "Piano" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)13, "Xylophone" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)19, "Organ" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)24, "Guitar" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)43, "Contrabass" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)40, "Violin" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)48, "String_ensemble_1" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)56, "Trumpet" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)57, "Trombone" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)65, "soprano_sax" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)73, "Flute" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)78, "Whistle" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)79, "Ocarina" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)112, "Tinkle_bell" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)113, "Agogo" );
		INSTRUMENT_BYTE_TO_STRING.put( (byte)114, "Steel_drums" );
		
		for( Byte id : INSTRUMENT_BYTE_TO_STRING.keySet() )
		{
			String val = INSTRUMENT_BYTE_TO_STRING.get( id );
			INSTRUMENT_STRING_TO_BYTE.put( val.toUpperCase(), id );
		}
	}

	
	public static Image appIcon( int size )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );

		int thick = size/8;

		basicPainter2D.oval( -size + thick, -1, 2*size - (3*thick)/2, size, thick, Color.ORANGE, Color.WHITE, img );
		basicPainter2D.line( thick/2 - 1, 0, thick/2 - 1, size, thick, Color.ORANGE, img );

		Image quaver = Quaver( size - (int)(4.25 * thick), size - (int)(4.25 * thick), Color.BLACK, null );

		basicPainter2D.composeImage( img, (int)( 1.1 * thick ), ( size - quaver.getHeight( null ) ) /2, quaver );

		return img;
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
		Image vert = basicPainter2D.rectangle( thick, height - circ.getHeight( null ) /2, 1, fillColor, fillColor );
		Image hor = basicPainter2D.rectangle( width - circ.getWidth( null ), 2 * thick, 1, fillColor, fillColor );
		
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
	
	public static Image getInstrument( List< Byte > idInstruments, int size, Color c )
	{
		Set< String > instr = new TreeSet< String >();
		
		for( Byte val : idInstruments )
		{
			instr.add( getInstrumentString( val ) );
		}
		
		return getInstrument( instr , size, c );
	}
	
	public static Image getInstrument( Set< String > idInstruments, int size, Color c )
	{
		Image img =  basicPainter2D.circle( 0, 0, size, c, null );
		
		if( idInstruments != null && !idInstruments.isEmpty() )
		{
			List< String > knowInstrs = new ArrayList< String >();
			for( String inst : idInstruments )
			{
				if( INSTRUMENT_STRING_TO_BYTE.containsKey( inst.toUpperCase() ) )
				{
					knowInstrs.add( inst );
				}
			}
			
			if( !knowInstrs.isEmpty() )
			{
				img = basicPainter2D.createEmptyCanva( size, size, null );
				List< Point > locs = new ArrayList<Point>();
				
				int sizeInst = size / 2 - 1;
				if( knowInstrs.size() == 1 )
				{
					sizeInst = size;
				}
				else if( knowInstrs.size() > 4 )
				{
					sizeInst = size / 3  -1;
				}
				
				switch ( knowInstrs.size() ) 
				{
					case 1:
					{
						locs.add( new Point() );
						break;
					}
					case 2:
					{
						locs.add( new Point( 0, 0 ) );
						locs.add( new Point( size - sizeInst, size - sizeInst) );
						
						break;
					}
					case 3:
					{	
						locs.add( new Point( ( size - sizeInst ) / 2, 0 ) );
						locs.add( new Point( 0, size - sizeInst ) );
						locs.add( new Point( size - sizeInst, size - sizeInst ) );
						
						break;
					}
					case 4:
					{
						locs.add( new Point( 0, 0 ) );
						locs.add( new Point( size - sizeInst, 0 ) );
						locs.add( new Point( 0, size - sizeInst ) );
						locs.add( new Point( size - sizeInst, size - sizeInst ) );
						
						break;
					}
					case 5:
					{
						locs.add( new Point( 0, 0 ) );
						locs.add( new Point( size - sizeInst, 0) );
						locs.add( new Point( ( size - sizeInst ) / 2, ( size - sizeInst ) / 2 ) );
						locs.add( new Point( 0, size - sizeInst) );
						locs.add( new Point( size - sizeInst, size - sizeInst ) );
						
						break;
					}
					case 6:
					{
						locs.add( new Point( 0, 0 ) );
						locs.add( new Point( size - sizeInst, 0) );
						locs.add( new Point( sizeInst / 2, ( size - sizeInst ) / 2 ) );
						locs.add( new Point( size - sizeInst - sizeInst / 2, ( size - sizeInst ) / 2 ) );
						locs.add( new Point( 0, size - sizeInst) );
						locs.add( new Point( size - sizeInst, size - sizeInst ) );
						
						break;
					}
					case 7:
					{
						locs.add( new Point( 0, 0 ) );
						locs.add( new Point( (size - sizeInst) / 2, 0) );
						locs.add( new Point( size - sizeInst, 0) );
						locs.add( new Point( 0, (size - sizeInst) / 2 ) );
						locs.add( new Point( (size - sizeInst) / 2, (size - sizeInst) / 2) );
						locs.add( new Point( size - sizeInst, (size - sizeInst) / 2) );
						locs.add( new Point( (size - sizeInst) / 2, size - sizeInst) );
						
						break;
					}
					case 8:
					{
						locs.add( new Point( 0, 0 ) );
						locs.add( new Point( (size - sizeInst) / 2, 0) );
						locs.add( new Point( size - sizeInst, 0) );
						locs.add( new Point( 0, (size - sizeInst) / 2 ) );
						locs.add( new Point( (size - sizeInst) / 2, (size - sizeInst) / 2) );
						locs.add( new Point( size - sizeInst, (size - sizeInst) / 2) );
						locs.add( new Point( 0, size - sizeInst) );
						locs.add( new Point( size - sizeInst, size - sizeInst ) );
						
						break;
					}
					default:
					{
						locs.add( new Point( 0, 0 ) );
						locs.add( new Point( (size - sizeInst) / 2, 0) );
						locs.add( new Point( size - sizeInst, 0) );
						locs.add( new Point( 0, (size - sizeInst) / 2 ) );
						locs.add( new Point( (size - sizeInst) / 2, (size - sizeInst) / 2) );
						locs.add( new Point( size - sizeInst, (size - sizeInst) / 2) );
						locs.add( new Point( 0, size - sizeInst) );
						locs.add( new Point( (size - sizeInst) / 2, size - sizeInst ) );
						locs.add( new Point( size - sizeInst, size - sizeInst ) );
						
						break;
					}
				}
				
				int count = 0;
				for( String inst : knowInstrs )
				{
					Image imgInst = getInstrument( inst.toLowerCase(), sizeInst, c );
					
					Point loc = locs.get( count );
					
					basicPainter2D.composeImage( img, loc.x, loc.y, imgInst );
					
					count++;
					if( count > 8 )
					{
						break;
					}
				}
			}
		}
		
		return img;
	}
	
	public static Image getInstrument( String idInstrument, int size, Color c )
	{
		Image img = null;

		switch( idInstrument.toLowerCase() )
		{
			case "piano":
			{
				img = Piano( size, c );
				break;
			}
			case "organ":
			{
				img = Organ( size, c );
				break;
			}
			case "guitar":
			{
				img = Guitar(size, c);
				break;
			}
			case "whistle":
			{
				img = Whistle(size, c);
				break;
			}
			case "tinkle_bell":
			{
				img = Bell(size, c);
				break;
			}
			case "agogo":
			{
				img = Maraca(size, c);
				break;
			}
			case "contrabass":
			{
				img = Contrabass(size, c);
				break;
			}
			case "soprano_sax":
			{
				img = Sax( size, c);
				break;
			}
			case "violin":
			{
				img = Violin(size, c);
				break;
			}
			case "string_ensemble_1":
			{
				img = StringEnsemble( size, c);
				break;
			}
			case "ocarina":
			{
				img = Ocarina(size, c);
				break;
			}
			case "flute":
			{
				img = Flute(size, c);				
				break;
			}
			case "trombone":
			{
				img = Trombone(size, c);
				break;
			}
			case "xylophone":
			{
				img = Xylophone(size, c);
				break;
			}
			case "trumpet":
			{
				img = Trumpet(size, c);
				break;
			}
			case "steel_drums":
			{
				img = Drum(size, c);
				break;
			}			
			default:
			{
				img = Quaver( size, size, c, null );
				break;
			}
		}

		return img;
	}

	public static Image getInstrument( Byte idInstrument, int size, Color c )
	{
		String instr = getInstrumentString( idInstrument );
		
		return getInstrument( instr , size, c );
	}

	private static String getInstrumentString( Byte idInstrument )
	{
		String instr = "undefined";

		if( idInstrument < 8 ) // Piano 
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)0 );
		}
		else if( ( idInstrument > 7 && idInstrument < 16 ) || ( idInstrument == 108 ) ) // Chromatic Percussion
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)13 );
		}
		else if( idInstrument > 15 && idInstrument < 24 ) // Organ
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)19 );
		}
		else if( ( idInstrument > 24 && idInstrument < 32 ) || ( idInstrument > 103 && idInstrument < 108 ) ) // Guitar
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)24 );
		}
		else if( idInstrument > 31 && idInstrument < 40 ) // Bass
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)43 );
		}
		else if( ( idInstrument > 39 && idInstrument < 48 ) || ( idInstrument == 110 ) ) // Strings
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)40 );
		}
		else if( idInstrument > 47 && idInstrument < 56 ) // Ensemble
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)48 );
		}
		else if( idInstrument == 56 ) // Trumpet
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)idInstrument );
		}
		else if( idInstrument > 56 && idInstrument < 64 ) // Brass
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)57 );
		}
		else if( idInstrument > 63 && idInstrument < 68 ) // Sax 
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)57 );
		}
		else if( ( idInstrument > 67 && idInstrument < 78 ) || ( idInstrument == 109 ) || ( idInstrument == 111 ) ) // Pipe
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)73 );
		}
		else if( idInstrument == 78 ) // Whistle
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)idInstrument );
		}
		else if( idInstrument == 79 ) // Ocarina
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)idInstrument );
		}
		else if( idInstrument == 112 ) // Bell 
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)idInstrument );
		}
		else if( idInstrument == 113 ) // Agogo
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)idInstrument );
		}
		else if( idInstrument > 113 && idInstrument < 120 ) // Percussive
		{
			instr = INSTRUMENT_BYTE_TO_STRING.get( (byte)114 );
		}
		
		return instr;
	}
	
	public static Image Piano( int size, Color c )
	{	
		int thicknessBorder = size / 16;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}

		Image img = basicPainter2D.rectangle( size, size, thicknessBorder, c, Color.WHITE );

		double w = img.getWidth( null ) / 4.0 - thicknessBorder/2;

		double t4 = thicknessBorder / 4;

		double p = 0;
		for( int i = 0; i < 4; i++ )
		{				
			int x = (int)( p + thicknessBorder / 2);
			basicPainter2D.line( x, 0, x, img.getHeight( null ), thicknessBorder, c, img );

			p += w + t4;
		}

		Image black = basicPainter2D.rectangle( img.getWidth( null ) / 6, img.getHeight( null ) / 2, 1, c, c );
		p = p - ( w + t4);
		for( int i = 0; i < 3; i++ )
		{
			basicPainter2D.composeImage( img, (int)(p - black.getWidth( null ) / 2 + thicknessBorder / 2), thicknessBorder, black );

			p -= (w + t4);
		}

		return img;
	}

	public static Image Flute( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );

		int divSize = size / 10;

		if( divSize < 1 )
		{
			divSize = 1;
		}

		int[] xs = new int[] { 0, size - 2 * divSize, size - divSize / 2, size
				,size - divSize / 2, divSize + divSize / 2 };
		int[] ys = new int[] { size - divSize - divSize / 2, divSize /2, 0
				, divSize / 2, divSize * 2, size };

		basicPainter2D.fillPolygon( xs, ys, c, img );


		//Color holeColor = new Color( ~c.getRGB() );
		Color holeColor = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			holeColor = Color.BLACK;
		}
		
		
		int x = divSize;
		int y = size - 2 * divSize;

		for( int i = 0; i < 6; i++ )
		{
			basicPainter2D.circle( x, y, divSize, holeColor, img );
			x += divSize;
			y -= divSize;
		}

		xs = new int[] { size - 2 * divSize - divSize / 2
				, size - divSize - divSize / 2
				, size - divSize
				, size - divSize*2
				//, size - (int)( 0.2 * divSize ) - divSize
				//, size - (int)( 0.5 * divSize ) - divSize
		};
		ys = new int[] { divSize * 2
				, divSize
				, divSize + divSize / 2
				, 2 * divSize + divSize/2
				//, (int)( 1.5 * divSize )
				//, (int)( 1.2 * divSize )
		};

		basicPainter2D.fillPolygon(xs, ys, holeColor, img);

		return img;
	}

	public static Image Xylophone( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );

		int thicknessBorder = size / 32;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}

		int numPiece = 4;

		int pieceWitdh = size / numPiece - thicknessBorder;
		int divStepY = size / ( 3 * numPiece ) - thicknessBorder / 2;

		if( pieceWitdh < 1 )
		{
			pieceWitdh = 1;
		}

		if( divStepY < 1 )
		{
			divStepY = 1;
		}

		int x1 = 0;
		int x2 = x1 + pieceWitdh;
		int y1 = 0;
		int y2 = size - y1;
		int pH = size / 3 ;
		int r = pieceWitdh / 3;
		//Color nc = new Color( ~c.getRGB() );
		
		Color nc = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			nc = Color.BLACK;
		}
		
		
		for( int i = 0; i < numPiece; i++ )
		{
			basicPainter2D.fillPolygon( new int[] { x1, x2, x2, x1}
			, new int[] { y1, y1, y2, y2}
			, c, img );

			basicPainter2D.circle( (x1 + x2)/2 - r / 2, pH - r / 2,
					r, nc, img);
			basicPainter2D.circle( (x1 + x2)/2 - r / 2, size - pH - r
					/ 2, r, nc, img);

			x1 = x2 + thicknessBorder;
			x2 = x1 + pieceWitdh;
			y1 += divStepY;
			y2 = size - y1;
		}

		thicknessBorder = 2 * thicknessBorder;
		basicPainter2D.line( size / 2, size / 2, size -
				thicknessBorder, size - thicknessBorder, thicknessBorder , c, img );

		r = 3 * thicknessBorder;
		basicPainter2D.circle( ( size - r) /2, ( size - r) / 2, r,
				c, img );

		thicknessBorder = (int)( 0.9 * thicknessBorder );
		basicPainter2D.line( size / 2, size / 2, size -
				(int)(thicknessBorder * 1.2 ), size - (int)(thicknessBorder * 1.2 ),
				thicknessBorder , nc, img );
		r = 3 * thicknessBorder;
		basicPainter2D.circle( ( size - r) /2, ( size - r) / 2, r,
				nc, img );

		return img;
	}

	public static Image Trumpet( int size, Color c)
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );

		Image tr = basicPainter2D.fillPolygon( new int[] { 0, size/4, size/4 }
																, new int[] { size / 4, 0, size / 2 }
																, c, null );

		int thicknessBorder = tr.getHeight( null ) / 4;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}

		int y = tr.getHeight( null ) / 2;
		basicPainter2D.line( 0, y, size, y, thicknessBorder, c,
				img );
		basicPainter2D.composeImage( img, size - tr.getWidth( null ), 0, tr );

		basicPainter2D.line( thicknessBorder / 4 - 1, y - thicknessBorder / 2
										, thicknessBorder / 4 - 1, y + thicknessBorder / 2, thicknessBorder / 2
										, c , img );
		
		int w = size - tr.getWidth( null ) - thicknessBorder;
		int h = size - tr.getHeight( null ) /2 - thicknessBorder;
		basicPainter2D.roundRectangle( ( int )( thicknessBorder ) 
													, y
													, w
													, h
													, w / 2
													, (int)( 0.9 * h ) 
													, thicknessBorder
													, c, null, img );

		int x = ( int )( 1 * thicknessBorder ) + w / 2;  
		basicPainter2D.line( x, y - thicknessBorder / 2, x, y + h
				+ thicknessBorder / 2,  (int)( 0.65 * thicknessBorder ), c, img );
		basicPainter2D.line( x, y - thicknessBorder, x, y - thicknessBorder / 2
											, thicknessBorder / 4, c, img );
		basicPainter2D.line( x - thicknessBorder / 3, y - thicknessBorder - thicknessBorder / 4
											, x + thicknessBorder / 3, y - thicknessBorder - thicknessBorder / 4
											,  thicknessBorder / 4, c, img );
		
		
		x += (int)( 1.25 * thicknessBorder );
		basicPainter2D.line( x, y - thicknessBorder / 2, x, y + h
				+ thicknessBorder / 2,  (int)( 0.65 * thicknessBorder ), c, img );
		basicPainter2D.line( x, y - thicknessBorder, x, y - thicknessBorder / 2
											, thicknessBorder / 4, c, img );
		basicPainter2D.line( x - thicknessBorder / 3, y - thicknessBorder - thicknessBorder / 4
											, x + thicknessBorder / 3, y - thicknessBorder - thicknessBorder / 4
											,  thicknessBorder / 4, c, img );
		
		x -=  (int)(  2.5 * thicknessBorder );
		basicPainter2D.line( x, y - thicknessBorder / 2, x, y + h
				+ thicknessBorder / 2, (int)( 0.65 * thicknessBorder ), c, img );
		basicPainter2D.line( x, y - thicknessBorder, x, y - thicknessBorder / 2
											, thicknessBorder / 4, c, img );
		basicPainter2D.line( x - thicknessBorder / 3, y - thicknessBorder - thicknessBorder / 4
											, x + thicknessBorder / 3, y - thicknessBorder - thicknessBorder / 4
											,  thicknessBorder / 4, c, img );
		
		
		basicPainter2D.composeImage( basicPainter2D.createEmptyCanva( size, size, null )
												, 0, ( size - img.getHeight( null ) ) / 2
												, img );
		
				
		return img;
	}
	
	public static Image Drum( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		//Color borderColor = new Color( ~c.getRGB() );
				
		Color borderColor = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			borderColor = Color.BLACK;
		}
		
		int divStep = size / 10;
		
		int thicknessBorder = size / 32;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		int ovalHeigh = divStep * 4;
		
		
		basicPainter2D.oval( 0, size - ovalHeigh, size, ovalHeigh, thicknessBorder, c, c, img );
		basicPainter2D.fillPolygon( new int[] { size, 0, 0, size }
													, new int[] { size - ovalHeigh / 2, size - ovalHeigh / 2
													, size - ( 3 * ovalHeigh ) / 2, size - ( 3 * ovalHeigh ) / 2}
													, c, img);
		
		basicPainter2D.oval( 0, size - 2 * ovalHeigh, size, ovalHeigh, thicknessBorder, borderColor, borderColor, img );
		basicPainter2D.oval( 0, size - (int)( 2.05 * ovalHeigh ), size, ovalHeigh, thicknessBorder, c, borderColor, img );
		
		int y = size - (int)( 2.05 * ovalHeigh );
		basicPainter2D.dot( ( 2 * size ) / 3, y - 4 * thicknessBorder, thicknessBorder * 3, c, true, img );
		basicPainter2D.line( ( 2 * size ) / 3 + thicknessBorder, y - 4 * thicknessBorder + thicknessBorder
											, size - thicknessBorder, thicknessBorder, thicknessBorder
											, c, img );
		
		basicPainter2D.dot( size / 3, y - 4 * thicknessBorder, thicknessBorder * 3, c, true, img );
		basicPainter2D.line( size / 3 + thicknessBorder, y - 4 * thicknessBorder + thicknessBorder
											, thicknessBorder, thicknessBorder, thicknessBorder
											, c, img );
		
		return img;
	}

	public static Image Trombone( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 10;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
						
		double gridSize = size / 8;
		int startAngle = 90;
		int arcAngle = 180;
		double arcSize = 3.25  *gridSize;
		
		Image tr = basicPainter2D.triangle( (int)( gridSize * 3), 1, c, c, basicPainter2D.WEST );

		Image curv = basicPainter2D.arc( 0, 0, (int)arcSize, (int)arcSize, startAngle, arcAngle, thicknessBorder, c, null, null );
		Image curv2 = basicPainter2D.arc( 0, 0, (int)arcSize, (int)arcSize, -startAngle, arcAngle, thicknessBorder, c, null, null );
		
		basicPainter2D.composeImage( img, size - tr.getWidth( null ), 0, tr );
		
		basicPainter2D.line( (int)( arcSize / 2 ) + thicknessBorder, tr.getHeight( null ) / 2
											, size - tr.getWidth( null ) / 2, tr.getHeight( null ) / 2
											, thicknessBorder, c, img );
		
		basicPainter2D.composeImage( img, 0, tr.getHeight( null ) / 2 - thicknessBorder / 2, curv );
		
		basicPainter2D.line( curv.getWidth( null ) /2 + thicknessBorder
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - thicknessBorder
											, size - tr.getWidth( null ) 
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - thicknessBorder 
											, thicknessBorder, c, img );
		
		basicPainter2D.composeImage( img, size - tr.getWidth( null ) - curv.getWidth( null ) / 2 + thicknessBorder/ 2
										, tr.getHeight( null ) / 2 + curv.getHeight( null ) - thicknessBorder - thicknessBorder / 2, curv2 );
		
		basicPainter2D.line( curv.getWidth( null ) /4 
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - 2*thicknessBorder + curv2.getHeight( null )
											, size - tr.getWidth( null )
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - 2*thicknessBorder  + curv2.getHeight( null )
											, thicknessBorder, c, img );
		
		/****/
		basicPainter2D.line( curv.getWidth( null ) /4
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - 2*thicknessBorder + curv2.getHeight( null ) - thicknessBorder / 2
											, curv.getWidth( null ) /4
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - 2*thicknessBorder  + curv2.getHeight( null ) + thicknessBorder / 2
											, thicknessBorder, c, img );
		
		
		basicPainter2D.line( size /2 - thicknessBorder / 2
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - thicknessBorder
											, size / 2 - thicknessBorder / 2
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - 2*thicknessBorder + curv2.getHeight( null )
											, thicknessBorder, c, img );
	
		basicPainter2D.line( curv.getWidth( null ) / 2 + thicknessBorder / 2
											, tr.getHeight( null ) / 2
											, curv.getWidth( null ) / 2 + thicknessBorder / 2
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - thicknessBorder
											, thicknessBorder, c, img );
		
		basicPainter2D.line( size - tr.getWidth( null ) - thicknessBorder
											, tr.getHeight( null ) / 2
											, size - tr.getWidth( null ) - thicknessBorder
											, tr.getHeight( null ) / 2 + curv.getHeight( null ) - thicknessBorder
											, thicknessBorder, c, img );
		
		return img;
	}
	
	public static Image Organ( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 10;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color holeColor = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			holeColor = Color.BLACK;
		}		
		
		int tubeNum = 5;
		
		int pad = thicknessBorder;
		
		int tubePad = 1;
		int tubeWidth = size / tubeNum - tubePad;
		int tubeHeight = size  / 2;		
		int tubeArc = tubeWidth / 3;		
		
		if( tubeWidth < 1 )
		{
			tubeWidth = 1;
		}
		
		if( tubeHeight < 1 )
		{
			tubeHeight = 1;
		}		
		
		for( int i = 0; i < tubeNum; i++ )
		{ 
			Image tubeTop = basicPainter2D.oval( 0, 0, tubeWidth, tubeWidth / 2, 1, c, c, null );
			int holeWidth =  (2 * tubeWidth ) / 3;
			int holeHeight = tubeWidth / 3;
			if( holeWidth < 1 )
			{
				holeWidth = 1;
			}
			
			if( holeHeight < 1 )
			{
				holeHeight = 1;
			}
			Image tubeTopHole = basicPainter2D.oval( 0, 0, holeWidth, holeHeight, 1, holeColor, holeColor, null );
			
			Image tube = basicPainter2D.roundRectangle( 0, 0, tubeWidth, tubeHeight - tubeTop.getHeight( null ) / 2 + tubeArc / 2, tubeArc, tubeArc, 1, c, c, null );
			
			
			basicPainter2D.oval( tube.getWidth( null ) / 3, tube.getHeight( null ) - (int)( 1.5 * tubeArc )
															, tube.getWidth( null ) / 3, tubeArc / 2
															, 1, holeColor, holeColor, tube );
			
			basicPainter2D.composeImage( tubeTop, ( tubeTop.getWidth( null ) - tubeTopHole.getWidth( null ) ) / 2
													, ( tubeTop.getHeight( null ) - tubeTopHole.getHeight( null ) ) / 2
													, tubeTopHole );
			
			basicPainter2D.composeImage( img, i * ( tubeWidth + tubePad) , tubeTop.getHeight( null ) / 2 - tubeArc / 2, tube );
			basicPainter2D.composeImage( img, i * ( tubePad + tubeWidth ) , 0, tubeTop );
		}
		
		Image keyboard = basicPainter2D.rectangle( size, size - tubeHeight - tubePad, thicknessBorder, c, holeColor );
		
		int keyWidth = keyboard.getWidth( null ) / 8;
		Image blackKeys = basicPainter2D.rectangle( keyWidth - pad / 2, ( 2 * keyboard.getHeight( null ) ) / 3 - pad / 4
																	, thicknessBorder, c, c );
		
		basicPainter2D.composeImage( keyboard, keyWidth, 0, blackKeys );
		basicPainter2D.composeImage( keyboard, 2 * keyWidth, 0, blackKeys );
		basicPainter2D.composeImage( keyboard, 3 * keyWidth, 0, blackKeys );
		basicPainter2D.composeImage( keyboard, 5 * keyWidth, 0, blackKeys );
		basicPainter2D.composeImage( keyboard, 6 * keyWidth, 0, blackKeys );
		
		basicPainter2D.composeImage( img, 0, size - keyboard.getHeight( null ), keyboard );
		
		return img;
	}

	public static Image Guitar( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 16;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color c2 = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] > 0.75 )
		{
			c2 = Color.BLACK;
		}		
		
		
		int mast = thicknessBorder * 2;
		int mastHead = thicknessBorder * 3; 
		basicPainter2D.line( size - mast, mast, size / 2, size / 2, mast, c, img );
		basicPainter2D.line(  size - mast, mast,  size - mast - mastHead / 10, mast + mastHead / 10, mastHead, c, img );
		
		
		int r = (int)( 0.6 *  size );
		int r2 = ( 3 * r ) / 4;
		int r3 = ( 4 * r2 ) / 10;
		
		Image circ1 = basicPainter2D.circle( 0, 0, r, c, null );
		Image circ2 = basicPainter2D.circle( 0, 0, r2, c, null );
		
		basicPainter2D.composeImage( img, 0, size - circ1.getHeight( null ), circ1 );
		basicPainter2D.composeImage( img, circ1.getWidth( null ) / 2 - circ2.getWidth( null ) / 8
											, size - circ1.getHeight( null ) 
												- (int)( Math.cos( Math.PI / 4) *  circ2.getHeight( null ) / 2 ) 
												+ circ2.getHeight( null ) / 8
											, circ2 );
		
		basicPainter2D.circle( circ1.getWidth( null ) / 2 - circ2.getWidth( null ) / 8 + circ2.getWidth( null ) / 2 - r3 / 2
												, size - circ1.getHeight( null ) 
													- (int)( Math.cos( Math.PI / 4) *  circ2.getHeight( null ) / 2 ) 
													+ circ2.getHeight( null ) / 8 
													+ circ2.getHeight( null ) / 2
													- r3 / 2
												, r3, c2, img );
		
		basicPainter2D.line( 3 * thicknessBorder, size - r + 3*thicknessBorder
											, r - 3 * thicknessBorder, size - 3*thicknessBorder, thicknessBorder, c2, img );
				
				
		return img;
	}

	public static Image Contrabass( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 10;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color c2 = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			c2 = Color.BLACK;
		}		
		
		int w = (int)( 0.60 * size );
		int w2 = (int)( 0.75 * size );
		
		if( w < 1 )
		{
			w = 1;
		}
		
		if( w2 < 1 )
		{
			w2 = 1;
		}
		
		int pad = (int)( 1.05 * thicknessBorder );
		
		Image topCirc = basicPainter2D.arc( 0, 0, w, w, 0, 180, 1, c, c, null );
		Image butCirc = basicPainter2D.arc( 0, 0, w2, w2, 180, 180, 1, c, c, null );
		Image fillPad = basicPainter2D.rectangle( w, pad, 1, c, c );
		
		Image leftCurve = basicPainter2D.arc( 0, 0
															, ( butCirc.getWidth( null ) - topCirc.getWidth( null ) ), pad * 2 + thicknessBorder /2
															, 270, 90, thicknessBorder / 2, c, null, null);
		
		int r = (int)( 0.75 * thicknessBorder );
		if( r < 1 )
		{
			r = 1;
		}
		Image circ = basicPainter2D.circle( 0, 0, r, c2, null );
		
		Image rigthCurve = basicPainter2D.arc( 0, 0
														, ( butCirc.getWidth( null ) - topCirc.getWidth( null ) ), pad * 2 + thicknessBorder /2
														, 180, 90, thicknessBorder / 2, c, null, null);
				
		basicPainter2D.composeImage( img, 0, size - butCirc.getHeight( null), butCirc );
		
		basicPainter2D.composeImage( img, (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2
											,  size - butCirc.getHeight( null) / 2 - topCirc.getHeight( null ) / 2 - pad
											, topCirc );
		
		basicPainter2D.composeImage( img, (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2
											,  size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) 
											, fillPad );
		
		basicPainter2D.composeImage( img, (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 - leftCurve.getWidth( null ) + thicknessBorder / 2
										, size - butCirc.getHeight( null)/2 - leftCurve.getHeight( null ) + thicknessBorder / 2, leftCurve );
		
		basicPainter2D.composeImage( img, butCirc.getWidth( null) - rigthCurve.getWidth( null ) + thicknessBorder / 2
										, size - butCirc.getHeight( null)/2 - rigthCurve.getHeight( null ) + thicknessBorder / 2, rigthCurve );
		
				
		basicPainter2D.line( (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
											, 0
											, (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
											, size / 2, (int)( 0.75 * thicknessBorder ), c, img );		
		
		basicPainter2D.line( (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
												, 0
												, (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
												, thicknessBorder, thicknessBorder, c, img );
		
		basicPainter2D.line(  size - thicknessBorder
											, 4 * thicknessBorder
											, size - thicknessBorder
											, size - thicknessBorder, thicknessBorder / 2, c, img );
		
		
		
		
		
		basicPainter2D.composeImage( img, butCirc.getWidth( null )/3  - circ.getWidth( null ) / 2
											, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null )
											, circ );
		
		basicPainter2D.composeImage( img, butCirc.getWidth( null ) / 3 - circ.getWidth( null )   
												, size - butCirc.getHeight( null) / 3
												, circ );
		
		basicPainter2D.line( butCirc.getWidth( null ) / 3 + r/4 - circ.getHeight( null ) / 2
											, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) + circ.getHeight( null ) / 2 + r/ 4
											, butCirc.getWidth( null ) / 3 + r/4 - circ.getHeight( null ) /2
											, size - butCirc.getHeight( null) / 3 + circ.getHeight( null ) / 2 - r/4
											, r/ 2, c2, img );
		
		
		
		
		basicPainter2D.composeImage( img, ( 2 * butCirc.getWidth( null ) ) / 3 - circ.getWidth( null ) / 2
											, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null )
											, circ );

		basicPainter2D.composeImage( img, ( 2 * butCirc.getWidth( null ) ) / 3    
											, size - butCirc.getHeight( null) / 3
											, circ );

		basicPainter2D.line( ( 2 * butCirc.getWidth( null ) ) / 3 + r /4
												, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) + circ.getHeight( null ) / 2 + r/ 4
												, ( 2 * butCirc.getWidth( null ) ) / 3 + r/4 
												, size - butCirc.getHeight( null) / 3 + circ.getHeight( null ) / 2 - r/4
												, r/ 2, c2, img );
		
		
		return img;
	}
	
	public static Image Violin( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 10;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color c2 = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			c2 = Color.BLACK;
		}		
		
		int w = (int)( 0.5 * size );
		int w2 = (int)( 0.5 * size );
		
		int pad = (int)( 2 * thicknessBorder );
		
		if( w < 1 )
		{
			w = 1;
		}
		
		if( w2 < 1 )
		{
			w2 = 1;
		}
					
		
		Image topCirc = basicPainter2D.arc( 0, 0, w, w, 0, 180, 1, c, c, null );
		Image butCirc = basicPainter2D.arc( 0, 0, w2, w2, 180, 180, 1, c, c, null );
		Image fillPad = basicPainter2D.rectangle( w - thicknessBorder, pad, 1, c, c );
		
		int xShift = ( size - topCirc.getWidth( null ) ) / 4;
		
		int curvBotH = pad;
		if( curvBotH < 1 )
		{
			curvBotH = 1;
		}
		
		int curvW = ( butCirc.getWidth( null ) - topCirc.getWidth( null ) );
		if( curvW < 1 )
		{
			curvW = topCirc.getWidth( null ) / 4; 
		}
			
		
		Image leftCurve = basicPainter2D.arc( 0, 0
															, curvW
															, curvBotH
															, 270, 180, thicknessBorder / 2, c, null, null);
		
		Image rigthCurve = basicPainter2D.arc( 0, 0
															, curvW
															, curvBotH
															, 90, 180, thicknessBorder / 2, c, null, null);
		
		int r = (int)( 0.75 * thicknessBorder );
		if( r < 1 )
		{
			r = 1;
		}
		Image circ = basicPainter2D.circle( 0, 0, r, c2, null );
				
				
		basicPainter2D.composeImage( img, xShift + 0, size - butCirc.getHeight( null), butCirc );
		
		basicPainter2D.composeImage( img, xShift + (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2
											,  size - butCirc.getHeight( null) / 2 - topCirc.getHeight( null ) / 2 - pad
											, topCirc );
						
		basicPainter2D.composeImage( img,  xShift + (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + ( topCirc.getWidth( null ) - fillPad.getWidth( null ) ) / 2 
												,  size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) 
												, fillPad );
		
		
		basicPainter2D.composeImage( img
										, xShift -leftCurve.getWidth( null ) / 2 + thicknessBorder / 4
										, size - butCirc.getHeight( null)/2 - leftCurve.getHeight( null ) + thicknessBorder / 8
										, leftCurve );

		
		basicPainter2D.composeImage( img, xShift + (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) - rigthCurve.getWidth( null ) / 2 - thicknessBorder / 4 
										, size - butCirc.getHeight( null)/2 - rigthCurve.getHeight( null ) + thicknessBorder / 8
										, rigthCurve );
		
		
		
		
				
		basicPainter2D.line( xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
											, 0
											, xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
											, size / 2, (int)( 0.75 * thicknessBorder ), c, img );		
		
		basicPainter2D.line( xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
												, 0
												, xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2
												, thicknessBorder, thicknessBorder, c, img );
		
		basicPainter2D.line( xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2 - ( 3 * thicknessBorder ) / 4 
												, thicknessBorder / 4
												, xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2 + ( 3 * thicknessBorder ) / 4 
												, thicknessBorder / 4
												, thicknessBorder / 4, c, img );
		
		basicPainter2D.line( xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2 - ( 3 * thicknessBorder ) / 4
											, thicknessBorder / 4 + thicknessBorder / 2
											, xShift +  (butCirc.getWidth( null ) - topCirc.getWidth( null ) ) / 2 + topCirc.getWidth( null ) / 2 + ( 3 * thicknessBorder ) / 4 
											, thicknessBorder / 4 + thicknessBorder / 2
											, thicknessBorder / 4, c, img );
		
		
		
		basicPainter2D.line( xShift + topCirc.getWidth( null ) + thicknessBorder
											, 3 * thicknessBorder
											, xShift + topCirc.getWidth( null ) + thicknessBorder 
											, size - thicknessBorder / 2
											, thicknessBorder / 2, c, img );
		
		basicPainter2D.line( xShift + topCirc.getWidth( null ) + thicknessBorder
											, (int)( 3.25 * thicknessBorder )
											, xShift + topCirc.getWidth( null ) + thicknessBorder + thicknessBorder / 2 
											, (int)( 3.25  * thicknessBorder )
											, thicknessBorder / 2, c, img );
		
		basicPainter2D.line( xShift +  topCirc.getWidth( null ) + thicknessBorder
										, (int)( size - thicknessBorder * 1.25 )
										, xShift + topCirc.getWidth( null ) + thicknessBorder + thicknessBorder / 2 
										, (int)( size - thicknessBorder * 1.25 )
										, thicknessBorder / 2, c, img );
		
		basicPainter2D.line( xShift + topCirc.getWidth( null ) + thicknessBorder + thicknessBorder / 2
											, (int)( 3.25 * thicknessBorder )
											, xShift + topCirc.getWidth( null ) + thicknessBorder + thicknessBorder / 2 
											, (int)( size - thicknessBorder * 1.25 )
											, thicknessBorder / 4, c, img );
		
		
		basicPainter2D.composeImage( img, xShift + butCirc.getWidth( null )/3  - circ.getWidth( null ) / 2
											, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) - topCirc.getHeight( null ) / 4
											, circ );
		
		basicPainter2D.composeImage( img, xShift + butCirc.getWidth( null ) / 3 - circ.getWidth( null )   
												, size - butCirc.getHeight( null) / 3 - topCirc.getHeight( null ) / 4
												, circ );
		
		basicPainter2D.line( xShift + butCirc.getWidth( null ) / 3 + r/4 - circ.getHeight( null ) / 2
											, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) + circ.getHeight( null ) / 2 + r/ 4 - topCirc.getHeight( null ) / 4
											, xShift + butCirc.getWidth( null ) / 3 + r/4 - circ.getHeight( null ) /2
											, size - butCirc.getHeight( null) / 3 + circ.getHeight( null ) / 2 - r/4 - topCirc.getHeight( null ) / 4
											, r/ 2, c2, img );
		
		
		
		
		basicPainter2D.composeImage( img, xShift + ( 2 * butCirc.getWidth( null ) ) / 3 - circ.getWidth( null ) / 2
											, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) - topCirc.getHeight( null ) / 4
											, circ );

		basicPainter2D.composeImage( img, xShift + ( 2 * butCirc.getWidth( null ) ) / 3    
											, size - butCirc.getHeight( null) / 3 - topCirc.getHeight( null ) / 4
											, circ );

		basicPainter2D.line( xShift + ( 2 * butCirc.getWidth( null ) ) / 3 + r /4
												, size - butCirc.getHeight( null) / 2 - fillPad.getHeight( null ) + circ.getHeight( null ) / 2 + r/ 4 - topCirc.getHeight( null ) / 4
												, xShift +  ( 2 * butCirc.getWidth( null ) ) / 3 + r/4 
												, size - butCirc.getHeight( null) / 3 + circ.getHeight( null ) / 2 - r/4 - topCirc.getHeight( null ) / 4
												, r/ 2, c2, img );
		
		basicPainter2D.oval( xShift + thicknessBorder
											, (int)( size - thicknessBorder * 1.25 )
											, (int)( 1.75 * thicknessBorder)
											, thicknessBorder, 1, c2, c2, img );
		
		return img;
	}
		
	public static Image StringEnsemble( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 10;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color c2 = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			c2 = Color.BLACK;
		}
		
		int w = size / 2;
		int h = ( 2 * size ) / 3;
		
		if( w < 1 )
		{
			w = 1;
		}
		
		if( h < 1 )
		{
			h = 1;
		}
		
		Image head = basicPainter2D.rectangle( w, h, 1, c, c );
		
		head = basicPainter2D.fillPolygon( new int[] { 0, w, w, w - w/6, w / 6, 0}
															, new int[] { 0, 0, h - h / 6, h, h, h - h / 6 }
															, c, null );
		
		int r = w / 5;
		if( r < 1 )
		{
			r = 1;
		}
		Image circ = basicPainter2D.circle( 0, 0, r, c2, null );
						
		basicPainter2D.composeImage( head, circ.getWidth( null ) / 2, circ.getWidth( null ), circ );
		basicPainter2D.composeImage( head, circ.getWidth( null ) / 2, (int)( 2.5 * circ.getWidth( null ) ), circ );
		basicPainter2D.composeImage( head, circ.getWidth( null ) / 2, 4 * circ.getWidth( null ), circ );
		
		basicPainter2D.composeImage( head, head.getWidth( null ) - circ.getWidth( null ) - circ.getWidth( null ) / 2, circ.getWidth( null ), circ );
		basicPainter2D.composeImage( head, head.getWidth( null ) - circ.getWidth( null ) - circ.getWidth( null ) / 2, (int)( 2.5 * circ.getWidth( null ) ), circ );
		basicPainter2D.composeImage( head, head.getWidth( null ) - circ.getWidth( null ) - circ.getWidth( null ) / 2, 4 * circ.getWidth( null ), circ );
		
		
		
		
		int fretPad = r / 4;
		
		int wFret = ( 2 * w ) / 3;
		int hFret = ( size - head.getHeight( null ) ) / 2 - fretPad;
		if( wFret < 1 )
		{
			wFret = 1;
		}
		
		if( hFret < 1 )
		{
			hFret = 1;
		}
		 
		Image fret = basicPainter2D.rectangle( wFret, hFret, 1, c, c );
		
		
		int ovalW = (int)( 0.75 * r );
		int ovalH = (int)( 1.25 * r );
		if( ovalW < 1 )
		{
			ovalW = 1;
		}
		
		if( ovalH < 1 )
		{
			ovalH = 1;
		}
		
		Image oval = basicPainter2D.oval( 0, 0, ovalW, ovalH, 1, c, c, null );
		
		
		basicPainter2D.composeImage( img
											, ( size - head.getWidth( null ) ) / 2
											, 0
											, head );
		
		
		
		basicPainter2D.composeImage( img
										, ( size - head.getWidth( null ) ) / 2 - (int)( 1.5 * oval.getWidth( null ) )
										, circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2
										, oval );
		
		basicPainter2D.line( ( size - head.getWidth( null ) ) / 2 - (int)( 1.5 * oval.getWidth( null ) ) + ovalW / 2
											, circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2 + ovalH / 2
											, ( size - head.getWidth( null ) ) / 2
											, circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2 + ovalH / 2
											, r / 2, c, img );
		
		basicPainter2D.composeImage( img
										, ( size - head.getWidth( null ) ) / 2 - (int)( 1.5 * oval.getWidth( null ) )
										, (int)( 2.5 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2)
										, oval );
		
		basicPainter2D.line( ( size - head.getWidth( null ) ) / 2 - (int)( 1.5 * oval.getWidth( null ) ) + ovalW / 2
											, (int)( 2.5 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
											, ( size - head.getWidth( null ) ) / 2
											, (int)( 2.5 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
											, r / 2, c, img );
		
		basicPainter2D.composeImage( img
										, ( size - head.getWidth( null ) ) / 2 - (int)( 1.5 * oval.getWidth( null ) )
										, (int)( 4 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2)
										, oval );
		
		basicPainter2D.line( ( size - head.getWidth( null ) ) / 2 - (int)( 1.5 * oval.getWidth( null ) ) + ovalW / 2
											, (int)( 4 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
											, ( size - head.getWidth( null ) ) / 2
											, (int)( 4 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
											, r / 2, c, img );
		
		
		basicPainter2D.composeImage( img
										, ( size - head.getWidth( null ) ) / 2 + head.getWidth( null ) + (int)( 0.5 * oval.getWidth( null ) )
										, circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2
										, oval );

		basicPainter2D.line( ( size - head.getWidth( null ) ) / 2 + head.getWidth( null )
											, circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2 + ovalH / 2
											, ( size - head.getWidth( null ) ) / 2 + head.getWidth( null ) + oval.getWidth( null )
											, circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2 + ovalH / 2
											, r / 2, c, img );		
		
		basicPainter2D.composeImage( img
										, ( size - head.getWidth( null ) ) / 2  + head.getWidth( null ) + (int)( 0.5 * oval.getWidth( null ) )
										, (int)( 2.5 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2)
										, oval );
		
		basicPainter2D.line( ( size - head.getWidth( null ) ) / 2 + head.getWidth( null )
										, (int)( 2.5 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
										, ( size - head.getWidth( null ) ) / 2 + head.getWidth( null ) + oval.getWidth( null )
										, (int)( 2.5 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
										, r / 2, c, img );

		basicPainter2D.composeImage( img
										, ( size - head.getWidth( null ) ) / 2  + head.getWidth( null ) + (int)( 0.5 * oval.getWidth( null ) )
										, (int)( 4 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2)
										, oval );
		
		basicPainter2D.line( ( size - head.getWidth( null ) ) / 2 + head.getWidth( null )
										, (int)( 4 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
										, ( size - head.getWidth( null ) ) / 2 + head.getWidth( null ) + oval.getWidth( null )
										, (int)( 4 * circ.getHeight( null ) - ( oval.getHeight( null ) - circ.getHeight( null ) ) / 2) + ovalH / 2
										, r / 2, c, img );
		
		
		basicPainter2D.composeImage( img
											, ( size - fret.getWidth( null ) ) / 2
											, size - fret.getHeight( null )
											, fret);
		
		basicPainter2D.composeImage( img
											, ( size - fret.getWidth( null ) ) / 2
											, size - 2 * fret.getHeight( null ) - fretPad
											, fret);
		
		return img;
	}

	public static Image Sax( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 10;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color c2 = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			c2 = Color.BLACK;
		}
		
		int holeW = size / 3;
		int holeH = holeW / 2;
		
		if( holeW < 1 )
		{
			holeW = 1;
		}
		
		if( holeH < 1 )
		{
			holeH = 1;
		}
		
		Image hole = basicPainter2D.arc( 0, 0, holeW, holeH
														, 0, 360, 1, c, c, null );
	
		int holeW2 = holeW - 10;
		int holeH2 = holeH - 10; 
		if( holeW2 < 1 )
		{
			holeW2 = 1;
		}
		
		if( holeH2 < 1 )
		{
			holeH2 = 1;
		}
		
		Image hole2 = basicPainter2D.arc( 0, 0, holeW2, holeH2
														, 0, 360, 1, c2, c2, null );
		
		basicPainter2D.composeImage( hole
										, ( hole.getWidth( null ) - hole2.getWidth( null ) ) / 2
										, ( hole.getHeight( null ) - hole2.getHeight( null ) ) / 2
										, hole2 );
		
		Image tr = basicPainter2D.triangle( hole.getWidth( null ), 1, c, c, basicPainter2D.SOUTH );
		
		Image butCurve = basicPainter2D.arc( 0, 0
															, (int)( 1.5 * tr.getWidth( null ) )
															, size / 2
															, 180, 180
															, (int)( thicknessBorder * 2 )
															, c, null, null );
				
		Image vertLin = basicPainter2D.line( thicknessBorder * 2
															, 0
															, thicknessBorder * 2
															, size - butCurve.getHeight( null ) / 2 - thicknessBorder * 3
															, thicknessBorder * 4
															, c, null );
		
		Image circTop = basicPainter2D.circle( 0, 0, vertLin.getWidth( null ), c, null );
		
		int rck = vertLin.getWidth( null ) / 2;
		if( rck < 1 )
		{
			rck = 1;
		}
		
		Image circKey = basicPainter2D.circle( 0, 0, rck, c2, null );
		
		basicPainter2D.composeImage( vertLin
											, vertLin.getWidth( null ) - circKey.getWidth( null )
											, vertLin.getHeight( null ) / 2
											, circKey );
		
		basicPainter2D.composeImage( vertLin
										, vertLin.getWidth( null ) - circKey.getWidth( null )
										, vertLin.getHeight( null ) / 2 - circKey.getHeight( null ) - rck / 4
										, circKey );
		
		basicPainter2D.composeImage( vertLin
										, vertLin.getWidth( null ) - circKey.getWidth( null )
										, vertLin.getHeight( null ) / 2 + circKey.getHeight( null ) + rck / 4
										, circKey );
		
		Image blowCurve = basicPainter2D.arc( 0, 0
															, circTop.getWidth( null ) * 3
															, circTop.getHeight( null ) * 2
															, 0, -80
															, thicknessBorder / 2
															, c, null, null );
		
		basicPainter2D.composeImage( img
										, size - hole.getWidth( null ) - butCurve.getWidth( null ) / 2 - thicknessBorder / 2
										, size - butCurve.getHeight( null )
										, butCurve );
		
		basicPainter2D.composeImage( img
											, size - hole.getWidth( null ) - butCurve.getWidth( null ) / 2 - thicknessBorder / 2
											, size - butCurve.getHeight( null ) / 2 - vertLin.getHeight( null )
											, vertLin );
		
		basicPainter2D.composeImage( img
											, size - hole.getWidth( null ) - butCurve.getWidth( null ) / 2 - thicknessBorder / 2
											, size - butCurve.getHeight( null ) / 2 - vertLin.getHeight( null ) - circTop.getHeight( null ) / 2
											, circTop );
		
		basicPainter2D.composeImage( img
										, size - hole.getWidth( null ) - butCurve.getWidth( null ) / 2 - thicknessBorder / 2 - blowCurve.getWidth( null ) + thicknessBorder /2
										, size - butCurve.getHeight( null ) / 2 - vertLin.getHeight( null ) - circTop.getHeight( null ) / 2 - blowCurve.getHeight( null ) / 2 + thicknessBorder * 2
										, blowCurve );
				
				
		basicPainter2D.composeImage( img
										, size - tr.getWidth( null ) 
										, size - butCurve.getHeight( null ) / 2 - tr.getHeight( null ) / 2 
										, tr );
		
		basicPainter2D.composeImage( img
											, size - hole.getWidth( null ) 
											,size - butCurve.getHeight( null ) / 2 - tr.getHeight( null ) / 2 - hole.getHeight( null )/ 2 
											, hole );
				
		
				
		
		return img;
	}

	public static Image Ocarina( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 10;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color c2 = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			c2 = Color.BLACK;
		}
		
		int w = size / 2;
		if( w < 1 )
		{
			w = 1;
		}
		Image tr = basicPainter2D.triangle( w, 1, c, c, basicPainter2D.NORTH );
		
		int r = w / 4;
		if( r < 1 )
		{
			r = 1;
		}
		Image circ = basicPainter2D.circle( 0, 0, r, c2, null );
		
		Image body = basicPainter2D.oval( 0, 0, size, size - tr.getHeight( null ) / 2, 1, c, c, null  );
		
		basicPainter2D.composeImage( body, circ.getWidth( null ) / 2, body.getHeight( null ) / 2, circ );
		basicPainter2D.composeImage( body, circ.getWidth( null ) / 2 + circ.getWidth( null ), body.getHeight( null ) / 2 + circ.getHeight( null ), circ );
		basicPainter2D.composeImage( body, circ.getWidth( null ) / 2 + circ.getWidth( null ) * 2, body.getHeight( null ) / 2, circ );
		
		basicPainter2D.composeImage( body, body.getWidth( null ) / 2, circ.getHeight( null ) / 2 - circ.getHeight( null ) / 3, circ );
		basicPainter2D.composeImage( body, body.getWidth( null ) / 2 + (int)( 1 * circ.getWidth( null ) ), circ.getHeight( null ) / 2 - circ.getHeight( null ) / 3 + circ.getHeight( null ) / 3, circ );
		basicPainter2D.composeImage( body, body.getWidth( null ) / 2 + 2 * circ.getWidth( null ), circ.getHeight( null ) / 2 - circ.getHeight( null ) / 3 + (int)( 2.5 * circ.getHeight( null ) ) / 3, circ );
		basicPainter2D.composeImage( body, body.getWidth( null ) / 2 + (int)( 3 * circ.getWidth( null ) ) - circ.getWidth( null ) / 4, circ.getHeight( null ) / 2 - circ.getHeight( null ) / 3 + (int)( 5 * circ.getHeight( null ) ) / 3, circ );
		
		basicPainter2D.composeImage( img, tr.getWidth( null ) / 8, 0, tr );
		
		basicPainter2D.composeImage( img, 0, size - body.getHeight( null ), body );
				
				
		return img;
	}
	
	public static Image Whistle( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 2;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		int radio = ( 3 * size ) / 4;
		
		if( radio < 1)
		{
			radio = 1;
		}
		
		basicPainter2D.circumference( 0, size - radio, radio, thicknessBorder, c, img ); 
		
		double angle = Math.PI/11;
		
		
		double x1 = ( Math.cos( Math.PI / 2 + angle ) + 1 ); 
		double y1 = ( Math.sin( -( Math.PI / 2 + angle ) ) + 1 );
		
		double m = -Math.tan( angle );
		double n = y1 - m * x1; 				
		
		double x2 = -n / m;
		double y2 = m * x2 + n;
		x1 = x1 / 2;
		y1 = y1 / 2;
				
		int[] xs = new int[] { (int)( radio * x1 ), (int)( size * x2 ) , size, radio - thicknessBorder/2};
		int[] ys = new int[] { (int)( y1 * radio +  size - radio ), (int)( size * y2 ) , (size-radio)/2, size/2};
		
		basicPainter2D.fillPolygon(xs, ys, c, img );
		
		int thickness = size /18;
		if(thickness < 1 )
		{
			thickness = 1;
		}
		
		basicPainter2D.line( (int)( 2 * thickness ), thickness, xs[ 0 ] - (int)( 1 * thickness ), ys[ 0 ] - (int)( 1.5 * thickness ), thickness, c, img );
		basicPainter2D.line( thickness, thickness * 4, xs[ 0 ] - thickness * 2, ys[ 0 ], thickness, c, img );
		basicPainter2D.line( xs[0] + thickness, ys[ 0 ] - (int)(thickness * 1.5 ), xs[0] + (int)( thickness * 1.5 ), ys[ 0 ] - thickness * 4, thickness, c, img );
		
		
		return img;
	}
	
	public static Image Bell( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int bellBallSize = size / 5;
		if( bellBallSize < 1 )
		{
			bellBallSize = 1;
		}
				
		Image bellBall = basicPainter2D.arc( 0, 0, bellBallSize, bellBallSize, 180, 180, 1.5F, c, c, null );
		Image bellBottom = basicPainter2D.arc( 0, 0, size, 4 * bellBallSize, 0, 180, 1.5F, c, c, null );
		Image bellBody = basicPainter2D.rectangle( 3 * bellBallSize + 1, 3 * bellBallSize, 2, c, c );
		Image bellTop = basicPainter2D.arc( 0, 0, 3 * bellBallSize, 2 * bellBallSize, 0, 180, 1.5F, c, c, null );
		
		basicPainter2D.composeImage( img, bellBallSize, 0, bellTop );
		basicPainter2D.composeImage( img, bellBallSize, bellBallSize, bellBody );
		basicPainter2D.composeImage( img, 0, size - bellBottom.getHeight( null )/2 - bellBall.getHeight( null ) / 2, bellBottom );
		basicPainter2D.composeImage( img, ( size - bellBall.getWidth( null ) ) /2, size - bellBall.getHeight( null ), bellBall );
		
		
		return img;
	}
	
	public static Image Maraca( int size, Color c )
	{
		Image img = basicPainter2D.createEmptyCanva( size, size, null );
		
		int thicknessBorder = size / 32;

		if( thicknessBorder < 1 )
		{
			thicknessBorder = 1;
		}
		
		Color c2 = Color.WHITE;
		float[] hsb = new float[ 3 ];
		Color.RGBtoHSB( c.getRed(), c.getGreen(), c.getBlue(), hsb );
		
		if( hsb[ 1 ] < 0.25 )
		{
			c2 = Color.BLACK;
		}
		
		int w = size / 2 - thicknessBorder;
		int h = (size * 1 ) / 2;
		
		Image oval = basicPainter2D.oval( 0, 0, w, h, 2, c, c, null );
		
		int yL =  oval.getHeight( null ) / 2 - thicknessBorder /2;
		
		for( int pos = 0; pos < oval.getWidth( null ); pos += thicknessBorder )
		{
			basicPainter2D.circle( pos, yL, thicknessBorder, c2, oval );
		}
				
		int wStick = oval.getWidth( null ) / 3;
		if( wStick < 1 )
		{
			wStick = 1;
		}
		
		Image stick = basicPainter2D.oval( 0, 0, wStick, ( h * 4 ) / 3 , 2, c, c, null );
				
		
		basicPainter2D.composeImage( img, 0, 0, oval );
		basicPainter2D.composeImage( img, ( oval.getWidth( null ) - stick.getWidth( null ) ) / 2, size - stick.getHeight( null ), stick );
		
		basicPainter2D.composeImage( img, size - oval.getWidth( null ), 0, oval );
		basicPainter2D.composeImage( img, size - oval.getWidth( null ) + ( oval.getWidth( null ) - stick.getWidth( null ) ) / 2, size - stick.getHeight( null ), stick );
		
		return img;
	}
}

