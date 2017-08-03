package djay.com.sminqtaskdemo.data.model;

/**
 * Model class for parsing web-api response
 *
 * @author Dhananjay Kumar
 */
@SuppressWarnings("unused")
public class Tasks {
    private String taskId;

    public Tasks() {
        // Required empty public constructor
    }

    public Tasks(String taskTitle, String taskDesc) {
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
    }

    private String taskTitle;
    private String taskDesc;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
}
