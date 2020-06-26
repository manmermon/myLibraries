/* 
 * Copyright 2018-2020 by Manuel Merino Monge <manmermon@dte.us.es>
 *  
 *   This file is part of LSLRec.
 *
 *   LSLRec is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   LSLRec is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with LSLRec.  If not, see <http://www.gnu.org/licenses/>.
 *   
 */
package general;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class ConvertTo
{
	public enum DataType
	{
		int8, int16, int32, int64, float32, double64
	};
	
	public static double[][] NumberMatrix2doubleMatrix( Number[][] matrix )
	{
		double[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new double[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].doubleValue();
					}
				}
			}
			else
			{
				res = new double[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static Double[][] NumberMatrix2DoubleMatrix( Number[][] matrix )
	{
		Double[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new Double[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].doubleValue();
					}
				}
			}
			else
			{
				res = new Double[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static float[][] NumberMatrix2floatMatrix( Number[][] matrix )
	{
		float[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new float[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].floatValue();
					}
				}
			}
			else
			{
				res = new float[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static Float[][] NumberMatrix2FloatMatrix( Number[][] matrix )
	{
		Float[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new Float[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].floatValue();
					}
				}
			}
			else
			{
				res = new Float[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static long[][] NumberMatrix2longMatrix( Number[][] matrix )
	{
		long[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new long[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].longValue();
					}
				}
			}
			else
			{
				res = new long[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static Long[][] NumberMatrix2LongMatrix( Number[][] matrix )
	{
		Long[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new Long[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].longValue();
					}
				}
			}
			else
			{
				res = new Long[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static int[][] NumberMatrix2integerMatrix( Number[][] matrix )
	{
		int[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new int[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].intValue();
					}
				}
			}
			else
			{
				res = new int[ 0 ][ 0 ];
			}
		}
		
		return res;
	}

	public static Integer[][] NumberMatrix2IntegerMatrix( Number[][] matrix )
	{
		Integer[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new Integer[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].intValue();
					}
				}
			}
			else
			{
				res = new Integer[ 0 ][ 0 ];
			}
		}
		
		return res;
	}

	public static short[][] NumberMatrix2shortMatrix( Number[][] matrix )
	{
		short[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new short[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].shortValue();
					}
				}
			}
			else
			{
				res = new short[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static Short[][] NumberMatrix2ShortMatrix( Number[][] matrix )
	{
		Short[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new Short[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].shortValue();
					}
				}
			}
			else
			{
				res = new Short[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static byte[][] NumberMatrix2byteMatrix( Number[][] matrix )
	{
		byte[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new byte[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].byteValue();
					}
				}
			}
			else
			{
				res = new byte[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static Byte[][] NumberMatrix2ByteMatrix( Number[][] matrix )
	{
		Byte[][] res = null;
		
		if( matrix != null )
		{
			if( matrix.length > 0 )
			{
				res = new Byte[ matrix.length ][ matrix[ 0 ].length ];
				
				for( int r = 0; r < matrix.length; r++ )
				{
					for( int c = 0; c < matrix[ 0 ].length; c++ )
					{
						res[ r ][ c ] = matrix[ r ][ c ].byteValue();
					}
				}
			}
			else
			{
				res = new Byte[ 0 ][ 0 ];
			}
		}
		
		return res;
	}
	
	public static Tuple< String[][], String[] > StringArray2Matrix( String[] array, int numCols )
	{
		Tuple< String[][], String[] > res = new Tuple<String[][], String[]>( new String[0][0], new String[0] );
		if( array != null )
		{
			if( numCols == 0 )
			{
				numCols = -1;
			}
			
			int L = array.length;
			int rows = L / numCols;
			
			if( rows <= 0 )
			{
				res = new Tuple<String[][], String[]>( new String[0][0], array );
			}
			else
			{
				String[][] matrix = new String[ rows ][ numCols ];
				
				int iAr = 0;
				int r = 0;
				int c = 0;
				for( ; r < rows; iAr++ )
				{
					String val = array[ iAr ];
					
					matrix[ r ][ c ] = val;
					
					c++;
					if( c >= numCols )
					{
						c = 0;
						r++;
					}
				}
				
				String[] rest = new String[ L - iAr ];
				int i = 0;
				for( ; iAr < L; iAr++ )
				{
					rest[ i ] = array[ iAr ];
					i++;
				}
				
				res = new Tuple<String[][], String[]>( matrix, rest );
			}
		}
		
		return res;
	}

	public static Tuple< Number[][], Number[] > Array2Matrix( Number[] array, int numCols )
	{
		Tuple< Number[][], Number[] > res = new Tuple<Number[][], Number[]>( new Number[0][0], new Number[0] );
		if( array != null )
		{
			if( numCols == 0 )
			{
				numCols = -1;
			}
			
			int L = array.length;
			int rows = L / numCols;
			
			if( rows <= 0 )
			{
				res = new Tuple<Number[][], Number[]>( new Number[0][0], array );
			}
			else
			{
				Number[][] matrix = new Number[ rows ][ numCols ];
				
				int iAr = 0;
				int r = 0;
				int c = 0;
				for( ; r < rows; iAr++ )
				{
					Number val = array[ iAr ];
					
					matrix[ r ][ c ] = val;
					
					c++;
					if( c >= numCols )
					{
						c = 0;
						r++;
					}
				}
				
				Number[] rest = new Number[ L - iAr ];
				int i = 0;
				for( ; iAr < L; iAr++ )
				{
					rest[ i ] = array[ iAr ];
					i++;
				}
				
				res = new Tuple<Number[][], Number[]>( matrix, rest );
			}
		}
		
		return res;
	}
	
	public static char[] CharacterArray2charArray(Character[] d)
	{
		char[] out = new char[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static Character[] charArray2CharacterArray(char[] d)
	{
		Character[] out = new Character[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static int[] IntegerArray2intArray(Integer[] d)
	{
		int[] out = new int[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static Integer[] intArray2IntegerArray(int[] d)
	{
		Integer[] out = new Integer[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static byte[] ByterArray2byteArray(Byte[] d)
	{
		byte[] out = new byte[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static Byte[] byteArray2ByteArray(byte[] d)
	{
		Byte[] out = new Byte[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static short[] ShortArray2shortArray(Short[] d)
	{
		short[] out = new short[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static Short[] shortArray2ShortArray(short[] d)
	{
		Short[] out = new Short[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static long[] LongArray2longArray(Long[] d)
	{
		long[] out = new long[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static Long[] longArray2LongArray(long[] d)
	{
		Long[] out = new Long[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static float[] FloatArray2floatArray(Float[] d)
	{
		float[] out = new float[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static Float[] floatArray2FloatArray(float[] d)
	{
		Float[] out = new Float[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static double[] DoubleArray2doubleArray(Double[] d)
	{
		double[] out = new double[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static Double[] doubleArray2DoubleArray(double[] d)
	{
		Double[] out = new Double[d.length];

		for (int i = 0; i < d.length; i++)
		{
			out[i] = d[i];
		}

		return out;
	}

	public static byte[] DoubleArray2byteArray(Double[] d)
	{
		byte[] out = null;

		if (d != null)
		{
			double[] aux = DoubleArray2doubleArray(d);

			out = doubleArray2byteArray(aux);
		}

		return out;
	}

	public static byte[] doubleArray2byteArray(double[] d)
	{
		byte[] out = null;

		if (d != null)
		{
			int bytes = Double.BYTES;

			out = new byte[d.length * bytes];

			ByteBuffer data = ByteBuffer.wrap(out);
			DoubleBuffer dBuf = data.asDoubleBuffer();
			dBuf.put(d);
		}

		return out;
	}

	public static Double ByteArray2Double(byte[] bytes)
	{
		Double val = null;

		if (bytes != null && bytes.length == Double.BYTES)
		{
			val = ByteBuffer.wrap(bytes).getDouble();
		}

		return val;
	}

	public static long[] ByteArray2LongArray(byte[] bytes)
	{
		long[] out = null;

		if (bytes != null && (bytes.length % Long.BYTES) == 0)
		{
			LongBuffer buf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asLongBuffer();

			out = new long[buf.remaining()];
			buf.get(out);
		}

		return out;
	}

	public static int[] ByteArray2IntegerArray(byte[] bytes)
	{
		int[] out = null;

		if (bytes != null && (bytes.length % Integer.BYTES) == 0)
		{
			IntBuffer intBuf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asIntBuffer();

			out = new int[intBuf.remaining()];
			intBuf.get(out);
		}

		return out;
	}

	public static short[] ByteArray2ShortArray(byte[] bytes)
	{
		short[] out = null;

		if (bytes != null && (bytes.length % Short.BYTES) == 0)
		{
			ShortBuffer buf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asShortBuffer();

			out = new short[buf.remaining()];
			buf.get(out);
		}

		return out;
	}

	public static float[] ByteArray2FloatArray(byte[] bytes)
	{
		float[] out = null;

		if (bytes != null && (bytes.length % Float.BYTES) == 0)
		{
			FloatBuffer buf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asFloatBuffer();

			out = new float[buf.remaining()];
			buf.get(out);
		}

		return out;
	}

	public static double[] ByteArray2DoubleArray(byte[] bytes)
	{
		double[] out = null;

		if (bytes != null && (bytes.length % Double.BYTES) == 0)
		{
			DoubleBuffer buf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asDoubleBuffer();

			out = new double[buf.remaining()];
			buf.get(out);
		}

		return out;
	}

	public static Number[] ByteArrayTo(Byte[] bytes, DataType dataType)
	{
		return ByteArray2ArrayOf(ByterArray2byteArray(bytes), dataType);
	}

	public static Number[] ByteArray2ArrayOf(byte[] bytes, DataType toDataType)
	{
		Number[] out = null;

		switch (toDataType)
		{
		case int8:
		{
			out = byteArray2ByteArray(bytes);

			break;
		}
		case int16:
		{
			out = shortArray2ShortArray(ByteArray2ShortArray(bytes));

			break;
		}
		case int32:
		{
			out = intArray2IntegerArray(ByteArray2IntegerArray(bytes));

			break;
		}
		case int64:
		{
			out = longArray2LongArray(ByteArray2LongArray(bytes));

			break;
		}
		case float32:
		{
			out = floatArray2FloatArray(ByteArray2FloatArray(bytes));

			break;
		}
		case double64:
		{
			out = doubleArray2DoubleArray(ByteArray2DoubleArray(bytes));

			break;
		}
		default:
		{
			break;
		}
		}

		return out;
	}

	public static char[] ByteArray2charArray(byte[] bytes)
	{
		return (new String(bytes)).toCharArray();
	}

	public static double[] NumberArray2DoubleArray(Number[] values)
	{
		double[] vals = null;

		if (values != null && values.length > 0)
		{
			vals = new double[values.length];

			for (int i = 0; i < values.length; i++)
			{
				vals[i] = values[i].doubleValue();
			}
		}

		return vals;
	}

	public static float[] NumberArray2FloatArray(Number[] values)
	{
		float[] vals = null;

		if (values != null && values.length > 0)
		{
			vals = new float[values.length];

			for (int i = 0; i < values.length; i++)
			{
				vals[i] = values[i].floatValue();
			}
		}

		return vals;
	}

	public static byte[] NumberArray2ByteArray(Number[] values)
	{
		byte[] vals = null;

		if (values != null && values.length > 0)
		{
			vals = new byte[values.length];

			for (int i = 0; i < values.length; i++)
			{
				vals[i] = values[i].byteValue();
			}
		}

		return vals;
	}

	public static short[] NumberArray2ShortArray(Number[] values)
	{
		short[] vals = null;

		if (values != null && values.length > 0)
		{
			vals = new short[values.length];

			for (int i = 0; i < values.length; i++)
			{
				vals[i] = values[i].shortValue();
			}
		}

		return vals;
	}

	public static int[] NumberArray2IntegerArray(Number[] values)
	{
		int[] vals = null;

		if (values != null && values.length > 0)
		{
			vals = new int[values.length];

			for (int i = 0; i < values.length; i++)
			{
				vals[i] = values[i].intValue();
			}
		}

		return vals;
	}

	public static long[] NumberArray2LongArray(Number[] values)
	{
		long[] vals = null;

		if (values != null && values.length > 0)
		{
			vals = new long[values.length];

			for (int i = 0; i < values.length; i++)
			{
				vals[i] = values[i].longValue();
			}
		}

		return vals;
	}

	public static Number NumberTo(Number value, DataType type)
	{
		Number val = null;

		if (value != null)
		{
			switch (type)
			{
			case double64:
			{
				val = value.doubleValue();

				break;
			}
			case float32:
			{
				val = value.floatValue();

				break;
			}
			case int8:
			{
				val = value.byteValue();

				break;
			}
			case int16:
			{
				val = value.shortValue();

				break;
			}
			case int32:
			{
				val = value.intValue();

				break;
			}
			case int64:
			{
				val = value.longValue();

				break;
			}
			default:
			{
				break;
			}
			}
		}

		return val;
	}

	public static Number[] Interleaved(Number[] array, int chunkSize)
	{
		Number[] inter = null;

		if (array != null)
		{
			if (array.length <= chunkSize)
			{
				inter = array;
			} else
			{
				inter = new Number[array.length];

				int index = 0;
				for (int i = 0; i < chunkSize; i++)
				{
					for (int j = i; j < array.length; j = j + chunkSize)
					{
						inter[index] = array[j];

						index++;
					}
				}
			}
		}

		return inter;
	}

	public static Number[] Interleaved(Number[] array, int channels, int chunkSize)
	{
		Number[] inter = null;

		if (array != null)
		{
			if (array.length <= channels)
			{
				inter = array;
			} else
			{
				int from = 0;
				int step = chunkSize * channels;
				int index = 0;

				inter = new Number[array.length];

				while (from < array.length && index < array.length)
				{
					int to = from + step;

					if (to > array.length)
					{
						to = array.length;
					}

					Number[] aux = Interleaved(Arrays.copyOfRange(array, from, to), chunkSize);

					for (int i = 0; i < aux.length && index < inter.length; i++)
					{
						inter[index] = aux[i];

						index++;
					}

					from = to;
				}
			}
		}

		return inter;
	}

}