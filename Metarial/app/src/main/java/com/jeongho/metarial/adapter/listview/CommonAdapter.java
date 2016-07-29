package com.jeongho.metarial.adapter.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Jeongho on 2016/7/29.
 */
public abstract class CommonAdapter<T> extends BaseAdapter{

    public Context mContext;
    public List<T> mList;
    public int mItemLayoutId;

    public CommonAdapter(Context context, List<T> list, int itemLayoutId) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (convertView == null){
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext)
//                    .inflate(R.layout.item_attention_user, parent, false);
//            holder.tv = (TextView) convertView.findViewById(R.id.tv_user_name);
//            convertView.setTag(holder);
//        }else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.tv.setText(mList.get(position));


        CommonViewHolder holder = getViewHolder(convertView, parent, position);

        convert(holder, getItem(position));
//        CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, R.layout.item_attention_user, parent, position);
//        TextView textView = holder.initView(R.id.tv_user_name);
//        textView.setText(mList.get(position));
        return holder.getConvertView();
    }

    public abstract void convert(CommonViewHolder holder, T item);
//    public class ViewHolder{
//        private TextView tv;
//
//        public TextView getTv() {
//            return tv;
//        }
//
//        public void setTv(TextView tv) {
//            this.tv = tv;
//        }
//    }

    private CommonViewHolder getViewHolder(View convertView, ViewGroup parent, int position){
        return CommonViewHolder.get(mContext, convertView, mItemLayoutId, parent, position);
    }
}
