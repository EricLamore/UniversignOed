package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class AdminPermissionsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminPermissions.class);
        AdminPermissions adminPermissions1 = new AdminPermissions();
        adminPermissions1.setId("id1");
        AdminPermissions adminPermissions2 = new AdminPermissions();
        adminPermissions2.setId(adminPermissions1.getId());
        assertThat(adminPermissions1).isEqualTo(adminPermissions2);
        adminPermissions2.setId("id2");
        assertThat(adminPermissions1).isNotEqualTo(adminPermissions2);
        adminPermissions1.setId(null);
        assertThat(adminPermissions1).isNotEqualTo(adminPermissions2);
    }
}
