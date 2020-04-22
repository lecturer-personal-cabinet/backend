package by.psu.services.dialogs.mappers;

import by.psu.database.entities.DialogEntity;
import by.psu.database.entities.DialogMessageEntity;
import by.psu.services.dialogs.model.Dialog;
import by.psu.services.dialogs.model.DialogMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface DialogsMapper {
    Dialog toDto(DialogEntity entity);
    DialogEntity toEntity(Dialog dialog);

    @Mapping(source = "dialog.id", target = "dialogId")
    DialogMessage toDto(DialogMessageEntity entity);
    @Mapping(target = "dialog.id", source = "dialogId")
    DialogMessageEntity toEntity(DialogMessage dto);

    default Timestamp map(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    default LocalDateTime map(Timestamp ts) {
        return ts == null ? null : ts.toLocalDateTime();
    }

}
