package djay.com.sminqtaskdemo.tasks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import djay.com.sminqtaskdemo.R;
import djay.com.sminqtaskdemo.data.model.Tasks;


/**
 * Adapter class for tasks list extending @{@link RecyclerView.Adapter}
 *
 * @author Dhananjay Kumar
 */
class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.DataObjectHolder> {

    private static TasksAdapter.MyClickListener sClickListener;
    private Context mContext;
    private List<Tasks> mList;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    /**
     * {@link TasksAdapter} constructor with context and list of tasks
     *
     * @param context Context
     * @param list    {@link List} of tasks
     */
    TasksAdapter(Context context, List<Tasks> list) {
        mContext = context;
        mList = list;
    }

    /**
     * sets item click listener on the RecylerView
     *
     * @param myClickListener {@link MyClickListener} instance
     */
    void setOnItemClickListener(TasksAdapter.MyClickListener myClickListener) {
        sClickListener = myClickListener;
    }

    @Override
    public TasksAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksAdapter.DataObjectHolder holder, int position) {

        holder.tvName.setText(mList.get(position).getTaskTitle());
        holder.tvHref.setText(mList.get(position).getTaskDesc());
//        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * To apply the animation to items
     *
     * @param viewToAnimate view to animate
     * @param position      the last position
     */
    @SuppressWarnings("unused")
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    /**
     * On item click listener interface
     */
    interface MyClickListener {
        void onItemClick(int position, View v);
    }

    /**
     * {@link RecyclerView.ViewHolder} class for tasks list
     */
    static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_title)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvHref;

        DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

}
