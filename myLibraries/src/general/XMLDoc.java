package general;

import java.io.StringReader;
import java.io.StringWriter;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLDoc 
{
	public static String convertDocumentToString(Document doc) 
	{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		
		String output = null;
		
		try 
		{
			transformer = tf.newTransformer();
			
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString();
		} 
		catch (TransformerException e) 
		{
	            e.printStackTrace();
	    }
	    
		return output;
	 }
	
	public static Document convertStringToDocument(String xmlStr) 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		Document doc = null; 
		
		try
		{
			builder = factory.newDocumentBuilder();
			doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );
	    } 
		catch (Exception e) 
		{  
	            e.printStackTrace();  
	    } 
	    
		return doc;
	}
}
