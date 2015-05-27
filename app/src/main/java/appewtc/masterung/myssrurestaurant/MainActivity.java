package appewtc.masterung.myssrurestaurant;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private UserTABLE objUserTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objUserTABLE = new UserTABLE(this);

        //Test Add Value
       // testAddValue();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // onCreate

    private void synJSONtoSQLite() {

        //Setup New Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }   //if

        InputStream objInputStream = null;
        String strJSON = "";

    }   // synJSON

    private void testAddValue() {
        objUserTABLE.addNewData("testUser", "testPassword", "testOfficer");
        FoodTABLE objFoodTABLE = new FoodTABLE(this);
        objFoodTABLE.addFood("testFood", "12345");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
