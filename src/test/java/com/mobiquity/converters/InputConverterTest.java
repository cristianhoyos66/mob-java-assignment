package com.mobiquity.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mobiquity.exception.APIException;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputConverterTest {

  private InputConverter inputConverter;

  @BeforeEach
  void setup() {
    inputConverter = new InputConverter();
  }

  @Test
  void getItemProfitInputsShouldReturnProperlyFormattedList() {
    var url = ClassLoader.getSystemResource("ut-test-file.txt");
    var file = new File(url.getPath());
    var result = inputConverter.getItemProfitInputs(file.getAbsolutePath());

    assertEquals(2, result.size());

    assertEquals(10, result.get(0).getCapacity());
    assertEquals(2.5, result.get(0).getWeights()[0]);
    assertEquals(3.0, result.get(0).getWeights()[1]);
    assertEquals(30, result.get(0).getProfits()[0]);
    assertEquals(40, result.get(0).getProfits()[1]);

    assertEquals(20, result.get(1).getCapacity());
    assertEquals(4.0, result.get(1).getWeights()[0]);
    assertEquals(5.5, result.get(1).getWeights()[1]);
    assertEquals(50, result.get(1).getProfits()[0]);
    assertEquals(60, result.get(1).getProfits()[1]);
  }

  @Test
  void getItemProfitInputsShouldThrowAPIException() {
    assertThrows(
        APIException.class, () -> inputConverter.getItemProfitInputs("nonexistentfile.txt"));
  }
}
