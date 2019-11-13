package org.openpaas.paasta.marketplace.web.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openpaas.paasta.marketplace.web.user.service.InstanceServiceTest;
import org.openpaas.paasta.marketplace.web.user.service.PriceServiceTest;
import org.openpaas.paasta.marketplace.web.user.service.ProfileServiceTest;
import org.openpaas.paasta.marketplace.web.user.service.SoftwareServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
        // @formatter:off
        InstanceServiceTest.class,
        PriceServiceTest.class,
        ProfileServiceTest.class,
        SoftwareServiceTest.class,
        // @formatter:on
})
public class ServiceTests {

}
