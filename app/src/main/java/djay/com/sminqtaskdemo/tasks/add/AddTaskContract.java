package djay.com.sminqtaskdemo.tasks.add;


import djay.com.sminqtaskdemo.BasePresenter;
import djay.com.sminqtaskdemo.BaseView;

/**
 * Interface acting as a contract between add task fragment view and presenter
 *
 * @author Dhananjay Kumar
 */
@SuppressWarnings("unused")
interface AddTaskContract {

    interface Presenter extends BasePresenter {
        void add(String title, String desc);

        boolean validate(String title, String desc);
    }

    interface View extends BaseView<Presenter> {
        void showSuccess();

        void showError();

        void showTitleError(String error);

        void showDescError(String error);

        void setLoadingIndicator(boolean active);
    }
}
