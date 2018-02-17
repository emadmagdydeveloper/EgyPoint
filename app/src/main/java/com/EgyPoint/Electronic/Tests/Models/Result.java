package com.EgyPoint.Electronic.Tests.Models;

import java.io.Serializable;

/**
 * Created by Delta on 15/02/2018.
 */

public class Result implements Serializable {
    private String status;

    public Result() {
    }

    public Result(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
