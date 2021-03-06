package study.com.rvandlvandetdemo.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import study.com.rvandlvandetdemo.R;
import study.com.rvandlvandetdemo.model.ItemBean;

/**
 * 将EditText跟ListView结合使用
 */

public class EdittextAdapter extends BaseAdapter implements View.OnClickListener, View.OnTouchListener,
        View.OnFocusChangeListener, View.OnLongClickListener {
    private int selectedEditTextPosition = -1;
    private List<ItemBean> mList;
    private Context mContext;

    public EdittextAdapter(Context mContext, List<ItemBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public List<ItemBean> getmList() {
        return mList;
    }

    public void setmList(List<ItemBean> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_edittext_listview, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.editText.setOnTouchListener(this); // 正确写法
        vh.editText.setOnFocusChangeListener(this);
        vh.editText.setTag(position);

        if (selectedEditTextPosition != -1 && position == selectedEditTextPosition) { // 保证每个时刻只有一个EditText能获取到焦点
            vh.editText.requestFocus();
        } else {
            vh.editText.clearFocus();
        }

        String text = mList.get(position).getText();
        vh.editText.setText(text);
        vh.editText.setSelection(vh.editText.length());

        convertView.setTag(R.id.item_root, position); // 应该在这里让convertView绑定position
        convertView.setOnClickListener(this);
        convertView.setOnLongClickListener(this);

        return convertView;
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (selectedEditTextPosition != -1) {
                Log.w("MyEditAdapter", "onTextPosiotion " + selectedEditTextPosition);
                ItemBean itemTest = (ItemBean) getItem(selectedEditTextPosition);
                itemTest.setText(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            EditText editText = (EditText) v;
            selectedEditTextPosition = (int) editText.getTag();  //根据当前点击的EditText获取其位置
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditText editText = (EditText) v;
        if (hasFocus) {
            editText.addTextChangedListener(mTextWatcher);
        } else {
            editText.removeTextChangedListener(mTextWatcher);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item_root) {
            int position = (int) view.getTag(R.id.item_root);
            Toast.makeText(mContext, "点击第 " + position + " 个item", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.item_root) {
            int position = (int) view.getTag(R.id.item_root);
            Toast.makeText(mContext, "长按第 " + position + " 个item", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public class ViewHolder {
        EditText editText;

        public ViewHolder(View convertView) {
            editText = (EditText) convertView.findViewById(R.id.et_test);
        }
    }
}
