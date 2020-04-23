/**
 * 
 */
package GUI.tabbedpanel;

import java.awt.Color;
import java.awt.Font;
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
public class CustomTabbedPaneUI extends MetalTabbedPaneUI
{
   private Rectangle xRect;
  
   protected void installListeners() 
   {
      super.installListeners();
      super.tabPane.addMouseListener( new MyMouseHandler() );
   }
  
   protected void paintTab( Graphics g, int tabPlacement,
                           Rectangle[] rects, int tabIndex,
                           Rectangle iconRect
                           , Rectangle textRect) 
   {
      super.paintTab( g, tabPlacement, rects, tabIndex, iconRect, textRect);
  
      g.setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 11 ) );
      FontMetrics fm = g.getFontMetrics(g.getFont());
      
      int charWidth = fm.charWidth('x');
      
      Image img = basicPainter2D.createEmptyCanva( charWidth, fm.getHeight(), null );
      basicPainter2D.text( 0, 0, "x", fm, Color.RED, Color.RED, img );
      
      g.drawImage( img, textRect.x + textRect.width, textRect.y, null );
      
      xRect = new Rectangle( textRect.x + textRect.width,
                 			textRect.y
                 			, img.getWidth( null )
                 			, img.getHeight( null ) );
      
    }
  
    public class MyMouseHandler extends MouseAdapter 
    {
        public void mousePressed( MouseEvent e) 
        {
            if ( xRect.contains( e.getPoint() ) ) 
            {
               JTabbedPane tabPane = (JTabbedPane)e.getSource();
               
               int tabIndex = tabForCoordinate( tabPane, e.getX(), e.getY());
               
               tabPane.remove(tabIndex);
            }
        }
    }
}
