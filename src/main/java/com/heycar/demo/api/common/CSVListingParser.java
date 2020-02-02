package com.heycar.demo.api.common;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.heycar.demo.domain.model.Item;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CSVListingParser {

  public static List<List<String>> parseFile(File file) throws IOException {
    CsvSchema bootstrapSchema = CsvSchema.emptySchema();
    CsvMapper mapper = new CsvMapper();
    mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
    MappingIterator<List<String>> readValues = mapper.readerFor(List.class).with(bootstrapSchema)
        .readValues(file);
    List<List<String>> allValues = new ArrayList<>();
    boolean isHeader = true;
    while (readValues.hasNextValue()) {
      // Skip Header
      if (isHeader) {
        isHeader = false;
        readValues.nextValue();
        continue;
      }
      allValues.add(readValues.nextValue());
    }

    return allValues;

  }
}
