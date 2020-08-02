package com.shaposhnikandrii.urlshortener.util.pagination;

import java.util.List;

public interface Page<T> {
  List<T> getContent();

  int getNumber();

  int getTotalPages();

  long getTotalElements();
}
