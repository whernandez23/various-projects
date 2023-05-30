package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import com.email.durgesh.Email;



public class Main extends Application 
{
	double total=0;
	TextArea clock;
	int    numOfItems=0;
	String paymentChosen = " ";
	String timeChosen = " ";
	int cost1;
	int cost2;
	int cost3;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try {
			primaryStage.setTitle("Expedia");
			primaryStage.setWidth(1120);
			primaryStage.setHeight(600);
	        
	        TextArea ta = new TextArea();
	        ta.setEditable(false);
	        ta.setPrefWidth(50);
	        ta.setPrefHeight(100);
	        
	        TextArea ta2= new TextArea();
	        ta2.setEditable(false);   
	        ta2.setStyle("-fx-font-weight: bold");
	        ta2.setPrefWidth(200);
	        ta2.setPrefHeight(100);
	        
	        clock = new TextArea();
	        clock.setEditable(false);
	        clock.setPrefHeight(30);   
	        clock.setPrefWidth(900);
		
			//DepartTimes
			GridPane right = new GridPane();
			Label lb1        = new Label("Depart Times and Price:                         "
						+ "       ");
			right.setMinWidth(200);
			right.setMaxWidth(200);
			RadioButton times1 = new RadioButton();
			RadioButton times2 = new RadioButton();
			RadioButton times3 = new RadioButton();
			TextArea waiting = new TextArea();
		    times1.setPrefHeight(300);   
		    times1.setPrefWidth(300);
			waiting.setText("Waiting for input");
			times2.setPrefHeight(300);   
	        times2.setPrefWidth(300);
	        times3.setPrefHeight(300);   
	        times3.setPrefWidth(300);
			right.add(lb1, 0, 0);
			right.add(waiting, 0,1);
			right.add(times1, 0, 2);
			right.add(times2, 0, 3);
			right.add(times3, 0, 4);
			ToggleGroup Times = new ToggleGroup();
			times1.setToggleGroup(Times);
			times2.setToggleGroup(Times);
			times3.setToggleGroup(Times);
			
			Times.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
	        { 
	            public void changed(ObservableValue<? extends Toggle> ob,  
	                                                    Toggle o, Toggle n) 
	            { 
	  
	                RadioButton rb = (RadioButton)Times.getSelectedToggle(); 
	  
	                if (rb != null) { 
	                    String s = rb.getText(); 
	                    timeChosen = s;
	                    // change the label 
	                    
	                } 
	            } 
	        });
			
