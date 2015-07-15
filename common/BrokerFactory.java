package common;

//import java.util.Scanner;
//
public class BrokerFactory {

		  public static BrokerClient creatHOPP(String para)  {
		   

		    if (para.equals("corba")) {
	
		      return new corba.BrokerHOPP();
		    }
		    if (para.equals("rmi")) {
		      return new rmi.BrokerHOPP();
		    }
		    return null;
		  }
		}


	

