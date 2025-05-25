package com.wood.onemall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wood.common.utils.PageUtils;
import com.wood.common.utils.Query;
import com.wood.onemall.product.dao.CategoryDao;
import com.wood.onemall.product.entity.CategoryEntity;
import com.wood.onemall.product.service.CategoryBrandRelationService;
import com.wood.onemall.product.service.CategoryService;
import com.wood.onemall.product.vo.Catalog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> entities = baseMapper.selectList(null);

        //组装成父子的树形结构
        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(menu -> {
                    menu.setChildren(getChildrens(menu, entities));
                    return menu;
                }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> list) {
        // TODO 1.检查当前删除的菜单，是否被别的地方引用
        baseMapper.deleteBatchIds(list);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        List<Long> catelogPath = findParentPath(catelogId, path);
        Collections.reverse(catelogPath);
        return catelogPath.toArray(new Long[0]);
    }

    @Override
    @Transactional
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    @Override
    public List<CategoryEntity> getLevelOneCategories() {
        List<CategoryEntity> entities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return entities;
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        // 查出所有的菜单
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        // 所有一级菜单
        List<CategoryEntity> level1Categories = categoryEntities.stream().filter(item -> item.getParentCid() == 0).collect(Collectors.toList());

        Map<String, List<Catalog2Vo>> catelogMap = level1Categories.stream()
                // collectors.toMap 生成map
                .collect(Collectors.toMap(
                        // 一级分类的catId作为map的键
                        level1Category -> level1Category.getCatId().toString(),
                        // 一级分类的所有二级分类作为map的值
                        leve1Category -> {
                            // 先过滤出这个一级分类下的全部二级分类
                            List<Catalog2Vo> catelog2VOList = categoryEntities.stream().filter(category -> category.getParentCid().equals(leve1Category.getCatId()))
                                    // 再讲二级分类实体类 -> Catelog2VO
                                    .map(level2Category -> {
                                        // 设置属性
                                        Catalog2Vo catelog2VO = new Catalog2Vo();
                                        catelog2VO.setCatalog1Id(leve1Category.getCatId().toString());
                                        catelog2VO.setId(level2Category.getCatId().toString());
                                        catelog2VO.setName(level2Category.getName());
                                        // Catelog2VO中有个字段是Catelog3VO
                                        // 先过滤出这个二级分类下的所有分类
                                        List<Catalog2Vo.Catalog3Vo> catelog3VOList = categoryEntities.stream().filter(category -> category.getParentCid().equals(level2Category.getCatId()))
                                                // 再把这些三级分类对象 -> catelog3VO
                                                .map(level3Category -> {
                                                    // 设置属性
                                                    Catalog2Vo.Catalog3Vo catelog3VO = new Catalog2Vo.Catalog3Vo();
                                                    catelog3VO.setCatalog2Id(level2Category.getCatId().toString());
                                                    catelog3VO.setId(level3Category.getCatId().toString());
                                                    catelog3VO.setName(level3Category.getName());
                                                    return catelog3VO;
                                                }).collect(Collectors.toList());
                                        // 填充给catelog2VO对应的字段
                                        catelog2VO.setCatalog3List(catelog3VOList);
                                        return catelog2VO;
                                    }).collect(Collectors.toList());
                            // 返回catelog2VOList，作为map<k,v>的值
                            return catelog2VOList;
                        }));
        return catelogMap;
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        CategoryEntity byId = getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        if (all.stream().noneMatch(categoryEntity -> Objects.equals(categoryEntity.getParentCid(), root.getCatId()))) {
            return null;
        }
        return all.stream().filter(categoryEntity -> Objects.equals(categoryEntity.getParentCid(), root.getCatId()))
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

}