package com.splat_spb.mapper;

public interface Mapper<D, E> {
    D mapToDto(E entity);

    E mapToEntity(D dto);
}
