package org.example.files;

import java.io.FileNotFoundException;

public interface Parser {
     void toParse(String path) throws FileErrorException, FileNotFoundException, Exception;
}
