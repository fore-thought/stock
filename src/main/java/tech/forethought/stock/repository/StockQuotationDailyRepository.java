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

package tech.forethought.stock.repository;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.wood.BaseMapper;
import org.noear.wood.DbTableQuery;
import org.noear.wood.annotation.Db;
import org.noear.wood.utils.RunUtils;
import tech.forethought.stock.constant.QuotationField;
import tech.forethought.stock.entity.StockQuotationDaily;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StockQuotationDailyRepository {
    @Db
    private BaseMapper<StockQuotationDaily> stockQuotationDailyMapper;

    public void insertList(List<StockQuotationDaily> stockQuotationDailyList) {
        stockQuotationDailyMapper.insertList(stockQuotationDailyList);
    }

    public void upsertById(StockQuotationDaily stockQuotationDaily) {
        stockQuotationDailyMapper.upsert(stockQuotationDaily, true);
    }

    public void updateListById(List<StockQuotationDaily> stockQuotationDailyList) {
        stockQuotationDailyMapper.updateList(stockQuotationDailyList, (stockQuotationDaily, dataItem) -> dataItem.setEntity(stockQuotationDaily), StockQuotationDaily::getId);
    }

    public StockQuotationDaily findById(String id) {
        return stockQuotationDailyMapper.selectById(id);
    }

    public StockQuotationDaily findLatestByCode(String code) {
        return stockQuotationDailyMapper.selectItem(mapper -> mapper.whereEq(StockQuotationDaily::getCode, code)
                .andByDesc(StockQuotationDaily::getId));
    }

    public List<List<Object>> listFields(String code, LocalDate tradeDateStart, LocalDate tradeDateEnd, List<String> fields) {
        return RunUtils.call(() -> {
                    DbTableQuery query = stockQuotationDailyMapper.db().table(stockQuotationDailyMapper.tableName()).whereEq(QuotationField.CODE.columnName(), code);
                    Optional.ofNullable(tradeDateStart).ifPresent(date -> query.andGte(QuotationField.TRADE_DATE.columnName(), date));
                    Optional.ofNullable(tradeDateEnd).ifPresent(date -> query.andLte(QuotationField.TRADE_DATE.columnName(), date));
                    return query.orderByAsc(QuotationField.ID.columnName()).selectMapList(String.join(",", fields));
                }).stream().map(map -> fields.stream().map(map::get)
                        .map(obj -> obj instanceof Date ? obj.toString() : obj).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
