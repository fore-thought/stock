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

package tech.forethought.stock.api.model;

import lombok.Data;
import org.noear.snack.annotation.ONodeAttr;
import org.noear.snack.core.utils.DateUtil;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.Size;
import tech.forethought.stock.constant.QuotationField;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 每日行情字段集合请求体
 */
@Data
public class QuotationFieldsReqVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 代码 */
    @NotBlank(message = "代码不能为空！")
    private String code;

    /** 交易时间开始 */
    @ONodeAttr(format = DateUtil.FORMAT_10_a, timezone = "UTC+8")
    private LocalDate tradeDateStart;
    /** 交易时间结束 */
    @ONodeAttr(format = DateUtil.FORMAT_10_a, timezone = "UTC+8")
    private LocalDate tradeDateEnd;

    /** 每日行情字段集合 */
    @Size(min = 1)
    private List<QuotationField> fields;
}
