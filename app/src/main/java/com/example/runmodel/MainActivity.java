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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
//    private String TAG = "MainActivity";

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private TextView textView;
    private EditText editBox;
    private Spinner algSpinner;
    private Spinner fileNameSpinner;

    private String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().toString() + "/";
    private String APPLICATION_FOLDER = EXTERNAL_STORAGE_DIRECTORY + "RunModel/";
    private String REQUIRED_FOLDER = APPLICATION_FOLDER + "Required/";

    private static String TAG = "MainActivity";
    //private String TRAIN_DATASET = REQUIRED_FOLDER + "train.arff";
    private String TEST_DATASET = REQUIRED_FOLDER + "test.arff";

    private String LOG_FILENAME = APPLICATION_FOLDER + "log.txt";

    private FileWriter logFileWriter;
    private String logMessage;

    private Data trainModel;
    private Data testModel;

    private Model rfModel;

    private int numIteration;

    private String trainFileNames[] = {
            "train_0.arff",
            "train_25.arff",
            "train_50.arff",
            "train_75.arff"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.numIteration = 100;

        this.textView  = (TextView) findViewById(R.id.logTextView);
        this.textView.setText("Activity starting");

        this.editBox = (EditText) findViewById(R.id.numIterationEditText);
        this.editBox.setText(Integer.toString(this.numIteration));


        this.algSpinner = (Spinner) findViewById(R.id.spinnerAlgorithm);
        this.fileNameSpinner = (Spinner) findViewById(R.id.spinnerFileName);

        try {
            this.logFileWriter = new FileWriter(new File(LOG_FILENAME), true);
            this.logMessage = "";
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        int selectedAlg = this.algSpinner.getSelectedItemPosition();
        int selectionFname = this.fileNameSpinner.getSelectedItemPosition();
        view.setClickable(false);
        this.algSpinner.setEnabled(false);
        this.fileNameSpinner.setEnabled(false);


        this.trainModel = null;

        this.logMessage = "";
        this.logMessage += "===========================\n";
        this.logMessage += "train file name: " + this.trainFileNames[selectionFname] + "\n";
        this.logMessage += "train start: " + formatter.format(new Date()) + "\n";
        try {
            this.trainModel = new Data(REQUIRED_FOLDER + this.trainFileNames[selectionFname], 0.6);
            this.trainModel.loadData();
//            Toast.makeText(this, "Number of instance: " + this.trainModel.numAllInstances(), Toast.LENGTH_SHORT).show();
            this.textView.setText("Number of instance: " + this.trainModel.numAllInstances());

            this.rfModel = new Model(this.trainModel.getAllData());
            if (0 == selectedAlg)
            {
                this.logMessage += "Algorithm name: knn\n";
                Log.d(TAG, "Random Forest started..");
                this.rfModel.fitRF();
                Log.d(TAG, "Random Forest done..");
            }
            else
            {
                this.logMessage += "Algorithm name: j48\n";
                Log.d(TAG, "J48 started..");
                this.rfModel.fitJ48();
                Log.d(TAG, "J48 done..");
            }
            this.logMessage += "train end: " + formatter.format(new Date()) + "\n";
//            Toast.makeText(this, "FIT Number of instance: " + this.trainModel.numAllInstances(), Toast.LENGTH_SHORT).show();
            this.textView.setText("FIT Number of instance: " + this.trainModel.numAllInstances());
            view.setClickable(true);
            this.algSpinner.setEnabled(true);
            this.fileNameSpinner.setEnabled(true);
        } catch (IOException e) {
            Toast.makeText(this, "Hata " + this.algSpinner.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, " EXCEPTION Hata " + String.valueOf(this.algSpinner.getSelectedItem()), Toast.LENGTH_SHORT).show();
        }
    }


    public void testButton(View view) {
        int i;
        long startTime, stopTime;
        int selectedAlg = this.algSpinner.getSelectedItemPosition();
        view.setClickable(false);
        this.testModel = null;
        this.numIteration = Integer.parseInt(this.editBox.getText().toString());
        this.textView.setText("Iteration count is " + this.numIteration);

        this.logMessage += "test num iteration: " + this.numIteration + "\n";
//        this.textView.setText(this.rfModel.getSummary());
        try {
            this.testModel = new Data(TEST_DATASET, 0.4);
            this.testModel.loadData();

            this.logMessage += "test start: " + formatter.format(new Date()) + "\n";
            if (0 == selectedAlg)
            {
                this.logMessage += "test algorithm: knn\n";
                startTime = new Date().getTime();
                for (i = 0; i < this.numIteration; i++) {
                    this.rfModel.evaluateRF(this.testModel.getAllData());
                }
                stopTime = new Date().getTime();
            }
            else
            {
                this.logMessage += "test algorithm: j48\n";
                startTime = new Date().getTime();
                for (i = 0; i < this.numIteration; i++) {
                    this.rfModel.evaluateJ48(this.testModel.getAllData());
                }
                stopTime = new Date().getTime();
            }

            this.logMessage += "test end: " + formatter.format(new Date()) + "\n";

            this.logMessage += "test elapsed time: " + (stopTime - startTime) + "\n";

            try {
                this.logFileWriter = new FileWriter(new File(LOG_FILENAME), true);
                this.logFileWriter.write(logMessage);
                this.logMessage = "";
                this.logFileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.textView.setText("Iteration count is " + this.numIteration + " is done. \n\n" + this.rfModel.getSummary());
            Toast.makeText(this, this.rfModel.getSummary(), Toast.LENGTH_LONG).show();
            view.setClickable(true);

            this.algSpinner.setEnabled(true);
            this.fileNameSpinner.setEnabled(true);

        } catch (IOException e) {
            Toast.makeText(this, "Hata", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
