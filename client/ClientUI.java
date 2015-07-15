package client;

//import java.io.IOException;
//import java.rmi.ConnectException;
//import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import broker.Client;
import constants.Constants;

public class ClientUI {
	private ClientHOOP clientHOPP;
	//private static final String HOST = "localhost";
	public static void main(String[] args){
	
		if (args.length < 1) {
		      System.err.println("Usage: Client args");
		      System.exit(1);
		    }

		 ClientUI ui = new ClientUI();
		    ui.displayMenu();

	 }

		  /**
		   * Constructor for objects of class HotelClientUI
		   */
			public ClientUI() {
				clientHOPP = new ClientHOOP();
			}

		  public void displayMenu() {
		    Scanner console = new Scanner(System.in);
		    String line = null;
		    do {

		      System.out.print("Welcome to Hotel Reservation Client\r\n");
			  System.out.println("Enter Requeset"+Constants.CR_LF +"0:quit");
			  System.out.print("1:listcity"+Constants.CR_LF);
			  System.out.print("2:listhotel"+Constants.CR_LF);
			  System.out.print("3:listcityhotel"+Constants.CR_LF);
			  System.out.print("4:listroom"+Constants.CR_LF);
			  System.out.print("5:book"+Constants.CR_LF);
		      
	
				  line = console.next();
			 
		      	switch(line){
				case "1":{
					 String[] cities = clientHOPP.cities();
					 if (cities.length == 0) 
					      System.out.print("No city list available"+Constants.CR_LF);
					 else{
						 for (int i = 0; i < cities.length; i++)
				          System.out.println("\t\t" + cities[i]);
					 }
			        break;
				}
				case "0": {
			        System.out.println("\n\t\tGoodbye!");
			        break;
			      }
				case "2":{
					String[] hotel = clientHOPP.hotels();
					if (hotel.length == 0) 
					      System.out.print("No city list available"+Constants.CR_LF);
					else{
						for(int i=0; i<hotel.length; i++)
						System.out.println("\t\t" + hotel[i]);
					}
					break;
				}
			case "3":{
				String city = null;
				System.out.print("please input city"+Constants.CR_LF);
				city = console.next();
				String[] hotel = clientHOPP.selectCityHotel(city);
				if(hotel.length==0)
					System.out.print("No hotel in this city "+Constants.CR_LF);
		
			else{
				for(int i=0; i<hotel.length; i++)
				System.out.println("\t\t" + hotel[i]);
				}
				break;
			}
			 case "4":{
				 boolean flag =false;
					while(!flag)
					{
					 Scanner s = new Scanner(System.in);
					 System.out.print("please enter the city"+Constants.CR_LF);
					 String city =s.next();
					 System.out.print("please enter the hotel"+Constants.CR_LF);
					 String hotel =s.next();
				
					 if((hotel.equalsIgnoreCase(Constants.Hilton)||hotel.equalsIgnoreCase(Constants.Jinjiang)||
							 hotel.equalsIgnoreCase(Constants.Regent))&&(city.equalsIgnoreCase(Constants.Beijing)||
									 city.equalsIgnoreCase(Constants.Nanjing) ||city.equalsIgnoreCase(Constants.Shanghai))){
						 
						 //ArrayList<String> list = new ArrayList<String>();
						String[] list = clientHOPP.listRoom(city,hotel);
						 if(list.length==0){
							 System.out.println(" no hotel in this city, please check before enter "); continue;
						 }
						 else{
								for(int i=0; i<list.length; i++)
									System.out.print("\t\t" + list[i]);
								System.out.println();
						}
						// System.out.print("city"+"\t hote"+"      "+"price"+"      "+"rate"+"      "+"style"+Constants.CR_LF);
//						 Iterator<String> it = list.iterator();
//						 int count =0;
//					        while(it.hasNext()){
//					        	String str = it.next();
//					        		count++;
//					        	   System.out.print(str+"\t");
//					        	   if (count%5==0){
//					        		   System.out.print(Constants.CR_LF);
//					        	   }
//					        	  
//					        }
					      flag =true;
					 }
					 
					 else
						 System.out.print("your city or hotel is not exist,please enter the correct city or hotel!"+Constants.CR_LF);
				
					
					}
					break;
				 }
			 
				
			 
		case "5":{
			
				
				boolean flag =false;
				
				
				while(!flag){
					Scanner s = new Scanner(System.in);
					System.out.print("please enter the city"+Constants.CR_LF);
					String city =s.next();
					System.out.print("Please enter the hotel name"+Constants.CR_LF);
					String hotel =s.next();
					//ArrayList<String> list = new ArrayList<String>();
					 if(!((hotel.equalsIgnoreCase(Constants.Hilton)||hotel.equalsIgnoreCase(Constants.Jinjiang)||
							 hotel.equalsIgnoreCase(Constants.Regent))&&(city.equalsIgnoreCase(Constants.Beijing)||
									 city.equalsIgnoreCase(Constants.Nanjing) ||city.equalsIgnoreCase(Constants.Shanghai)))){
						 	System.out.print("no this hotel or city"+Constants.CR_LF);
						 continue;
					 }
					if((clientHOPP.listRoom(city, hotel)).length==0){
						System.out.print(" hotel or city is not exist,please check before select"+Constants.CR_LF);
						continue;
					}
						System.out.print("please enter the room type you want book<single or double>"+Constants.CR_LF);
						String type = s.next();
						String in=null;
						String out=null;
						while(true){
							System.out.print("please enter the check in date (formate:yyyy-mm-dd)"+Constants.CR_LF);
							 in = s.next(); 
							 if(!(isDataFor(in))) 
							 { 
								 System.out.println("wrong date formate");
								 continue;
							 }
								 
							System.out.print("please enter the check out date (formate: yyyy-mm-dd)"+Constants.CR_LF);
							 out = s.next(); 
							 if(!(isDataFor(out)))
							 {
								 System.out.println("wrong date formate");
								 continue; 
							 }
							 if((out.compareTo(in))<=0)
							 {
								 System.out.println("  check out date is illeage");
								 continue;
							 }
							 break;
						}
						
						flag = true;
					double id=0;
					try {
						 id = clientHOPP.bookRoom(city,hotel,type,in,out);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(id<=0){//if id >0 indicate id is the user id 
						//System.out.println(id);
						System.out.print("room is not available"+Constants.CR_LF);break;
					}
					else{
						System.out.print("the room is availabe and your ID is"+id +Constants.CR_LF);
						
						userInfo(id+"",hotel);
						
					}
				}
				break;
			}
			
		
		    	default:  System.out.print("plesea enter the correct command:"+Constants.CR_LF);break;
		      }
		   } while (!line.equals("0"));

		  }


		public void userInfo(String id,String hotel){
			String cno=null;
			Scanner s = new Scanner(System.in);
			System.out.print("please enter your information"+Constants.CR_LF);
			
			System.out.print("please enter your name"+Constants.CR_LF);
			String name = s.next();
			while(true){
			System.out.print("please enter your credit number"+Constants.CR_LF);
			 cno = s.next();
			 if(!isID(cno))
				 {
				 System.out.println("wrong credit formate"); continue;
				 }
			 else    break;
			}
			while(true){
			System.out.print("please enter your email"+Constants.CR_LF);
			String email = s.next(); 
			if(isEmail(email)){
				
				
				String result=clientHOPP.insertInfo(id,name,cno,email,hotel);
				System.out.print(result+Constants.CR_LF);break;
			}
			else{
				System.out.print("wrong email formate"+Constants.CR_LF);
				continue;
			}
			
			
			
		}	
		
			
		}
		 public boolean isEmail(String email){  
		     if (null==email || "".equals(email)) return false;    
		     Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//¸´ÔÓÆ¥Åä  
		     Matcher m = p.matcher(email);  
		     return m.matches();  
		    }  
		 public boolean isDataFor(String data){  
		     if (null==data || "".equals(data)) return false;    
		     Pattern p =  Pattern.compile("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$");//¸´ÔÓÆ¥Åä  
		     Matcher m = p.matcher(data);  
		     return m.matches();  
		    }  
		 public boolean isID(String ID){  
		     if (null==ID || "".equals(ID)) return false;    
		     Pattern p =  Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");//¸´ÔÓÆ¥Åä  
		     Matcher m = p.matcher(ID);  
		     return m.matches();  
		    }

		}

	


