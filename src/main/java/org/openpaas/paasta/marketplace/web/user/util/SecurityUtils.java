package org.openpaas.paasta.marketplace.web.user.util;

import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.api.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public abstract class SecurityUtils {

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("authentication={}", authentication);

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        log.debug("principal={}", principal);

        if (principal == null) {
            return null;
        }

        if (principal instanceof User) {
            return (User) principal;
        }

        return null;
    }

/*    public static String getUserId() {
        User user = getUser();

        if (user == null) {
            return null;
        }

        return user.getId();
    }*/

/*    public static Long getRegionId() {
        User user = getUser();

        if (user == null) {
            return null;
        }

        return user.getRegion();
    }*/

/*    public static void assertCreator(AbstractEntity entity) {
        Assert.notNull(entity, "Entity can't be null.");
        Assert.notNull(entity.getCreatedId(), "Entity's createdId can't be null.");
        Assert.isTrue(entity.getCreatedId().equals(getUserId()),
                "Entity's createdId must be equals to user's id. Entity's createdId is '" + entity.getCreatedId()
                        + "'. But user's id is '" + getUserId() + "'");
    }*/

/*    public static void assertUser(String userId) {
        Assert.notNull(userId, "User's id can't be null.");
        Assert.isTrue(userId.equals(getUserId()), "userId must be equals to user's id. userId is '" + userId
                + "'. But user's id is '" + getUserId() + "'");
    }*/

/*    public static boolean isCreator(AbstractEntity entity) {
        String createdId = entity.getCreatedId();
        if (createdId == null) {
            return false;
        }

        return createdId.equals(getUserId());
    }*/

}
