package com.sample;

import android.view.View;
import android.widget.TextView;

import com.library.Adapter.SimpleRecyclerAdapter;
import com.library.Adapter.SimpleRecyclerViewHolder;

/**
 * Created by xiaoye on 2016/6/1.
 */
public class ViewHolder  {


    public static final class TextViewHolder extends SimpleRecyclerViewHolder<Object, SimpleRecyclerAdapter>{
        TextView view;

        public TextViewHolder(View view, SimpleRecyclerAdapter adapter) {
            super(view, adapter);
            this.view = (TextView) view.findViewById(R.id.text);
        }

        @Override
        public int getType() {
            return R.layout.item;
        }

        @Override
        public void bindData(int position) {
            super.bindData(position);
            view.setText(position + "");
        }
    }

    public static final class ImageViewHolder extends SimpleRecyclerViewHolder<Object, SimpleRecyclerAdapter>{

        public ImageViewHolder(View view, SimpleRecyclerAdapter adapter) {
            super(view, adapter);
        }

        @Override
        public int getType() {
            return R.layout.image_item;
        }

        @Override
        public void bindData(int position) {
            super.bindData(position);
        }
    }
}
