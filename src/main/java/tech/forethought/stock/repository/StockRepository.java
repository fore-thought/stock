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

import lombok.SneakyThrows;
import org.noear.solon.annotation.Component;
import org.noear.wood.BaseMapper;
import org.noear.wood.IPage;
import org.noear.wood.WhereBase;
import org.noear.wood.annotation.Db;
import tech.forethought.stock.entity.stock.Stock;
import tech.forethought.stock.model.PageReq;
import tech.forethought.stock.model.PageResp;
import tech.forethought.stock.model.StockReq;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StockRepository {
    @Db
    private BaseMapper<Stock> stockMapper;

    public void insertList(List<Stock> stockList) {
        stockMapper.insertList(stockList);
    }

    public void refreshSyncTime(Stock stock) {
        stock.setName(null);
        stock.setExchange(null);
        stock.setIndustryCode(null);
        stock.setIndustryName(null);
        stock.setQuotationDailySyncTime(LocalDateTime.now());
        stockMapper.updateById(stock, true);
    }

    public void updateListById(List<Stock> stockList) {
        stockMapper.updateList(stockList, (stock, dataItem) -> dataItem.setEntity(stock), Stock::getCode);
    }

    public Set<String> listAllCode() {
        return stockMapper.selectArray("code", WhereBase::whereTrue)
                .stream().filter(Objects::nonNull).map(String::valueOf).collect(Collectors.toSet());
    }

    public List<Stock> listAll() {
        return stockMapper.selectList(mapperWhereQ -> mapperWhereQ.whereNeq(Stock::getIndustryCode, null).orderByAsc(Stock::getCode));
    }

    public long countAll() {
        return stockMapper.selectCount(mapperWhereQ -> mapperWhereQ.whereNeq(Stock::getIndustryCode, null).orderByAsc(Stock::getCode));
    }

    public List<Stock> listRange(int start, int size) {
        return stockMapper.selectList(start, size, mapperWhereQ -> mapperWhereQ.whereNeq(Stock::getIndustryCode, null).orderByAsc(Stock::getCode));
    }

    @SneakyThrows
    public PageResp<Stock> page(PageReq<StockReq> pageReq) {
        StockReq req = pageReq.getCondition();
        IPage<Stock> page = stockMapper.selectPage(pageReq.getIndex() * pageReq.getSize(), pageReq.getSize(), mapper -> {
            mapper.whereNeq(Stock::getIndustryCode, null);
            Optional.ofNullable(req.getNameLike()).filter(n -> !n.isBlank()).ifPresent(n -> mapper.andLk(Stock::getName, "%" + n + "%"));
            Optional.ofNullable(req.getExchange()).filter(e -> !e.isBlank()).ifPresent(e -> mapper.andEq(Stock::getExchange, e));
            Optional.ofNullable(req.getIndustryCode()).filter(c -> !c.isBlank()).ifPresent(c -> mapper.andEq(Stock::getIndustryCode, c));
            Optional.ofNullable(req.getIndustryCodeParent()).filter(c -> !c.isBlank()).ifPresent(c -> mapper.andLk(Stock::getIndustryCode, c + "%"));
            mapper.orderByAsc(Stock::getCode);
        });
        return new PageResp<>(page.getList(), page.getTotal(), page.getSize(), pageReq.getIndex());
    }

}
