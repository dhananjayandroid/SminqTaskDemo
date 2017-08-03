package djay.com.sminqtaskdemo;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module class for dependency injection
 *
 * @author Dhananjay Kumar
 */
@Module
public class AppModule {

    TasksApplication mTasksApplication;

    public AppModule(TasksApplication tasksApplication) {
        mTasksApplication = tasksApplication;
    }

    @Singleton
    @Provides
    DatabaseReference provideRepository() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        return mDatabase.child("tasksentries");
    }

}
