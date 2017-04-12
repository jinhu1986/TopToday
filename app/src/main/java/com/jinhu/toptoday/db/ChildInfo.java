package com.jinhu.toptoday.db;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  20:12
 */


import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * onCreated = "sql"：当第一次创建表需要插入数据时候在此写sql语句
 */
@Table(name = "news", onCreated = "")
public class ChildInfo {
    /**
     * name = "id"：数据库表中的一个字段
     * isId = true：是否是主键
     * autoGen = true：是否自动增长
     * property = "NOT NULL"：添加约束
     */
    @Column(name = "id", isId = true, autoGen = true, property = "NOT NULL")
    private int id;
    @Column(name = "c_name")
    private String cName;
    @Column(name = "c_title")
    private String cTitle;
    @Column(name = "c_image")
    private String cImage;
    @Column(name = "c_category")
    private String cCategory;


    //默认的构造方法必须写出，如果没有，这张表是创建不成功的
    public ChildInfo() {
    }

    public String getcCategory() {
        return cCategory;
    }

    public void setcCategory(String cCategory) {
        this.cCategory = cCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    @Override
    public String toString() {
        return "ChildInfo{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                ", cTitle='" + cTitle + '\'' +
                ", cImage='" + cImage + '\'' +
                '}';
    }
}
