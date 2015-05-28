package appewtc.masterung.myssrurestaurant;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by masterUNG on 5/28/15 AD.
 */
public class MyAlertDialog {

    public void showDialog(Context context, String strTitle, String strMessage) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(R.drawable.danger);

    }

}   // Main Class
