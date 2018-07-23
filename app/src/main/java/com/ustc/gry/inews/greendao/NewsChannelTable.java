package com.ustc.gry.inews.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

@Entity(nameInDb = "NEWS_CHANNEL_TABLE")
public class NewsChannelTable {

    @Property(nameInDb = "ID")
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "NEWS_CHANNEL_NAME")
    private String new_channel_name;

    @Property(nameInDb = "NEWS_CHANNEL_ID")
    private String new_channel_id;

    @Property(nameInDb = "NEWS_CHANNEL_TYPE")
    private String new_channel_type;

    @Property(nameInDb = "NEWS_CHANNEL_SELECT")
    private boolean new_channel_select;

    @Property(nameInDb = "NEWS_CHANNEL_INDEX")
    private int new_channel_index;

    @Property(nameInDb = "NEWS_CHANNEL_FIXED")
    private boolean new_channel_fixed;

    @Generated(hash = 1423175576)
    public NewsChannelTable(long id, String new_channel_name, String new_channel_id,
            String new_channel_type, boolean new_channel_select,
            int new_channel_index, boolean new_channel_fixed) {
        this.id = id;
        this.new_channel_name = new_channel_name;
        this.new_channel_id = new_channel_id;
        this.new_channel_type = new_channel_type;
        this.new_channel_select = new_channel_select;
        this.new_channel_index = new_channel_index;
        this.new_channel_fixed = new_channel_fixed;
    }

    @Generated(hash = 518806505)
    public NewsChannelTable() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNew_channel_name() {
        return this.new_channel_name;
    }

    public void setNew_channel_name(String new_channel_name) {
        this.new_channel_name = new_channel_name;
    }

    public String getNew_channel_id() {
        return this.new_channel_id;
    }

    public void setNew_channel_id(String new_channel_id) {
        this.new_channel_id = new_channel_id;
    }

    public String getNew_channel_type() {
        return this.new_channel_type;
    }

    public void setNew_channel_type(String new_channel_type) {
        this.new_channel_type = new_channel_type;
    }

    public boolean getNew_channel_select() {
        return this.new_channel_select;
    }

    public void setNew_channel_select(boolean new_channel_select) {
        this.new_channel_select = new_channel_select;
    }

    public int getNew_channel_index() {
        return this.new_channel_index;
    }

    public void setNew_channel_index(int new_channel_index) {
        this.new_channel_index = new_channel_index;
    }

    public boolean getNew_channel_fixed() {
        return this.new_channel_fixed;
    }

    public void setNew_channel_fixed(boolean new_channel_fixed) {
        this.new_channel_fixed = new_channel_fixed;
    }

   
}
