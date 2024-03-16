package iljLab2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXQuery {

	public static void main(String[] args) throws IOException, XMLStreamException {
		// TODO Auto-generated method stub
		boolean bFirstName = false;
	      boolean bLastName = false;
	      boolean bNickName = false;
	      boolean bMarks = false;
	      boolean isRequestRollNo = false;
	      boolean find=false;
	      BufferedReader reader = new BufferedReader(
	    	        new InputStreamReader(System.in));
	    	String line = reader.readLine();
	 String line2;
	    	while((line2=reader.readLine())!=null && !line2.equals("EXIT")) {
	    		 if(line2.startsWith("ELEMENT")) {
	    				String[] array = line2.split(" ");
	    		    	if(array.length==3) {
	    		    		StringWriter sw = new StringWriter();
	    		    	    XMLOutputFactory of = XMLOutputFactory.newInstance(); 
	    		    	    XMLEventWriter xw = null;
	    		    	    XMLInputFactory f = XMLInputFactory.newInstance();
	    		    	    XMLEventReader xr = f.createXMLEventReader(new FileInputStream(line));
	    		   if(array[2].equals("*")) {
	    		    	    while (xr.hasNext()) {
	    		    	        XMLEvent e = xr.nextEvent();
	    		    	        if (e.isStartElement()
	    		    	                && ((StartElement) e).getName().getLocalPart().equals(array[1].substring(1, array[1].length()-1))) {
	    		    	            xw = of.createXMLEventWriter(sw);
	    		    	            find=true;
	    		    	        } else if (e.isEndElement()
	    		    	                && ((EndElement) e).getName().getLocalPart().equals(array[1].substring(1, array[1].length()-1))) {
	    		    	        	 xw.close();
	    		    		    	    System.out.println(sw);
	    		    		    	    sw = new StringWriter();
	    		    		    	    find=false;
	    		    	        } else if (xw != null && find) {
	    		    	            xw.add(e);
	    		    	        }
	    		    	    }
	    		    	    
	    		    	} else {
	    		    		int cnt = Integer.valueOf(array[2]);
	    		    		 while (xr.hasNext()&&cnt!=0) {
		    		    	        XMLEvent e = xr.nextEvent();
		    		    	        if (e.isStartElement()
		    		    	                && ((StartElement) e).getName().getLocalPart().equals(array[1].substring(1, array[1].length()-1))) {
		    		    	            xw = of.createXMLEventWriter(sw);
		    		    	            find=true;
		    		    	        } else if (e.isEndElement()
		    		    	                && ((EndElement) e).getName().getLocalPart().equals(array[1].substring(1, array[1].length()-1))) {
		    		    	        	 xw.close();
		    		    		    	    System.out.println(sw);
		    		    		    	    sw = new StringWriter();
		    		    		    	    cnt--;
		    		    		    	    find=false;
		    		    	        } else if (xw != null && find) {
		    		    	            xw.add(e);
		    		    	        }
		    		    	    }
		    		    	    
	    		    	}
	    		    	}
	    		
	    		 }
	    		 
	    		 
	    		 
	    		 if(line2.startsWith("ATTRIBUTE")) {
	    			 String array[]=line2.split(" ");
	    			 if(array.length==3) {
	    				 XMLInputFactory xmlif = XMLInputFactory.newFactory();
	    			        Reader reader2 = new FileReader(line);
	    			        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(reader2);
	    			        if(array[2].equals("*")) {
	    			        while (xmlsr.hasNext()) {
	    			            switch (xmlsr.next()) {
	    			                case XMLStreamReader.START_ELEMENT:
	    			                  
	    			                    int numberOfAttributes = xmlsr.getAttributeCount();
	    			                    if (numberOfAttributes > 0) {
	    			                        for (int i = 0; i < numberOfAttributes; i++) {
	    			                            String attributeName = xmlsr.getAttributeLocalName(i);
	    			                            String attributeValue = xmlsr.getAttributeValue(i);
	    			                            if(attributeName.equals(array[1])) {
	    			                            System.out.println(attributeValue);
	    			                            }
	    			                        }
	    			                    }

	    			                    break;
	    			                case XMLStreamReader.END_ELEMENT:
	    			                   
	    			                    break;
	    			            }

	    			        }
	    			        }else {
	    			        	int cnt = Integer.valueOf(array[2]);
	    			        	while (xmlsr.hasNext()&& cnt!=0) {
		    			            switch (xmlsr.next()) {
		    			                case XMLStreamReader.START_ELEMENT:
		    			                  
		    			                    int numberOfAttributes = xmlsr.getAttributeCount();
		    			                    if (numberOfAttributes > 0) {
		    			                        for (int i = 0; i < numberOfAttributes; i++) {
		    			                            String attributeName = xmlsr.getAttributeLocalName(i);
		    			                            String attributeValue = xmlsr.getAttributeValue(i);
		    			                            if(attributeName.equals(array[1])) {
		    			                            System.out.println(attributeValue);
		    			                            cnt--;
		    			                            }
		    			                        }
		    			                    }

		    			                    break;
		    			                case XMLStreamReader.END_ELEMENT:
		    			                   
		    			                    break;
		    			            }

		    			        }
	    			        	
	    			        }
	    			        
	    			 }
	    		 } if (line2.startsWith("TEXT")) {
	    			 String array[]=line2.split(" ");
	    			 if(array.length==3) {
	    				 XMLInputFactory xmlif = XMLInputFactory.newFactory();
	    			        Reader reader2 = new FileReader(line);
	    			        XMLEventReader eventReader =
	    			                xmlif.createXMLEventReader(new FileReader(line));
	    			        boolean find2=false;
	    			        boolean a=false;
	    			        if(array[2].equals("*")) {
	    			        while (eventReader.hasNext()) {
	    			        	XMLEvent event = eventReader.nextEvent();
	    			        	
	    			            switch (event.getEventType()) {
	    			            case XMLStreamConstants.START_ELEMENT:
	    			                  StartElement startElement = event.asStartElement();
	    			                  String qName = startElement.getName().getLocalPart();
	    			                  
	    			                	 if (qName.equalsIgnoreCase(array[1].substring(1, array[1].length()-1))) {
	    			                		 find2=true;
	    			                	 }else {
	    			                		 if(find2==true) {
	    			                			 a=true;
	    			                		 }
	    			                	 }
	    			                	   break;
	    			            case XMLStreamConstants.CHARACTERS:
	    			                  Characters characters = event.asCharacters();
	    			               
	    			                	if(find2&&!a) {
	    			                		if(!(characters.getData().isBlank()&&!characters.getData().isEmpty())) {
	    			                		System.out.println(characters.getData().trim());
	    			                		}
	    			                	}
	    			                	break;
	    			            case XMLStreamConstants.END_ELEMENT:
	    			            	 EndElement endElement = event.asEndElement();
	    			            	 String EName = endElement.getName().getLocalPart();
	    			            	 if (EName.equalsIgnoreCase(array[1].substring(1, array[1].length()-1))) {
	    			            		 find2=false;
    			                		 break;
    			                	 }else {
    			                		 a=false;
    			                	 }
    			                		
    			                	 
	    			                   
	    			            }

	    			        }
	    			        }else {
	    			        	int cnt = Integer.valueOf(array[2]);
	    			        	 while (eventReader.hasNext()&&cnt!=0) {
	 	    			        	XMLEvent event = eventReader.nextEvent();
	 	    			            switch (event.getEventType()) {
	 	    			            case XMLStreamConstants.START_ELEMENT:
	 	    			                  StartElement startElement = event.asStartElement();
	 	    			                  String qName = startElement.getName().getLocalPart();
	 	    			                  
	 	    			                	 if (qName.equalsIgnoreCase(array[1].substring(1, array[1].length()-1))) {
	 	    			                		 find2=true;
	 	    			                	 }else {
		    			                		 if(find2==true) {
		    			                			 a=true;
		    			                		 }
		    			                	 }
	 	    			                	   break;
	 	    			            case XMLStreamConstants.CHARACTERS:
	 	    			                  Characters characters = event.asCharacters();
	 	    			               
	 	    			                 if(find2&&!a) {
		    			                		if(!(characters.getData().isBlank()&&!characters.getData().isEmpty())) {
		    			                		System.out.println(characters.getData().trim());
		    			                		
		    			                		}
		    			                	}
	 	    			                	break;
	 	    			            case XMLStreamConstants.END_ELEMENT:
	 	    			            	 EndElement endElement = event.asEndElement();
		    			            	 String EName = endElement.getName().getLocalPart();
		    			            	 if (EName.equalsIgnoreCase(array[1].substring(1, array[1].length()-1))) {
		    			            		 find2=false;
		    			            		 cnt--;
	    			                		 break;
	    			                	 }else {
	    			                		 a=false;
	    			                	 }
	 	    			            }

	 	    			        }
	    			        }
	    			 }
	    		 }
	    
	}
}
}
