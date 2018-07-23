package com.ustc.gry.inews.event;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/14
 */

public class ChannelChangeMessageEvent{
    private Boolean channelChange;

    public ChannelChangeMessageEvent(Boolean channelChange) {
        this.channelChange = channelChange;
    }

    public Boolean getChannelChange() {
        return channelChange;
    }

    public void setChannelChange(Boolean channelChange) {
        this.channelChange = channelChange;
    }
}
