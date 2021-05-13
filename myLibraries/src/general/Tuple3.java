/**
 * 
 */
package general;

/**
 * @author Manuel Merino Monge
 *
 */
public class Tuple3< X, Y, Z > extends Tuple< X, Y > 
{
	public final Z t3;

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public Tuple3(X x, Y y, Z z) 
	{
		super(x, y);
		
		this.t3 = z;
	}
	
	public static boolean isCorrectTypes( Tuple3 t, Class xClass, Class yClass, Class zClass )
	{	
		boolean correct = false;
		
		if( t != null && xClass != null && yClass != null && zClass != null )
		{
			correct = ( xClass.isInstance( t.t1 ) ) && ( yClass.isInstance( t.t2 ) && ( zClass.isInstance( t.t3 ) ) );
		}
		
		return correct;
	}
	
	public static boolean isCorrectTypes( Tuple3 obj, Class cl )
	{	
		return isCorrectTypes( obj, cl, cl, cl );
	}
	
	@Override
	public boolean equals( Object obj ) 
	{
		boolean eq = ( obj != null ) && ( obj instanceof Tuple3 );
		
		if( eq )
		{
			Tuple3 tin = (Tuple3)obj;
			
			eq = tin.t1.equals( super.t1 ) && tin.t2.equals( super.t2 ) && tin.t3.equals( this.t3 );
		}
				
		return eq;		
	}
	
	@Override
	public String toString() 
	{
		String out = "<";
		if( t1 != null )
		{
			out += t1;
		}
		
		out += ",";
		
		if( t2 != null )
		{
			out += " " + t2; 
		}
		
		out += ",";
		
		if( t3 != null )
		{
			out += " " + t3; 
		}
		
		out += ">";
		
		return out;
	}  

}
