package hr.tel.fer.dz1.htmlregex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
boolean unos = true;
boolean prva =false;
BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in));
String line = reader.readLine();
String file = line;
if(Files.exists(Paths.get(line))){ 
	// /Users/valentinavalic/eclipse-workspace/zadaca/src/hr/tel/fer/dz1/htmlregex/prvi.html
while((line=reader.readLine())!=null && !line.equals("EXIT")) {
  int i=0;
  boolean find=false;
  
  
  if(line.equals("HELP")) {
	System.out.println("|-------------|");
	System.out.println("|OPCIJA     ID|");
	System.out.println("|ALL         1|");
	System.out.println("|ALL <tag>   2|");
	System.out.println("|ALL email   3|");
	System.out.println("|ALL IP      4|");
	System.out.println("|ALL date    5|");
	System.out.println("|ALL tel     6|");
	System.out.println("|ONLY <tag>  7|");
	System.out.println("|ONLY email  8|");
	System.out.println("|ONLY IP     9|");
	System.out.println("|ONLY date  10|");
	System.out.println("|-------------|");
}
  
  
    if(line.startsWith("REGEX ID")) {
    	String[] array = line.split(" ");
    	if(array.length==3) {
    		int number = Integer.parseInt(array[2]);
    		
    		if(number==1 || number==8) {
    			System.out.println("[a-z0-9]+.[a-z0-9]+@[a-z]+\\.[a-z]{2,3}");
    		}
    		if(number==2 || number==7) {
    			System.out.println("(?<=<%tag.*>)[^<%tag>](.+?)(?=</%tag>)");
    		}
    		if(number==4 || number==9) {
    			System.out.println("(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])");
    		}
    		if(number==5 || number==10) {
    			System.out.println("(\\d{1,2}/\\d{1,2}/\\d{4})");
    		}
    		if(number==6 || number==11) {
    			System.out.println("([0-9]+)[ -]([0-9]+)[ -]([0-9]+)[ -]([0-9]+)");
    		}
    	}
    }

  
  
  
  
  
  
    BufferedReader reader2 = new BufferedReader(new FileReader(file));
    String line2 = null;
    while(( line2=reader2.readLine())!=null) {
    	
    	
    	
    	if(line.equals("ALL")) {
    		System.out.println(line2);
    	}
    	
    	
    	
    	else if(line.equals("ALL email")){
    	    String regex = "[a-z0-9]+.[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
    	    Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line2);
    	       while (matcher.find()) {
    	          System.out.println(matcher.group());
    	      }
    	} 
    	
    	
    	
    	else if(line.equals("ALL IP")) {
          String regexIP = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
          Pattern pattern = Pattern.compile(regexIP);
          Matcher matcher = pattern.matcher(line2);
            while (matcher.find()) {
                System.out.println(matcher.group());
                  }
      }
    	
    	
    	else if(line.equals("ALL date")) {
    	   String regexDate="(\\d{1,2}/\\d{1,2}/\\d{4})";
    	   Pattern pattern = Pattern.compile(regexDate);
           Matcher matcher = pattern.matcher(line2);
    	     while (matcher.find()) {
    	       System.out.println(matcher.group());
    	     }
    	}
    	

    
    	else if(line.equals("ALL tel")) {
        	String regNum = "([0-9]+)[ -]([0-9]+)[ -]([0-9]+)[ -]([0-9]+)";
        	Pattern pattern = Pattern.compile(regNum);
        	Matcher matcher = pattern.matcher(line2);
        	while (matcher.find()) {
        	for (int j = 1;j <= matcher.groupCount(); j++) {
      	         System.out.print(matcher.group(j) + " ");
        		}
        	    System.out.println();
        	}
    	}
    	
    	
    	
    	else if(line.startsWith("ONLY email")) {
    		String[] epolje = line.split(" ");
    		if(epolje.length==3) {
    			int number = Integer.parseInt(epolje[2]);
    			 String regex = "[a-z0-9]+.[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
    	    	 Pattern pattern = Pattern.compile(regex);

    	    	    Matcher matcher = pattern.matcher(line2);
    	    	    while (matcher.find()&& i<number) {
    	    	        System.out.println(matcher.group());
    	    	        i++;
    	    	    }
    			
    		}
    	}
    	
    	
    	else if(line.startsWith("ONLY IP")) {
    	    String[] IPpolje = line.split(" ");
    		    if(IPpolje.length==3) {
    			    int broj = Integer.parseInt(IPpolje[2]);
                    String regex = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
    	    	    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(line2);
    	    	         while (matcher.find()&& i<broj) {
    	    	            System.out.println(matcher.group());
    	    	            i++;
    	    	    }
    		}
    }
    	
    	
    	
    	else if(line.startsWith("ONLY date")) {
    		 String[] dpolje = line.split(" ");
    		     if(dpolje.length==3) {
    			    int broj = Integer.parseInt(dpolje[2]);
                    String regex = "(\\d{1,2}/\\d{1,2}/\\d{4})";
    	    	    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(line2);
    	    	      while (matcher.find()&& i<broj) {
    	    	          System.out.println(matcher.group());
    	    	           i++;
    	    	     }
    			}
    	    }
    	
    	
    	
    	else if(line.startsWith("ONLY tel")) {
    		String[] telArray = line.split(" ");
    		  if(telArray.length==3) {
    			int number = Integer.parseInt(telArray[2]);
                String regex = "([0-9]+)[ -]([0-9]+)[ -]([0-9]+)[ -]([0-9]+)";
    	    	Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line2);
    	    	    while (matcher.find()&& i<number) {
    	            	for (int j = 1; j <= matcher.groupCount(); j++) {
    	          	       System.out.print(matcher.group(j) + " ");
    	            	}
    	            	System.out.println();
    	            	i++;
    	            }
    		  }
    	} 
    	
    	
    	
    	else if(line.startsWith("ALL <") && line.endsWith(">") ) {
    		String[] tagArray = line.split(" ");
    		  if(tagArray.length==2) {
    			String tag = tagArray[1].substring(0, tagArray[1].indexOf(">"));
    			String t1 =tag.substring(tag.indexOf("<")+1);
    			String regex = String.format("(?<=<%s.*>)[^<%s>](.+?)(?=</%s>)",t1,t1,t1);
     	    	Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line2);
                boolean sameLine = false;
                
                while(matcher.find()){
                	   sameLine=true;
               	       System.out.println(matcher.group());
                }
                
                
    			String reg = String.format("%s.*>",tag);
    			Pattern pattern2= Pattern.compile(reg);
                Matcher matcher2 = pattern2.matcher(line2);
           
                
                while(matcher2.find() && find==false && sameLine==false ) {
                	prva=true;
                	
            if(line2.substring(matcher2.group().indexOf(">")+1+matcher2.start(), line2.length()).length()>0) {
             System.out.println(line2.substring(matcher2.group().indexOf(">")+1+matcher2.start(), line2.length()));
                    	
            }
                	find=true;
                	matcher2 = matcher2.reset();
        }
                
                
                 if(find==true && sameLine==false) {
                   String t =tag.substring(tag.indexOf("<")+1);
                   regex = String.format("</%s>",t);
    	           pattern = Pattern.compile(regex);
                   matcher = pattern.matcher(line2);
    	    	    while (matcher.find()) {
    	    	    	    if(line2.length()>regex.length()) {
    	    	    	    	if(!(line2.substring(0,matcher.start())).isBlank()) {
    	    	    	    		System.out.println(line2.substring(0,matcher.start()));
    	    	    	    	}
    	    	    	    }
    	            		find=false;
    	            	}
    	    	    
    	    	    
    	    	    if(sameLine==false && find==true && prva==false) {
    	    	    	System.out.println(line2);
    	    	        } else {
    	    	    	   prva=false;
    	    	    }
                }
    	    }
    	}
    	
    	
    	
    	else if(line.startsWith("ONLY <")) {
    		String[] tagArray = line.split(" ");
    		if(tagArray.length==3) {
    			int number = Integer.parseInt(tagArray[2]);
    			String tag = tagArray[1].substring(0, tagArray[1].indexOf(">"));
    			String t1 =tag.substring(tag.indexOf("<")+1);
    			String regex = String.format("(?<=<%s.*>)[^<%s>](.+?)(?=</%s>)",t1,t1,t1);
     	    	Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line2);
                boolean sameLine = false;
                
                while(matcher.find() && number>i){
                	   sameLine=true;
               	       System.out.println(matcher.group());
               	       i++;
                }
                
                
    			String reg = String.format("%s.*>",tag);
    			Pattern pattern2= Pattern.compile(reg);
                Matcher matcher2 = pattern2.matcher(line2);
           
                
                while(matcher2.find() && find==false && sameLine==false && i<number) {
                	prva=true;
                	i++;
                      if(line2.substring(matcher2.group().indexOf(">")+1+matcher2.start(), line2.length()).length()>0) {
                	     System.out.println(line2.substring(matcher2.group().indexOf(">")+1+matcher2.start(), line2.length()));
                      }
                	find=true;
                	matcher2 = matcher2.reset();
                }
                
                 if(find==true && sameLine==false) {
                   String t =tag.substring(tag.indexOf("<")+1);
                   regex = String.format("</%s>",t);
    	           pattern = Pattern.compile(regex);
    	           matcher = pattern.matcher(line2);
    	           
    	    	      while (matcher.find()) {
    	    	    	    if(line2.length()>regex.length()) {
    	    	    	    	if(!line2.substring(0,matcher.start()).isBlank()) {
    	    	    	    		System.out.println(line2.substring(0,matcher.start()));
    	    	    	    	}
    	    	    	    }
    	            		find=false;
    	            	}
    	    	    
    	    	    
    	    	    if(sameLine==false && find==true && prva==false) {
    	    	    	System.out.println(line2);
    	    	    } else {
    	    	    	prva=false;
    	    	    }
                }
        	}
     	}
    }
 }
	       } else {
		      System.out.print("pogresna putanja");
	        }

        }
    }