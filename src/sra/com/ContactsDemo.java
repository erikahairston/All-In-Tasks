package sra.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi") public class ContactsDemo extends Activity implements OnClickListener {
	private Button mBtnContacts;
	private final int PICK = 1;

	/** Called when the activity is first created. */
	
	//public void onCreate(Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		public void Contacts(){
			setContentView(R.layout.main);
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
					String name = c.getString(c.getColumnIndexOrThrow(People.NAME));
					// TODO Whatever you want to do with the selected contact
					// name.
					Uri uri = data.getData();
					Cursor cursor=managedQuery(uri, null, null, null, null);
					String phoneNumber = null;
					while (cursor.moveToNext()) { 
					String name1 = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)); 
					Log.d("HELLO : ", " "+contactId); 
					String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
					Log.d("HELLO : ", " "+hasPhone); 
					if ((Integer.parseInt(hasPhone)==1 )){ 
					Log.d("HELLO : ", " IN if"); 
					Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					null, 
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, 
					null, null); 
					while (phones.moveToNext()) { 
					phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); 
					System.out.println(phoneNumber);
					} 
			
					} 
					Log.d("HELLO : ", " "+name); 
					Log.d("HELLO : ", " "+phoneNumber); 
					}
				}
			}
			break;
		}
	}
}