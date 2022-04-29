package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ScooterTest {

    @Test
    void should_write_to_string_and_parse_from_string_symmetrically() {
        // given
        Scooter aScooter = Scooter.of("SomeModel", 15.5, "Yellow", LocalDate.of(2022, 4, 28), 3);

        // then
        assertThat(Scooter.fromString(aScooter.toString())).isEqualTo(aScooter);
    }

    @Test
    void should_write_to_string_with_correct_format() {
        // given
        Scooter aScooter = Scooter.of("SomeModel", 15.5, "Yellow", LocalDate.of(2022, 4, 28), 3);

        // then
        assertThat(aScooter.toString()).isEqualTo("SomeModel,15.5,Yellow,2022-04-28,3");
    }
}