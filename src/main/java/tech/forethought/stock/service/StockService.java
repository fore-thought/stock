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

package tech.forethought.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import tech.forethought.stock.entity.Industry;
import tech.forethought.stock.entity.Stock;
import tech.forethought.stock.model.PageReq;
import tech.forethought.stock.model.PageResp;
import tech.forethought.stock.model.StockReq;
import tech.forethought.stock.repository.IndustryRepository;
import tech.forethought.stock.repository.StockRepository;

import java.util.List;

@Component
@Slf4j
public class StockService {
    @Inject
    private StockRepository stockRepository;
    @Inject
    private IndustryRepository industryRepository;

    public List<Industry> industryListAll() {
        return industryRepository.listAll();
    }

    public PageResp<Stock> page(PageReq<StockReq> pageReq) {
        return stockRepository.page(pageReq);
    }
}
