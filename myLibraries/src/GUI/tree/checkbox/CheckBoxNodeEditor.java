/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski     
ISBN: 1-893115-78-X
Publisher: APress
*/
package GUI.tree.checkbox;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

/**
 * @author manuel
 *
 */
public class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 9031324778222524801L;

	private CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

	  private ChangeEvent changeEvent = null;

	  private JTree tree;
	  
	  private ButtonGroup gr = new ButtonGroup(); 

	  public CheckBoxNodeEditor(JTree tree) 
	  {
	    this.tree = tree;
	  }

	  public Object getCellEditorValue() 
	  {
	    JCheckBox checkbox = renderer.getLeafRenderer();
	    CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText()
	    											, checkbox.isSelected());
	    	    	
	    return checkBoxNode;
	  }

	  public boolean isCellEditable(EventObject event) 
	  {
	    boolean returnValue = false;
	    
	    if (event instanceof MouseEvent) 
	    {
	      MouseEvent mouseEvent = (MouseEvent) event;
	      
	      TreePath path = tree.getPathForLocation(mouseEvent.getX(),  mouseEvent.getY());
	      
	      if (path != null) 
	      {
	        Object node = path.getLastPathComponent();
	        if ((node != null) && (node instanceof DefaultMutableTreeNode)) 
	        {
	          DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
	          Object userObject = treeNode.getUserObject();
	          returnValue = ((treeNode.isLeaf()) && (userObject instanceof CheckBoxNode));
	        }
	      }
	    }
	    return returnValue;
	  }

	  public Component getTreeCellEditorComponent(JTree tree, Object value,
	      boolean selected, boolean expanded, boolean leaf, int row) 
	  {
	    Component editor = renderer.getTreeCellRendererComponent(tree, value,
	    															true, expanded, leaf, row, true);

	    // editor always selected / focused
	    ItemListener itemListener = new ItemListener() 
	    {
	      public void itemStateChanged(ItemEvent itemEvent) 
	      {
	        if (stopCellEditing()) 
	        {
	          fireEditingStopped();
	        }
	      }
	    };
	    
	    if (editor instanceof JCheckBox) 
	    {
	    	gr.add( (JCheckBox)editor );
	      ((JCheckBox) editor).addItemListener(itemListener);
	    }

	    return editor;
	  }

}





