package sra.com;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MakeNewGroup 
{
	
	
	@SuppressWarnings("null")
	public void Save ()
	{
		Button Save = null;
		final EditText group_name;
		final EditText current_date;
		final EditText due_date;
		final EditText attendees;
			    
		group_name = (EditText)findViewById(R.id.group_name);
	    
	    current_date = (EditText)findViewById(R.id.current_date);
	    
	    due_date = (EditText)findViewById(R.id.due_date);
	    
	    attendees = (EditText)findViewById(R.id.attendees);

	    Save.setOnClickListener(
	        new View.OnClickListener()
	        {
	            public void onClick(View view)
	            {
	                Log.v("EditText", group_name.getText().toString());
	                Log.v("EditText", current_date.getText().toString());
	                Log.v("EditText", due_date.getText().toString());
	                Log.v("EditText", attendees.getText().toString());
	            }
	        });
	 }

	private EditText findViewById(int groupName) {
		// TODO Auto-generated method stub
		return null;
	}

}
