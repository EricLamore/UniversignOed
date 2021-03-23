package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class MapPropertiesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapProperties.class);
        MapProperties mapProperties1 = new MapProperties();
        mapProperties1.setId("id1");
        MapProperties mapProperties2 = new MapProperties();
        mapProperties2.setId(mapProperties1.getId());
        assertThat(mapProperties1).isEqualTo(mapProperties2);
        mapProperties2.setId("id2");
        assertThat(mapProperties1).isNotEqualTo(mapProperties2);
        mapProperties1.setId(null);
        assertThat(mapProperties1).isNotEqualTo(mapProperties2);
    }
}
