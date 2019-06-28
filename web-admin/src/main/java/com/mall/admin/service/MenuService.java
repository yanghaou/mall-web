package com.mall.admin.service;

import com.mall.common.entity.Menu;
import com.mall.common.util.Result;

public interface MenuService {

    Result save(Menu menu);

    Result query(Menu menu);

    Result getTree();
}
