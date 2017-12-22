package com.github.bfh.study.slb.imports;

/**
 * This exception will throw when the source could not be found.
 *
 * @author Samuel Ackermann
 */
public class SourceNotFoundException extends RuntimeException {

    private String sourceName;

    SourceNotFoundException(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String getMessage() {
        return String.format("Source with name '%s' could not be found.", sourceName);
    }
}
