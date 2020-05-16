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

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DialogsMapper {
    private final UserMapper userMapper;

    public DialogsMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Dialog toDto(DialogEntity entity) {
        Dialog dialog = Dialog.builder()
                .id(entity.getId())
                .name(entity.getName())
                .participantsHash(entity.getParticipantsHash())
                .build();

        if(entity.getMessages() != null)
            dialog.setMessages(entity.getMessages().parallelStream().map(this::toDto).collect(Collectors.toList()));

        if(entity.getParticipants() != null)
            dialog.setParticipants(entity.getParticipants().parallelStream().map(DialogParticipantEntity::getId).collect(Collectors.toList()));

        return dialog;
    }

    public DialogEntity toEntity(Dialog dto) {
        DialogEntity entity = new DialogEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setParticipantsHash(dto.getParticipantsHash());
        if(dto.getMessages() != null)
            entity.setMessages(dto.getMessages().parallelStream().map(this::toEntity).collect(Collectors.toSet()));
        return entity;
    }

    public DialogMessage toDto(DialogMessageEntity entity) {
        return DialogMessage.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .createdTs(entity.getCreatedTs().toLocalDateTime())
                .dialogId(entity.getDialog().getId())
                .sender(userMapper.toDto(entity.getSender()))
                .isRead(entity.getIsRead())
                .build();
    }

    public DialogMessageEntity toEntity(DialogMessage dto) {
        DialogEntity d = new DialogEntity();
        d.setId(dto.getDialogId());

        DialogMessageEntity entity = new DialogMessageEntity();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setCreatedTs(Timestamp.valueOf(dto.getCreatedTs()));
        entity.setDialog(d);
        entity.setSender(userMapper.toEntity(dto.getSender()));
        entity.setIsRead(dto.getIsRead());

        return entity;
    }

    public DialogParticipant toDto(DialogParticipantEntity entity) {
        return DialogParticipant.builder()
                .id(entity.getId())
                .user(userMapper.toDto(entity.getUser()))
                .build();
    }

    public DialogParticipantEntity toEntity(DialogParticipant dto, String dialogId) {
        DialogParticipantEntity dialogParticipantEntity = new DialogParticipantEntity();

        dialogParticipantEntity.setId(dto.getId());
        if(dto.getUser() != null) dialogParticipantEntity.setUser(userMapper.toEntity(dto.getUser()));
        if(dialogId != null) {
            DialogEntity dialogEntity = new DialogEntity();
            dialogEntity.setId(dialogId);
            dialogParticipantEntity.setDialog(dialogEntity);
        }

        return dialogParticipantEntity;
    }
}
