package com.example.roomsqlitedemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.roomsqlitedemo.databinding.ActivityMainBinding;
import com.example.roomsqlitedemo.utils.SpUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ArrayList<Userbean> listBean;
    private ArrayList<String> listString;
    private ArrayList<Map<String, Object>> listMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        initListener();
        SpUtils.getSp(this);
    }


    private void initView() {
        listString = new ArrayList<String>();
        listBean = new ArrayList<Userbean>();
        listMap = new ArrayList<>();
    }

    int i = 0;

    private void initListener() {

        binding.btPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先把对应id的删除，然后再存
                deleAccount(SpUtils.getDataList("listBean", Userbean.class), "1523");
                //存，此处只是模拟
                for (int i = 0; i < 10; i++) {
                    Userbean userbean = new Userbean();
                    userbean.setUserId("152" + i);
                    userbean.setSecurity("12354882050" + i);
                    listBean.add(userbean);
                    SpUtils.setDataList("listBean", listBean);
                }
            }


        });
        binding.btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = SpUtils.getDataList("listBean", Userbean.class).toString();
                binding.tvContent.setText(content);
//                getStringUserId(SpUtils.<Userbean>getDataList("listBean"),"1521");
                getStringUserId(SpUtils.<Userbean>getDataList("listBean", Userbean.class), "1521");
            }
        });
    }

    /**
     * 取所有的IDS
     *
     * @param list
     * @param id
     */
    private void getStringUserId(List<Userbean> list, String id) {

        if (list != null && list.size() > 0) {
            StringBuilder ids = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                ids.append(list.get(i).getUserId()).append(",");
            }
            if (ids.length() > 0) {
                ids = new StringBuilder(ids.substring(0, ids.length() - 1));
            }
            Toast.makeText(MainActivity.this, "ids的是" + ids, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 插入数据前要遍历下有没有有的话删除，保存最新的
     */
    private void deleAccount(List<Userbean> list, String id) {
        if (list != null && list.size() > 0) {
            //for循环删除
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getUserId().equals(id)) {
//                    list.remove(i);
//                    break;
//                }
//            }
            //使用迭代器最好
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Userbean userbean = (Userbean) it.next();
                if (id.equals(userbean.getUserId())) {
                    it.remove();
                }
            }
            SpUtils.setDataList("listBean", list);

        }
    }


}
