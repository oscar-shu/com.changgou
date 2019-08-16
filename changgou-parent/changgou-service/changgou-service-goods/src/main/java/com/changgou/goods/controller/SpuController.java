package com.changgou.goods.controller;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    /**
     * 商品还原
     * @param id
     * @return
     */
    @GetMapping("/restore/{id}")
    public Result restore(@PathVariable(value = "id") Long id) {
        spuService.restore(id);
        return new Result(true, StatusCode.OK, "商品还原");
    }

    /**
     * 商品删除
     * @param id
     * @return
     */
    @GetMapping("/logicDelete/{id}")
    public Result logicDelete(@PathVariable(value = "id") Long id) {
        spuService.logicDelete(id);
        return new Result(true, StatusCode.OK, "商品删除");
    }

    /**
     * 批量上/下架
     * @param isMarketable
     * @param ids
     * @return
     */
    @PostMapping("/isShows/{ids}/{isMarketable}")
    public Result isShows(@PathVariable(value = "isMarketable") String isMarketable,@PathVariable(value = "ids") Long[] ids) {
        spuService.isShows(ids,isMarketable);
        return new Result(true, StatusCode.OK, "商品批量上/下架成功");
    }
    /**
     * 商品上/下架
     * @param id
     * @param isMarketable
     * @return
     */
    @GetMapping("/isShow/{id}/{isMarketable}")
    public Result isShow(@PathVariable(value = "id") Long id,@PathVariable(value = "isMarketable") String isMarketable) {
        spuService.isShow(id,isMarketable);
        return new Result(true, StatusCode.OK, "商品上/下架成功");
    }

    /**
     * 商品审核
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/audit/{id}/{status}")
    public Result audit(@PathVariable(value = "id") Long id,@PathVariable(value = "status") String status) {
        spuService.audit(id,status);
        return new Result(true, StatusCode.OK, "商品上/下架成功");
    }

    /**
     * 修改商品
     * @param goods
     * @return
     */
    @PostMapping("/updateGoods")
    public Result updateGoods(@RequestBody Goods goods) {
        spuService.updateGoods(goods);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 保存商品
     * @param goods
     * @return
     */
    @PostMapping("/saveGoods")
    public Result saveGoods(@RequestBody Goods goods) {
        spuService.saveGoods(goods);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    /***
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Spu spu, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Spu> pageInfo = spuService.findPage(spu, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<Spu> pageInfo = spuService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Spu>> findList(@RequestBody(required = false)  Spu spu){
        List<Spu> list = spuService.findList(spu);
        return new Result<List<Spu>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        spuService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Spu spu,@PathVariable Long id){
        //设置主键值
        spu.setId(id);
        //修改数据
        spuService.update(spu);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Spu数据
     * @param spu
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Spu spu){
        spuService.add(spu);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable Long id){
        //根据ID查询
        Spu spu = spuService.findById(id);
        return new Result<Spu>(true,StatusCode.OK,"查询成功",spu);
    }

    /***
     * 查询Spu全部数据
     * @return
     */
    @GetMapping
    public Result<Spu> findAll(){
        List<Spu> list = spuService.findAll();
        return new Result<Spu>(true, StatusCode.OK,"查询成功",list) ;
    }
}
