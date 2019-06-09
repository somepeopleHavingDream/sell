package com.imooc.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类目
 *
 * @author yangxin
 * 2019/05/26 14:52
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    // 类目id
    @Id
    @GeneratedValue
    private Integer categoryId;

    // 类目名字
    private String categoryName;

    // 类目编号
    private Integer categoryType;

    // 创建时间
    private Date gmtCreate;

    // 更新时间
    private Date gmtModified;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
