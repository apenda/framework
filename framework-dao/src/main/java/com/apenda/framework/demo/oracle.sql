-- 创建用户表
DROP TABLE IF EXISTS USER;
CREATE TABLE USER
(
    ID BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID', -- ORACLE 不一樣
    NAME VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    PHONE VARCHAR(20) NULL DEFAULT NULL COMMENT '手机号',
    ID_CARD VARCHAR(30) NULL DEFAULT NULL COMMENT '身份证',
    BANK_CARD VARCHAR(30) NULL DEFAULT NULL COMMENT '银行卡',
    AGE INT(11) NULL DEFAULT NULL COMMENT '年龄',
    EMAIL VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    LAST_UPDATE_DATE DATETIME DEFAULT NULL COMMENT '最后更新日期',
    CREATE_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期', -- ORACLE 不一樣
    UPDATE_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期', -- ORACLE 不一樣
    PRIMARY KEY (ID) COMMENT '用户表'
);

-- 更改字段日期为自动更新
ALTER TABLE USER MODIFY COLUMN LAST_UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- 插入测试数据
DELETE FROM USER;
INSERT INTO USER (ID, NAME, PHONE, ID_CARD, BANK_CARD, AGE, EMAIL) VALUES
(1, 'Jone_oracle', 13515026323, 600225189912053012, 625208524520391210, 18, 'Jone1@baomidou.com'),
(2, 'Jack_oracle', 15050263254, 440225197912053052, 621208754507397252, 20, 'Jack1@baomidou.com'),
(3, 'Tom_oracle', 13715236326, 520225195912053036, 622908414507352220, 28, 'Tom1@baomidou.com'),
(4, 'Sandy_oracle', 13515026343, 440225197912053096, 623708324507757270, 21, 'Sandy1@baomidou.com'),
(5, 'Billie_oracle', 13715546321, 480225198912053088, 627508394507397245, 24, 'Billie1@baomidou.com'),
(6, 'Jone2_oracle', 13515026323, 600225189912054012, 625208526520393210, 18, 'Jone2@baomidou.com'),
(7, 'Jack2_oracle', 15050263253, 440225197912055052, 621208754567397252, 20, 'Jack2@baomidou.com'),
(8, 'Tom2_oracle', 13715236326, 520225195912056036, 622908414567352220, 28, 'Tom2@baomidou.com'),
(9, 'Sandy2_oracle', 13515026343, 440225197912073096, 623708324607757270, 21, 'Sandy2@baomidou.com'),
(10, 'Billie2_oracle', 13715546321, 480225198912083088, 627508396507397245, 24, 'Billie2@baomidou.com'),
(11, 'Jone3_oracle', 13515026323, 600225189912054012, 625208524540391210, 18, 'Jone3@baomidou.com'),
(12, 'Jack3_oracle', 15050263251, 440225197912053452, 621208754504397252, 20, 'Jack3@baomidou.com'),
(13, 'Tom3_oracle', 13715236326, 520225195912053536, 622908414504352220, 28, 'Tom3@baomidou.com'),
(14, 'Sandy3_oracle', 13515026343, 440225197912053196, 623708324407757270, 21, 'Sandy3@baomidou.com'),
(15, 'Billie3_oracle', 13715546321, 480225198912053688, 627508395507397245, 24, 'Billie3@baomidou.com'),
(16, 'Jone4_oracle', 13515026323, 600225189512053012, 625208524520391410, 18, 'Jone4@baomidou.com'),
(17, 'Jack4_oracle', 15050263252, 440225197962053052, 621208754507397552, 20, 'Jack4@baomidou.com'),
(18, 'Tom4_oracle', 13715236326, 520225195922053036, 622908414507352720, 28, 'Tom4@baomidou.com'),
(19, 'Sandy4_oracle', 13515026343, 440225197712053096, 623708324507758270, 21, 'Sandy4@baomidou.com'),
(20, 'Billie4_oracle', 13715546321, 480225198911053088, 627508394507399245, 24, 'Billie4@baomidou.com');
