package djay.com.sminqtaskdemo.tasks.add;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.firebase.database.DatabaseReference;

import djay.com.sminqtaskdemo.data.model.Tasks;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Presenter class for adding tasks extends {@link AddTaskContract.Presenter
 *
 * @author Dhananjay Kumar
 */
public class AddTaskPresenter implements AddTaskContract.Presenter {

    private AddTaskContract.View mView;

    private DatabaseReference tasksEndPoint;


    /**
     * {@link AddTaskPresenter} constructor with {@link AddTaskContract.View} and {@link DatabaseReference}
     *
     * @param view     view to present
     * @param database databaseReference
     */
    public AddTaskPresenter(@NonNull AddTaskContract.View view, DatabaseReference database) {
        this.mView = checkNotNull(view, "view cannot be null!");
        this.tasksEndPoint = checkNotNull(database, "view cannot be null!");
        mView.setPresenter(this);
    }


    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void add(String title, String desc) {
        Tasks task = new Tasks(title, desc);
        String key = tasksEndPoint.push().getKey();
        task.setTaskId(key);
        tasksEndPoint.child(key).setValue(task);
        mView.showSuccess();
    }

    @Override
    public boolean validate(String title, String desc) {
        boolean isValidated = true;
        if (TextUtils.isEmpty(title.trim())) {
            mView.showTitleError("Title cannot be empty.");
            isValidated = false;
        }
        if (TextUtils.isEmpty(desc.trim())) {
            mView.showDescError("Description cannot be empty.");
            isValidated = false;
        }
        return isValidated;
    }

}
