package com.tenstep.db;

/**
 * Created by Administrator on 2016-1-14 0014.
 */
import com.tenstep.entity.Channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelDb {

    private static List<Channel>   selectedChannel=new ArrayList<Channel>();
    static{
        selectedChannel.add(new Channel("","头条",0,"",""));
        selectedChannel.add(new Channel("","娱乐",0,"",""));
        selectedChannel.add(new Channel("","体育",0,"",""));
        selectedChannel.add(new Channel("","财经",0,"",""));
        selectedChannel.add(new Channel("","热点",0,"",""));
        selectedChannel.add(new Channel("","科技",0,"",""));
        selectedChannel.add(new Channel("","图片",0,"",""));
        selectedChannel.add(new Channel("","汽车",0,"",""));
        selectedChannel.add(new Channel("","时尚",0,"",""));
    }
    public static  List<Channel> getSelectedChannel(){
        return selectedChannel;
    }
}
