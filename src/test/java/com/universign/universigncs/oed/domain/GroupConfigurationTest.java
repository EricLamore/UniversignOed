package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class GroupConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupConfiguration.class);
        GroupConfiguration groupConfiguration1 = new GroupConfiguration();
        groupConfiguration1.setId("id1");
        GroupConfiguration groupConfiguration2 = new GroupConfiguration();
        groupConfiguration2.setId(groupConfiguration1.getId());
        assertThat(groupConfiguration1).isEqualTo(groupConfiguration2);
        groupConfiguration2.setId("id2");
        assertThat(groupConfiguration1).isNotEqualTo(groupConfiguration2);
        groupConfiguration1.setId(null);
        assertThat(groupConfiguration1).isNotEqualTo(groupConfiguration2);
    }
}
