package djay.com.sminqtaskdemo.tasks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import djay.com.sminqtaskdemo.R;
import djay.com.sminqtaskdemo.TasksApplication;
import djay.com.sminqtaskdemo.data.model.Tasks;
import djay.com.sminqtaskdemo.tasks.add.AddTaskDialogFragment;
import djay.com.sminqtaskdemo.tasks.add.AddTaskPresenter;

public class TasksActivity extends AppCompatActivity implements TasksContract.View, TasksAdapter
        .MyClickListener {
    @BindView(R.id.pb_recipe_topics)
    ProgressBar mProgressBar;

    @BindView(R.id.btn_retry)
    Button mRetry;

    @BindView(R.id.rv_top_stories)
    RecyclerView mRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_no_tasks)
    TextView tvNoTasks;

    private TasksContract.Presenter mPresenter;
    private TasksAdapter adapter;

    @Inject
    DatabaseReference mTasksDb;
    private List<Tasks> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        initializeDagger();
        ButterKnife.bind(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        initRecyclerView();

        mPresenter = new TasksPresenter(this, mTasksDb);
        mPresenter.fetch();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TasksAdapter(this, mList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.fab)
    void addTaskAction() {
        AddTaskDialogFragment addTaskDialogFragment = new AddTaskDialogFragment();
        addTaskDialogFragment.setPresenter(new AddTaskPresenter(addTaskDialogFragment, mTasksDb));
        addTaskDialogFragment.show(getFragmentManager(), "DialogFragment");
    }

    /**
     * Injects Dagger dependencies
     */
    private void initializeDagger() {
        TasksApplication app = (TasksApplication) getApplication();
        app.getAppComponent().inject(this);
    }


    @Override
    public void showTopics(List<Tasks> list) {
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
        if (!mList.isEmpty())
            tvNoTasks.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mProgressBar.setVisibility(View.GONE);
        mRetry.setVisibility(View.VISIBLE);
        mRetry.setText(getString(R.string.retry));
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (!active) {
            mRetry.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }


    /**
     * OnClick of Retry button
     */
    @OnClick(R.id.btn_retry)
    public void retry() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRetry.setVisibility(View.GONE);
        mPresenter.fetch();
    }

    @Override
    public void onItemClick(int position, View v) {

    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

}
