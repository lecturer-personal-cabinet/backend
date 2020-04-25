package by.psu.controllers;

import by.psu.services.dialogs.interfaces.DialogsService;
import by.psu.services.dialogs.model.DialogMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
