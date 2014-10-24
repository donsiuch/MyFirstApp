package InheritedViews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myfirstapp.R;
import java.util.List;
import Task.Task;

public class CustomArrayAdapter extends ArrayAdapter {

    // Increases program speed.
    // One copy exists in static space
    // Holder pattern
    static class StaticTaskDisplayComponentContainer
    {
        TextView description;
        TextView _id;
        TextView delete;
    }

    private Context context;
    private int layoutResourceId;
    private List<Task> taskList;

    /**
     * Constructor
     * @param context Application's context
     * @param resourceId ???
     * @param taskList A list of all saved tasks
     */
    public CustomArrayAdapter(Context context, int resourceId, List<Task> taskList){
        super(context,resourceId, taskList);
        this.context = context;
        this.layoutResourceId = resourceId;
        this.taskList = taskList;
    }

    /**
     *
     * @param position Which row from the list
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        StaticTaskDisplayComponentContainer container = null;

        if(row == null) {
            // Instantiate the layout XML file into its corresponding View objects.
            // In other words, takes as input an XML file and builds the View objects from it.
            // inflate == render
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            // "view holder" pattern
            container = new StaticTaskDisplayComponentContainer();

            // "view holder" pattern
            // Bind the reference to the actual views to the static object's fields
            container._id = (TextView)row.findViewById(R.id._id);
            container.description = (TextView)row.findViewById(R.id.description);
            container.delete = (TextView)row.findViewById(R.id.delete);

            // Provide some kind of glue to the actual row in the list view
            row.setTag(container);
        }

        // Else, the row extists...
        else
            // Get the reference to the row.
            container = (StaticTaskDisplayComponentContainer)row.getTag();

        // "view holder" pattern
        Task task = taskList.get(position);

        // "view holder" pattern
        // Bind the values from the task to the fields
        container._id.setText(""+task.getTaskId());
        container.description.setText(task.getDescription());
        container.delete.setText("false");

        return row;
    }
}
