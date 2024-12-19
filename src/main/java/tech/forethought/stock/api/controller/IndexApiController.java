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
import tech.forethought.stock.api.model.QuotationFieldsReqVO;
import tech.forethought.stock.service.IndexQuotationDailyService;

import java.util.List;

@Slf4j
@Controller
@Mapping("/api/index")
public class IndexApiController {
    @Inject
    private IndexQuotationDailyService indexQuotationDailyService;

    @Get
    @Mapping("/job/daily")
    public void execJobDaily() {
        indexQuotationDailyService.jobDaily();
    }

    @Post
    @Mapping("/quotation/list-fields")
    public List<List<Object>> listQuotationFields(@Body QuotationFieldsReqVO reqVO) {
        return indexQuotationDailyService.listFields(reqVO.getCode(), reqVO.getTradeDateStart(), reqVO.getTradeDateEnd(), reqVO.getFields());
    }
}
