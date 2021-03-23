package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class PropertiesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Properties.class);
        Properties properties1 = new Properties();
        properties1.setId("id1");
        Properties properties2 = new Properties();
        properties2.setId(properties1.getId());
        assertThat(properties1).isEqualTo(properties2);
        properties2.setId("id2");
        assertThat(properties1).isNotEqualTo(properties2);
        properties1.setId(null);
        assertThat(properties1).isNotEqualTo(properties2);
    }
}
