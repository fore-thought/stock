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
import java.util.ArrayList;
import java.util.List;

@Data
public class PageResp<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<T> list;
    private final long total;
    private final int size;
    private final int index;
    private final long pages;

    public PageResp(List<T> list, long total, int size, int index) {
        this.list = null == list ? new ArrayList<>() : list;
        this.total = Math.max(total, 0);
        this.size = size <= 0 ? 10 : size;
        this.index = Math.max(index, 0);
        this.pages = 0L == this.total % this.size ? this.total / this.size : this.total / this.size + 1;
    }
}
