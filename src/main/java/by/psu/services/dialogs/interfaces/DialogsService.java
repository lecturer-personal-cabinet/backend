package by.psu.services.dialogs.interfaces;

import by.psu.services.dialogs.model.Dialog;
import by.psu.services.dialogs.model.DialogMessage;
import by.psu.services.dialogs.model.DialogParticipant;
import by.psu.services.dialogs.model.SendMessageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DialogsService {
    List<Dialog> getAllDialogsByUserId(String userId);
    List<DialogMessage> getAllMessagesByDialogId(String dialogId);
    int updateMessagesReadStatus(String dialogId, String excludeParticipant, Boolean status);
    DialogMessage sendDialogMessage(SendMessageRequest sendMessageRequest);
    Dialog saveDialog(Dialog dialog);
    DialogMessage saveMessage(DialogMessage dialogMessage);
    DialogParticipant saveDialogParticipant(DialogParticipant dialogParticipant, String dialogId);
    List<DialogParticipant> saveDialogParticipants(List<DialogParticipant> dialogParticipant, String dialogId);
}
