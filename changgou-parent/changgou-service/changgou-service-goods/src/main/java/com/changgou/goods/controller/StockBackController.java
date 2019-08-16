package com.changgou.goods.controller;

import com.changgou.goods.pojo.StockBack;
import com.changgou.goods.service.StockBackService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/stockBack")
@CrossOrigin
public class StockBackController {

    @Autowired
    private StockBackService stockBackService;

    /***
     * StockBack分页条件搜索实现
     * @param stockBack
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  StockBack stockBack, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<StockBack> pageInfo = stockBackService.findPage(stockBack, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * StockBack分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<StockBack> pageInfo = stockBackService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param stockBack
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<StockBack>> findList(@RequestBody(required = false)  StockBack stockBack){
        List<StockBack> list = stockBackService.findList(stockBack);
        return new Result<List<StockBack>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        stockBackService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改StockBack数据
     * @param stockBack
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  StockBack stockBack,@PathVariable String id){
        //设置主键值
        stockBack.setSkuId(id);
        //修改数据
        stockBackService.update(stockBack);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增StockBack数据
     * @param stockBack
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   StockBack stockBack){
        stockBackService.add(stockBack);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询StockBack数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<StockBack> findById(@PathVariable String id){
        //根据ID查询
        StockBack stockBack = stockBackService.findById(id);
        return new Result<StockBack>(true,StatusCode.OK,"查询成功",stockBack);
    }

    /***
     * 查询StockBack全部数据
     * @return
     */
    @GetMapping
    public Result<StockBack> findAll(){
        List<StockBack> list = stockBackService.findAll();
        return new Result<StockBack>(true, StatusCode.OK,"查询成功",list) ;
    }
}
