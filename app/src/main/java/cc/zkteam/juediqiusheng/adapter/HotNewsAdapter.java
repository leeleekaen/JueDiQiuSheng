package cc.zkteam.juediqiusheng.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cc.zkteam.juediqiusheng.R;
import cc.zkteam.juediqiusheng.bean.SortDetailBean;
import cc.zkteam.juediqiusheng.view.ZKImageView;

/**
 * HotNewsAdapter
 * Created by Doraemon123 on 2017/12/17.
 */
public class HotNewsAdapter
        extends RecyclerView.Adapter<HotNewsAdapter.ViewHolder> {

    List<SortDetailBean> list;


    private onItemClickListener listener;

    public HotNewsAdapter(List list) {
        this.list = list;
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hot_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object obj = list.get(position);
        if (obj == null)
            return;

        if (obj instanceof SortDetailBean) {
            SortDetailBean sortDetailBean = list.get(position);
            holder.tvArticalName.setText(sortDetailBean.getArtifactName());
            holder.imageView.setImageURI(sortDetailBean.getPicUrl());
            holder.tvOrigin.setText("来源：XX");
            holder.tvTime.setText("2017/12/17");
            if (listener != null) {
                holder.setOnClickListener(listener, sortDetailBean);
            }
        } else if (obj instanceof String) {
//            holder.textView.setText((CharSequence) obj);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ZKImageView imageView;
        public final TextView tvArticalName;
        public final TextView tvOrigin;
        public final TextView tvTime;


        public View contentView;
        private SortDetailBean data;

        public ViewHolder(View view) {
            super(view);
            this.contentView = view;
            imageView = view.findViewById(R.id.pic);
            tvArticalName = view.findViewById(R.id.tv_title);
            tvOrigin = view.findViewById(R.id.tv_origin);
            tvTime = view.findViewById(R.id.tv_time);
        }

        public void setOnClickListener(final onItemClickListener listener, SortDetailBean data) {
            this.data = data;
            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(ViewHolder.this.data);
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClicked(SortDetailBean data);
    }

}
