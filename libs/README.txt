All In README File 						Erika Hairston, Analisse Marquez
							Asia Brown, Ron Tricoche, Nubia Jackson

In this readme document we will explain our pivot and what we were able to accomplish versus what we were not. To make matters simple, we will explain the app screen by screen with matching screen shots.

The Pivot:
Originally the goal was to create an app that made synchronizing events very simple and quick. It was aimed towards parents and families; however, well into the process after creating baseline XML templates and some hard code, we had a lot of trouble learning how to work with the Google API. After much discussion with our TA and a lot of hours of effort, we decided to pivot to All In Tasks, which focused less on the APIs and more on our own abilities to code using what we’ve learned. 

Welcome screen
The Make New Group button takes the user to the next screen, in which the user enters information needed to initialize a new group. Below the Make New Group button is the text, “Current Groups:” If we had had more time, this is where group names of past groups would have appeared. By clicking on the group name, you could then edit the group.  Unfortunately, we were only able to store the information into the parse database, but were not able to pull the information to use in the app. 

Make New Group screen
User is prompted to enter the necessary information to build a new group. All of this information saves to the parse database. Also, we encountered a small bug, which was that you have to push the Save button twice before the Assign Tasks button appears. 

Attendee Tasks screen
When inputting into the 3rd textbox the keyboard covers the textbox. So, you must hide the keyboard to click on the textbox in order to type in it. We created a loop for this screen that would display the Attendee button + message textbox input as many times as the value for number of attendees that was input on Screen 2. Unfortunately, we were not able to pull data from the parse database, so we did not use this code. It is outlined here:
  int numAttendees = Integer.parseInt(num_attendees.getText().toString());

//for creating new layout: http://www.techotopia.com/index.php/Creating_an_Android_User_Interface_in_Java_Code

//creates new layout with specified number of buttons, text edit and edit view

//linear layout format: http://stackoverflow.com/questions/7241145/creating-linearlayout-in-java-elements-are-not-shown

//idea for new button loop format: http://stackoverflow.com/questions/18871030/android-how-can-i-set-up-buttons-in-a-for-loop
  public void buttonLoop(View view){
		   
	    	Button Save;
	    	Save = (Button) findViewById(R.id.Save);
	    	
	    	 Button[] btnArray = new Button[numAttendees];
	         EditText[] textArray = new EditText[numAttendees];
	         TextView[] viewArray = new TextView[numAttendees];

	        //makes new layout
	        
	            LinearLayout myLayout = new LinearLayout(this);
	            
	            LinearLayout.LayoutParams buttonParams = 
	                    new LinearLayout.LayoutParams(
	                        LinearLayout.LayoutParams.WRAP_CONTENT, 
	                        LinearLayout.LayoutParams.WRAP_CONTENT);
	            		
	         myLayout.setOrientation(LinearLayout.VERTICAL);
	      
	           //creates specified number of buttons, edit text and text view
	            for(int i=0;i<numAttendees;i++){
	                btnArray[i]=new Button(this);
	                btnArray[i].setId(i);
	                btnArray[i].setText("Attendee "+ (i+1));
	                myLayout.addView(btnArray[i]);

	                textArray[i]=new EditText(this);
	                textArray[i].setId(i);
	                myLayout.addView(textArray[i]);
	                
	                viewArray[i]=new TextView(this);
	                viewArray[i].setId(i);
	                viewArray[i].setText("Lovechild "+ (i+1));
	                myLayout.addView(viewArray[i]);
	       }
	            setContentView(myLayout);
	            
	    }
	   
Attendee Tasks screen (cont.)
Users click on the Attendee button, which opens their contacts. They then select, by clicking, the contact of their choice. Upon clicking on the contact, the display name and phone number of this contact are saved to the parse database. If we had had more time, we would have created a way to make the attendee button chance to text that would be equal to the contact’s display name. The user then enters the task they wish to send to that attendee in the text box and this message is scanned. You must click the SEND button twice. The Send button then uses the phone number and task of each attendee to send them a text of their task. 

Sources:
https://www.parse.com/docs/android_guide
http://stackoverflow.com/questions/15376970/android-eclipse-button-onclick-event
/*The first part of this code up to the if (c.moveToFirst()) was obtained from 
 * http://www.mediafire.com/download/05md5mjc5cps44t/PickContact.zip[mediafire.com] 
 * The second part up to the sms was obtained from
 * http://stackoverflow.com/questions/3830184/in-android-how-to-pick-a-contact-and-display-it-on-my-app?rq=1[stackoverflow.com]
 * The sms code was obtained from
 * http://www.tutorialspoint.com/android/android_sending_sms.htm[tutorialspoint.com]
/*The first part of this code up to the if (c.moveToFirst()) was obtained from 
 * http://www.mediafire.com/download/05md5mjc5cps44t/PickContact.zip[mediafire.com] 
 * The second part up to the sms was obtained from
 * http://stackoverflow.com/questions/3830184/in-android-how-to-pick-a-contact-and-display-it-on-my-app?rq=1[stackoverflow.com]
 * The sms code was obtained from
 * http://www.tutorialspoint.com/android/android_sending_sms.htm[tutorialspoint.com]
