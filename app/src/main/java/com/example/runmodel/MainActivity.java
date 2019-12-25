package com.example.runmodel;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    private String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().toString() + "/";
    private String APPLICATION_FOLDER = EXTERNAL_STORAGE_DIRECTORY + "TransportModeDetection/";
    private String REQUIRED_FOLDER = APPLICATION_FOLDER + "Required/";

    private static String TAG = "MainActivity";
    private String TRAIN_DATASET = REQUIRED_FOLDER + "train.arff";
    private String TEST_DATASET = REQUIRED_FOLDER + "test.arff";

    private Data trainModel;
    private Data testModel;

    private Model rfModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.textView  = (TextView) findViewById(R.id.logTextView);
        this.textView.setText("Activity starting");



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "TIKLANDI burayı doldurabiliriz tıklayınca sınıflandırma işlemine başlasın.");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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


/*    public void bradleyHTC(View view){
        try {
            new BRADLEYHTC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void trainButton(View view) {
        view.setClickable(false);
        this.trainModel = null;
        try {
            this.trainModel = new Data(TRAIN_DATASET, 0.6);
            this.trainModel.loadData();
//            Toast.makeText(this, "Number of instance: " + this.trainModel.numAllInstances(), Toast.LENGTH_SHORT).show();
            this.textView.setText("Number of instance: " + this.trainModel.numAllInstances());

            this.rfModel = new Model(this.trainModel.getAllData());
            this.rfModel.fitRF();
//            Toast.makeText(this, "FIT Number of instance: " + this.trainModel.numAllInstances(), Toast.LENGTH_SHORT).show();
            this.textView.setText("FIT Number of instance: " + this.trainModel.numAllInstances());
        } catch (IOException e) {
            Toast.makeText(this, "Hata", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, " EXCEPTION Hata", Toast.LENGTH_SHORT).show();
        }
    }


    public void testButton(View view) {
//        view.setClickable(false);
        this.testModel = null;
        try {
            this.testModel = new Data(TEST_DATASET, 0.4);
            this.testModel.loadData();

            this.rfModel.evaluateRF(this.testModel.getAllData());
//            Toast.makeText(this, this.rfModel.getSummary(), Toast.LENGTH_LONG).show();
            this.textView.setText(this.rfModel.getSummary());
        } catch (IOException e) {
            Toast.makeText(this, "Hata", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
