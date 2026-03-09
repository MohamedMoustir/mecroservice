package com.streaming.user.mapper;
import com.streaming.user.dto.WatchHistoryRequestDTO;
import com.streaming.user.dto.WatchHistoryResponseDTO;
import com.streaming.user.entity.WatchHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface WatchHistoryMapper {
    @Mapping(target = "video", ignore = true)
    WatchHistoryResponseDTO toResponseDTO(WatchHistory wh);
    WatchHistory toEntity(WatchHistoryRequestDTO dto);
}