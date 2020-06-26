package bean;

public class Drug {
    private String m_name;  // 药品名称
    private String effect;  // 药品功效与作用
    private double price;   // 药品价格
    private String m_use;   // 药品用法
    private String taboo;   // 药品禁忌
    private String classification;   // 药品用法
    private String remark;  // 药品备注
    private String picture;  // 药品图片
    public Drug() {
    }
    public Drug(String m_name, String effect, double price, String m_use, String taboo, String classification, String remark) {
        this.m_name = m_name;
        this.effect = effect;
        this.price = price;
        this.m_use = m_use;
        this.taboo = taboo;
        this.classification = classification;
        this.remark = remark;
    }
    public String getM_name() {
        return m_name;
    }
    public void setM_name(String m_name) {
        this.m_name = m_name;
    }
    public String getEffect() {
        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }
    public Double getPrice() {
        return price;
    }
    public String getM_use() {
        return m_use;
    }
    public void setM_use(String m_use) {
        this.m_use = m_use;
    }
    public String getTaboo() {
        return taboo;
    }
    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }
    public String getClassification() {
        return classification;
    }
    public void setClassification(String classification) {
        this.classification = classification;
    }
    public String getRemark() {
        return remark;
    }
    public String getPicture() {
        return picture;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
