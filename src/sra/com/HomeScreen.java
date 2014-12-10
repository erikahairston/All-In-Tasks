package sra.com;

import android.app.Activity;
import android.os.Bundle;
<<<<<<< HEAD
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

=======
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeScreen extends Activity{

    //@Override
   // protected void onCreate(Bundle savedInstanceState) {
       //super.onCreate(savedInstanceState);
       // setContentView(R.layout.welcome_screen);
       
    //}
>>>>>>> FETCH_HEAD

    
    //public void makeNewGroup(View view){
   // setContentView(R.layout.new_group);
  
   // }
    
    
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.due_date) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}