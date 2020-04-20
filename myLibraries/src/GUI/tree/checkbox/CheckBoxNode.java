/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski     
ISBN: 1-893115-78-X
Publisher: APress
*/
package GUI.tree.checkbox;

/**
 * @author manuel
 *
 */
public class CheckBoxNode 
{
	  private String text;

	  private boolean selected;

	  public CheckBoxNode(String text, boolean selected) 
	  {
	    this.text = text;
	    this.selected = selected;
	  }

	  public boolean isSelected() 
	  {
	    return selected;
	  }

	  public void setSelected( boolean newValue ) 
	  {
	    selected = newValue;
	  }

	  public String getText() 
	  {
	    return text;
	  }

	  public void setText(String newValue) 
	  {
	    text = newValue;
	  }

	  public String toString() 
	  {
	    return getClass().getName() + "[" + text + "/" + selected + "]";
	  }
	}