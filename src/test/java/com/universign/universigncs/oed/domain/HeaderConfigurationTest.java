package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class HeaderConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HeaderConfiguration.class);
        HeaderConfiguration headerConfiguration1 = new HeaderConfiguration();
        headerConfiguration1.setId("id1");
        HeaderConfiguration headerConfiguration2 = new HeaderConfiguration();
        headerConfiguration2.setId(headerConfiguration1.getId());
        assertThat(headerConfiguration1).isEqualTo(headerConfiguration2);
        headerConfiguration2.setId("id2");
        assertThat(headerConfiguration1).isNotEqualTo(headerConfiguration2);
        headerConfiguration1.setId(null);
        assertThat(headerConfiguration1).isNotEqualTo(headerConfiguration2);
    }
}
