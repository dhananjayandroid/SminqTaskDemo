package djay.com.sminqtaskdemo.tasks;


import java.util.List;

import djay.com.sminqtaskdemo.BasePresenter;
import djay.com.sminqtaskdemo.BaseView;
import djay.com.sminqtaskdemo.data.model.Tasks;

/**
 * Interface acting as a contract between tasks list view and presenter
 *
 * @author Dhananjay Kumar
 */
interface TasksContract {

    interface Presenter extends BasePresenter {
        void fetch();
    }

    interface View extends BaseView<Presenter> {
        void showTopics(List<Tasks> list);

        void showError();

        void setLoadingIndicator(boolean active);
    }
}
