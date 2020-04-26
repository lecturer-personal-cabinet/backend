package by.psu.services.dialogs.interfaces;

import by.psu.services.dialogs.model.Dialog;
import by.psu.services.dialogs.model.DialogMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DialogsService {
    List<Dialog> getAllDialogsByUserId(String userId);
    List<DialogMessage> getAllMessagesByDialogId(String dialogId);
    void updateMessagesReadStatus(String dialogId, List<String> senders, Boolean status);
}
