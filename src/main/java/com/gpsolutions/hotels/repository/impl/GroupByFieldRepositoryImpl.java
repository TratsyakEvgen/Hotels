package com.gpsolutions.hotels.repository.impl;

import com.gpsolutions.hotels.dto.GroupedHotels;
import com.gpsolutions.hotels.entity.Hotel;
import com.gpsolutions.hotels.enums.GroupingFieldHotel;
import com.gpsolutions.hotels.repository.GroupByFieldRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class GroupByFieldRepositoryImpl implements GroupByFieldRepository {
    private final EntityManager entityManager;
    private final Map<GroupingFieldHotel, Function<Root<Hotel>, Path<?>>> pathSupplierMap = Map.of(
            GroupingFieldHotel.AMENITIES, root -> root.get("amenities").get("name")
    );

    @Override
    public List<GroupedHotels> groupByField(GroupingFieldHotel groupingFieldHotel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupedHotels> query = criteriaBuilder.createQuery(GroupedHotels.class);
        Root<Hotel> root = query.from(Hotel.class);
        Path<?> path = getPath(groupingFieldHotel, root);

        query.multiselect(path.alias("name"), criteriaBuilder.count(root).alias("count"))
                .groupBy(path);

        return entityManager.createQuery(query).getResultList();
    }

    private Path<?> getPath(GroupingFieldHotel groupingFieldHotel, Root<Hotel> root) {
        return Optional.ofNullable(pathSupplierMap.get(groupingFieldHotel))
                .map(rootPathFunction -> rootPathFunction.apply(root))
                .orElseGet(() -> root.get(groupingFieldHotel.name().toLowerCase()));
    }
}
