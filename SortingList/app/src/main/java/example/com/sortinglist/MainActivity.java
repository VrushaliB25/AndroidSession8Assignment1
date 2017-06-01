package example.com.sortinglist;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

//A comparison function, which imposes a total ordering on some collection of objects
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mAscButton;
    private Button mDescButton;
    ListView listView;
    private List<String> strList;
    private StringAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating Array of strings
        strList = new ArrayList<String>();
        strList.add("April");
        strList.add("February");
        strList.add("January");
        strList.add("July");
        strList.add("June");
        strList.add("March");
        strList.add("December");
        strList.add("October");
        strList.add("November");
        strList.add("August");
        strList.add("September");
        strList.add("May");

        //initializing Adapter
        adapter = new StringAdapter(this, R.layout.list_white_text, strList);

        //calling  setAdapter() on our ListView object

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        mAscButton = (Button) findViewById(R.id.btasc);
        mDescButton = (Button) findViewById(R.id.btdesc);


        mAscButton.setOnClickListener(this);
        mDescButton.setOnClickListener(this);

    }

    // Comparator for Ascending Order
    public static Comparator<String> StringAscComparator = new Comparator<String>() {

        public int compare(String str1, String str2) {

            String stringName1 = str1;
            String stringName2 = str2;

            return stringName1.compareToIgnoreCase(stringName2);
        }
    };

    //Comparator for Descending Order
    public static Comparator<String> StringDescComparator = new Comparator<String>() {

        public int compare(String str1, String str2) {

            String stringName1 = str1;
            String stringName2 = str2;

            return stringName2.compareToIgnoreCase(stringName1);
        }
    };

    //creating own Custom Adapter
    private class StringAdapter extends ArrayAdapter<String> {
        // Attributes
        private List<String> str;

        public StringAdapter(Context context, int textViewResourceId,
                             List<String> strM) {
            super(context, textViewResourceId, strM);
            this.str = strM;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Holder holder = null;

            if (view == null) {
                view = View.inflate(MainActivity.this,
                        R.layout.list_white_text, null);

                holder = new Holder();
                holder.StringNameTextView = (TextView) view
                        .findViewById(R.id.list_content);

                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            String nameText = str.get(position);
            holder.StringNameTextView.setText(nameText);
            return view;
        }
    }

    static class Holder {
        private TextView StringNameTextView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btasc:
                Collections.sort(strList, StringAscComparator);

                // A Toast Notification for user when list arranged in Ascending Order
                Toast.makeText(MainActivity.this, "Sorting in Ascending Order", Toast.LENGTH_LONG).show();
                break;
            case R.id.btdesc:
                Collections.sort(strList, StringDescComparator);

                // A Toast Notification for user when list arranged in Descending Order
                Toast.makeText(MainActivity.this, "Sorting in Descending Order", Toast.LENGTH_LONG).show();
                break;
        }

        //Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself.
        adapter.notifyDataSetChanged();

    }
}