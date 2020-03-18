package org.dr.box.boundary;

import lombok.RequiredArgsConstructor;
import org.dr.box.boundary.command.CreateNewBoxCommand;
import org.dr.box.boundary.vo.BoxVO;
import org.dr.box.control.mappers.BoxMapper;
import org.dr.box.control.repository.BoxRepostiory;
import org.dr.box.entity.BoxEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxService {

    @PersistenceContext
    private EntityManager entityManager;
    private final BoxRepostiory boxRepostiory;

    public List<BoxVO> getAllBoxes() {
        return boxRepostiory
                .findAll()
                .stream()
                .map(BoxMapper.INSTANCE::mapEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createBox(@NotNull CreateNewBoxCommand command) {
        BoxEntity entity = BoxEntity.builder()
                .length(command.getLength())
                .width(command.getWidth())
                .height(command.getHeight())
                .build();
        entityManager.persist(entity);
    }
}
