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

package tech.forethought.stock.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.*;
import tech.forethought.stock.entity.stock.Stock;
import tech.forethought.stock.model.*;
import tech.forethought.stock.page.util.StockUtils;
import tech.forethought.stock.service.StockQuotationDailyService;
import tech.forethought.stock.service.StockService;

import java.util.List;

@Slf4j
@Controller
@Mapping("/api/stock")
public class StockApiController {
    @Inject
    private StockService stockService;
    @Inject
    private StockQuotationDailyService stockQuotationDailyService;

    @Get
    @Mapping("/job/daily")
    public void execJobDaily() {
        stockQuotationDailyService.jobDaily();
    }

    @Get
    @Mapping("/industry/sync")
    public void syncIndustry() {
        stockQuotationDailyService.syncIndustry();
    }

    @Get
    @Mapping("/industry/list-tree")
    public List<IndustryResp> listIndustryTree() {
        return StockUtils.convertTreeList(stockService.industryListAll());
    }

    @Post
    @Mapping("/quotation/list-fields")
    public List<List<Object>> listQuotationFields(@Body QuotationFieldsReq req) {
        return stockQuotationDailyService.listFields(req.getCode(), req.getTradeDateStart(), req.getTradeDateEnd(), req.getFields());
    }

    @Post
    @Mapping("/page")
    public PageResp<StockResp> page(@Body PageReq<StockReq> req) {
        PageResp<Stock> page = stockService.page(req);
        return new PageResp<>(page.getList().parallelStream().map(stock ->
                StockUtils.convert(stock, stockQuotationDailyService.findLatest(stock.getCode()))).toList(),
                page.getTotal(), page.getSize(), page.getIndex());
    }
}
