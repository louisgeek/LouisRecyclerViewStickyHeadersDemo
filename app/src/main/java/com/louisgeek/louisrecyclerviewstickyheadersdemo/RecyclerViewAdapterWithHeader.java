package com.louisgeek.louisrecyclerviewstickyheadersdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eowise.recyclerview.stickyheaders.StickyHeadersAdapter;


public class RecyclerViewAdapterWithHeader extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeadersAdapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener listener;
    private String[] countries;

    public RecyclerViewAdapterWithHeader(Context context) {
        this.mContext = context;
        countries =mContext.getResources().getStringArray(R.array.countries_cn);
        //2016年4月12日10:41:08
        setHasStableIds(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder  holder, final int position) {

        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.mTextView.setText(countries[position].toString());

        myViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onItemClick(position, countries[position].toString());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return countries.length;
    }

    //必须重写  不然item会错乱
    @Override
    public long getItemId(int position) {
        //return super.getItemId(position);
        // return countries[position].hashCode();
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header, parent, false);

        return new MyHeaderViewHolder(itemView);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyHeaderViewHolder myHeaderViewHolder= (MyHeaderViewHolder) viewHolder;
        myHeaderViewHolder.title.setText(countries[position].toString().subSequence(0, 1));
        //headerViewHolder.title.setText(countries[position].subSequence(0, 1));
    }

    @Override
    public long getHeaderId(int position) {
        /*     if (position<3){
            return 0;
        }else if (position<5){
            return 1;
        }else if (position<8){
            return 2;
        }else {
            return 3;
        }*/
        return countries[position].subSequence(0, 1).hashCode();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.id_tv_item);
        }
    }
    public static class MyHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public MyHeaderViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.id_tv_head_item);
        }
    }




    /**
     * 内部接口回调方法
     */
    public interface OnItemClickListener {
        void onItemClick(int position, Object object);
    }
    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
