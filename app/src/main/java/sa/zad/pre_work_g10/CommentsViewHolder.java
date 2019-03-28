package sa.zad.pre_work_g10;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import sa.zad.pre_work_g10.models.Comments;

public class CommentsViewHolder extends RecyclerView.ViewHolder {

    public CommentsViewHolder(View view) {
        super(view);
    }

    public void bind(Comments.Children children) {
        TextView username = itemView.findViewById(R.id.user_name);
        TextView comment = itemView.findViewById(R.id.comment);
        username.setText(children.author);
        comment.setText(children.comment);
    }
}