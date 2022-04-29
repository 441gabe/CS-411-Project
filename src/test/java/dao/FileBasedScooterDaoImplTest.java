package dao;

import model.Scooter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static dao.ScooterSearchCriteria.Builder.builder;
import static org.assertj.core.api.Assertions.assertThat;

class FileBasedScooterDaoImplTest {

    private FileBasedScooterDaoImpl objectUnderTest = new FileBasedScooterDaoImpl("scooters_test.txt");

    @Test
    void should_retrieve_scooters_from_file_correctly() throws IOException {
        // given
        Scooter expected = Scooter.of("Yamaha", 55.5, "Green", LocalDate.of(2022, 4, 28), 3);

        // when
        assertThat(objectUnderTest.getAllScooters()).contains(expected);
    }

    @Test
    void should_find_correct_scooter_based_on_colour() {
        // given
        ScooterSearchCriteria criteria = builder().colour(Optional.of("Green")).build();

        // when
        Collection<Scooter> result = objectUnderTest.findScooters(criteria);

        // then
        assertThat(result).containsExactlyInAnyOrder(
                Scooter.of("Yamaha", 55.5, "Green", LocalDate.of(2022, 4, 28), 3),
                Scooter.of("Honda", 45.5, "Green", LocalDate.of(2022, 4, 28), 1),
                Scooter.of("Suzuki", 55.5, "Green", LocalDate.of(2022, 4, 29), 3)
        );
    }

    @Test
    void should_find_correct_scooter_based_on_multiple_criteria() {
        // given
        ScooterSearchCriteria criteria = builder()
                .reservationDate(Optional.of(LocalDate.of(2022, 4, 28)))
                .colour(Optional.of("Green"))
                .maxPrice(Optional.of(45.5))
                .build();

        // when
        Collection<Scooter> result = objectUnderTest.findScooters(criteria);

        // then
        assertThat(result).containsExactlyInAnyOrder(
                Scooter.of("Honda", 45.5, "Green", LocalDate.of(2022, 4, 28), 1)
        );
    }
}