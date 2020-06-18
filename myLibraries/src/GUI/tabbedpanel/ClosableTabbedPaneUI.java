/**
 * 
 */
package GUI.tabbedpanel;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

import image.basicPainter2D;

/**
 * @author manuel merino
 * 
 * based on
 * http://esus.com/adding-a-close-x-button-to-tabs-in-a-jtabbedpane/
 */
public class ClosableTabbedPaneUI extends MetalTabbedPaneUI
{
   private Rectangle xRect;
  
   protected void installListeners() 
   {
      super.installListeners();
      MouseHandler handler = new MouseHandler();
      
      super.tabPane.addMouseListener( handler );
      super.tabPane.addMouseMotionListener(handler );
   }
  
   protected void paintTab( Graphics g, int tabPlacement,
                           Rectangle[] rects, int tabIndex,
                           Rectangle iconRect
                           , Rectangle textRect) 
   {
	  FontMetrics fm = g.getFontMetrics(g.getFont());
      
      Image img = basicPainter2D.text( 0, 0, "X", fm, Color.RED, Color.RED, null );
            
      super.paintTab( g, tabPlacement, rects, tabIndex, iconRect, textRect);
      
      int y = (int)( textRect.getMaxY() - img.getHeight( null ) );
      g.drawImage( img, textRect.x + textRect.width, y, null );
      
      xRect = new Rectangle( textRect.x + textRect.width
                 			, textRect.y
                 			, img.getWidth( null )
                 			, img.getHeight( null ) );
      
    }
  
    public class MouseHandler extends MouseAdapter 
    {
        public void mousePressed( MouseEvent e) 
        {
            if ( xRect.contains( e.getPoint() ) ) 
            {
               JTabbedPane tabPane = (JTabbedPane)e.getSource();
               
               int tabIndex = tabForCoordinate( tabPane, e.getX(), e.getY());
               
               if( tabIndex >= 0 )
               {
            	   tabPane.remove( tabIndex );
               }
            }
        }
        
        /*(non-Javadoc)
         * @see @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
         */
        /*
        @Override
        public void mouseMoved(MouseEvent e)        
        {
        	if( xRect.contains( e.getPoint() ) )
        	{
	        	JTabbedPane tab = (JTabbedPane)e.getSource();
	        	
	        	Graphics g = tab.getGraphics();
	        	FontMetrics fm = g.getFontMetrics(g.getFont());
	            
	            Image img = basicPainter2D.outlineText( 0, 0, "X", fm, Color.BLACK, null );
	            
	            g.drawImage( img, xRect.x, xRect.y, null );
        	}
        }
        //*/
    }
}
