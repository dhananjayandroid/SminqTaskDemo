package djay.com.sminqtaskdemo;


import javax.inject.Singleton;

import dagger.Component;
import djay.com.sminqtaskdemo.tasks.TasksActivity;

/**
 * Application Component interface for dependency injection with Dagger
 *
 * @author Dhananjay Kumar
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(TasksActivity tasksActivity);
}
