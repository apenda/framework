-- 创建用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    phone VARCHAR(20) NULL DEFAULT NULL COMMENT '手机号',
    id_card VARCHAR(30) NULL DEFAULT NULL COMMENT '身份证',
    bank_card VARCHAR(30) NULL DEFAULT NULL COMMENT '银行卡',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    last_update_date DATETIME DEFAULT NULL COMMENT '最后更新日期',
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    PRIMARY KEY (id) COMMENT '用户表'
);

-- 日期自动更新为自动更新
ALTER TABLE user MODIFY COLUMN last_update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- 插入测试数据
DELETE FROM user;
INSERT INTO user (id, name, phone, id_card, bank_card, age, email) VALUES
(1, 'Jone', 13515026323, 600225189912053012, 625208524520391210, 18, 'Jone1@baomidou.com'),
(2, 'Jack', 15050263254, 440225197912053052, 621208754507397252, 20, 'Jack1@baomidou.com'),
(3, 'Tom', 13715236326, 520225195912053036, 622908414507352220, 28, 'Tom1@baomidou.com'),
(4, 'Sandy', 13515026343, 440225197912053096, 623708324507757270, 21, 'Sandy1@baomidou.com'),
(5, 'Billie', 13715546321, 480225198912053088, 627508394507397245, 24, 'Billie1@baomidou.com'),
(6, 'Jone2', 13515026323, 600225189912054012, 625208526520393210, 18, 'Jone2@baomidou.com'),
(7, 'Jack2', 15050263253, 440225197912055052, 621208754567397252, 20, 'Jack2@baomidou.com'),
(8, 'Tom2', 13715236326, 520225195912056036, 622908414567352220, 28, 'Tom2@baomidou.com'),
(9, 'Sandy2', 13515026343, 440225197912073096, 623708324607757270, 21, 'Sandy2@baomidou.com'),
(10, 'Billie2', 13715546321, 480225198912083088, 627508396507397245, 24, 'Billie2@baomidou.com'),
(11, 'Jone3', 13515026323, 600225189912054012, 625208524540391210, 18, 'Jone3@baomidou.com'),
(12, 'Jack3', 15050263251, 440225197912053452, 621208754504397252, 20, 'Jack3@baomidou.com'),
(13, 'Tom3', 13715236326, 520225195912053536, 622908414504352220, 28, 'Tom3@baomidou.com'),
(14, 'Sandy3', 13515026343, 440225197912053196, 623708324407757270, 21, 'Sandy3@baomidou.com'),
(15, 'Billie3', 13715546321, 480225198912053688, 627508395507397245, 24, 'Billie3@baomidou.com'),
(16, 'Jone4', 13515026323, 600225189512053012, 625208524520391410, 18, 'Jone4@baomidou.com'),
(17, 'Jack4', 15050263252, 440225197962053052, 621208754507397552, 20, 'Jack4@baomidou.com'),
(18, 'Tom4', 13715236326, 520225195922053036, 622908414507352720, 28, 'Tom4@baomidou.com'),
(19, 'Sandy4', 13515026343, 440225197712053096, 623708324507758270, 21, 'Sandy4@baomidou.com'),
(20, 'Billie4', 13715546321, 480225198911053088, 627508394507399245, 24, 'Billie4@baomidou.com');