	        //body
	        Label startLoc        = new Label(" Starting Location: ");
	        startLoc.setStyle("-fx-font-weight: bold");
			//TextField startLocTF   = new TextField();
			Label endLoc        = new Label(" Ending Location: ");
			endLoc.setStyle("-fx-font-weight: bold");
			ComboBox<String> startLocTF = new ComboBox<String>();
			startLocTF.getItems().addAll(
				    "Miami",
				    "New York City",
				    "Los Angeles",
				    "Nairobi",
				    "Tokyo",
				    "Atlantis",
				    "London",
				    "Paris"
				);
			
			
			ComboBox<String> endLocTF = new ComboBox<String>();
			endLocTF.getItems().addAll(
					"Miami",
				    "New York City",
				    "Los Angeles",
				    "Nairobi",
				    "Tokyo",
				    "Atlantis",
				    "London",
				    "Paris"
				);
			
			
			Button findTimes = new Button("FIND TIMES");
			findTimes.setPrefHeight(34);
			findTimes.setStyle("-fx-font-family: Impact");
			findTimes.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	if (startLocTF.getValue()== null || endLocTF.getValue()== null) {
		        		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				           
				        alert.setTitle("Incomplete Data");
				        if(startLocTF.getValue() == null) {
				        	alert.setHeaderText("Starting Location Cannot Be Empty");
					        alert.setContentText("Please fill in your starting location.");
				        }
				           
				        else if(endLocTF.getValue() == null) {
				        	alert.setHeaderText("Ending Location Cannot Be Empty");
					        alert.setContentText("Please fill in your desired ending location.");
				        }
				           
				        Optional<ButtonType> result = alert.showAndWait();
				           
				        if (result.get() == ButtonType.OK)
				        {
					         
				        }
				        else 
				        {
				               // ... user chose CANCEL or closed the dialog
				        }
		        	}
	            	else if(startLocTF.getValue().equals(endLocTF.getValue())) {
	            		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				           
				        alert.setTitle("Error");
				        alert.setHeaderText("Starting Location and Ending Location cannot Match");
					    alert.setContentText("Please change starting or ending location.");
				           
				        Optional<ButtonType> result = alert.showAndWait();
				           
				        if (result.get() == ButtonType.OK)
				           {
					         
				           }
				        else 
				           {
				               // ... user chose CANCEL or closed the dialog
				           }
	            	}
	            	else {
		            	String strSTA = startLocTF.getValue();
		            	String strEND = endLocTF.getValue();
		            	//System.out.println(strEND);
		            	if(strEND.equals("Miami"))
		            	{  		
		            		waiting.setText("Select One Time");
		            		times1.setText("11:30 AM - $150;\n");
		            		times2.setText("12:20 PM - $230;\n");
		            		times3.setText("1:15 PM - $275;");
		            	}
		            	else if(strEND.equals("New York City"))
		            	{  		
		            		waiting.setText("Select One Time");
		            		times1.setText("10:30 AM - $750;\n");
		            		times2.setText("1:50 PM - $400;\n");
		            		times3.setText("3:15 PM - $600;");
		            	}
		            	else if(strEND.equals("Los Angeles"))
		            	{  		
		            		waiting.setText("Select One Time");
		            		times1.setText("9:30 AM - $350;\n");
		            		times2.setText("2:30 PM - $440;\n");
		            		times3.setText("4:45 PM - $705;");
		            	}
		            	else if(strEND.equals("Nairobi"))
		            	{  		
		            		waiting.setText("Select One Time");
		            		times1.setText("8:30 AM - $780;\n");
		            		times2.setText("9:56 AM - $210;\n");
		            		times3.setText("7:45 PM - $655;");
		            	}
		            	else if(strEND.equals("Tokyo"))
		            	{  		
		            		waiting.setText("Select One Time");
		            		times1.setText("12:30 AM - $200;\n");
		            		times2.setText("4:30 AM - $420;\n");
		            		times3.setText("12:45 PM - $775;");
		            	}
		            	else if(strEND.equals("Atlantis"))
		            	{  		
		            		waiting.setText("Select One Time");
		            		times1.setText("3:30 AM - $290;\n");
		            		times2.setText("5:00 AM - $520;\n");
		            		times3.setText("3:45 PM - $666;");
		            	}
		            	else if(strEND.equals("London"))
		            	{  		
		            		waiting.setText("Select One Time");
		            		times1.setText("6:30 AM - $120;\n");
		            		times2.setText("7:00 PM - $530;\n");
		            		times3.setText("11:45 PM - $230;");
		            	}
		            	else
		            	{
		            		waiting.setText("Select One Time");
		            		times1.setText("4:30 AM - $800;\n");
		            		times2.setText("5:00 PM - $500;\n");
		            		times3.setText("6:45 PM - $600;");
		            		
		            	}
	            	}
	            }
	        });
			//payment method
			Label name           = new Label("\t Name : ");
			TextField nameF      = new TextField();
			Label nameERR        = new Label("");
			Label phone	         = new Label("\t Phone Number: ");
			TextField phoneF     = new TextField();
			Label phoneERR       = new Label("");
			Label email          = new Label("\t Email : ");
			TextField emailF     = new TextField();
			Label emailERR       = new Label("");
			Label blank          = new Label ("");
			Label pay			 = new Label ("\t Payment Method :  Select      ");
			Label Card           = new Label ("\t Card Number : ");
			TextField CardF      = new TextField();
			Label help           = new Label("");
		
			ToggleGroup Payment = new ToggleGroup();
			RadioButton RB1 = new RadioButton("Credit\t");
			RadioButton RB2 = new RadioButton("Debit\t");
			RB1.setToggleGroup(Payment);
			RB2.setToggleGroup(Payment);
		
			Payment.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
	        { 
	            public void changed(ObservableValue<? extends Toggle> ob,  
	                                                    Toggle o, Toggle n) 
	            { 
	  
	                RadioButton rb = (RadioButton)Payment.getSelectedToggle(); 
	  
	                if (rb != null) { 
	                    String s = rb.getText(); 
	                    paymentChosen = s;
	                    // change the label 
	                    pay.setText("\t Payment Method : " + s); 
	                } 
	            } 
	        });
		
			
			//Image Buttons (Just going to be lying around for now)
			Text reccLabel        = new Text("  Recommendations: ");
			reccLabel.setStyle("-fx-font-size: 200");
			reccLabel.setStyle("-fx-font-weight: bold");
			
			Image miamiPic = new Image("https://grist.org/wp-content/uploads/2017/08/miami.jpg");
			ImageView imageMI = new ImageView(miamiPic);
			Button buttonI1 = new Button ("    Miami    ", imageMI);
			imageMI.setFitWidth(160);
			imageMI.setFitHeight(160);
			buttonI1.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("MIAMI");
					           alert.setContentText("Coastal city located in southeastern Florida in the United States. It is the third most populous metropolis on the East coast of the United States, and it is the seventh largest in the country.");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			
			Image nycPic = new Image("https://www.amny.com/wp-content/uploads/2020/03/GettyImages-1181858711.jpg");
			ImageView imageNY = new ImageView(nycPic);
			Button buttonI2 = new Button ("    New York City    ", imageNY);
			imageNY.setFitWidth(160);
			imageNY.setFitHeight(160);
			buttonI2.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("NEW YORK CITY");
					           alert.setContentText(" Many of the city's landmarks, skyscrapers, and parks are known around the world. The Empire State Building has become the global standard of reference to describe the height and length of other structures");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			
			Image losaPic = new Image("https://thumbnails.expedia.com/vAfdSxQ45I8ksj7KXJI0nMZeS8k=/800x533/a.cdn-hotels.com/gdcs/production194/d1896/4362b070-8f10-11e8-9bad-0242ac110002.jpg");
			ImageView imageLA = new ImageView(losaPic);
			Button buttonI3 = new Button ("    Los Angeles    ", imageLA);
			imageLA.setFitWidth(160);
			imageLA.setFitHeight(160);
			buttonI3.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("LOS ANGELES");
					           alert.setContentText("Los Angeles is known for its Mediterranean climate, ethnic and cultural diversity, Hollywood entertainment industry, and its sprawling metropolitan area. ");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			Image nairobiPic = new Image("https://cdn.audleytravel.com/4082/2913/79/8003731-nairobi.jpg");
			ImageView imageNairobi = new ImageView(nairobiPic);
			Button buttonNairobi = new Button ("    Nairobi    ", imageNairobi);
			imageNairobi.setFitWidth(160);
			imageNairobi.setFitHeight(160);
			buttonNairobi.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("NAIROBI");
					           alert.setContentText("Home to thousands of Kenyan businesses and over 100 major international companies and organizations, including the United Nations Environment Programme (UN Environment) and the United Nations Office at Nairobi (UNON), Nairobi is an established hub for business and culture. ");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			
			Image tokyoPic = new Image("https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/02/a0002533/img/basic/a0002533_main.jpg");
			ImageView imageTokyo = new ImageView(tokyoPic);
			Button buttonTokyo = new Button ("    Tokyo    ", imageTokyo);
			imageTokyo.setFitWidth(160);
			imageTokyo.setFitHeight(160);
			buttonTokyo.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("TOKYO");
					           alert.setContentText("As the largest population center in Japan and the site of the country's largest broadcasters and studios, Tokyo is frequently the setting for many Japanese movies, television shows, animated series (anime), web comics, light novels, video games, and comic books (manga). ");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			Image atlantisPic = new Image("https://thumbor.forbes.com/thumbor/fit-in/1200x0/filters%3Aformat%28jpg%29/https%3A%2F%2Fblogs-images.forbes.com%2Fdavidanderson%2Ffiles%2F2018%2F12%2Fatlantis-aquaman-1200x633.jpeg");
			ImageView imageAtlantis = new ImageView(atlantisPic);
			Button buttonAtlantis = new Button ("    Atlantis    ", imageAtlantis);
			imageAtlantis.setFitWidth(160);
			imageAtlantis.setFitHeight(160);
			buttonAtlantis.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("ATLANTIS");
					           alert.setContentText("Atlantis, Paradise Island is a lush, oceanside resort located on Paradise Island. A dynamic destination that launched over two decades ago as a first-of-its kind modern marvel of nature and engineering. ");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			Image londonPic = new Image("https://www.history.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTYyNDg1MjE3MTI1Mjc5Mzk4/topic-london-gettyimages-760251843-promo.jpg");
			ImageView imageLondon = new ImageView(londonPic);
			Button buttonLondon = new Button ("    London    ", imageLondon);
			imageLondon.setFitWidth(160);
			imageLondon.setFitHeight(160);
			buttonLondon.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("LONDON");
					           alert.setContentText("London is one of the world's most important global cities. It exerts a considerable impact upon the arts, commerce, education, entertainment, fashion, finance, healthcare, media, professional services, research and development, tourism and transportation ");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			Image parisPic = new Image("https://images.adsttc.com/media/images/5d44/14fa/284d/d1fd/3a00/003d/large_jpg/eiffel-tower-in-paris-151-medium.jpg");
			ImageView imageParis = new ImageView(parisPic);
			Button buttonParis = new Button ("    Paris    ", imageParis);
			imageParis.setFitWidth(160);
			imageParis.setFitHeight(160);
			buttonParis.setOnAction(new EventHandler<ActionEvent>()
	                {
	                    @Override public void handle(ActionEvent e)
	                    {
	                    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					           
					           alert.setTitle("City Information");
					           alert.setHeaderText("PARIS");
					           alert.setContentText("Since the 17th century, Paris has been one of Europe's major centres of finance, diplomacy, commerce, fashion, gastronomy, science and arts. The City of Paris is the centre and seat of government of the Île-de-France, or Paris Region, which has an estimated population of 12,174,880, or about 18 percent of the population of France as of 2017. ");

					           Optional<ButtonType> result = alert.showAndWait();
					           
					           if (result.get() == ButtonType.OK)
					           {
					        	   
					           }
					           else 
					           {
					               // ... user chose CANCEL or closed the dialog
					           }
	                    }
	                });
			//End of City Images
			
			Image EBG = new Image("https://i.pinimg.com/originals/dd/91/4c/dd914c6cca076f8cebb463a81e73e7e5.jpg");
			BackgroundImage OrangeBG = new BackgroundImage(EBG, null, null, null, null);
			
			Image EXP = new Image("https://lh3.googleusercontent.com/proxy/5qazP9aR6aFS9ry_jlpbsLp0CivmsWCwiIyGO9d8OqFW76YfopxLkkbgaSwr61V8Iak7ap3z9mHkq_P2GR3oMbe-L3BrI3oQmzXKIsqC5jhS9lKo68BNnBIiFH8");
			ImageView imageE = new ImageView(EXP);
			

			//Gridpane for Buttons
			BorderPane reccs = new BorderPane();
			GridPane gridPane = new GridPane();
			ScrollPane scr = new ScrollPane();
			//gridPane.add(reccLabel,	     0, 0, 1, 1);
			gridPane.add(buttonI1,	     0, 1, 1, 1);
			gridPane.add(buttonI2,       1, 1, 1, 1); 		
			gridPane.add(buttonI3,       2, 1, 1, 1);
			gridPane.add(buttonNairobi,       3, 1, 1, 1);
			gridPane.add(buttonTokyo,    0, 2, 1, 1);
			gridPane.add(buttonAtlantis, 1, 2, 1, 1); 		
			gridPane.add(buttonLondon,   2, 2, 1, 1);
			gridPane.add(buttonParis,   3, 2, 1, 1);
			gridPane.setStyle("-fx-background-color:#Ffe48b; -fx-opacity:1;");
			scr.setContent(gridPane);
			scr.setPrefViewportWidth(10);
			scr.setPrefViewportHeight(200);
			reccs.setTop(reccLabel);
			reccs.setBottom(scr);
			
			// Gridpane for contact info
			GridPane contact = new GridPane();
						
			contact.add(name, 		     0, 0, 1, 1);
			contact.add(nameF, 	     1, 0, 1, 1);
			contact.add(nameERR,        2, 0, 1, 1);
		    contact.add(email, 	     0, 2, 1, 1);
			contact.add(emailF, 	     1, 2, 1, 1);
			contact.add(emailERR, 	     2, 2, 1, 1);
			contact.add(blank, 	     0, 3, 1, 1);
			contact.add(help,           1, 3, 1, 1);
			contact.add(pay,            0, 3, 1, 1);
			contact.add(Card,           0, 6, 1, 1);
			contact.add(CardF,          1, 6, 1, 1);
			contact.add(RB1, 1, 4, 1, 1);
			contact.add(RB2, 1, 5, 1, 1);
			
			//Added insurance that fields must be filled out
			Button submitButton    = new Button("SUBMIT");     
	        submitButton.setOnAction(new EventHandler<ActionEvent>() 
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	
	            	Platform.runLater(new Runnable() 
					 {
					        public void run() 
					        {
					        	if (nameF.getText().isEmpty() || startLocTF.getValue()== null || endLocTF.getValue()== null || emailF.getText().isEmpty() 
					        			|| CardF.getText().isEmpty() || paymentChosen.equals(" ") || timeChosen.equals(" ")) {
					        		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							           
							           alert.setTitle("Incomplete Data");
							           if(startLocTF.getValue() == null) {
							        	   alert.setHeaderText("Starting Location Cannot Be Empty");
								           alert.setContentText("Please fill in your starting location.");
							           }
							           else if(nameF.getText().isEmpty()) {
								           alert.setHeaderText("Name Field Cannot Be Empty");
								           alert.setContentText("Please fill in your name.");
							           }
							           else if(endLocTF.getValue() == null) {
							        	   alert.setHeaderText("Ending Location Cannot Be Empty");
								           alert.setContentText("Please fill in your desired ending location.");
							           }
							           else if(emailF.getText().isEmpty()) {
							        	   alert.setHeaderText("Email Field Cannot Be Empty");
								           alert.setContentText("Please fill in your email address.");
							           }
							           else if(CardF.getText().isEmpty()) {
							        	   alert.setHeaderText("Card Field Cannot Be Empty");
								           alert.setContentText("Please fill in your card number.");
							           }
							           else if(paymentChosen.equals(" ")) {
							        	   alert.setHeaderText("Payment type must be selected");
								           alert.setContentText("Please select a payment type.");
							           }
							           else if(timeChosen.equals(" ")) {
							        	   alert.setHeaderText("Time must be selected");
								           alert.setContentText("Please select a time.");
							           }
							           Optional<ButtonType> result = alert.showAndWait();
							           
							           if (result.get() == ButtonType.OK)
							           {
								         
							           }
							           else 
							           {
							               // ... user chose CANCEL or closed the dialog
							           }
					        	}
					        	
					            	
					        	else {
					        	String rs=null;
					            socketUtils su = new socketUtils();
					            
					            if (su.socketConnect() == true) //this always seems to be false for whatever reason
					            {
					            	String strDouble = String.format("%.2f", total);
					            	String msg = "Transaction>kiosk#001" + "," + numOfItems + "," + strDouble;
	            	                su.sendMessage(msg);				            	
	            	                String ackOrNack = su.recvMessage();
	            	                
	            	                String msg1 = "Name: " + nameF.getText();
	            	                su.sendMessage(msg1);
	            	                msg1 = "Starting Location: "+ startLocTF.getValue();
	            	                su.sendMessage(msg1);
	            	                msg1 = "Ending Location: "+ endLocTF.getValue();
	            	                su.sendMessage(msg1);
	            	                msg1 = "Email: " + emailF.getText();
	            	                su.sendMessage(msg1);
	            	                msg1 = "Payment Method: "+ paymentChosen;
	            	                su.sendMessage(msg1);
	            	                msg1 = "Card #: "+ CardF.getText();
	            	                su.sendMessage(msg1);
	            	                msg1 = "Time and Price: "+ timeChosen;
	            	                su.sendMessage(msg1);
	            	                
	            	                
	            	                msg = "quit";
	            	                su.sendMessage(msg);
	            	                rs = su.recvMessage();
	            	                
	            	                System.out.println("Sending email to " + emailF.getText());
	            	                
	            	                try{
	            	                	Email email = new Email("tanvirthrowaway@gmail.com", "JavaFx@67");
	            	                	email.setFrom("tanvirthrowaway@gmail.com", "Expedia SE Project");
	            	                	email.setSubject("Expedia Purchase Receipt");
	            	                	email.setContent("<h1>Thank You For Your Expedia Purchase!</h1>"
	            	                			+ "<p>"
	            	                			+ "Starting Location: "+ startLocTF.getValue() 
	            	                			+ "<br>" + "Ending Location: "+ endLocTF.getValue()
	            	                			+ "<br>" + "Email: " + emailF.getText()
	            	                			+ "<br>" + "Payment Method: "+ paymentChosen
	            	                			+ "<br>" + "Time and Price: "+ timeChosen
	            	                			+ "<br> Card #: "+ CardF.getText()
	            	                			+ "</p>", "text/html");
	            	                	email.addRecipient(emailF.getText());
	            	                	email.send();
	            	                	}
	            	                catch (Exception e) {
	            	            		e.printStackTrace();
	            	            		}
	            	                
	            	                
	            	                //
	            	                // close the socket connection
	            	                //
	            	                su.closeSocket();
	            	                
	            	                // 
	            	                // write to transaction log
	            	                //
	            	                msg = "CLIENT : Transaction>kiosk#001" + "," + numOfItems + "," + strDouble;
	            	                fileIO trans = new fileIO();
	            	                trans.wrTransactionData(msg);
	            	                
	            	                
	            	                // initialize variables back to zero
	            	                total=0.0;
	            	                numOfItems=0;        
	            	                
	            	                ta.setText("");
	            	                ta2.setText("");
	            	                
	            	                if (ackOrNack.startsWith("ACK") == true)
	            	                {
	            	                	ta2.setText("Success!    Message was received and processed by the Socket Server!");
	            	                }
	            	                else
	            	                {
	            	                   ta2.setText("RECV : " + ackOrNack);
	            	                   ta2.appendText(rs);
	            	                }
					            }
					            else
					            {
					            	// 
	            	                // write to transaction log
	            	                //
					            	String strDouble = String.format("%.2f", total);
	            	                String msg = "CLIENT NETWORK ERROR : Transaction>kiosk#001" + "," + numOfItems + "," + strDouble;
	            	                
	            	                fileIO trans = new fileIO();
	            	                trans.wrTransactionData(msg);
	            	                
	            	                
					            	Alert alert = new Alert(Alert.AlertType.ERROR);
							        alert.setTitle("--- Network Communications Error ---");
							        alert.setHeaderText("Unable to talk to Socket Server!");
							          
							        alert.showAndWait();
					            }
					        	}
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
					          
					          String hStr="- Starting Locations and Ending Locations can be chosen via a drop-down menu.\r\n" + 
					        		      "- Click on FIND TIMES to get a list of Depart Times to choose from.\r\n" +
					        		      "- Click on one of the Recommendations to purchase a great-value, preset package.\r\n" +
					        		      "- Please fill out all forms before submitting for purchase.\r\n" +
					        		      "- Click on SUBMIT to confirm a purchase.\r\n" +
					        		      "- Click on LOG DATA to display current logs.\r\n";
					         
					          alert.setContentText(hStr);
					          alert.showAndWait();
					        }
					    });
				}
			});
		
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
					           alert.setContentText("Are you sure you want to exit this program");

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
			
			//Gridpane for payment
	        	contact.add(submitButton, 1, 7, 1, 1);
			contact.add(logData, 0, 9, 1, 1);
			contact.add(helpButton, 1, 9, 1, 1);
			contact.add(exitButton, 2, 9, 1, 1);
			contact.setVgap(5);
			
			//Gridpane for Body
			GridPane body = new GridPane();
			body.add(startLoc,    0, 0);
			body.add(startLocTF,   1, 0);
			body.add(endLoc,    3, 0);
			body.add(endLocTF,   4, 0);
			//body.add(answerL,  0, 2);
			//body.add(answerTF, 1, 2);
			body.add(findTimes, 5, 0);
			//body.add(imageE, 6, 12, 1, 1);
			body.add(contact, 1, 1);

			
			
			//Setup layout 
			BorderPane bp = new BorderPane();
		bp.setBackground(new Background(OrangeBG));
	        bp.setTop(clock);
	        bp.setCenter(body);
	        bp.setRight(right);
	        bp.setBottom(reccs);
	        refreshClock();
	        
			Scene scene = new Scene(bp);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
	// Clock - thread code
    private void refreshClock()
    {
    	Thread refreshClock = new Thread()
		   {  
			  public void run()
			  {	 
				while (true)
				{
					Date dte = new Date();
		
					String topMenuStr = "       " + dte.toString();					      
				    clock.setText("Expedia   			" + topMenuStr); 
			               
				    try
				    {
					   sleep(500L);
				    }
				    catch (InterruptedException e) 
				    {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
				    }
				  
	            }  // end while ( true )
				 
		    } // end run thread
		 };

	     refreshClock.start();
    }
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
