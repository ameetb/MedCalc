package com.example.ameet.medcalc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    DatabaseTable db;
    TextView textView;
    CustomArrayAdapter customArrayAdapter;
    ArrayList<Med> medList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//         textView=(TextView)findViewById(R.id.textView);

        final DataRetriever dr=new DataRetriever(this);
//        textView.setText(String.valueOf(dr.getValue("toyota")));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, dr.getStringList());
        final AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.nameEntryText);
        textView.setAdapter(adapter);

        customArrayAdapter = new CustomArrayAdapter(this, R.layout.list_item, medList);

        listView = (ListView) (findViewById(R.id.resultsList));
        medList = new ArrayList<>();
        final Med testMed=new Med("testResult", 30);
        medList.add(testMed);

        customArrayAdapter.add(testMed);
        listView.setAdapter(customArrayAdapter);
        customArrayAdapter.notifyDataSetChanged();

        Button b =(Button)findViewById(R.id.nameAddButton);
        final EditText et=(EditText)findViewById(R.id.weightEditText);
        final TextView textView1=(TextView)findViewById(R.id.addDrug);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=String.valueOf(textView.getText());
//                int weight= Integer.parseInt(String.valueOf(et.getText()));
//                textView1.setText(String.valueOf(dr.getValue(name)*weight));
                if(dr.checkNameExists(name)) {
                    Med tempMed = new Med(name, dr.getValue(name));
                    medList.add(tempMed);
                    customArrayAdapter.add(tempMed);
                    customArrayAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast toast=Toast.makeText(MainActivity.this, "Name not recognized", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    toast.show();

                }

            }
        });

    }

//    private static final String[] autoFillNames = new String[] {
//            "Belgium", "France", "Italy", "Germany", "Spain"
//    };





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



}
