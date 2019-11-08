package com.imooc.form;

import lombok.Data;

/**
 * 类目表单类
 *
 * @author yangxin
 * 2019/11/08 11:46
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

}
