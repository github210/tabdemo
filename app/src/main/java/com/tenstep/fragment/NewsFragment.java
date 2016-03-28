package com.tenstep.fragment;

/**
 * Created by Administrator on 2016-1-14 0014.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tenstep.R;
import com.tenstep.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private String weburl;
    private String channelName;
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
    }
    @Override
    public void setArguments(Bundle bundle) {
        // TODO Auto-generated method stub
      //  weburl=bundle.getString("weburl");
        channelName=bundle.getString("name");

    }

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){//优化View减少View的创建次数
            //该部分可通过xml文件设计Fragment界面，再通过LayoutInflater转换为View组件
            //这里通过代码为fragment添加一个TextView
            /*
            TextView tvTitle=new TextView(getActivity());
            tvTitle.setText(channelName);
            tvTitle.setTextSize(16);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT));
            view=tvTitle;
        }
        ViewGroup parent=(ViewGroup)view.getParent();
        if(parent!=null){//如果View已经添加到容器中，要进行删除，负责会报错
            parent.removeView(view);*/
            view=inflater.inflate(R.layout.refresh,null);

        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 模拟一些数据
        final List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("新闻 - " + i);
        }

        // 构造适配器
        final BaseAdapter adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                datas);
        // 获取listview实例
        ListView listView = (ListView)getActivity().findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // 获取RefreshLayout实例
        final RefreshLayout myRefreshListView = (RefreshLayout)
              getActivity().findViewById(R.id.swipe_layout);

        // 设置下拉刷新时的颜色值,颜色值需要定义在xml中
        myRefreshListView.setColorScheme(R.color.umeng_comm_text_topic_light_color,R.color.umeng_comm_yellow, R.color.umeng_comm_green, R.color.umeng_comm_linked_text);
        // 设置下拉刷新监听器
        myRefreshListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // 更新数据
                        // datas.add(new Date().toGMTString());
                        adapter.notifyDataSetChanged();
                        // 更新完后调用该方法结束刷新
                        myRefreshListView.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        // 加载监听器
        myRefreshListView.setOnLoadListener(new RefreshLayout.OnLoadListener() {

            @Override
            public void onLoad() {

                Toast.makeText(getActivity(), "load", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // datas.add(new Date().toGMTString());
                        for (int i = 0; i < 20; i++) {
                            datas.add("item - " + i);
                        }
                        adapter.notifyDataSetChanged();
                        // 加载完后调用该方法
                        myRefreshListView.setLoading(false);
                    }
                }, 1500);

            }
        });
    }

}
