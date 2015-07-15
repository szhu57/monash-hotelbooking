package database;
import java.sql.*;
import java.util.ArrayList;

import constants.Constants;


public class DBUtility {
	private Connection conn = null;
	public DBUtility(){
		
		conn = GetDBCon();
	
}

public Connection GetDBCon() {
    try {
       
        Class.forName("com.mysql.jdbc.Driver").newInstance();
       String url = "jdbc:mysql://127.0.0.1:3306/id26346915";
        String user = "fit5183a2"; 
      String password = "";

        Connection conn = DriverManager.getConnection(url,user,password);
     System.out.println("DataBase connecting sucess");
        return conn;
    } catch (ClassNotFoundException ex1) {
        System.out.println("Can't find driver");
        ex1.printStackTrace();
        return null;
    } catch (SQLException ex2) {
        System.out.println("DataBase connection failed");
        ex2.printStackTrace();
        return null;
    } catch (InstantiationException e) {
		
		e.printStackTrace();
		return null;
	} catch (IllegalAccessException e) {
		
		e.printStackTrace();
		return null;
	}

}
public String [] selectCityFromDatabase(){
	 try{
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("select distinct city from hotel");
		 if(rs == null){
			 System.out.println("Empty Set!\n");
			 return null;
		 }
		 String[] cities = new String[3];
		 int i = 0; 
		 while(rs.next()){
			 
			cities[i++] = rs.getString(1);
		 }
		 return cities;
	 }catch(SQLException e){
		 e.printStackTrace();
		 return null;
	 }
	 
}
public String[] selectHotelFromDatabase(){
	 
	 try{
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("select distinct name from hotel");
		 if(rs == null){
			 System.out.println("Empty Set!\n");
			 return null;
		 }
		 String[] hotels = new String[3];
		 int i = 0; 

		 while(rs.next()){
			 hotels[i++] = rs.getString(1);
		
		 }
		 return hotels;
	 }catch(SQLException e){
		 e.printStackTrace();
		 return null;
	 }
}
public String[] selectCityHotelFromDatabase(String city){
	 
	  try{
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("select  name from hotel where city= '"+city+"'");
		 if(rs == null){
			 System.out.println("Empty Set!\n");
			 return null;
		 }
		 ArrayList<String> tempList = new ArrayList<String>();
		 //String[] hotels = new String[2];

		 while(rs.next()){
			 tempList.add(rs.getString(1));
		
		 }
		 String[] hotels = new String[tempList.size()];
		    tempList.toArray(hotels);
		 return hotels;
	 }catch(SQLException e){
		 e.printStackTrace();
		 return null;
	 }
	}
public String[] selectRoomDatabase(String city,String hotel){
	  
	  try{
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("select  city,Hotel_name,price,rate,style from hotelroom where city= '"+city+"'and Hotel_name = '"+hotel+"'");
			 if(rs == null){
				 System.out.println("Empty Set!\n");
				 return null;
			 }
			 ArrayList<String> tempList = new ArrayList<String>();
			 //String[] hotels = new String[2];

			 while(rs.next()){
				 tempList.add(rs.getString(1));
				 tempList.add(rs.getString(2));
				 tempList.add(rs.getString(3));
				 tempList.add(rs.getString(4));
				 tempList.add(rs.getString(5));
			
			 }
			 String[] room = new String[tempList.size()];
			tempList.toArray(room);   
//			 if(room.length==0){
//				 System.out.println(" no hotel in this city, please check before enter "); 
//			 }
//			 else{
//					for(int i=0; i<room.length; i++)
//						System.out.print("\t\t" + room[i]);
//					System.out.println();
//			}System.out.println("zhushuaitest");
			return room;
			 
	  }catch(SQLException e){
		  e.printStackTrace();
		  return null;
	  }
	}
public  double selectStateFromDatabase(String city,String hotel,String type,String in,String out){
	 try{
		 int check_in; 
		 int check_out;
		 String [] str = in.split("-");
		 String ch_in = str[2];
		 check_in = Integer.parseInt(ch_in);
		 //System.out.println("%%^%^");

		 String [] str1 = out.split("-");
		 String ch_out = str1[2];
		 check_out = Integer.parseInt(ch_out);
		 Statement stmt = conn.createStatement();
		 ResultSet rs1 = stmt.executeQuery("select hotel_id from hotelroom where city= '"+city+"'and Hotel_name = '"+hotel+"'and style='"+type+"'" );
		 if(rs1 ==null){
			 //System.out.println(-1);
			 return -1;
		 }
		 int re=0;
		 while(rs1.next()){
			 re = (Integer) rs1.getInt(1);
		   //System.out.println(rs.getObject(1));
			 //tempList.add(((Integer) rs.getObject(1)));
		 }	
		 ResultSet rs2 = stmt.executeQuery("select AvaNo from vacancy where ID= '"+re+"'and Riqi >= '"+check_in+"'and Riqi <='"+check_out+"'" );
		 if(rs2 ==null){
			 System.out.println(-1);
			 return -1;
		 }
		 ArrayList<Integer> tempList = new ArrayList<Integer>();
		 while(rs2.next()){
			 
			 tempList.add(((Integer) rs2.getObject(1)));
		 }	
		  Integer[] result = new Integer[tempList.size()];
		  
		tempList.toArray(result);
		boolean flag = false;
		 for(int i=0; i<result.length;i++){
			 
			 System.out.println(result[i]);
			 
			if(result[i]<0)
	
				flag = true;	
				
		 }
		 if (!flag){
			 int id=0;
			 double x;
			id =(int)((Math.random()*9+1)*100000);//产生一个随机的六位用户id
			//String user_id= new String(id);
			//return id;
			 x = (double) id;
			if((insertBooking(id+"",in,out,hotel,city,type))&&(updataState(re,check_in,check_out)))//insert booking table information
			
			return x;												//if success then update the available room numbe		
	 }
		 
		 return -1;
	 } 
	 catch(SQLException ex){
		 ex.printStackTrace();
		 return -1;
	 }
	
}

public boolean insertBooking(String id,String in,String out,String hotel,String city,String type ){
   // String values = id+Constants.comma+in+Constants.comma+out+Constants.comma+hotel+Constants.comma+city+Constants.comma+type;
	 String table = "booking";
	 boolean res = insertIntoDatabase(table,id,in,out,hotel,city,type);
	 return res;
}
public String insertUserInfo(String id, String name,String cno,String email){

	// String values = id + Constants.comma+name+Constants.comma+cno+Constants.comma+email;
	 String table = "user";
	 boolean res = insertIntoDatabase(table,id,name,cno,email);
	 if(res) return "booking sucess!!!";
	 return null;

}
public boolean insertIntoDatabase(String table,String id,String in,String out,String hotel,String city,String type){
	 try{
		 Statement stmt = conn.createStatement();
		 int rs = stmt.executeUpdate("insert into "+ table +Constants.space +"values('"+id+"','"+in+"','"+out+"','"+hotel+"','"+city+"','"+type+"')" );
		 //System.out.println(rs);
		 if(rs==1)
		 return true;
		 return false;
	 }
	 catch(SQLException ex){
		 ex.printStackTrace();
		 return false;
	 }
}
public boolean insertIntoDatabase(String table,String id,String name,String cno,String email){
	 try{
		 Statement stmt = conn.createStatement();
		 int rs = stmt.executeUpdate("insert into "+ table +Constants.space +"values('"+id+"','"+name+"','"+cno+"','"+email+"')");
		 //System.out.println(rs);
		 if(rs==1)
		 return true;
		 return false;
	 }
	 catch(SQLException ex){
		 ex.printStackTrace();
		 return false;
	 }
}


public boolean updataState(int re,int in,int out){
 try{
		 Statement stmt = conn.createStatement();
		 stmt.execute("LOCK TABLES "+"vacancy"+" WRITE;");
		 int rs = stmt.executeUpdate("update vacancy set AvaNo=AvaNo-1 where ID= '"+re+"'and Riqi >= '"+in+"'and Riqi<='"+out+"'" );
		 stmt.execute("UNLOCK TABLES");	 //System.out.println(rs);
		 return true;
		
	 }
	 catch(SQLException ex){
		 ex.printStackTrace();
		 return false;
	 }

}	          

}