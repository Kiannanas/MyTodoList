package annaik.anyouzoo.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by aanyouzoo on 01/03/2017.
 */
public class TodoActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener  {

    private TextView tvTitle;
    private Button btnTodoAdd;
    private ListView listView;
    //private ArrayList<String> todoList = TodoListManager.getInstance().getTodoList();
    private ArrayList<String> todoList;
    public static final String PREFS_NAME = "MyTodoList";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        Set<String> todoListTmp;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);
        //Permet la création d'une vue avec le contenu du layout
        //nommé main.xml dans les ressources (/res/layout/main.xml)

        // Récupération des composants
        tvTitle = (TextView) findViewById(R.id.task_layout_tv_title);
        btnTodoAdd = (Button) findViewById(R.id.task_layout_btn_todo_add);
        listView = (ListView) findViewById(R.id.task_layout_listView);

        // Récupération de la todolist
        //listView.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.simple_list_item_custom, this.todoList));
        settings = getSharedPreferences(PREFS_NAME, 0);
        todoListTmp = settings.getStringSet("todolist", null);
        // si la valeur correspondant à la clé "todolist" de SharedPreferences n'est pas nulle on récupère la todolist
        if(todoListTmp != null){
            this.todoList = new ArrayList<String>(todoListTmp);
            Log.d("Récup todolist", Arrays.toString(this.todoList.toArray()));
        }else{
            // sinon on initialise la todolist à un tableau vide
            settings = getSharedPreferences(PREFS_NAME, 0);
            editor = settings.edit();
            editor.putStringSet("todolist", new HashSet<String>());
            editor.commit();
        }

        // on remplit la listView avec notre todoList
        listView.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.simple_list_item_custom, this.todoList));

        // Création des listeners sur les boutons
        btnTodoAdd.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.task_layout_btn_todo_add){
            Intent myIntent = new Intent(getApplicationContext(),TodoAddActivity.class);
            startActivity(myIntent);
            //this.todoList = TodoListManager.getInstance().getTodoList();
            //listView.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.simple_list_item_custom, this.todoList));
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"J'ai sélectionné l'item "+position,Toast.LENGTH_SHORT).show();
        Log.d("TodoListApp","J'ai sélectionné l'item "+position);
    }

}
