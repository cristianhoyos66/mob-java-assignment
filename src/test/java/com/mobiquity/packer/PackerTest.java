package com.mobiquity.packer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import org.junit.jupiter.api.Test;

class PackerTest {

  @Test
  void packShouldGetMostProfitableItems() {
    var url = ClassLoader.getSystemResource("ut-test-file.txt");
    var file = new File(url.getPath());
    var mostProfitableItems = Packer.pack(file.getAbsolutePath());

    assertNotNull(mostProfitableItems);
    assertEquals("2, 1\n2, 1", mostProfitableItems);
  }
}
