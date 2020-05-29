package com.app.admin.model.Requirement;

import com.app.admin.model.User.User;

import java.util.Date;
import java.util.List;

public class Requirement {

    private long id;

    private long orderId;

    private long creatorId;

    private String describe;

    private String goodName;

    private Integer count;

    private short isDelete;

    private Date createTime;

    private Date updateTime;

    private String userName;

    private String bindName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(short isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBindName() {
        return bindName;
    }

    public void setBindName(String bindName) {
        this.bindName = bindName;
    }
}
