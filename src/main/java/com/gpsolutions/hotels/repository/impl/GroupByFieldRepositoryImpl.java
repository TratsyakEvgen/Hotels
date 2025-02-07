package com.gpsolutions.hotels.repository.impl;

import com.gpsolutions.hotels.enums.GroupField;
import com.gpsolutions.hotels.model.GroupHotels;
import com.gpsolutions.hotels.model.entity.Hotel;
import com.gpsolutions.hotels.repository.GroupByFieldRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DefaultGroupByFieldRepository implements GroupByFieldRepository {
    private final EntityManager entityManager;

    @Override
    public List<GroupHotels> fgfd(GroupField groupField) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupHotels> query = cb.createQuery(GroupHotels.class);
        Root<Hotel> root = query.from(Hotel.class);

        String field = groupField.name().toLowerCase();
        Path<Object> path = root.get(field);
        query.multiselect(
                path.alias("name"),
                cb.count(root).alias("count")
        ).groupBy(path);

        return entityManager.createQuery(query).getResultList();
    }
}
