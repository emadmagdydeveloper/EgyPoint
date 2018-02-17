package com.EgyPoint.Electronic.Tests.Models;

import java.io.Serializable;

/**
 * Created by Delta on 16/02/2018.
 */

public class MessageModel implements Serializable {
    String from;
    String text;
    long date;

    public MessageModel() {
    }

    public MessageModel(String from, String text, long date) {
        this.from = from;
        this.text = text;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
