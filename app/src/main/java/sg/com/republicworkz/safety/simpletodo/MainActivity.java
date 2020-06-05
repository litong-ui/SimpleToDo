package sg.com.republicworkz.safety.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spn;
    Button btnAdd,btnClear,btnDelete;
    EditText etToDo;
    ListView lvToDo;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        etToDo = findViewById(R.id.editText);
        lvToDo = findViewById(R.id.listViewToDo);
        alToDo = new ArrayList<>();
        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etToDo.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etToDo.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = etToDo.getText().toString();
                etToDo.setText(null);
                alToDo.add(todo);
                aaToDo.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Item to de added", Toast.LENGTH_SHORT).show();

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "To do List cleared", Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etToDo.getText().toString());
                if(pos>alToDo.size()){
                    Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_SHORT).show();
                }else if(alToDo.size() == 0){
                    Toast.makeText(MainActivity.this, "You don't have any task to be removed", Toast.LENGTH_SHORT).show();
                }else{
                    alToDo.remove(pos);
                    aaToDo.notifyDataSetChanged();
                    etToDo.setText(null);
                    Toast.makeText(MainActivity.this,"To do item deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
