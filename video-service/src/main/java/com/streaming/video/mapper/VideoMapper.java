package com.streaming.video.mapper;
import com.streaming.video.dto.VideoRequestDTO;
import com.streaming.video.dto.VideoResponseDTO;
import com.streaming.video.entity.Video;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoResponseDTO toResponseDTO(Video video);
    Video toEntity(VideoRequestDTO dto);
    void updateEntityFromDTO(VideoRequestDTO dto, @MappingTarget Video video);
}