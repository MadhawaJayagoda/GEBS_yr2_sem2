package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class generator {

	/*-------------------Alert Box for errors ----------------*/
	public static void getAlert(String title,  String message ) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/*-------------------------- Directory path change --------------------------------*/
	public static String generateFilePathsToJavaFormat( String path ) {
		
		String newPath = "";
		
		newPath = path.replace("/", "\\");
		newPath = newPath.replace("\\", "\\" + "\\");
		System.out.println(newPath);
		return newPath;
	
	}
	
	/*------------------------------- Generate month integer value -------------------------------*/
	public static int getMonthIntValue( String monthString ) {
			
			int month = 0;
	    	//switch to get the month values
	    	switch(monthString) {
	    	
	    	case "January"	: 	month = 1;
	    						break;
	    					
	    	case "February"	: 	month = 2;
	    						break;
	    					
	    	case "March"	:	month = 3;
	    						break;
	    						
	    	case "April"	: 	month = 4;
	    						break;
	    						
	    	case "May"		: 	month = 5;
	    						break;
	    						
	    	case "June"		: 	month = 6;
	    						break;
	    						
	    	case "July" 	: 	month = 7;
	    						break;
	    	
	    	case "August"	: 	month = 8;
	    						break;
	    						
	    	case "September":	month = 9;
	    						break;
	    						
	    	case "October" 	: 	month = 10;
	    						break;
	    						
	    	case "November"	: 	month = 11;
	    						break;
	    						
	    	case "December"	: 	month = 12;
	    						break;
	    	
	    	} //end switch
	    	
	    	return month;
		}
		
		public static String getMonthStringValue( int monthInt ) {
			
			String month = "";
	    	//switch to get the month values
	    	switch(monthInt) {
	    	
	    	case 1	: 	month = "January";
								break;
		
			case 2	: 	month = "February";
								break;
				
			case 3	:	month = "March";
								break;
					
			case 4	: 	month = "April";
								break;
					
			case 5	: 	month = "May";
								break;
					
			case 6		: 	month = "June";
								break;
					
			case 7 	: 	month = "July";
								break;
			
			case 8	: 	month = "August";
								break;
					
			case 9:	month = "September";
								break;
					
			case 10 	: 	month = "October";
								break;
					
			case 11	: 	month = "November";
								break;
					
			case 12	: 	month = "December";
								break;
	    	
	    	} //end switch
	    	
	    	return month;
		}
}
