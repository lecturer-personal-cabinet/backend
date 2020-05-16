package by.psu.services.dialogs.implementations;

import by.psu.database.entities.DialogEntity;
import by.psu.database.entities.DialogMessageEntity;
import by.psu.database.entities.DialogParticipantEntity;
import by.psu.database.repositories.DialogMessageRepository;
import by.psu.database.repositories.DialogParticipantRepository;
import by.psu.database.repositories.DialogRepository;
import by.psu.services.dialogs.PublishRequest;
import by.psu.services.dialogs.interfaces.DialogsService;
import by.psu.services.dialogs.interfaces.PublisherService;
import by.psu.services.dialogs.mappers.DialogsMapper;
import by.psu.services.dialogs.model.*;
import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DialogsServiceImpl implements DialogsService {
    private final DialogsMapper dialogsMapper;
    private final DialogMessageRepository dialogMessageRepository;
    private final DialogParticipantRepository dialogParticipantRepository;
    private final DialogRepository dialogRepository;
    private final UsersService usersService;
    private final PublisherService publisherService;

    public DialogsServiceImpl(DialogsMapper dialogsMapper,
                              DialogMessageRepository dialogMessageRepository,
                              DialogRepository dialogRepository,
                              UsersService usersService,
                              DialogParticipantRepository dialogParticipantRepository,
                              PublisherService publisherService) {
        this.dialogsMapper = dialogsMapper;
        this.dialogMessageRepository = dialogMessageRepository;
        this.dialogRepository = dialogRepository;
        this.usersService = usersService;
        this.dialogParticipantRepository = dialogParticipantRepository;
        this.publisherService = publisherService;
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
                .parallelStream()
                .map(dialogsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public int updateMessagesReadStatus(String dialogId, String excludeParticipant, Boolean status) {
        return dialogMessageRepository.updateStatusInAllMessages(dialogId, status, excludeParticipant);
    }

    @Override
    public DialogMessage sendDialogMessage(SendMessageRequest sendMessageRequest) {
        List<String> participants = List.of(sendMessageRequest.getSenderId(), sendMessageRequest.getReceiverId());
        User sender = usersService.getUserById(sendMessageRequest.getSenderId())
                .orElseThrow();
        User receiver = usersService.getUserById(sendMessageRequest.getReceiverId())
                .orElseThrow();
        String participantsHash = getParticipantsHash(participants);

        Optional<DialogEntity> maybeDialog = dialogRepository.findByParticipantsHash(participantsHash);

        Dialog existingDialog = null;
        if (maybeDialog.isEmpty()) {
            DialogParticipant senderParticipant = DialogParticipant.builder().user(sender).build();
            DialogParticipant receiverParticipant = DialogParticipant.builder().user(receiver).build();
            Dialog dialog = Dialog.builder()
                    .name(UUID.randomUUID().toString())
                    .participants(List.of(senderParticipant, receiverParticipant))
                    .participantsHash(participantsHash)
                    .build();
            existingDialog = this.saveDialog(dialog);
            this.saveDialogParticipants(List.of(senderParticipant, receiverParticipant), existingDialog.getId());
        } else {
            existingDialog = dialogsMapper.toDto(maybeDialog.get());
        }

        DialogMessage dialogMessage = DialogMessage
                .builder()
                .dialogId(existingDialog.getId())
                .content(sendMessageRequest.getContent())
                .createdTs(LocalDateTime.now())
                .sender(sender)
                .isRead(false)
                .build();

        DialogMessage savedMessage = this.saveMessage(dialogMessage);

        DialogMessagePublishRequest dialogMessagePublishRequest = DialogMessagePublishRequest.builder()
                .dialogId(existingDialog.getId())
                .messageId(savedMessage.getId())
                .receiverId(receiver.getId())
                .senderId(sender.getId())
                .build();

        PublishRequest request = PublishRequest
                .builder()
                .data(dialogMessagePublishRequest)
                .eventType("new-message")
                .build();

        this.publisherService.publishMessageRequest(receiver.getId(), request);


        return savedMessage;
    }

    @Override
    public Dialog saveDialog(Dialog dialog) {
        DialogEntity dialogEntity = dialogsMapper.toEntity(dialog);
        return dialogsMapper.toDto(dialogRepository.save(dialogEntity));
    }

    @Override
    public DialogMessage saveMessage(DialogMessage dialogMessage) {
        DialogMessageEntity entity = dialogsMapper.toEntity(dialogMessage);
        return dialogsMapper.toDto(dialogMessageRepository.save(entity));
    }

    @Override
    public DialogParticipant saveDialogParticipant(DialogParticipant dialogParticipant, String dialogId) {
        var entity = dialogsMapper.toEntity(dialogParticipant, dialogId);
        return dialogsMapper.toDto(dialogParticipantRepository.save(entity));
    }

    @Override
    public List<DialogParticipant> saveDialogParticipants(List<DialogParticipant> dialogParticipant, String dialogId) {
        List<DialogParticipantEntity> entities = dialogParticipant
                .parallelStream()
                .map(e -> dialogsMapper.toEntity(e, dialogId))
                .collect(Collectors.toList());

        return dialogParticipantRepository
                .saveAll(entities)
                .parallelStream()
                .map(dialogsMapper::toDto)
                .collect(Collectors.toList());
    }

    private String getParticipantsHash(List<String> participants) {
        List<String> mutableList = new ArrayList<>(participants);
        mutableList.sort(Comparator.comparingInt(String::hashCode));
        String pString = String.join("", mutableList);
        String hash = DigestUtils.md5DigestAsHex(pString.getBytes());
        System.out.println("HASH: " + hash);
        return hash;
    }
}
