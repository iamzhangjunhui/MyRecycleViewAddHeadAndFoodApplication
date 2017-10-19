package com.example.kaylee.myrecycleviewaddheadandfoodapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kaylee on 2017/10/18.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ListHolder> {
    private static final String TAG = "RecycleAdapter";
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
//        public static final int TYPE_HEADER = 0;  //说明是带有Header的
//        public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
//        public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
//
//        //获取从Activity中传递过来每个item的数据集合
//        private List<String> mDatas;
//        //HeaderView, FooterView
//        private View mHeaderView;
//        private View mFooterView;
//
//        //构造函数
//    public RecycleAdapter(List<String> list){
//            this.mDatas = list;
//        }
//
//        //HeaderView和FooterView的get和set函数
//    public View getHeaderView() {
//        return mHeaderView;
//    }
//    public void setHeaderView(View headerView) {
//        mHeaderView = headerView;
//        notifyItemInserted(0);
//    }
//    public View getFooterView() {
//        return mFooterView;
//    }
//    public void setFooterView(View footerView) {
//        mFooterView = footerView;
//        notifyItemInserted(getItemCount()-1);
//    }
//
//    /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
//    @Override
//    public int getItemViewType(int position) {
//        if (mHeaderView == null && mFooterView == null){
//            return TYPE_NORMAL;
//        }
//        if (position == 0){
//            //第一个item应该加载Header
//            return TYPE_HEADER;
//        }
//        if (position == getItemCount()-1){
//            //最后一个,应该加载Footer
//            return TYPE_FOOTER;
//        }
//        return TYPE_NORMAL;
//    }
//
//    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
//    @Override
//    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if(mHeaderView != null && viewType == TYPE_HEADER) {
//            return new ListHolder(mHeaderView);
//        }
//        if(mFooterView != null && viewType == TYPE_FOOTER){
//            return new ListHolder(mFooterView);
//        }
//        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
//        return new ListHolder(layout);
//    }
//
//    //绑定View，这里是根据返回的这个position的类型，从而进行绑定的，   HeaderView和FooterView, 就不同绑定了
//    @Override
//    public void onBindViewHolder(ListHolder holder, int position) {
//        if(getItemViewType(position) == TYPE_NORMAL){
//            if(holder instanceof ListHolder) {
//                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
//                ((ListHolder) holder).tv.setText(mDatas.get(position-1));
//                return;
//            }
//            return;
//        }else if(getItemViewType(position) == TYPE_HEADER){
//            return;
//        }else{
//            return;
//        }
//    }
//
//    //在这里面加载ListView中的每个item的布局
//    class ListHolder extends RecyclerView.ViewHolder{
//        TextView tv;
//        public ListHolder(View itemView) {
//            super(itemView);
//            //如果是headerview或者是footerview,直接返回
//            if (itemView == mHeaderView){
//                return;
//            }
//            if (itemView == mFooterView){
//                return;
//            }
//            tv = (TextView)itemView.findViewById(R.id.text);
//        }
//    }
//
//    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
//    @Override
//    public int getItemCount() {
//        if(mHeaderView == null && mFooterView == null){
//            return mDatas.size();
//        }else if(mHeaderView == null && mFooterView != null){
//            return mDatas.size() + 1;
//        }else if (mHeaderView != null && mFooterView == null){
//            return mDatas.size() + 1;
//        }else {
//            return mDatas.size() + 2;
//        }
//    }
}

