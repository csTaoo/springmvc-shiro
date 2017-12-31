package com.shitao.sys.dao;

import java.util.List;

import com.shitao.common.persistence.dao.BaseDao;
import com.shitao.sys.entity.Carousel;

public interface CarouselDao extends BaseDao<Carousel>{
	
	List<Carousel> listToshow();
	
}
