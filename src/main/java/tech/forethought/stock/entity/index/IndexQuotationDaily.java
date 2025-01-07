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

package tech.forethought.stock.entity.index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.wood.annotation.Table;
import tech.forethought.stock.entity.QuotationDaily;

import java.math.BigDecimal;

/**
 * 指数每日行情
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("index_quotation_daily")
public class IndexQuotationDaily extends QuotationDaily {
    /** 成交额（元） */
    private BigDecimal transactionAmount;
    /** 换手率（%） */
    private BigDecimal turnoverRate;
}
