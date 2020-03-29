package by.psu.services.users.model;

import javax.persistence.Embeddable;

@Embeddable
public enum UserType {
    ADMIN, USER, PENDING_ADMIN, UNKNOWN
}
