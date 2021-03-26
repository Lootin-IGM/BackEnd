package fr.uge.lootin.back.mappers;

import fr.uge.lootin.back.dto.MessageResponse;
import fr.uge.lootin.back.models.Message;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target="sender", expression = "java(message.getUser().getId())")
    MessageResponse toMessageResponse(Message message);
}
