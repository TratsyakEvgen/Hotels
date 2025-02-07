package com.gpsolutions.hotels.service.impl;

import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.entity.Hotel;
import com.gpsolutions.hotels.service.HotelSpecificationBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Component
public class DefaultHotelSpecificationBuilder implements HotelSpecificationBuilder {
    private final Set<Function<SearchFilter, Specification<Hotel>>> supplierSpecificationSet = Set.of(
            filter -> createSpecification(filter.getName(), root -> root.get("name")),
            filter -> createSpecification(filter.getBrand(), root -> root.get("brand")),
            filter -> createSpecification(filter.getCity(), root -> root.get("city")),
            filter -> createSpecification(filter.getCounty(), root -> root.get("county")),
            filter -> createSpecification(filter.getAmenities(), root -> root.join("amenities").get("name")));

    @Override
    public Specification<Hotel> build(SearchFilter searchFilter) {
        return supplierSpecificationSet.stream()
                .map(supplier -> supplier.apply(searchFilter))
                .reduce(Specification::and)
                .orElse((root, query, cb) -> cb.conjunction());
    }

    private Specification<Hotel> createSpecification(List<String> filter, Function<Root<Hotel>, Path<String>> extractor) {
        return (root, query, criteriaBuilder) -> filter == null || filter.isEmpty()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.lower(extractor.apply(root)).in(toLowerCase(filter));
    }

    private List<String> toLowerCase(List<String> strings) {
        return strings.stream()
                .map(String::toLowerCase)
                .toList();
    }
}
