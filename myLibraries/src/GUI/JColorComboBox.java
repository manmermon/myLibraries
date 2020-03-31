package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.*;

/**
 *
 * @author Manuel Merino,  based on sharath 
 * From: http://java-gui.blogspot.com/2011/07/jcolorcombobox-jcombobox-as-color.html
 */

public class JColorComboBox extends JComboBox 
{
	private LinkedHashMap<String, Color> colors;

	public JColorComboBox() 
	{
		this( null );
	}
	
	public JColorComboBox( LinkedHashMap< String, Color> cs )
	{	
		super();
		
		this.colors = new LinkedHashMap<String, Color>();
		
		if( cs != null || !cs.isEmpty() )
		{
			this.colors.clear();
			this.colors.putAll( cs );
		}
		else
		{
			this.colors.putAll( getColorTable() );
		}
		
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		
		
		Set< String > colorNames = this.colors.keySet();
		
		for( String temp : colorNames  )
		{	
			model.addElement( temp );
		}
		
		setModel( model );
		setRenderer( new ColorRenderer() );
		
		this.setOpaque( true );
		this.setSelectedIndex( 0 );
	}
	
	@Override
	public void setSelectedItem( Object anObject ) 
	{
		super.setSelectedItem( anObject );

		setBackground( (Color)colors.get( anObject ) );
		setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 12 ) );
		
		if( anObject.toString().equals("BLACK") 
				|| anObject.toString().equals("DARK_GRAY"))
		{
			setForeground(Color.white);
		}
	}
	
	public Color getSelectedColor()
	{
		return this.getBackground();
	} 
	
	public static LinkedHashMap< String, Color> getColorTable()
	{
		LinkedHashMap< String, Color >colors = new LinkedHashMap<String, Color>();

		colors.put("WHITE", Color.WHITE);
		colors.put("BLUE", Color.BLUE);
		colors.put("GREEN", Color.GREEN);
		colors.put("YELLOW", Color.YELLOW);
		colors.put("ORANGE", Color.ORANGE);
		colors.put("CYAN", Color.CYAN);
		colors.put("DARK_GRAY", Color.DARK_GRAY);
		colors.put("GRAY", Color.GRAY);
		colors.put("RED", Color.RED);
		colors.put("PINK",Color.PINK);
		colors.put("MAGENTA", Color.MAGENTA);
		colors.put("BLACK", Color.BLACK);

		return colors;
	}
	
	public static LinkedHashMap< Color, String> getColorNameTable()
	{
		LinkedHashMap< Color, String >colors = new LinkedHashMap< Color, String >();

		colors.put( Color.WHITE, "WHITE" );
		colors.put( Color.BLUE, "BLUE");
		colors.put( Color.GREEN, "GREEN");
		colors.put( Color.YELLOW, "YELLOW");
		colors.put( Color.ORANGE, "ORANGE");
		colors.put( Color.CYAN, "CYAN");
		colors.put( Color.DARK_GRAY, "DARK_GRAY");
		colors.put( Color.GRAY, "GRAY");
		colors.put( Color.RED, "RED");
		colors.put( Color.PINK, "PINK");
		colors.put( Color.MAGENTA,"MAGENTA");
		colors.put( Color.BLACK, "BLACK" );

		return colors;
	}

	class ColorRenderer extends JLabel implements javax.swing.ListCellRenderer 
	{
		public ColorRenderer() 
		{
			this.setOpaque(true);
		}
		
		public Component getListCellRendererComponent(JList list, Object key, int index,
				boolean isSelected, boolean cellHasFocus) 
		{

			Color color = colors.get(key);
			String name = key.toString();

			list.setSelectionBackground(null);
			list.setSelectionForeground(null);

			if(isSelected){
				setBorder(BorderFactory.createEtchedBorder());
			} else {
				setBorder(null);
			}
			setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
			setBackground(color);
			setText(name);
			setForeground(Color.black);
			if(name.equals("BLACK") || name.equals("DARK_GRAY"))
			{
				setForeground( Color.white );
			}

			return this;
		}
	}
}