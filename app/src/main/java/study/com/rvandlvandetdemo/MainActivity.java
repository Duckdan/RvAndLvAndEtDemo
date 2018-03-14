package study.com.rvandlvandetdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import study.com.rvandlvandetdemo.adapter.EdittextAdapter;
import study.com.rvandlvandetdemo.model.ItemBean;
import study.com.rvandlvandetdemo.model.Person;

public class MainActivity extends AppCompatActivity {

    private List<Person> lists = new ArrayList<>();
    private List<ItemBean> listBeans = new ArrayList<>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvData = (TextView) findViewById(R.id.tv_data);

        for (int i = 0; i < 20; i++) {
            String str = i <= 9 ? "0" + i : i + "";
            lists.add(new Person("益强科技" + str, null));
            listBeans.add(new ItemBean("益强科技" + str));
        }

        lv = (ListView) findViewById(R.id.lv);
        //以下方法无效因为EditText优先获取焦点
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "toast: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });

//        lv.setAdapter(new MyAdapter(this, lists));
        final EdittextAdapter adapter = new EdittextAdapter(this, listBeans);
        lv.setAdapter(adapter);
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ItemBean> list = adapter.getmList();
                for (int i = 0; i < list.size(); i++) {
                    Log.e("text::", list.get(i).getText());
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private List<Person> lists;

        public MyAdapter(Context context, List<Person> lists) {
            this.context = context;
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_layout, null);

                TextView tv = (TextView) convertView.findViewById(R.id.tv);
                EditText et = (EditText) convertView.findViewById(R.id.et);
                viewHolder = new ViewHolder(tv, et);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Person person = lists.get(position);
            viewHolder.tv.setText(person.getName());
            Log.e("text::", person.getName());
            return convertView;
        }


        class ViewHolder {
            public TextView tv;
            public EditText et;

            public ViewHolder(TextView tv, EditText et) {
                this.tv = tv;
                this.et = et;
            }
        }
    }


}
