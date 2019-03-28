package sa.zad.pre_work_g10;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import sa.zad.pre_work_g10.models.Comments;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    private List<Comments.Children> comments;

    public CommentsAdapter(List<Comments.Children> comments) {
        this.comments = comments;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_comment_view, parent, false);
        return new CommentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        Comments.Children comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}