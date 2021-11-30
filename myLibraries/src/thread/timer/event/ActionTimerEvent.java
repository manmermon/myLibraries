package thread.timer.event;

import java.util.EventObject;

public class ActionTimerEvent extends EventObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public enum Type { DESTROY, START, RESTART, STOP };
	
	private Type typeEvent;
	
	public ActionTimerEvent( Object source, Type type ) 
	{
		super( source );
		
		this.typeEvent = type;
	}
	
	public Type getType()
	{
		return this.typeEvent;
	}
}
