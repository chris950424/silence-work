package com.silence.stock.ui.web;

import com.silence.stock.application.service.StockApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * CategoryController
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@RestController
@RequestMapping("/stock")
public class StockController {


    @Resource
    private StockApplicationService service;

    @PostMapping(value = "/updateStockByProductId")
    public Boolean updateStockByProductId(@RequestParam("productId")Long productId) {
        return service.updateStockByProductId(productId);
    }

}
