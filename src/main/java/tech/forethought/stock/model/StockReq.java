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

package tech.forethought.stock.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class StockReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 名称（模糊） */
    private String nameLike;
    /** 交易所 */
    private String exchange;
    /** 证监会一级行业代码 */
    private String industryCodeParent;
    /** 证监会二级行业代码 */
    private String industryCode;
}