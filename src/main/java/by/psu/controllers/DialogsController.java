package by.psu.controllers;

import by.psu.services.dialogs.interfaces.DialogsService;
import by.psu.services.dialogs.model.DialogMessage;
import by.psu.services.dialogs.model.SendMessageRequest;
import by.psu.services.dialogs.model.UpdateMessageReadStatusRequest;
import by.psu.services.dialogs.model.UpdateMessageReadStatusResponse;
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

    @PostMapping(path = "/dialogs/{dialogId}/messages/is-read")
    public UpdateMessageReadStatusResponse updateMessagesReadStatus(@PathVariable("dialogId") String dialogId,
                                         @RequestBody UpdateMessageReadStatusRequest updateMessageReadStatusRequest) {
        int updatedNumber = dialogsService.updateMessagesReadStatus(
                dialogId,
                updateMessageReadStatusRequest.getExcludeParticipant(),
                updateMessageReadStatusRequest.getStatus());

        return UpdateMessageReadStatusResponse.builder()
                .updatedMessagesNumber(updatedNumber)
                .build();
    }

    @PostMapping(path = "/messages/send")
    public DialogMessage sendMessage(@RequestBody SendMessageRequest request) {
        return dialogsService.sendDialogMessage(request);
    }
}
