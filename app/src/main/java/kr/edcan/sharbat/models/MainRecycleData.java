package kr.edcan.sharbat.models;

import java.util.Date;

/**
 * Created by Chad on 7/17/16.
 */
public class MainRecycleData {
    private String from, address, title, content;
    private Date date;
    private boolean hasAttach;

    public MainRecycleData(String from, String address, String title, String content, Date date, boolean hasAttach) {
        this.from = from;
        this.address = address;
        this.title = title;
        this.content = content;
        this.date = date;
        this.hasAttach = hasAttach;
    }

    public String getFrom() {
        return from;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public boolean isHasAttach() {
        return hasAttach;
    }
}
