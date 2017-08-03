package djay.com.sminqtaskdemo.tasks.add;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import djay.com.sminqtaskdemo.R;

/**
 * Fragment class for adding new task extends {@link DialogFragment}
 *
 * @author Dhananjay Kumar
 */
public class AddTaskDialogFragment extends DialogFragment implements AddTaskContract.View {
    private AddTaskContract.Presenter mPresenter;
    private Context context;

    @BindView(R.id.edt_title)
    EditText edtTitle;
    @BindView(R.id.edt_desc)
    EditText edtDesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View rootView = inflater.inflate(R.layout.dialog_fragment_add_task, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * OnClick of add button
     */
    @OnClick(R.id.btn_add)
    public void addTask() {
        String title = edtTitle.getText().toString();
        String desc = edtDesc.getText().toString();
        if (mPresenter.validate(title, desc))
            mPresenter.add(title, desc);
    }

    /**
     * OnClick of cancel button
     */
    @OnClick(R.id.btn_cancel)
    public void cancelAdd() {
        dismiss();
    }

    @Override
    public void setPresenter(AddTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSuccess() {
        Toast.makeText(context, "Task added successfully.", Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void showError() {
        Toast.makeText(context, "Error in adding task.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTitleError(String error) {
        edtTitle.setError(error);
    }

    @Override
    public void showDescError(String error) {
        edtDesc.setError(error);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }
}
