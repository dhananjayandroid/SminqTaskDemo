package djay.com.sminqtaskdemo;

import android.app.Application;


/**
 * Application class extends @{@link Application}
 *
 * @author Dhananjay Kumar
 */
public class TasksApplication extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
