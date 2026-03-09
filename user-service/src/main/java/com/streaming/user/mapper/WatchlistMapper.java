package com.streaming.user.mapper;
import com.streaming.user.dto.WatchlistRequestDTO;
import com.streaming.user.dto.WatchlistResponseDTO;
import com.streaming.user.entity.Watchlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface WatchlistMapper {
    @Mapping(target = "video", ignore = true)
    WatchlistResponseDTO toResponseDTO(Watchlist watchlist);
    Watchlist toEntity(WatchlistRequestDTO dto);
}