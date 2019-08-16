package com.changgou.goods.controller;

import com.changgou.goods.pojo.Pref;
import com.changgou.goods.service.PrefService;
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
@RequestMapping("/pref")
@CrossOrigin
public class PrefController {

    @Autowired
    private PrefService prefService;

    /***
     * Pref分页条件搜索实现
     * @param pref
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Pref pref, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Pref> pageInfo = prefService.findPage(pref, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Pref分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<Pref> pageInfo = prefService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param pref
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Pref>> findList(@RequestBody(required = false)  Pref pref){
        List<Pref> list = prefService.findList(pref);
        return new Result<List<Pref>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        prefService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Pref数据
     * @param pref
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Pref pref,@PathVariable Integer id){
        //设置主键值
        pref.setId(id);
        //修改数据
        prefService.update(pref);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Pref数据
     * @param pref
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Pref pref){
        prefService.add(pref);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Pref数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Pref> findById(@PathVariable Integer id){
        //根据ID查询
        Pref pref = prefService.findById(id);
        return new Result<Pref>(true,StatusCode.OK,"查询成功",pref);
    }

    /***
     * 查询Pref全部数据
     * @return
     */
    @GetMapping
    public Result<Pref> findAll(){
        List<Pref> list = prefService.findAll();
        return new Result<Pref>(true, StatusCode.OK,"查询成功",list) ;
    }
}
