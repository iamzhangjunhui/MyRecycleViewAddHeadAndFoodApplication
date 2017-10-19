package com.example.kaylee.myrecycleviewaddheadandfoodapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kaylee on 2017/10/18.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ListHolder> {
    private List<String> list;
    private View head;
    private View foot;
    public static final int CONTENT = 0;
    public static final int HEAD = 1;
    public static final int FOOT = 2;

    public RecycleAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            return new ListHolder(head);
        } else if (viewType == FOOT) {
            return new ListHolder(foot);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
            ListHolder holder = new ListHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        if (getItemViewType(position) == CONTENT) {
            if (head!=null) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.textView.setText(list.get(position - 1));
            }else {
                holder.textView.setText(list.get(position));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (head!=null&&position == 0) {
            return HEAD;
        } else if (foot!=null&&position == getItemCount() - 1) {
            return FOOT;
        } else {
            return CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        if (head==null&&foot==null) {
            return list.size();
        }else if (head!=null&&foot==null){
            return list.size()+1;
        }else if (head==null&&foot!=null){
            return list.size()+1;
        }else {
            return list.size()+2;
        }
    }

    class ListHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ListHolder(View itemView) {
            super(itemView);
            if (getItemViewType() == HEAD||getItemViewType()==FOOT) {
                return;
            }
            textView = itemView.findViewById(R.id.text);

        }
    }

    public View getHead() {
        return head;
    }

    public void setHead(View head) {
        this.head = head;
        notifyItemChanged(0);
    }

    public View getFoot() {
        return foot;
    }

    public void setFoot(View foot) {
        this.foot = foot;
        notifyItemChanged(getItemCount() - 1);
    }
}

