package com.it.hr.view;


import com.google.gson.Gson;
import com.it.hr.model.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangmingbo6 on 2017/4/21.
 */

public class RefreshListView  {
    public static void main(String args[]) {
        List<News> list = new ArrayList<News>();
        News news = new News();
        news.setContentUrl("aaaa");
        news.setTitle("bbbb");
        news.setImgUrl("cccc");
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("sysDate","2017-04-21");
        dataMap.put("newsList",list);
        Gson gson = new Gson();
        System.out.println(gson.toJson(dataMap));

    }
}
