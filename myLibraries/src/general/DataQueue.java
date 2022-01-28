/**
 * 
 */
package general;

/**
 * @author Manuel Merino Monge
 *
 */
public class DataQueue< T > 
{
	private int capacity;
	
	private int writePointer = 0;
	private int readPointer = -1;
	private int len = 0;
	
	private Object[] buffer;
	
	private Object lock = new Object();
	
	public DataQueue( int capacity ) 
	{		
		if( capacity <= 0 )
		{
			throw new IllegalArgumentException( "Capacity must be > 0.");
		}
		
		this.capacity = capacity;
		this.buffer = new Object[ capacity ];
	}
	
	public int getCapacity()
	{
		return this.capacity;
	}
		
	public void put( T data )
	{			
		synchronized ( this.lock )
		{
			if( this.writePointer >= this.capacity )
			{
				this.writePointer = 0;
			}		
			this.buffer[ this.writePointer++ ] = data;
			
			if( this.readPointer < 0 )
			{
				if( this.writePointer >= this.capacity )
				{
					this.readPointer = 0;
				}
			}
			else
			{
				if( this.writePointer >= this.readPointer && this.isFull() )
				{
					this.readPointer = this.writePointer;
					if( this.readPointer >= this.capacity )
					{
						this.readPointer = 0;
					}
				}
			}
			
			this.len = this.capacity > this.len ? this.len + 1 : this.len;
		}
	}
	
	/**
	 * 
	 * @return get the oldest element in the queue. This element is not remove. 
	 */
	public T get()
	{
		return this.getAux( false );
	}
	
	/**
	 * 
	 * @return get and remove the oldest element in the queue.
	 */
	public T pull()
	{
		return this.getAux( true );
	}
	
	private T getAux( boolean moveReadPointer )
	{
		T val = null;
		
		synchronized ( this.lock )
		{
			if( this.len > 0 )
			{
				if( this.readPointer >= this.capacity )
				{
					this.readPointer = 0;
				}
				
				if( this.readPointer >= this.writePointer)
				{
					val = ( T )this.buffer[ this.readPointer++ ];
				}
				else
				{
					this.readPointer = this.readPointer < 0 ? 0 : this.readPointer;
					
					val = ( T )this.buffer[ this.readPointer++ ];
				}
				
				this.len--;
			}
		}
		
		return val;
	}
	
	/**
	 * 
	 * @return get the all elements in the queue. The elements are not remove.
	 */
	public T[] getAll()
	{
		Object[] D = new Object[ this.len ];
		
		synchronized ( this.lock) 
		{			
			int aux = this.len;
			int rP = this.readPointer;
			
			D = this.pullAll();
			
			this.len = aux;
			this.readPointer = rP;
		}
		
		return (T[])D;
	}
	
	/**
	 * 
	 * @return get and remove all elements in the queue.
	 */
	public T[] pullAll()
	{
		Object[] D = new Object[ this.len ];
		
		synchronized ( this.lock) 
		{
			if( this.len > 0 )
			{
				T data = null;
				int i = 0;
				do
				{
					data = this.pull();
					D[ i ] = data;
					i++;
				}
				while( i < D.length );
			}
		}
		
		return (T[])D;
	}
	
	public boolean isFull()
	{	
		return this.len == this.capacity;
	}
	
	public boolean isEmpty()
	{
		return this.len <= 0;
	}
	
	public int getNumberQueuedData()
	{
		return this.len;
	}
	
	public String toString()
	{
		String res = "[";
		
		synchronized ( this.lock )
		{
			int aux = this.len;
			int rP = this.readPointer;
			
			while( this.len > 0 )
			{
				res += this.pull();
				if( this.len > 0 )
				{
					res += ",";
				}
			}
			
			this.len = aux;
			this.readPointer = rP;
		}
		
		
		return res + "]";
	}
	
}
