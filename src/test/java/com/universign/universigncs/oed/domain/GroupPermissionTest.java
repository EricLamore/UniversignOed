package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class GroupPermissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupPermission.class);
        GroupPermission groupPermission1 = new GroupPermission();
        groupPermission1.setId("id1");
        GroupPermission groupPermission2 = new GroupPermission();
        groupPermission2.setId(groupPermission1.getId());
        assertThat(groupPermission1).isEqualTo(groupPermission2);
        groupPermission2.setId("id2");
        assertThat(groupPermission1).isNotEqualTo(groupPermission2);
        groupPermission1.setId(null);
        assertThat(groupPermission1).isNotEqualTo(groupPermission2);
    }
}
