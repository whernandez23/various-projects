package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class expediaServer extends Application 
{
	// global variables
	public static TextArea textArea;
	public static GridPane gridPane_1;
	public static TextArea textArea_2;
	public static VBox gridPane_3;
	public static TextArea textArea_3 = new TextArea();
	public static TextArea textArea_4 = new TextArea();
	public static TextArea ta = new TextArea();
	TextArea               clock;
	public static int[] startTrack = {0,0,0,0,0,0,0,0};
	public static String[] locations = {"Miami",
									    "New York City",
									    "Los Angeles",
									    "Nairobi",
									    "Tokyo",
									    "Atlantis",
									    "London",
									    "Paris"};
	
	//Important method for stat tracking
	static int countOccurences(String str, String findStr)
	{
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = str.indexOf(findStr,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		    }
		}
		return count;
	}
	
	@Override
	public void start (Stage stage) throws FileNotFoundException
	{
		InetAddress ipAddress = null;
		try
		{
			ipAddress = InetAddress.getLocalHost();
		}
		catch (UnknownHostException el)
		{
			el.printStackTrace();
		}
		
		stage.setTitle("Expedia Socket Server JAVA FX   :        " + 
	                   "IP : " + ipAddress.getHostAddress() + "     Port# : 3333");
		stage.setWidth(1000);
		stage.setHeight(700);
		
		
		//
		// text area for real time clock thread to display
		//
		clock = new TextArea();
		clock.setEditable(false);
		clock.setPrefHeight(40);
		clock.setPrefWidth(500);
		
		
		// available text area
		gridPane_1 = new GridPane();
		gridPane_1.setPrefHeight(80);
		gridPane_1.setPrefWidth(250);
		Image EXP = new Image("https://i.ibb.co/qJmYSkv/expedia-logo.png");
		ImageView imageE = new ImageView(EXP);
		//imageE.setFitHeight(80);
		imageE.setFitWidth(245);
		imageE.setPreserveRatio(true);
		gridPane_1.add(imageE,0,0);
		//Label expedia = new Label("Expedia");
		//gridPane_1.add(expedia, 0, 1);
		gridPane_1.setAlignment(Pos.CENTER);
		GridPane.setHalignment(gridPane_1, HPos.CENTER); // To align horizontally in the cell
		GridPane.setValignment(gridPane_1, VPos.CENTER); // To align vertically in the cell
		gridPane_1.setStyle("-fx-background-color:#ffdc64; -fx-opacity:1;");

		
		
		
		// main area for socket server to display messages
		textArea = new TextArea();
		textArea.setFont(Font.font("Verdana", 18));
		textArea.setEditable(false);
		textArea.setPrefHeight(80);
		textArea.setPrefWidth(200);
				
				
		// available text area
		gridPane_3 = new VBox();
		gridPane_3.setPrefHeight(80);
		gridPane_3.setPrefWidth(250);
		Label clientAct = new Label("Client Activity: ");
		gridPane_3.getChildren().add(clientAct);
		gridPane_3.setSpacing(10);
		gridPane_3.getChildren().add(textArea_3);
		HBox hbox = new HBox();
		Label clientNum = new Label("Number of Clients: ");
		hbox.getChildren().add(clientNum);
		ta.setPrefWidth(10);
		ta.setPrefHeight(10);
		ta.setEditable(false);
		ta.setText("1");
		hbox.getChildren().add(ta);
		gridPane_3.getChildren().add(hbox);
		gridPane_3.setStyle("-fx-background-color:#ffdc64; -fx-opacity:1;");
		
		
		
		
		// area for IP addresses of clients who connect to the socket server
		textArea_2 = new TextArea();
		textArea_2.setEditable(false);
		textArea_2.setPrefHeight(80);
		textArea_2.setPrefWidth(500);
		
		
		//
		// define all BUTTONS
		//
		Button exitButton = new Button("EXIT");
		exitButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {
				           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				           
				           alert.setTitle("Confirmation Dialog");
				           alert.setHeaderText("EXIT confirmation dialog");
				           alert.setContentText("Are you sure you want to exit this Socket Server Program?");

				           Optional<ButtonType> result = alert.showAndWait();
				           
				           if (result.get() == ButtonType.OK)
				           {
					           sockServer.writeHashTableData();
					           System.exit(0);
				           }
				           else 
				           {
				               // ... user chose CANCEL or closed the dialog
				           }
				        }
				    });	
			}
		});
		
		
		Button transdata = new Button("Transaction Data");
		transdata.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("--- Transactions ---");
				          alert.setHeaderText("Transaction History");
				          alert.setResizable(true);
				          
				          alert.setContentText(textArea_4.getText());
				          
				          alert.showAndWait();
				        }
				    });
			}
		});
		
		Button logData = new Button("Log Data");
		logData.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
					    String logString = "";
					    
				        public void run() 
				        {
				        	try
				            {
				        	      File f = new File("transactionLog.txt");
				        	      if (f.exists())
				        	      {
				                    FileReader reader = new FileReader("transactionLog.txt");
				                    BufferedReader br = new BufferedReader(reader);
				                  
				                    String line = br.readLine();
				                    while (line != null)
				                    {
				                    	logString = logString + line + "\r\n";
				                    	line = br.readLine();
				                    }
				                    
				                    br.close();
				        	      }
				        	      else
				        	      {
				        	    	  logString = "No log File Found!";
				        	      }
				        	 }
				             catch(Exception e2)
				             {   
				        	    e2.printStackTrace(); 
				             }		
				        	
				             Alert alert = new Alert(Alert.AlertType.INFORMATION);
				             alert.setTitle("--- Ticket Kiosk ---");
				             alert.setHeaderText("Transaction Log File");
				          
				             alert.setContentText("...\n"+logString.substring(logString.length()-1000,logString.length()-1));
				             alert.setWidth(300);
				             alert.setHeight(600);
				             alert.showAndWait();
				        }
				    });	
			}
		});
		
		
		Button newKiosk = new Button("New Kiosk");
		newKiosk.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {
				          sockServer.createNewKiosk();
				          
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("--- Ticket Kiosk ---");
				          alert.setHeaderText("Total Number of Transactions");
				          
				          alert.setContentText(sockServer.getAllTransactions());
				          
				          alert.showAndWait();
				        }
				    });	
			}
		});
		
		Button stracker = new Button("Stat Tracker");
		stracker.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("--- Stat Tracker ---");
				          alert.setHeaderText("Client Data");
				          alert.setResizable(true);
				          
				          String logString = "";
				          
				          try
				            {
				        	      File f = new File("transactionLog.txt");
				        	      if (f.exists())
				        	      {
				                    FileReader reader = new FileReader("transactionLog.txt");
				                    BufferedReader br = new BufferedReader(reader);
				                  
				                    String line = br.readLine();
				                    while (line != null)
				                    {
				                    	logString = logString + line + "\r\n";
				                    	line = br.readLine();
				                    }
				                    
				                    br.close();
				        	      }
				        	      else
				        	      {
				        	    	  logString = "No log File Found!";
				        	      }
				        	 }
				             catch(Exception e2)
				             {   
				        	    e2.printStackTrace(); 
				             }
				          
				          startTrack[0] = countOccurences(logString, "Miami");
				          startTrack[1] = countOccurences(logString, "New York City");
				          startTrack[2] = countOccurences(logString, "Los Angeles");
				          startTrack[3] = countOccurences(logString, "Nairobi");
				          startTrack[4] = countOccurences(logString, "Tokyo");
				          startTrack[5] = countOccurences(logString, "Atlantis");
				          startTrack[6] = countOccurences(logString, "London");
				          startTrack[7] = countOccurences(logString, "Paris");
				          int largest = 0;
				          for ( int i = 1; i < startTrack.length; i++ )
				          {
				              if ( startTrack[i] > startTrack[largest] ) largest = i;
				          }
				          String[] values= StringUtils.substringsBetween(logString,"$",";");
				          int sum=0;
				          for(int i = 0;i < values.length;i++)
				          {
				             // Note that this is assuming valid input
				             // If you want to check then add a try/catch 
				             // and another index for the numbers if to continue adding the others (see below)
				             sum += Integer.parseInt(values[i]);
				          }
				          
				          
				          alert.setContentText("Total Money Spent By Customers: $"+sum+"\nAverage Money Spent By Customers: $"+sum/values.length+"\nMost Popular City: "+locations[largest]);
				          
				          alert.showAndWait();
				        }
				    });
			}
		});
		
		Button query3 = new Button("LIST KIOSKS");
		query3.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				 Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("--- Ticket Kiosk ---");
				          alert.setHeaderText("Total Number of Transactions");
				          alert.setResizable(true);
				          
				          alert.setContentText(sockServer.getAllTransactions());
				          
				          alert.showAndWait();
				        }
				    });
			}
		});
	
		Button helpButton = new Button("HELP");
		helpButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				 Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("--- Ticket Kiosk Help Window ---");
				          alert.setHeaderText("Help Screen");
				          
				          String hStr="- Click on   EXIT   button to quit the socket server.\r\n" + 
				        		      "- Click on   Transaction Data   to display current transaction history.\r\n" +
				        		      "- Click on   Log Data   to display current logs.\r\n" +
				        		      "- Click on   New Kiosk   to create the next ticket kiosk station.\r\n" +
				        		      "- Click on   Stat Tracker   to display stat analyses.\r\n" +
				                      "- Click on   LIST KIOSKS to display current status of kiosks.\r\n";
				          
				          alert.setContentText(hStr);
				          alert.showAndWait();
				        }
				    });
			}
		});
		
		
		//
		// all buttons go to horizontal view
		//
		HBox hb = new HBox();
		hb.setPadding(new Insets(15, 12, 15, 12));
	    hb.setSpacing(80);
		hb.getChildren().addAll(exitButton, 
				                   transdata, 
				                   logData, 
				                  newKiosk, 
				                    stracker, 
				                    query3,
				               helpButton);
		
		//
		// vertical has IP text area and buttons below
		//
		VBox vb = new VBox();
		vb.getChildren().addAll(textArea_2, hb);
		
		
		//
		// main BORDER PANE pane layout
		//
		BorderPane bp = new BorderPane();
		bp.setTop(clock);
		bp.setLeft(gridPane_1);
		bp.setCenter(textArea);
		bp.setRight(gridPane_3);
		bp.setBottom(vb);
		
		// start all threads  for the GUI screen here
		startRealTimeClock();
		
		// start the socket server thread - will start to accept client connections
		startSockServer();
		
		//
		// lights, camera, action
		//
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
	}

	
  /*
   * Thread to update weather info for NYC and Boston    
   */     
  private void startSockServer()
  {	
	 Thread refreshWeatherThread = new Thread()
	 {
	    public void run()
		  { 	
			  sockServer.runSockServer();
	      }
	 };

    refreshWeatherThread.start();
  }
	
  
  /*
   * Time Updates
   */     
  private void startRealTimeClock()
  {	
	   Thread refreshClock = new Thread()
	   {
		  public void run()
		  {   
			 clock.setFont(Font.font("Arial", 14));
			 
			 while (true)
			 {	 			      
				   Date date = new Date();
				   String str = String.format("    %tc", date);
					 
				   clock.setText("Expedia " + str);
				   
			    	try
				    {
					   sleep(1000L);
				    }
				    catch (InterruptedException e)
				   {
					   // TODO Auto-generated catch block
					  e.printStackTrace();
				   }
             } // end while true 
	     }
	  };

    refreshClock.start();
   }
	
   //
   // main function starts here
   //
   public static void main(String[] args)
   {
		launch(args);
   }
}
