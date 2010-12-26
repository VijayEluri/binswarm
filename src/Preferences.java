import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Preferences
{
	public static UUID uuid;
	
	public Preferences()
	{
		//Constructor
		File prefs = new File("preferences.xml");
		if (!prefs.exists())
		{
			//create new file with defaults
			try
			{
				FileWriter fstream = new FileWriter("preferences.xml");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write("<?xml version='1.0' encoding='UTF-8'?>\n");
				out.write("<Settings>\n");
					out.write("<Preferences>\n");
						out.write("<UUID>\n");
							//generate UUID
							uuid = UUID.randomUUID();
						out.write(uuid.toString() + "\n");
						out.write("</UUID>\n");
					out.write("</Preferences>\n");
				out.write("</Settings>\n");
				
				out.close();
			}
			catch (Exception e) {
				  System.err.println("Error: " + e.getMessage());
			}
		}
		
		loadXMLFile();
	}
	
	private void loadXMLFile()
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse("preferences.xml");
			
			//grab the elements in preferences
			Element docEle = dom.getDocumentElement();
			System.out.println("here");
			
			NodeList nl = docEle.getElementsByTagName("Preferences");
			for (int i = 0; i < nl.getLength(); i++)
			{
				//get the element
				Element el = (Element)nl.item(i);
				System.out.println("element: " + el.getNodeValue());
			}
			
		}
		catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void saveXMLFile()
	{
		
	}
}
