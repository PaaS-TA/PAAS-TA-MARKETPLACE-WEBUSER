package org.openpaas.paasta.marketplace.web.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openpaas.paasta.marketplace.web.user.service.ProfileServiceTest;
import org.openpaas.paasta.marketplace.web.user.service.SoftwareServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
        // @formatter:off
        ProfileServiceTest.class,
        SoftwareServiceTest.class,
        // @formatter:on
})
public class ServiceTests {

}
