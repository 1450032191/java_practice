package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

import java.util.List;

public class CommodityCategory {
    @ZnSqlField(name = "id")
    private String id;
    @ZnSqlField(name = "name")
    private String name;
    @ZnSqlField(name = "person_id")
    private String personId;
    @ZnSqlField(name = "status")
    private String status;
    @ZnSqlField(name = "create_time")
    private String createTime;
    private List<CommodityCategory> child;

    public List<CommodityCategory> getChild() {
        return child;
    }

    public void setChild(List<CommodityCategory> child) {
        this.child = child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
