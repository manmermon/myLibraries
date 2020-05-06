/**
 * 
 */
package general;

/**
 * @author Manuel Merino Monge
 *
 */
public class Pair< X, Y>
{
	private X x1;
	private Y x2;
	
	/**
	 * 
	 */
	public Pair( )
	{
	}
	
	/**
	 * 
	 */
	public Pair( X x1, Y x2)
	{
		this.x1 = x1;
		this.x2 = x2;
	}
	
	/**
	 * @return the x1
	 */
	public X getX1()
	{
		return this.x1;
	}
	
	/**
	 * @return the x2
	 */
	public Y getX2()
	{
		return this.x2;
	}
	
	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(X x1)
	{
		this.x1 = x1;
	}
	
	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(Y x2)
	{
		this.x2 = x2;
	}
		
	@Override
	public boolean equals( Object obj ) 
	{
		boolean eq = (obj != null ) && ( obj instanceof Pair );
		
		if( eq )
		{
			Pair tin = (Pair)obj;
			
			eq = tin.x1.equals( this.x1 ) && tin.x2.equals( this.x2 );
		}
				
		return eq;		
	}
	
	@Override
	public String toString() 
	{
		String out = "<";
		
		out += this.x1;
		
		out += ",";
		
		out += " " + this.x2;
		
		out += ">";
		
		return out;
	}  
}
