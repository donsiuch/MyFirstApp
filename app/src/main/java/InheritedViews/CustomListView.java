/**
 * Custom list-view that allows for the following customizations:
 * -
 */

package InheritedViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by donnie on 10/15/14.
 */
public class CustomListView extends ListView {

    private boolean delete;

    private long _id;

    /**
     *
     * @param context
     */
    public CustomListView(Context context){
        // ??? why do I have to do the next line ???
        super(context);
        _id = -1;
        delete = false;
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Required to over-ride
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Gets the delete status flag
     * @return The value of the delete status flag
     */
    public boolean getDelete(){
        return delete;
    }

    /**
     * Gets the _id. This corresponds to the item's row in the database
     * @return The _id that corresponds to the item in the database
     */
    public long get_id(){
        return _id;
    }

    /**
     * Sets the delete marker flag
     * @param delete True or False that corresponds to whether the item is marked for deletion
     */
    public void setDelete(boolean delete){
        this.delete = delete;
    }

    /**
     * Sets the id of the marker flag
     * @param _id The _id to set the marker flag
     */
    public void set_id(long _id){
        this._id = _id;
    }
}
