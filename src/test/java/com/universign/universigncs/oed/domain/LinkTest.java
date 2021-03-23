package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class LinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Link.class);
        Link link1 = new Link();
        link1.setId("id1");
        Link link2 = new Link();
        link2.setId(link1.getId());
        assertThat(link1).isEqualTo(link2);
        link2.setId("id2");
        assertThat(link1).isNotEqualTo(link2);
        link1.setId(null);
        assertThat(link1).isNotEqualTo(link2);
    }
}
