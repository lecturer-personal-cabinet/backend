package by.psu.services.timeline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTimelinePost {
    private String id;
    private String title;
    private String content;
    private Timestamp createdTs;
    private String senderFirstName;
    private String senderLastName;
}
