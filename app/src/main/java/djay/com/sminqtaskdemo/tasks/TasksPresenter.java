package djay.com.sminqtaskdemo.tasks;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import djay.com.sminqtaskdemo.data.model.Tasks;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Presenter class for showing recipe list extends {@link djay.com.sminqtaskdemo.tasks.TasksContract.Presenter
 *
 * @author Dhananjay Kumar
 */
class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View mView;

    private DatabaseReference tasksEndPoint;


    /**
     * {@link TasksPresenter} constructor with {@link djay.com.sminqtaskdemo.tasks.TasksContract.View}
     * and {@link DatabaseReference}
     *
     * @param view     view to present
     * @param database DatabaseReference
     */
    TasksPresenter(@NonNull TasksContract.View view, DatabaseReference database) {
        this.mView = checkNotNull(view, "view cannot be null!");
        this.tasksEndPoint = checkNotNull(database, "view cannot be null!");

        mView.setPresenter(this);
    }

    @Override
    public void fetch() {
        tasksEndPoint.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Tasks> mTags = new ArrayList<>();
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    Tasks task = categorySnapshot.getValue(Tasks.class);
                    mTags.add(task);
                }
                mView.setLoadingIndicator(false);
                mView.showTopics(mTags);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mView.showError();
            }
        });
        mView.setLoadingIndicator(false);
    }

    @Override
    public void subscribe() {
        fetch();
    }

    @Override
    public void unSubscribe() {
    }
}
