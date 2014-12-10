package sra.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends Activity {

	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.welcome_screen);
	       
	    }

		public void makeNewGroup(View view){
	    setContentView(R.layout.new_group);
	  
	    }
	    
	   /* public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.test_main, menu);
	        return true;
	    }

	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    } */


}
