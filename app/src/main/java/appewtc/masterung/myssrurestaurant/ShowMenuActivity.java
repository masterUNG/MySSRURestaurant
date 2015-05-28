package appewtc.masterung.myssrurestaurant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class ShowMenuActivity extends ActionBarActivity {

    private TextView showOfficerTextView;
    private String officerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);

        //Show Officer
        showOfficer();

        //About Desk Spinner
        aboutDeskSpinner();


        //About Item Spinner


    }   //onCreate

    private void aboutDeskSpinner() {

        Spinner deskSpinner = (Spinner) findViewById(R.id.spinner);
        String showDesk[] = getResources().getStringArray(R.array.desk);
        ArrayAdapter<String> deskAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, showDesk);
        deskSpinner.setAdapter(deskAdapter);
    }

    private void showOfficer() {
        showOfficerTextView = (TextView) findViewById(R.id.textView);
        officerString = getIntent().getExtras().getString("Officer");
        showOfficerTextView.setText(officerString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_menu, menu);
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
