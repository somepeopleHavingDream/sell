USE sell;

-- 类目
DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category (
    category_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(64) NOT NULL COMMENT '类目名字',
    category_type INT UNSIGNED NOT NULL COMMENT '类目编号',
    gmt_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (category_id)
) COMMENT '类目表';

-- 商品
DROP TABLE IF EXISTS product_info;
CREATE TABLE product_info (
    product_id VARCHAR(32) NOT NULL,
    product_name VARCHAR(64) NOT NULL COMMENT '商品名称',
    product_price DECIMAL(8, 2) UNSIGNED NOT NULL COMMENT '单价',
    product_stock INT UNSIGNED NOT NULL COMMENT '库存',
    product_description VARCHAR(64) COMMENT '描述',
    product_icon VARCHAR(512) COMMENT '小图',
    product_status TINYINT(3) UNSIGNED DEFAULT 0 COMMENT '商品状态，0正常1下架',
    category_type INT UNSIGNED NOT NULL COMMENT '类目编号',
    gmt_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (product_id)
) COMMENT '商品表';

-- 订单
DROP TABLE IF EXISTS order_master;
CREATE TABLE order_master (
    order_id VARCHAR(32) NOT NULL,
    buyer_name VARCHAR(32) NOT NULL COMMENT '买家名字',
    buyer_phone VARCHAR(32) NOT NULL COMMENT '买家电话',
    buyer_address VARCHAR(128) NOT NULL COMMENT '买家地址',
    buyer_openid VARCHAR(64) NOT NULL COMMENT '买家微信openid',
    order_amount DECIMAL(8, 2) UNSIGNED NOT NULL COMMENT '订单总金额',
    order_status TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '订单状态，默认为新下单',
    pay_status TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '支付状态，默认未支付',
    gmt_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (order_id),
    key idx_buyer_openid (buyer_openid)
) COMMENT '订单表';

-- 订单商品
DROP TABLE IF EXISTS order_detail;
CREATE TABLE order_detail (
    detail_id VARCHAR(32) NOT NULL,
    order_id VARCHAR(32) NOT NULL,
    product_id VARCHAR(32) NOT NULL,
    product_name VARCHAR(64) NOT NULL COMMENT '商品名称',
    product_price DECIMAL(8, 2) UNSIGNED NOT NULL COMMENT '当前价格，单位（分）',
    product_quantity INT UNSIGNED NOT NULL COMMENT '数量',
    product_icon VARCHAR(512) COMMENT '小图',
    gmt_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (detail_id),
    KEY IDX_ORDER_ID (order_id)
) COMMENT '订单商品表';

-- 卖家（登录后台使用，卖家登录之后可能直接采用微信扫码登录，不使用账号密码）
DROP TABLE IF EXISTS seller_info;
CREATE TABLE seller_info (
    id VARCHAR(32) NOT NULL,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    openid VARCHAR(64) NOT NULL COMMENT '微信openid',
    gmt_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) COMMENT '卖家信息表';
