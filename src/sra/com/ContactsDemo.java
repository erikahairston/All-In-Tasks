package sra.com;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.*;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi") public class ContactsDemo extends Activity implements OnClickListener {
	ParseObject groupInfo = new ParseObject("groupInfo");
	ParseObject smsInfo = new ParseObject("smsInfo");

	String phone = "";
	String name = "";
	String summaryOfGroup = "";
	private Button mBtnContacts;
	private Button mBtnContacts1;
	private Button mBtnContacts2;
	private Button mBtnContacts3;
	private final int PICK = 1;
	Button Save;
	EditText group_name;
	EditText current_date;
	EditText due_date;
	EditText num_attendees;
	TextView testPrint; 
	int numAttendees = 4; 
	String phone1 = "";
	String phone2 = "";
	String phone3 = "";
	String phone4 = "";
	String message = "";
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { //set to home screen when app starts
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
		// Enable Local Datastore.
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "lF24JPvwrScO4guu4ZOWTgkQyQkOoS1iLarv99AL", "PFnvzzENsvJj6k5JxMV2WZvZfyWPADR0zvEZDILs");


	}

	public void makeNewGroup(View view){ // from the home screen when you click on Make New Group Button
		setContentView(R.layout.new_group);
	}

	public void groupPage (View view)
	{
		setContentView(R.layout.group);
	}		

	public void Home (View view)
	{
		setContentView(R.layout.welcome_screen);
	}

	public void saveGroup(View view){

		group_name = (EditText)findViewById(R.id.group_name);
		current_date = (EditText)findViewById(R.id.current_date);
		due_date = (EditText)findViewById(R.id.due_date);
		num_attendees = (EditText)findViewById(R.id.attendees);
		Save = (Button) findViewById(R.id.Save);

		Save.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				String ButtonText = Save.getText().toString();

				if(ButtonText.equals("Save"))
				{
					group_name = (EditText)findViewById(R.id.group_name);
					current_date = (EditText)findViewById(R.id.current_date); 
					due_date = (EditText)findViewById(R.id.due_date);
					num_attendees = (EditText)findViewById(R.id.attendees);

					String GroupName = group_name.getText().toString();
					String CurrentDate = current_date.getText().toString();
					String DueDate = due_date.getText().toString();
					String NumAttendees = num_attendees.getText().toString();
					
					
					/*pushing inputed into Parse
					groupInfo.put("GroupName", GroupName);
					groupInfo.put("NumAttendees", NumAttendees);
					groupInfo.put("phone", phone);
					groupInfo.saveInBackground();*/
					
					//summaryOfGroup = ("Welcome " + GroupName + "these tasks are due" + DueDate);
					//testPrint= (TextView)findViewById(R.id.printOut);
					//testPrint.setText(summaryOfGroup);  //wish to print this home screen
					
					
					Button button = (Button) findViewById(R.id.Save);
					button.setText("Assign Tasks");
				}
				else
				{
					//code for move to next screen 
					setContentView(R.layout.pick_contact_button_screen);

				}
			}

		});

	};

	public void contact(View v){
		mBtnContacts = (Button) findViewById(R.id.xBtnContacts);
		mBtnContacts.setOnClickListener(this);
		
		mBtnContacts1 = (Button) findViewById(R.id.Button01);
		mBtnContacts1.setOnClickListener(this);
		
		mBtnContacts2 = (Button) findViewById(R.id.Button02);
		mBtnContacts2.setOnClickListener(this);
		
		mBtnContacts3 = (Button) findViewById(R.id.Button05);
		mBtnContacts3.setOnClickListener(this);
		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, PICK);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// Opening Contacts Window as a Window
		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		// calling OnActivityResult with intent And Some contact for identifier
		startActivityForResult(intent, PICK);
	}


	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);
		final String PREFS_NAME = "MyApp_Settings";
		switch (reqCode) {
		//what is called when you select a contact
		case (PICK):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c = managedQuery(contactData, null, null, null, null);
				ContentResolver cr = getContentResolver();
				if (c.moveToFirst()) {

					try
					{
						//getting the display name to read into String name
						String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
						String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
						smsInfo.put("name", name);
						smsInfo.saveInBackground();


						if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) 
						{
							Cursor pCur = cr.query(
									ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
									null, 
									ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
											new String[]{id}, null);
							while (pCur.moveToNext()) 
							{
								try
								{
									//getting the actual phone number to read into String phone
									phone = phone + pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts.Data.DATA1)) + ",";
									
								}
								catch(Exception ex)
								{
									Toast.makeText(getApplicationContext(), "No phone number listed", Toast.LENGTH_LONG).show();

								}
							} 
							pCur.close();
							if(phone.length() > 0)
							{
								//getting rid of the comma on the end
								phone = phone.substring(0,phone.length()-1);
								//storing phone number in parse
								
								System.out.println(phone);
							}
						}
						if (phone.length() == 10){
						
							phone1 = phone.substring(0,10);
						}
						if (phone.length() == 20){
						
							phone2 = phone.substring(10,20);
						}
						if (phone.length() == 30){

						phone3 = phone.substring(20,30);
						}
						if (phone.length() == 40){

						phone4 = phone.substring(30,40);
						}
						
					}
					
					catch(Exception ex)
					{
						Toast.makeText(getApplicationContext(), "No name selected", Toast.LENGTH_LONG).show();
					}
				}
				

			}
		}
		//the following two lines saved the attendee's number to the column phone in parse
		//smsInfo.put("phone", phone);
		//smsInfo.saveInBackground();
		//this was one of our attempts to pull data from parse
		/*ParseQuery<ParseObject> query = ParseQuery.getQuery("phone");
	      query.whereEqualTo("Erika Hairton", name);

	      query.findInBackground(new FindCallback<ParseObject>() {
	        public void done(List<ParseObject> phone2, ParseException e) {
	          // commentList now has the comments for myPost
	        	Toast.makeText(getApplicationContext(),
	    				phone2 + "hey",
	    				Toast.LENGTH_LONG).show();
	        	System.out.println(phone2 + "hey");
	        }
	      });
	      */
	}

	
	public void SMS (String phone, String message){
		EditText task1 = (EditText)findViewById(R.id.editText1);
		EditText task2 = (EditText)findViewById(R.id.editText2);
		EditText task3 = (EditText)findViewById(R.id.editText3);
		EditText task4 = (EditText)findViewById(R.id.editText4);

		String message1 = task1.getText().toString();
		String message2 = task2.getText().toString();
		String message3 = task3.getText().toString();
		String message4 = task4.getText().toString();
		
	for (int x = 1; x <= 4; x++){
		
		if (x == 1){
			message = message1;
			phone = phone1;
			System.out.println(message);
			System.out.println(phone);
			System.out.println(message1);
			System.out.println(phone1);
		}
		if(x == 2){
			message = message2;
			phone = phone2;
			System.out.println(message);
			System.out.println(phone);
			System.out.println(message2);
			System.out.println(phone2);
		}
		if(x == 3){
			message = message3;
			phone = phone3;
			System.out.println(message);
			System.out.println(phone);
			System.out.println(message3);
			System.out.println(phone3);
		}
		if (x == 4){
			message = message4;
			phone = phone4;
			System.out.println(message);
			System.out.println(phone);
			System.out.println(message4);
			System.out.println(phone4);
		}
		try {
			// calling the smsManager
			SmsManager smsManager = SmsManager.getDefault();
			//inputting parameters into the smsManager
			smsManager.sendTextMessage(phone, null, message, null, null);
			//prints SMS sent if the message is successfully send
			Toast.makeText(getApplicationContext(), "SMS sent.",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			//prints failed if it does not send
			Toast.makeText(getApplicationContext(),
					"SMS failed, please try again.",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	}

	public void sendSMSButton(View view) {
		Button sendSMS;
		sendSMS= (Button) findViewById(R.id.sendSMS);
		
		sendSMS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				SMS(phone, message);
			}
		});
	}
	
	public void goHome(View view) {
		setContentView(R.layout.add_tasks);
		Button home;
		home = (Button) findViewById(R.id.Home);

		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				setContentView(R.layout.welcome_screen);

			}


		});

		Button group = null;

		group.setOnClickListener(
				new View.OnClickListener()
				{
					public void onClick(View view)
					{
						setContentView(R.layout.group);
					}
				});
	}
}

