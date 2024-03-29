package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SpuService {
    /**
     * 商品还原
     * @param id
     */
    void restore(Long id);
    /**
     * 逻辑删除
     * @param id
     */
    void logicDelete(Long id);
    /**
     * 批量上/下架
     * @param ids
     * @param isMarketable
     */
    void isShows(Long[] ids,String isMarketable);
    /**
     * 商品上/下架
     * @param id
     * @param isMarketable
     */
    void isShow(Long id,String isMarketable);
    /**
     * 商品审核
     * @param id
     * @param status
     */
    void audit(Long id,String status);
    /**
     * 商品修改
     * @param goods
     */
    void updateGoods(Goods goods);
    /**
     * 商品保存
     * @param goods
     */
    void saveGoods(Goods goods);

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();
}
