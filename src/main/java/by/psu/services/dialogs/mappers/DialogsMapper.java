package by.psu.services.dialogs.mappers;

import by.psu.database.entities.DialogEntity;
import by.psu.database.entities.DialogMessageEntity;
import by.psu.database.entities.DialogParticipantEntity;
import by.psu.services.dialogs.model.Dialog;
import by.psu.services.dialogs.model.DialogMessage;
import by.psu.services.dialogs.model.DialogParticipant;
import by.psu.services.users.mappers.UserMapper;
import by.psu.services.users.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DialogsMapper {
    private final UserMapper userMapper;

    public DialogsMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Dialog toDto(DialogEntity entity) {
        return Dialog.builder()
                .id(entity.getId())
                .name(entity.getName())
                .messages(entity.getMessages().parallelStream().map(this::toDto).collect(Collectors.toList()))
                .participants(entity.getParticipants().parallelStream().map(this::toDto).collect(Collectors.toList()))
                .build();
    }

    public DialogMessage toDto(DialogMessageEntity entity) {
        return DialogMessage.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .createdTs(entity.getCreatedTs().toLocalDateTime())
                .dialogId(entity.getDialog().getId())
                .sender(userMapper.toDto(entity.getSender()))
                .build();
    }

    public DialogParticipant toDto(DialogParticipantEntity entity) {
        return DialogParticipant.builder()
                .id(entity.getId())
                .user(userMapper.toDto(entity.getUser()))
                .build();
    }
}
