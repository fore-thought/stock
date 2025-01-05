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

package tech.forethought.stock.constant;

/**
 * 每日行情字段
 */
public enum QuotationField {
    ID("id"),
    CODE("code"),
    TRADE_DATE("trade_date"),
    // common
    OPENING_PRICE("opening_price"),
    HIGHEST_PRICE("highest_price"),
    LOWEST_PRICE("lowest_price"),
    CLOSING_PRICE("closing_price"),
    TRADING_VOLUME("trading_volume"),
    AMPLITUDE("amplitude"),
    PERCENT_CHANGE("percent_change"),
    PRICE_CHANGE("price_change"),
    // stock & index
    TRANSACTION_AMOUNT("transaction_amount"),
    TURNOVER_RATE("turnover_rate"),
    ;

    private final String columnName;

    QuotationField(String columnName) {
        this.columnName = columnName;
    }

    public String columnName() {
        return this.columnName;
    }
}
