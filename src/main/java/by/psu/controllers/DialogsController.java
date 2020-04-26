package by.psu.controllers;

import by.psu.services.dialogs.interfaces.DialogsService;
import by.psu.services.dialogs.model.DialogMessage;
import by.psu.services.dialogs.model.UpdateMessageReadStatusRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DialogsController {
    private final DialogsService dialogsService;

    public DialogsController(DialogsService dialogsService) {
        this.dialogsService = dialogsService;
    }

    @GetMapping(path = "/dialogs/{dialogId}")
    public List<DialogMessage> getAllMessagesByDialogId(@PathVariable("dialogId") String dialogId) {
        return dialogsService.getAllMessagesByDialogId(dialogId);
    }

    @PatchMapping(path = "/dialogs/{dialogId}/messages/is-read")
    public void updateMessagesReadStatus(@PathVariable("dialogId") String dialogId,
                                         @RequestBody UpdateMessageReadStatusRequest updateMessageReadStatusRequest) {
        dialogsService.updateMessagesReadStatus(dialogId, updateMessageReadStatusRequest.getStatus());
    }
}
