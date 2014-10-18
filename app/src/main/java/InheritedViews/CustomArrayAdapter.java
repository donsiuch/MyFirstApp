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
    }

    Context context;
    int layoutResourceId;
    List<Task> taskList = null;

    public CustomArrayAdapter(Context context, int resourceId, List<Task> taskList){
        super(context,resourceId, taskList);
        this.context = context;
        this.layoutResourceId = resourceId;
        this.taskList = taskList;
    }

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
            container._id = (TextView)row.findViewById(R.id._id);
            container.description = (TextView)row.findViewById(R.id.description);

            row.setTag(container);
        }

        else
            container = (StaticTaskDisplayComponentContainer)row.getTag();

        // "view holder" pattern
        Task task = taskList.get(position);
        container._id.setText(""+task.getTaskId());
        container.description.setText(task.getDescription());

        return row;
    }
}
