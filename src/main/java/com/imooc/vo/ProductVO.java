package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品视图类（包含类目）
 *
 * @author yangxin
 * 2019/06/08 16:21
 */
@Data
@Builder
public class ProductVO implements Serializable {
    private static final long serialVersionUID = -3974213702722475854L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
