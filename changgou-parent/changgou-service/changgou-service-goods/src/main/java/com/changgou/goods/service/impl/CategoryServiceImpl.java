package com.changgou.goods.service.impl;

import com.changgou.goods.dao.*;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Category业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;
    @Autowired(required = false)
    private BrandMapper brandMapper;
    @Autowired(required = false)
    private TemplateMapper templateMapper;
    @Autowired(required = false)
    private SpecMapper specMapper;
    @Autowired(required = false)
    private ParaMapper paraMapper;

    /**
     * 根据分类id获取整个页面需要的所有显示的数据
     * @param categoryId
     * @return
     */
    @Override
    public Map<String, Object> getAllObject(Integer categoryId) {
        // 创建map集合，用于封装数据
        Map<String,Object> map = new HashMap<>();
        // 通过categoryId获得category对象
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        // 通过categoryId获得brands对象
        List<Brand> brands = brandMapper.findBrandsByCategoryId(categoryId);
        // 封装品牌数据
        map.put("brands",brands);
        Integer templateId = category.getTemplateId();
        Template template = templateMapper.selectByPrimaryKey(templateId);
        // 封装模板数据
        map.put("template",template);
        // 封装规格数据
        Spec spec = specMapper.selectByPrimaryKey(templateId);
        List<Spec> specs = specMapper.select(spec);
        map.put("specs",specs);
        // 封装参数数据
        Para para = paraMapper.selectByPrimaryKey(templateId);
        List<Para> paras = paraMapper.select(para);
        map.put("paras",paras);
        return map;
    }

    /**
     * 获取商品的分类列表
     * @param parentId
     * @return
     */
    @Override
    public List<Category> findListByParentId(Integer parentId) {
        // 我们需要把id给封装到对应的pojo中去
        Category category = new Category();
        category.setParentId(parentId);
        List<Category> list = categoryMapper.select(category);
        return list;
    }

    /**
     * Category条件+分页查询
     * @param category 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Category> findPage(Category category, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(category);
        //执行搜索
        return new PageInfo<Category>(categoryMapper.selectByExample(example));
    }

    /**
     * Category分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Category> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Category>(categoryMapper.selectAll());
    }

    /**
     * Category条件查询
     * @param category
     * @return
     */
    @Override
    public List<Category> findList(Category category){
        //构建查询条件
        Example example = createExample(category);
        //根据构建的条件查询数据
        return categoryMapper.selectByExample(example);
    }


    /**
     * Category构建查询对象
     * @param category
     * @return
     */
    public Example createExample(Category category){
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if(category!=null){
            // 分类ID
            if(!StringUtils.isEmpty(category.getId())){
                    criteria.andEqualTo("id",category.getId());
            }
            // 分类名称
            if(!StringUtils.isEmpty(category.getName())){
                    criteria.andLike("name","%"+category.getName()+"%");
            }
            // 商品数量
            if(!StringUtils.isEmpty(category.getGoodsNum())){
                    criteria.andEqualTo("goodsNum",category.getGoodsNum());
            }
            // 是否显示
            if(!StringUtils.isEmpty(category.getIsShow())){
                    criteria.andEqualTo("isShow",category.getIsShow());
            }
            // 是否导航
            if(!StringUtils.isEmpty(category.getIsMenu())){
                    criteria.andEqualTo("isMenu",category.getIsMenu());
            }
            // 排序
            if(!StringUtils.isEmpty(category.getSeq())){
                    criteria.andEqualTo("seq",category.getSeq());
            }
            // 上级ID
            if(!StringUtils.isEmpty(category.getParentId())){
                    criteria.andEqualTo("parentId",category.getParentId());
            }
            // 模板ID
            if(!StringUtils.isEmpty(category.getTemplateId())){
                    criteria.andEqualTo("templateId",category.getTemplateId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Category
     * @param category
     */
    @Override
    public void update(Category category){
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 增加Category
     * @param category
     */
    @Override
    public void add(Category category){
        categoryMapper.insert(category);
    }

    /**
     * 根据ID查询Category
     * @param id
     * @return
     */
    @Override
    public Category findById(Integer id){
        return  categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Category全部数据
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }
}
