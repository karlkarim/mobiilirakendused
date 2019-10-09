package com.karl.aboutking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //A list of objects of the King type are created which then the programs data source
    private List<King> kings = (new Kings ()).getKings();

    //defined variables
    private TextView from, to;
    private ListView kingList;

    public static final int MENU_NAME = Menu.FIRST +1;
    public  static  final int MENU_TEXT = Menu.FIRST +2;
    public  static final int MENU_NEXT = Menu.FIRST +3;


    //when you select item from
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        King king = kings.get(menuInfo.position);
        switch (item.getItemId()){
            case MENU_NAME:
                Toast.makeText(this, king.getKing(),Toast.LENGTH_LONG).show();
                return true;
            case MENU_TEXT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(king.getKing());
                builder.setMessage(king.getText());
                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                return true;
            case MENU_NEXT:
                Intent nextPage=new Intent(getApplicationContext(),KingActivity.class);
                startActivity(nextPage);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(menu.NONE, MENU_NAME, Menu.NONE, "Kings");
        menu.add(menu.NONE, MENU_TEXT, Menu.NONE, "Description");
        menu.add(menu.NONE, MENU_NEXT, Menu.NONE, "Next page");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from = findViewById(R.id.txtFrom);
        to = findViewById(R.id.txtTo);
        kingList = findViewById(R.id.lstKings);

        kingList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kings));

        kingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                update(position);
            }
        });

        registerForContextMenu(kingList);
    }

    private void update(int position) {
        int a = kings.get(position).getFrom();
        int b = kings.get(position).getTo();
        from.setText(a == 0 ? "" : "" + a);
        to.setText(b == 9999 ? "" : "" + b);
    }


}
