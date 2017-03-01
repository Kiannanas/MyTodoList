package annaik.anyouzoo.mytodolist;

import java.util.ArrayList;

/**
 * Created by aanyouzoo on 01/03/2017.
 */
// La classe est finale, car un singleton n'est pas censé avoir d'héritier.
public final class TodoListManager {

    // L'utilisation du mot clé volatile, en Java version 5 et supérieure,
    // permet d'éviter le cas où "Singleton.instance" est non nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe ThreadLocal.
    private static volatile TodoListManager instance = null;

    // D'autres attributs, classiques et non "static".
    private ArrayList<String> todoList;
    //private int zzz;

    /**
     * Constructeur de l'objet.
     */
    private TodoListManager() {
        // La présence d'un constructeur privé supprime le constructeur public par défaut.
        // De plus, seul le singleton peut s'instancier lui-même.
        super();
        todoList = new ArrayList<String>();
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static TodoListManager getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (TodoListManager.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(TodoListManager.class) {
                if (TodoListManager.instance == null) {
                    TodoListManager.instance = new TodoListManager();
                }
            }
        }
        return TodoListManager.instance;
    }

    // D'autres méthodes classiques et non "static".
    public void addTodoItem(String todoItem) {
        this.todoList.add(todoItem);
    }

    public void removeTodoItem(String todoItem) {
        this.todoList.remove(todoItem);
    }
    public ArrayList<String> getTodoList(){
        return this.todoList;
    }

}