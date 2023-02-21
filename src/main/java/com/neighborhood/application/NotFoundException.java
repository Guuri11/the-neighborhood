package com.neighborhood.application;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {

    super("Could not find item ");
  }

  public NotFoundException(final Long id) {

    super("Could not find item " + id);
  }
}

