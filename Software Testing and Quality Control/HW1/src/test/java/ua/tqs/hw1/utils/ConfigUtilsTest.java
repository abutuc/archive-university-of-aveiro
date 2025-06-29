package ua.tqs.hw1.utils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigUtilsTest {
    @Test
    void givenProperty_returnCorrectPropertyValue(){
        assertThat(ConfigUtils.getPropertyFromConfig("key")).isEqualTo("8b5f3b52ca7814b00fa0587ca39d6ab7");
    }

}