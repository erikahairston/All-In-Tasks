package sra.com;

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
	private Button mBtnContacts;
	private final int PICK = 1;
	Button Save;
	EditText group_name;
	EditText current_date;
	EditText due_date;
	EditText num_attendees;
	TextView testPrint; 

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
					//current_date = (EditText)findViewById(R.id.current_date); 
					//due_date = (EditText)findViewById(R.id.due_date);
					num_attendees = (EditText)findViewById(R.id.attendees);

					String GroupName = group_name.getText().toString();
					//String CurrentDate = current_date.getText().toString();
					//String DueDate = due_date.getText().toString();
					String NumAttendees = num_attendees.getText().toString();
					
					
					//pushing inputed into Parse
					groupInfo.put("GroupName", GroupName);
					groupInfo.put("NumAttendees", NumAttendees);
					groupInfo.saveInBackground();
					
					//testPrint= (TextView)findViewById(R.id.printOut);
					//testPrint.setText("Welcome " + GroupName);  //need to print this home screen
					
					
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

	//setContentView(R.layout.add_tasks); (need to re-implement this to a new button)


	public void contact(View v){
		//setContentView(R.layout.pick_contact_button_screen);
		mBtnContacts = (Button) findViewById(R.id.xBtnContacts);
		mBtnContacts.setOnClickListener(this);
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
	String phone = "";


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
						SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
						SharedPreferences.Editor editor = settings.edit();
						//stores string
						String GroupA = "Group A:";
						editor.putString(GroupA, name);
						//Apply the edits!
						editor.apply();
						//access stored string
						String name1 = settings.getString(GroupA, "");
						String name2 = settings.getString("", "");
						name2 = (name1 + "" + name2);
						editor.putString("name2:", name2);
						//Apply the edits!
						editor.apply();


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
									phone = ""; //resets the phone number after each click
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
								//stores string
								editor.putString("number:", phone);
								// Apply the edits!
								editor.apply();
								//access stored string
								String number = settings.getString("number:", "");
								System.out.println(number);
							}
						}
					}
					catch(Exception ex)
					{
						Toast.makeText(getApplicationContext(), "No name selected", Toast.LENGTH_LONG).show();
					}
				}
				

			}
		}

	}


	public void SMS (String phone, String message){
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

	public void sendSMSButton(View view) {
		Button sendSMS;
		sendSMS= (Button) findViewById(R.id.sendSMS);
		
		sendSMS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String message = "Hi there!";
				
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

