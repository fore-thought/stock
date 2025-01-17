/*
 * Copyright (c) [2024] [Murphy]
 * [Stock] is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

package tech.forethought.stock.entity.stock;

import lombok.Data;
import org.noear.wood.annotation.PrimaryKey;
import org.noear.wood.annotation.Table;

/**
 * 证监会二级行业
 */
@Data
@Table("industry")
public class Industry {
    /** 二级行业代码 */
    @PrimaryKey
    private String code;
    /** 二级行业名称 */
    private String name;
    /** 所属一级行业代码 */
    private String codeParent;
    /** 所属一级行业名称 */
    private String nameParent;
}
