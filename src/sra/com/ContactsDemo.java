package sra.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi") public class ContactsDemo extends Activity implements OnClickListener {
	private Button mBtnContacts;
	private final int PICK = 1;
	Button Save;
	EditText group_name;
	EditText current_date;
	EditText due_date;
	EditText num_attendees;
	TextView testPrint; 
	String numAttendees = new String ();
	int numAttendees1 = 0; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { //set to home screen when app starts
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);

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

	//public void saveGroup(View view){
		public int saveGroup() {
		group_name = (EditText)findViewById(R.id.group_name);
		current_date = (EditText)findViewById(R.id.current_date);
		due_date = (EditText)findViewById(R.id.due_date);
		num_attendees = (EditText)findViewById(R.id.attendees);

		Save = (Button) findViewById(R.id.Save);

		//Save.setOnClickListener(new View.OnClickListener()
		{

			//@Override
			//public void onClick(View view) { //saves the inputted text in the Group
				
				String ButtonText = Save.getText().toString();

				System.out.println ("test5");
				group_name = (EditText)findViewById(R.id.group_name);
				current_date = (EditText)findViewById(R.id.current_date); 
				due_date = (EditText)findViewById(R.id.due_date);
				num_attendees = (EditText)findViewById(R.id.attendees);

				String GroupName = group_name.getText().toString();
				String CurrentDate = current_date.getText().toString();
				String DueDate = due_date.getText().toString();
				String numAttendees = num_attendees.getText().toString();
				//int numAttendees1 = Integer.parseInt(numAttendees);
				testPrint= (TextView)findViewById(R.id.printOut);
				testPrint.setText("Welcome " + GroupName);  //need to print this home screen
				Button button = (Button) findViewById(R.id.Save);
				if(numAttendees!=null){
					int numAttendees1 = Integer.parseInt(numAttendees);
				}
				else{
					numAttendees1 =0;
				}
				
				return numAttendees1;
				}
		}
		
		
		
	 public void buttonLoop (View view)
	 {
		 	//int numberAttendees = Integer.parseInt(numAttendees);
	    	Button AssignTasks;
	    	AssignTasks = (Button) findViewById(R.id.assignTasks);

	    	 Button[] btnArray = new Button[numAttendees1];
	         EditText[] textArray = new EditText[numAttendees1];
	         TextView[] viewArray = new TextView[numAttendees1];

	        //makes new layout

	            LinearLayout myLayout = new LinearLayout(this);

	            LinearLayout.LayoutParams buttonParams = 
	                    new LinearLayout.LayoutParams(
	                        LinearLayout.LayoutParams.WRAP_CONTENT, 
	                        LinearLayout.LayoutParams.WRAP_CONTENT);

	         myLayout.setOrientation(LinearLayout.VERTICAL);

	           //creates specified number of buttons, edit text and text view
	         System.out.println ("test");
	            for(int i=0;i<numAttendees1;i++){
	                btnArray[i]=new Button(this);
	                btnArray[i].setId(i);
	                btnArray[i].setText("Attendee "+ (i+1));
	                myLayout.addView(btnArray[i]);

	                System.out.println ("test1");

	                textArray[i]=new EditText(this);
	                textArray[i].setId(i);
	                myLayout.addView(textArray[i]);

	                viewArray[i]=new TextView(this);
	                viewArray[i].setId(i);
	                viewArray[i].setText("Lovechild "+ (i+1));
	                myLayout.addView(viewArray[i]);
	       }
	            setContentView(myLayout);
	            System.out.println ("test2");   
	 }

	

	//setContentView(R.layout.add_tasks); (need to re-implement this to a new button)


	public void contact(View v){
		setContentView(R.layout.pick_contact_button_screen);
		mBtnContacts = (Button) findViewById(R.id.xBtnContacts);
		mBtnContacts.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// Opening Contacts Window as a Window
		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		// calling OnActivityResult with intenet And Some conatct for Identifie
		startActivityForResult(intent, PICK);
	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);
		switch (reqCode) {
		case (PICK):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c = managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {
					fetchContacts();
				}
			}
		break;
		}
	}
	// TODO Whatever you want to do with the selected contact
	// name.

	public static void fetchContacts() {
		String phoneNumber = null;
		String email = null;
		Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
		String _ID = ContactsContract.Contacts._ID;
		String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
		String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
		Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
		StringBuffer output = new StringBuffer();
		System.out.println(NUMBER);
		System.out.println(DISPLAY_NAME);
		/*  Cursor cursor = ContentResolver.query(CONTENT_URI, null,null, null, null); 
						        // Loop for every contact in the phone
						        if (cursor.getCount() > 0) {
						            while (cursor.moveToNext()) {
						                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
						                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
						                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));
						                if (hasPhoneNumber > 0) {
						                    output.append("\n First Name:" + name);
						                    // Query and loop for every phone number of the contact
						                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
						                    while (phoneCursor.moveToNext()) {
						                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
						                        output.append("\n Phone number:" + phoneNumber);
		 */





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
