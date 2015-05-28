package appewtc.masterung.myssrurestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


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

        //Delete All Data
        deleteAllData();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // onCreate

    private void deleteAllData() {

        SQLiteDatabase deleteDatabase = openOrCreateDatabase("ssru.db", MODE_PRIVATE, null);
        deleteDatabase.delete("userTABLE", null, null);

    }

    private void synJSONtoSQLite() {

        //Setup New Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }   //if

        InputStream objInputStream = null;
        String strJSON = "";

        //Create Input Stream
        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/ssru2/php_get_data_master.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("ssru", "Create Stream ==> " + e.toString());
        }

        //Create strJSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferedReader.readLine()) != null) {
                objStringBuilder.append(strLine);
            }   //while

            objInputStream.close();
            strJSON = objStringBuilder.toString();
            //Log.d("ssru", "My JSON ==> " + strJSON);

        } catch (Exception e) {
            Log.d("ssru", "Create strJSON ==> " + e.toString());
        }

        //Update SQLite
        try {

            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {

                JSONObject myJsonObject = objJsonArray.getJSONObject(i);
                String strUser = myJsonObject.getString("User");
                String strPassword = myJsonObject.getString("Password");
                String strOfficer = myJsonObject.getString("Officer");

                objUserTABLE.addNewData(strUser, strPassword, strOfficer);

            }   //for

        } catch (Exception e) {
            Log.d("ssru", "Update SQLite ==> " + e.toString());
        }



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
