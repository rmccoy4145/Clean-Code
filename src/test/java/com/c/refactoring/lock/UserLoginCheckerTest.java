package com.c.refactoring.lock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class UserLoginCheckerTest {
    private static final User USER_1 = new User(
            "TEST_USER_ID");
    private static final User USER_2 = new User(
            "TEST_USER_ID_2");
    private static final int THREE_HOURS = 3 * 60 * 60 * 1000;
    private static final boolean IS_FIRST_SCREEN = true;
    private static final boolean IS_NOT_FIRST_SCREEN = false;
    private static final String LOCK_STATUS_NOT_USED = "NOT_USED";
        
    UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    public void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Object[] access = new Object[] { USER_2.getUserId(), new Date() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, LOCK_STATUS_NOT_USED, IS_FIRST_SCREEN, USER_1, Arrays.asList(new Object[][] { access }));
        assertUserLocked(lock);
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
                Object[] access = new Object[] { USER_1.getUserId(), new Date() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, LOCK_STATUS_NOT_USED, IS_FIRST_SCREEN, USER_1, Arrays.asList(new Object[][] { access }));
        assertUserNotLocked(lock);
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Object[] access = new Object[] { USER_1.getUserId(), new Date() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, LOCK_STATUS_NOT_USED, IS_NOT_FIRST_SCREEN, USER_1, Arrays.asList(new Object[][] { access }));
        assertUserNotLocked(lock);
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Object[] access = new Object[] { USER_1.getUserId(), threeHoursBefore() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, LOCK_STATUS_NOT_USED, IS_FIRST_SCREEN, USER_2, Arrays.asList(new Object[][] { access }));
        assertUserNotLocked(lock);
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Object[] access = new Object[] { USER_1.getUserId(), threeHoursBefore() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, LOCK_STATUS_NOT_USED, IS_NOT_FIRST_SCREEN, USER_2, Arrays.asList(new Object[][] { access }));
        assertUserLocked(lock);
    }


    
    public Date threeHoursBefore() {
        Date now = new Date();
        return new Date(now.getTime() - THREE_HOURS);
    }

    
        private void assertUserLocked(Lock lock)
    {
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    private void assertUserNotLocked(Lock lock)
    {
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }
        
}
