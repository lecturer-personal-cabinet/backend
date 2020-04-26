package by.psu.services.dialogs.implementations;

import by.psu.database.entities.DialogParticipantEntity;
import by.psu.database.repositories.DialogMessageRepository;
import by.psu.database.repositories.DialogParticipantRepository;
import by.psu.database.repositories.DialogRepository;
import by.psu.services.dialogs.interfaces.DialogsService;
import by.psu.services.dialogs.mappers.DialogsMapper;
import by.psu.services.dialogs.model.Dialog;
import by.psu.services.dialogs.model.DialogMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DialogsServiceImpl implements DialogsService {
    private final DialogsMapper dialogsMapper;
    private final DialogParticipantRepository dialogParticipantRepository;
    private final DialogMessageRepository dialogMessageRepository;

    public DialogsServiceImpl(DialogsMapper dialogsMapper,
                              DialogParticipantRepository dialogParticipantRepository,
                              DialogMessageRepository dialogMessageRepository) {
        this.dialogsMapper = dialogsMapper;
        this.dialogParticipantRepository = dialogParticipantRepository;
        this.dialogMessageRepository = dialogMessageRepository;
    }

    @Override
    public List<Dialog> getAllDialogsByUserId(String userId) {
        return dialogParticipantRepository
                .getAllByUserId(userId)
                .stream()
                .map(DialogParticipantEntity::getDialog)
                .map(dialogsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DialogMessage> getAllMessagesByDialogId(String dialogId) {
        return dialogMessageRepository
                .getAllByDialogId(dialogId)
                .stream()
                .map(dialogsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateMessagesReadStatus(String dialogId, String excludeParticipant, Boolean status) {
        dialogMessageRepository.updateStatusInAllMessages(dialogId, status, excludeParticipant);
    }
}
