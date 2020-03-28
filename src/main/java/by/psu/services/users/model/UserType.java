package by.psu.services.users.model;

import javax.persistence.Embeddable;

@Embeddable
public enum UserType {
    LECTURER, FULL_TIME_STUDENT, UNKNOWN
}
