/**
 * 
 */
package thread.timer;

import javax.swing.event.EventListenerList;

import thread.stoppableThread.AbstractStoppableThread;
import thread.timer.event.ActionTimerEvent;
import thread.timer.event.ActionTimerEvent.Type;
import thread.timer.event.IActionTimerEventListener;

/**
 * @author Manuel Merino Monge
 *
 */
public class Timer extends AbstractStoppableThread 
{
	private EventListenerList listenerList;

	private long time = 1000L;
	private ActionTimerThread action = null;
	
	private Object lock = new Object();
	
	private boolean pause = false;
	
	private boolean loop = true;
	
	/**
	 * 
	 */	
	public Timer( long time, boolean rep, ActionTimerThread act ) 
	{
		super();
		
		this.time = time;
		this.action = act;
		this.loop = rep;
		
		this.listenerList = new EventListenerList();
	}
	
	@Override
	protected void preStopThread(int friendliness) throws Exception 
	{
	}

	@Override
	protected void postStopThread(int friendliness) throws Exception 
	{	
	}
	
	@Override
	protected void preStart() throws Exception 
	{
		if( this.action != null )
		{
			this.action.startThread();
		}
	}
	
	@Override
	protected void startUp() throws Exception 
	{
		super.startUp();
		
		this.fireActionTimerEvent( ActionTimerEvent.Type.START );
	}
	
	@Override
	protected void runInLoop() throws Exception 
	{
		synchronized( this )
		{
			this.wait( this.time );
		}
		
		if( this.action != null )
		{
			this.action.execute();
		}
	}
	
	@Override
	protected void targetDone() throws Exception 
	{
		super.targetDone();
		
		if( !this.loop )
		{
			super.stopThread = true;
		}
	}
	
	@Override
	protected void runExceptionManager(Throwable e) 
	{
		if( !(e instanceof InterruptedException ) )
		{
			super.runExceptionManager( e );
		}
	}
	
	@Override
	protected void finallyManager() 
	{
		if( this.pause )
		{
			synchronized( this )
			{
				try 
				{
					super.wait();
				}
				catch (InterruptedException e) 
				{
				}
			}
		}
		else
		{
			super.finallyManager();
		}
	}
	
	public void restartTimer()
	{
		synchronized( this.lock )
		{
			System.out.println("ActionTimer.restartTimer() 1 ");
			this.pause = false;
			
			System.out.println("ActionTimer.restartTimer() 2 ");
			if( this.action != null )
			{
				this.action.interrupt();
			}
			super.interrupt();
			
			System.out.println("ActionTimer.restartTimer() 3 ");
			this.fireActionTimerEvent( Type.RESTART );
		}		
	}
	
	public void stopTimer()
	{
		synchronized( this.lock )
		{
			this.pause = true;
			
			super.interrupt();
			
			this.fireActionTimerEvent( Type.STOP );
		}
	}
	
	@Override
	protected void cleanUp() throws Exception 
	{	
		super.cleanUp();
		
		this.fireActionTimerEvent( Type.DESTROY );
	}
	
	public void addActionTimerEventListener( IActionTimerEventListener listener) 
	{
		this.listenerList.add( IActionTimerEventListener.class, listener );
	}
	
	public void removeActionTimerEventListener( IActionTimerEventListener listener) 
	{
		this.listenerList.remove( IActionTimerEventListener.class, listener );
	}
	
	private synchronized void fireActionTimerEvent( ActionTimerEvent.Type typeEvent )
	{
		ActionTimerEvent event = new ActionTimerEvent( this, typeEvent );

		IActionTimerEventListener[] listeners = this.listenerList.getListeners( IActionTimerEventListener.class );

		for (int i = 0; i < listeners.length; i++ ) 
		{
			listeners[ i ].StateChanged( event );
		}
	}
}
