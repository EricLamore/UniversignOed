package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class GroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Group.class);
        Group group1 = new Group();
        group1.setId("id1");
        Group group2 = new Group();
        group2.setId(group1.getId());
        assertThat(group1).isEqualTo(group2);
        group2.setId("id2");
        assertThat(group1).isNotEqualTo(group2);
        group1.setId(null);
        assertThat(group1).isNotEqualTo(group2);
    }
}
