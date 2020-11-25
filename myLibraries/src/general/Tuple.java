package general;

public class Tuple<X, Y> 
{
	public final X t1;
	public final Y t2;
	
	public Tuple( X x, Y y)
	{
		this.t1 = x;
		this.t2 = y;
	}
	
	public static boolean isCorrectTypes( Object obj, Class xClass, Class yClass )
	{
		boolean correct = false;
		
		if( obj != null && xClass != null && yClass != null )
		{
			if( obj instanceof Tuple )
			{
				Tuple t = (Tuple)obj;
				correct = ( xClass.isInstance( t.t1 ) ) && ( yClass.isInstance( t.t2 ) );
			}
		}
		
		return correct;
	}
	
	public static boolean isCorrectTypes( Object obj, Class cl )
	{	
		return isCorrectTypes( obj, cl, cl);
	}
	
	@Override
	public boolean equals( Object obj ) 
	{
		boolean eq = ( obj != null ) && ( obj instanceof Tuple );
		
		if( eq )
		{
			Tuple tin = (Tuple)obj;
			
			eq = tin.t1.equals( this.t1 ) && tin.t2.equals( this.t2 );
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
		
		out += ">";
		
		return out;
	}  
}
