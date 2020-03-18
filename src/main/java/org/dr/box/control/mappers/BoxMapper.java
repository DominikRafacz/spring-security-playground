package org.dr.box.control.mappers;

import org.dr.box.boundary.vo.BoxVO;
import org.dr.box.entity.BoxEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoxMapper {

    BoxMapper INSTANCE = Mappers.getMapper(BoxMapper.class);

    BoxVO mapEntity(BoxEntity example);
}
