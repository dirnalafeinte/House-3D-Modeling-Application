//package ca.ulaval.glo2004.domain.util;
//
//import org.junit.Test;
//
//import static com.google.common.truth.Truth.*;
//
//
//public class ImperialTest {
//    @Test
//    public void givenImperialFeetOnly_whenToString_thenShouldReturnFeetString() {
//        Imperial imperialFeetOnly = Imperial.fromFeet(5);
//
//
//        assertThat(imperialFeetOnly.toString()).isEqualTo("5'");
//    }
//
//    @Test
//    public void givenImperialFeetAndInches_whenToString_thenShouldReturnFeetAndInchesString() {
//        Imperial imperialFeetAndInches = Imperial.fromFeetAndInches(5, 6);
//
//        assertThat(imperialFeetAndInches.toString()).isEqualTo("5' 6\"");
//    }
//
//    @Test
//    public void givenImperialFeetAndFraction_whenToString_thenShouldReturnFeetAndFractionString() {
//        Imperial imperialFeetAndFraction = new Imperial(5, 0, 1, 2);
//
//        assertThat(imperialFeetAndFraction.toString()).isEqualTo("5' 1/2");
//    }
//
//    @Test
//    public void givenImperialFeetInchesAndFraction_whenToString_thenShouldReturnFeetInchesAndFractionString() {
//        Imperial imperialFeetInchesAndFraction = new Imperial(5, 6, 1, 2);
//
//        assertThat(imperialFeetInchesAndFraction.toString()).isEqualTo("5' 6\" 1/2");
//    }
//
//    @Test
//    public void givenImperialInches_whenToString_thenShouldReturnInchesString() {
//        Imperial imperialInches = Imperial.fromInches(6);
//
//        assertThat(imperialInches.toString()).isEqualTo("6\"");
//    }
//
//    @Test
//    public void givenImperialInchesAndFraction_whenToString_thenShouldReturnInchesAndFractionString() {
//        Imperial imperialInchesAndFraction = new Imperial(0, 6, 1, 2);
//
//        assertThat(imperialInchesAndFraction.toString()).isEqualTo("6\" 1/2");
//    }
//
//    @Test
//    public void givenImperialFraction_whenToString_thenShouldReturnFractionString() {
//        Imperial imperialFeetInchesAndFraction = new Imperial(0, 0, 1, 2);
//
//        assertThat(imperialFeetInchesAndFraction.toString()).isEqualTo("1/2");
//    }
//}