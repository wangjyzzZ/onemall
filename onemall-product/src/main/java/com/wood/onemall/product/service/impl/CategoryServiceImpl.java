package com.wood.onemall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wood.common.utils.PageUtils;
import com.wood.common.utils.Query;
import com.wood.onemall.product.dao.CategoryDao;
import com.wood.onemall.product.entity.CategoryEntity;
import com.wood.onemall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

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

        return entities;
    }

    @Override
    public void removeMenuByIds(List<Long> list) {
        // TODO 1.检查当前删除的菜单，是否被别的地方引用
        baseMapper.deleteBatchIds(list);
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream().filter(categoryEntity -> Objects.equals(categoryEntity.getParentCid(), root.getCatId()))
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

}