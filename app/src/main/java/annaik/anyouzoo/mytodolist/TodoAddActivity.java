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
public class TodoAddActivity extends Activity implements View.OnClickListener  {

    private TextView textView;
    private EditText editText;
    private Button btnClearList;
    private Button btnValidate;
    private ArrayList<String> todoList;
    public static final String PREFS_NAME = "MyTodoList";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add_layout);
        //Permet la création d'une vue avec le contenu du layout
        //nommé main.xml dans les ressources (/res/layout/main.xml)

        // Récupération des composants
        this.textView = (TextView) findViewById(R.id.task_layout_textView);
        this.editText = (EditText) findViewById(R.id.task_layout_editText);
        this.btnClearList = (Button) findViewById(R.id.task_layout_btn_clearlist);
        this.btnValidate = (Button) findViewById(R.id.task_layout_btn_validate);

        // Récupération de la todolist
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Set<String> todoListTmp = settings.getStringSet("todolist", null);
        if(todoListTmp != null){
            this.todoList = new ArrayList<String>(todoListTmp);
        }

        // Création des listeners sur les boutons
        btnClearList.setOnClickListener(this);
        btnValidate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        Intent myIntent;
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        Set<String> todoListTmp;

        switch (id){
            case R.id.task_layout_btn_validate:

                // on met à jour le nom de la tâche
                this.textView.setText(this.editText.getText());

                // Récupération de la todolist
                settings = getSharedPreferences(PREFS_NAME, 0);
                todoListTmp = settings.getStringSet("todolist", null);
                if(todoListTmp != null){
                    this.todoList = new ArrayList<String>(todoListTmp);
                    Log.d("MyApp",Arrays.toString(this.todoList.toArray()));
                }

                // on ajoute la tâche à la todolist
                //TodoListManager.getInstance().addTodoItem(this.editText.getText().toString());
                Log.d("MyApp",Arrays.toString(this.todoList.toArray()));
                this.todoList.add(this.editText.getText().toString());

                // on met à jour les SharedPreferences
                settings = getSharedPreferences(PREFS_NAME, 0);
                editor = settings.edit();
                editor.putStringSet("todolist", new HashSet<String>(this.todoList));
                editor.commit();

                // on affiche le feedback comme quoi la tâche a bien été ajoutée
                Toast.makeText(getApplicationContext(),"La tâche a bien été ajoutée",Toast.LENGTH_SHORT).show();

                // On revient à la liste des tâches
                myIntent = new Intent(getApplicationContext(),TodoActivity.class);
                startActivity(myIntent);

                break;

            case R.id.task_layout_btn_clearlist:

                // on met à jour le nom de la tâche
                this.textView.setText("");

                // Récupération de la todolist
                settings = getSharedPreferences(PREFS_NAME, 0);
                todoListTmp = settings.getStringSet("todolist", null);
                if(todoListTmp != null){
                    this.todoList = new ArrayList<String>(todoListTmp);
                }

                // on supprime la tâche de la todolist
                //TodoListManager.getInstance().removeTodoItem(this.editText.getText().toString());
                this.todoList.remove(this.editText.getText().toString());

                // on met à jour les SharedPreferences
                settings = getSharedPreferences(PREFS_NAME, 0);
                editor = settings.edit();
                editor.putStringSet("todolist", new HashSet<String>(this.todoList));
                editor.commit();

                // on affiche le feedback comme quoi la tâche a bien été supprimée
                Toast.makeText(getApplicationContext(),"La tâche a bien été supprimée",Toast.LENGTH_SHORT).show();

                // On revient à la liste des tâches
                myIntent = new Intent(getApplicationContext(),TodoActivity.class);
                startActivity(myIntent);

                break;
        }

    }

}
