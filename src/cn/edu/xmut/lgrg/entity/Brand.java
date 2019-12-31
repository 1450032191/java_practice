package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/31 11:06
 * @Description:
 */
public class Brand {
    @ZnSqlField(name = "id")
    private String brandId;
    @ZnSqlField(name = "name")
    private String brandName;
    @ZnSqlField(name = "create_time")
    private String create_time;
    @ZnSqlField(name = "img")
    private String img;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
